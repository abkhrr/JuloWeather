package com.juloweather.utils.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*
import kotlin.collections.ArrayList

class ListStringConverter {
    private var gson = Gson()

    @TypeConverter
    fun fromTimestamp(data: String?): List<String>? {

        if (data == null){
            return Collections.emptyList()
        }
        val listType = object : TypeToken<ArrayList<String>>() {}.type
        return gson.fromJson(data, listType)


    }

    @TypeConverter
    fun someObjectListToString(someObjects: List<String>?): String? {
        return gson.toJson(someObjects)
    }

}