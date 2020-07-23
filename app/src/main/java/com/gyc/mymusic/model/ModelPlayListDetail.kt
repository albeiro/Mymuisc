package com.gyc.mymusic.model

import android.util.Log
import com.gyc.mymusic.utils.*
import org.json.JSONObject

class ModelPlayListDetail {
    var title: String? = ""
    var description: String? = ""
    var id: String? = ""
    var url: String? = ""
    var images: String? = ""
    var artists: String? = ""
    var is_local:String? =""

    constructor(jsonObject: JSONObject) {
        try {
            this.title = jsonFirstLevel(jsonObject, "track", "name")
            this.description = jsonObject.optString("is_local")
            this.url = jsonFirstLevel(jsonObject, "track", "preview_url")
            this.id = jsonFirstLevel(jsonObject, "track", "id")
            this.images = jsonImagesPlayList(jsonObject)
            this.artists = jsonArtistPlayList(jsonObject)
            this.is_local = jsonObject.optString("is_local")
        } catch (e: Exception) {
            Log.e("ErorPlayList", e.message.toString())
        }
    }
    constructor(jsonObject: JSONObject, string:String) {
        try {
            this.title = jsonObject.optString("name")
            this.description = jsonArtistSearchTrack(jsonObject)
            this.images = jsonArray(jsonObject.getJSONObject("album"))
            this.url = jsonObject.optString("preview_url")
        } catch (e: Exception) {
            Log.e("ErorAccount", e.message.toString())
        }
    }
}