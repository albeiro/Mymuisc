package com.gyc.mymusic.utils

import org.json.JSONArray
import org.json.JSONObject

private fun nullValidate(valor: String): Boolean {
    return valor == "null"
}

private fun nullValidateJSON(json: JSONObject, valor: String): Boolean {
    return json.isNull(valor)
}

fun nullString(valor: String): String {
    return if (nullValidate(valor)) "" else valor
}

fun nullNumeric(valor: String): String {
    return if (nullValidate(valor)) "0" else valor
}

fun jsonFirstLevel(json: JSONObject, valor: String, key: String): String {
    return if (nullValidateJSON(json, valor)) {
        ""
    } else {
        return json.getJSONObject(valor).getString(key)
    }
}

fun jsonArray(jsonArray: JSONArray, position: Int, key:String):String{
    val sa = jsonArray[position]
    return JSONObject(sa.toString()).optString(key)
}