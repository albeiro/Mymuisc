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
fun jsonSecondLevel(
    json: JSONObject,
    valor1: String,
    valor2: String,
    key: String
): String {
    return if (nullValidateJSON(json, valor1)) {
        ""
    } else {
        if (nullValidateJSON(json.getJSONObject(valor1), valor2)) {
            ""
        } else {
            json.getJSONObject(valor1).getJSONObject(valor2).getString(key)
        }
    }
}

fun jsonArray(jsonArray: JSONArray, position: Int, key:String):String{
    val sa = jsonArray[position]
    return JSONObject(sa.toString()).optString(key)
}