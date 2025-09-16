package com.app.nobelprize.domain.repositories

import com.app.nobelprize.data.apiService.NobelApiService
import com.app.nobelprize.data.model.NobelPrize
import com.app.nobelprize.data.model.NobelPrizeResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
class NobelRepository @Inject constructor(
    private val nobelApiService: NobelApiService
) {
    fun emitNobelPrizes(offset: Int, limit: Int): Flow<Result<List<NobelPrize>>> = flow {
        try {
            val response = nobelApiService.getNobelPrizes(offset, limit).nobelPrizes
            emit(Result.success(response))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }.flowOn(Dispatchers.IO)
}
