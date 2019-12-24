package com.oluwafemi.mobcategories.util

object StringUtil {

    fun joinTwoStrings(firstString: String, secondString: String): String {
        return String.format("%s %s", firstString, secondString)
    }

}