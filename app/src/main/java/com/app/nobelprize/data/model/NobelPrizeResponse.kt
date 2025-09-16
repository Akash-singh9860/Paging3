package com.app.nobelprize.data.model

data class NobelPrizeResponse(
    val nobelPrizes: List<NobelPrize>
)

data class NobelPrize(
    val awardYear: String,
    val category: Category,
    val laureates: List<Laureate>?
)

data class Category(
    val en: String
)

data class Laureate(
    val knownName: KnownName?,
    val fullName: FullName?,
    val motivation: Motivation?
)

data class KnownName(val en: String)
data class FullName(val en: String)
data class Motivation(val en: String)
