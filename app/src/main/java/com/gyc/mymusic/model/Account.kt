package com.gyc.mymusic.model

import android.util.Log
import com.gyc.mymusic.utils.jsonArray
import com.gyc.mymusic.utils.jsonFirstLevel
import org.json.JSONObject

class Account {
    var name: String = "";
    var email: String = "";
    var followers: String = "";
    var images: String = "";
    var product: String = "";
    var country: String = "";

    constructor(jsonObject: JSONObject) {
        try {
            this.name = jsonObject.optString("display_name")
            this.email = jsonObject.optString("email")
            this.followers = jsonFirstLevel(jsonObject, "followers", "total")
            this.images = jsonArray(jsonObject.getJSONArray("images"), 0, "url")
            this.product = jsonObject.optString("product")
            this.country = jsonObject.optString("country")
        } catch (e: Exception) {
            Log.e("ErorAccount", e.message.toString())
        }
    }


    constructor()
}