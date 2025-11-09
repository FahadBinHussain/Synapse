package com.knilaruen.synapse.data

data class TriviaFact(
    val id: Int,
    val fact: String,
    val category: Category
)

enum class Category {
    SCIENCE,
    NATURE,
    HISTORY,
    SPACE,
    ANIMALS,
    HUMAN_BODY,
    TECHNOLOGY,
    QUIRKY,
    GEOGRAPHY,
    LANGUAGE
}
