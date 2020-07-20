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
                "Bearer BQDUuX8eXopX1hsnYYaEQdQ4MPcw_DYJxX-YBM3fyj6Zzp0zNx9SVlKqf7vuH8auTqtW4JE4DwKiHwVjI0A-1-KmUfUloV9CS0MEdRbaZJ4Gb_gHFCQQbxPnqBF31ohvvU7c5kr6-_xaeX3iFj_QcV14B1SWrHG12AB5HbBT2hf3heNsh6Hypw"
            )
        }
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
