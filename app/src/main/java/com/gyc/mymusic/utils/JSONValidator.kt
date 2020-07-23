package com.gyc.mymusic.utils

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

fun jsonArray(json: JSONObject  ): String? {
    val jsonArray= json.optJSONArray("images")
    if(jsonArray!=null){
        val first = jsonArray[0]
        return JSONObject(first.toString()).optString("url")
    }else{
        return null
    }
}

fun jsonArtist(json: JSONObject  ): String? {
    val jsonArray= json.optJSONArray("images")
    if(jsonArray!=null){
        val first = jsonArray[0]
        return JSONObject(first.toString()).optString("url")
    }else{
        return null
    }
}

fun jsonImagesPlayList(json: JSONObject): String {
    val images =
        json.getJSONObject("track").getJSONObject("album").getJSONArray("images")[0]
    return JSONObject(images.toString()).optString("url")
}

fun jsonArtistPlayList(json: JSONObject): String {
    val artists =
        json.getJSONObject("track").getJSONObject("album").getJSONArray("artists")[0]
    return JSONObject(artists.toString()).optString("name")
}

fun jsonArtistSearchTrack(json: JSONObject): String {
    val artists =
        json.getJSONObject("album").getJSONArray("artists")[0]
    return JSONObject(artists.toString()).optString("name")
}