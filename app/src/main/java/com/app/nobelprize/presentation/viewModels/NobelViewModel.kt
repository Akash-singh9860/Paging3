package com.app.nobelprize.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.nobelprize.data.model.NobelPrize
import com.app.nobelprize.domain.repositories.NobelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NobelViewModel @Inject constructor(
    private val nobelRepository: NobelRepository
) : ViewModel() {
    private val _prizes = MutableStateFlow<List<NobelPrize>>(emptyList())
    val prizes: StateFlow<List<NobelPrize>> = _prizes
    private var offset = 0
    private val limit = 50
    private var isLoading = false

    init {
        loadMorePrizes()
    }
    fun loadMorePrizes() {
        if (isLoading) return
        isLoading = true
        viewModelScope.launch {
            nobelRepository.emitNobelPrizes(offset, limit).collect { result ->
                result.onSuccess { newData ->
                    _prizes.value = _prizes.value + newData
                    offset += limit
                }
                isLoading = false
            }
        }
    }
}
