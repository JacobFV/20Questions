package com.example.a20questions

import android.util.Log
import java.util.*

class ItemDataset(data: List<Array<String>>) {

    val propertyNames: MutableList<String> = data.first().toMutableList().subList(1, data.first().size-1)
    var usablePropertyNames: MutableList<String> = propertyNames
    lateinit var allItems: List<SingleItem>

    init {
        Log.d("dataset", "${data.size} - ${data.first().size}")
        //Log.d("dataset", data.first().joinToString())
        //Log.d("dataset", data[0].joinToString())
        //Log.d("dataset", data[1].joinToString())
        //Log.d("dataset", data[2].joinToString())
        //Log.d("dataset", data.last().joinToString())
        Log.d("dataset", "slice size ${data.slice(IntRange(1, data.size-1)).size}")

        val subslice = data.slice(IntRange(1, data.size-1))
        //Log.d("dataset", "subslice $subslice size ${subslice.size}")
        //Log.d("dataset", "subslice size ${subslice.size}")
        allItems = subslice.map {
            //Log.d("dataset", "iteration on ${it.joinToString()}")
            SingleItem(it[0],
                it.slice(IntRange(1, it.size-1)).map { str: String ->
                    //Log.d("dataset", str)
                    when(str.lowercase()) {
                        "true", "1" -> true
                        "false", "0" -> false
                        else -> false
                    }
                }
            )
        }
        Log.d("dataset", "allItems.size=${allItems.size}")
    }
}

//data class SingleItem(val name: String, val propertyValues: List<Boolean>)
class SingleItem(val name: String, val propertyValues: List<Boolean>) {

    init {
        //Log.d("dataset", "creating single item $name with property vals ${propertyValues.joinToString()}")
    }
}