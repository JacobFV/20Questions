package com.example.a20questions

import java.util.*

class ItemDataset(data: List<Array<String>>) {

    val propertyNames: MutableList<String> = data.first().toMutableList().subList(1, data.first().size-1)
    var usablePropertyNames: MutableList<String> = propertyNames
    val allItems: MutableList<SingleItem> = mutableListOf()

    init {
        data.subList(1, data.size-1).forEach {
            allItems.plus(SingleItem(it[0],
                it.slice(IntRange(1, it.size-1)).map { str: String ->
                    when(str.lowercase()) {
                        "true", "1" -> true
                        "false", "0" -> false
                        else -> false
                    }
                }
            ))
        }
    }
}

data class SingleItem(val name: String, val propertyValues: List<Boolean>)