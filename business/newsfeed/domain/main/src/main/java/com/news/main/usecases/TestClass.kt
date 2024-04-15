package com.news.main.usecases

class TestClass {

    val item1 = Item1(true)
    val item2 = Item1(true)
    val state1 = TopLevel(1, listOf(item1, item2))
    fun map() {
        val state2 = state1.copy(items = state1.items.map {
            it.isFavorite = false
            it
        })
        println("${state1 == state2}")
    }
}

data class Item1(var isFavorite: Boolean)

data class TopLevel(val id: Int, val items: List<Item1>)