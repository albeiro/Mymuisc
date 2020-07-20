package com.gyc.mymusic.api

import com.android.volley.VolleyError

interface IResponseServer {
    fun success(response: Any?)

    fun error(error: VolleyError)
}