package com.gyc.mymusic.model

import android.util.Log
import com.gyc.mymusic.utils.jsonArray
import com.gyc.mymusic.utils.jsonFirstLevel
import org.json.JSONObject

class Account(jsonObject: JSONObject) {
    var id: String? = ""
    var name: String? = ""
    var email: String? = ""
    var followers: String? = ""
    var images: String? = ""
    var product: String? = ""
    var country: String? = ""

    init {
        try {
            this.id = jsonObject.optString("id")
            this.name = jsonObject.optString("display_name")
            this.email = jsonObject.optString("email")
            this.followers = jsonFirstLevel(jsonObject, "followers", "total")
            this.images = jsonArray(jsonObject)
            this.product = jsonObject.optString("product")
            this.country = jsonObject.optString("country")
        } catch (e: Exception) {
            Log.e("ErorAccount", e.message.toString())
        }
    }



}