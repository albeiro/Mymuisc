package com.gyc.mymusic.model

import android.util.Log
import com.gyc.mymusic.utils.jsonArray
import com.gyc.mymusic.utils.jsonFirstLevel
import org.json.JSONObject

class ModelRecyclerView {
    var title: String = "";
    var description: String = "";
    var id: String = "";
    var images: String = ""

    constructor(jsonObject: JSONObject) {
        try {
            this.title = jsonObject.optString("name")
            this.description = jsonFirstLevel(jsonObject, "owner", "display_name")
            this.id = jsonObject.optString("id")
            this.images = jsonArray(jsonObject.getJSONArray("images"), 0, "url")
        } catch (e: Exception) {
            Log.e("ErorAccount", e.message.toString())
        }
    }

    constructor(title: String, description: String, id: String, images: String) {
        this.title = title
        this.description = description
        this.id = id
        this.images = images

    }

    constructor()
}