package com.news.main

import android.content.SharedPreferences
import javax.inject.Inject

class NewsPersistanceStore @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {
    fun saveStringList(key: String, stringList: List<String>) {
        val editor = sharedPreferences.edit()
        editor.putStringSet(key, stringList.toSet())
        editor.apply()
    }

    fun getStringList(key: String): List<String> {
        val stringSet = sharedPreferences.getStringSet(key, setOf())
        return stringSet?.toList() ?: listOf()
    }

}