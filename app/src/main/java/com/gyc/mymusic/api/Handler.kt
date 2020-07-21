package com.gyc.mymusic.api




import android.content.Context
import android.util.Log

val TAG = "Mediador"
val HOST = "https://api.spotify.com/v1/"


fun headers(): HashMap<String, String> {
    return object : HashMap<String, String>() {
        init {
            put(
                "Authorization",
                "Bearer BQAqhJRCnUg8cTqD7tZ03kiwHadxeAg3OEtJgE_03TiNVhaLnUWXjtue0KPwDqGBnbxtviXDUBvQqwd3U7suhpshpLCVXunKLMNPRK-lYClCyUvwYN8NhTP0JQZRz3i0xBvvxzS6_bQiV4lN3wHEjyfattIRVziAc_PUteCF2muKHI_u8ViahA"
            )
        }
    }
}

fun getCountryFlagApi(
    context: Context,
    callback: IResponseServer,
    country:String
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
idPlaylist:String
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
    idTarck:String
) {
    try {
        val url = HOST.plus("tracks/$idTarck")

        VolleyRequest.getInstance(context).jsonRequest(url, null, headers(), callback)
    } catch (e: Exception) {
        Log.d(TAG, e.message!!)
    }
}
