package com.gyc.mymusic.api


import VolleyRequest
import android.content.Context
import android.util.Log
import com.gyc.mymusic.MainActivity
import org.json.JSONObject

val TAG = "Mediador"
val HOST = "https://api.spotify.com/v1/"


fun headers(): HashMap<String, String> {
    return object : HashMap<String, String>() {
        init {
            put(
                "Authorization",
                "Bearer BQAxyDk_DBww-h3uEtTMSgGb062OkE39-a452U8RvyHkt7WuHFBjNc1z3cf9H7D8E-nBt2wdFEDZXtq4vyUwNOqTCZU9a8mtkLbvHye5gT9Yo2Afft_m-amyz0iZWykQer_7IoKoep2U6g4lK-aaWUiP-yfipze9oV7gYK24N6aQyXz38Xmve7wAkSZ2pcyxje3HsuqA5Irr0k0dYX3PmIBdGhbnUA"
            )
            put("Content-Type", "application/json")
        }
    }
}

fun getCountryFlagApi(
    context: Context,
    callback: IResponseServer,
    country: String
) {
    try {
        val url = "https://restcountries.eu/rest/v2/alpha/$country"

        VolleyRequest.getInstance(context).jsonRequest(url, null, headers(), callback)
    } catch (e: Exception) {
        Log.d(TAG, e.message!!)
    }
}


fun getAccountApi(
    context: Context,
    callback: IResponseServer
) {
    try {
        val url = HOST.plus("me")

        VolleyRequest.getInstance(context).jsonRequest(url, null, headers(), callback)
    } catch (e: Exception) {
        Log.d(TAG, e.message!!)
    }
}

fun getPlayListApi(
    context: Context,
    callback: IResponseServer
) {
    try {
        val url = HOST.plus("me/playlists")

        VolleyRequest.getInstance(context).jsonRequest(url, null, headers(), callback)
    } catch (e: Exception) {
        Log.d(TAG, e.message!!)
    }
}

fun getPlayListDetailsApi(
    context: Context,
    callback: IResponseServer,
    idPlaylist: String
) {
    try {
        val url = HOST.plus("playlists/$idPlaylist/tracks")

        VolleyRequest.getInstance(context).jsonRequest(url, null, headers(), callback)
    } catch (e: Exception) {
        Log.d(TAG, e.message!!)
    }
}

fun getTrackApi(
    context: Context,
    callback: IResponseServer,
    idTarck: String
) {
    try {
        val url = HOST.plus("tracks/$idTarck")

        VolleyRequest.getInstance(context).jsonRequest(url, null, headers(), callback)
    } catch (e: Exception) {
        Log.d(TAG, e.message!!)
    }
}

fun newPlayListApi(
    context: Context,
    callback: IResponseServer,
    name: String
) {
    try {
        val idUser = MainActivity.idUser.toString()
        Log.i("iduser",idUser)
        val url = HOST.plus("users/$idUser/playlists")
        val parameters = JSONObject().apply {
            put("name", name)
            put("description","Create Api")
            put("public", true)
        }

        VolleyRequest.getInstance(context).jsonRequest(url, parameters, headers(), callback)
    } catch (e: Exception) {
        Log.d(TAG, e.message!!)
    }
}

fun searchTrackApi(
    context: Context,
    callback: IResponseServer,
    name: String
) {
    try {
        //%20
        val url = HOST.plus("search?q=$name&type=track&limit=10")
        VolleyRequest.getInstance(context).jsonRequest(url, null, headers(), callback)
    } catch (e: Exception) {
        Log.d(TAG, e.message!!)
    }
}
