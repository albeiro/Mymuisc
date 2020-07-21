package com.gyc.mymusic.model

import android.util.Log
import com.gyc.mymusic.utils.jsonArray
import com.gyc.mymusic.utils.jsonFirstLevel
import com.gyc.mymusic.utils.jsonSecondLevel
import org.json.JSONArray
import org.json.JSONObject

class ModelPlayListDetail {
    var title: String = "";
    var description: String = "";
    var id: String = "";
    var images: String = ""

    constructor(jsonObject: JSONObject) {
        try {
            this.title = jsonFirstLevel(jsonObject,"track","name")
            this.description = ""
            this.id = jsonFirstLevel(jsonObject,"track","id")
            this.images =jsonArray(JSONArray(jsonFirstLevel(jsonObject,"track","album")),0,"url")
        } catch (e: Exception) {
            Log.e("ErorAccount", e.message.toString())
        }
    }

    constructor()
}