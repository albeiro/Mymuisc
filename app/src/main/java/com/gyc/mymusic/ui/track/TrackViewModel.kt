package com.gyc.mymusic.ui.track

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.VolleyError
import com.gyc.mymusic.MainActivity
import com.gyc.mymusic.api.IResponseServer
import com.gyc.mymusic.api.getPlayListDetailsApi
import com.gyc.mymusic.api.getTrackApi
import com.gyc.mymusic.model.ModelPlayListDetail
import com.gyc.mymusic.model.ModelRecyclerView
import com.gyc.mymusic.utils.BaseViewModel
import org.json.JSONObject

class TrackViewModel(application: Application) : BaseViewModel(application) {
    val track = MutableLiveData<String>()
    val error = MutableLiveData<String>()

    fun getTrack() {
        val callbackGetPlayListDetail = object : IResponseServer {
            val listItems = ArrayList<ModelPlayListDetail>()

            override fun success(response: Any?) {
                val items = JSONObject(response.toString()).getString("preview_url")


                //track.value = listItems
            }

            override fun error(volleyError: VolleyError) {
                error.value = volleyError.networkResponse.statusCode.toString()
            }

        }

        getTrackApi(getApplication(), callbackGetPlayListDetail, MainActivity.idtrack!! )
    }


}