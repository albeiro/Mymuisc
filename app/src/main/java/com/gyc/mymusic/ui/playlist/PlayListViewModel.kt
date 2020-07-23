package com.gyc.mymusic.ui.playlist

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.VolleyError
import com.gyc.mymusic.MainActivity
import com.gyc.mymusic.api.*
import com.gyc.mymusic.model.ModelPlayListDetail
import com.gyc.mymusic.model.ModelRecyclerView
import com.gyc.mymusic.utils.BaseViewModel
import org.json.JSONObject

class PlayListViewModel(application: Application) : BaseViewModel(application) {

    val playList = MutableLiveData<List<ModelRecyclerView>>()
    val playListDetail  = MutableLiveData<List<ModelPlayListDetail>>()

    val error = MutableLiveData<String>()

    fun getPlayList() {

        val callbackGetPlayList = object : IResponseServer {
            val listItems = ArrayList<ModelRecyclerView>()

            override fun success(response: Any?) {
                val items = JSONObject(response.toString()).getJSONArray("items")
                for (i in 0 until items.length()) {
                    listItems.add(ModelRecyclerView(JSONObject(items[i].toString())))
                }

                playList.value = listItems
            }

            override fun error(volleyError: VolleyError) {
                error.value = volleyError.networkResponse.statusCode.toString()
            }

        }

        getPlayListApi(getApplication(), callbackGetPlayList)
    }

    fun getPlayListDetails() {
        val callbackGetPlayListDetail = object : IResponseServer {
            val listItems = ArrayList<ModelPlayListDetail>()

            override fun success(response: Any?) {
                val items = JSONObject(response.toString()).getJSONArray("items")
                for (i in 0 until items.length()) {
                    listItems.add(ModelPlayListDetail(JSONObject(items[i].toString())))
                }

                playListDetail.value = listItems
            }

            override fun error(volleyError: VolleyError) {
                error.value = volleyError.networkResponse.statusCode.toString()
            }

        }

        getPlayListDetailsApi(getApplication(), callbackGetPlayListDetail,MainActivity.idPlaylist?.id!! )
    }

    fun newPlayList(name:String) {
        val callbackNewPlayList = object : IResponseServer {


            override fun success(response: Any?) {
                getPlayList()

            }

            override fun error(volleyError: VolleyError) {
                error.value = volleyError.networkResponse.statusCode.toString()
            }

        }

        newPlayListApi(getApplication(), callbackNewPlayList,name )
    }

    fun searchTrack (name:String) {
        val callbackGetPlayListDetail = object : IResponseServer {


            override fun success(response: Any?) {
                val listItems = ArrayList<ModelPlayListDetail>()

                val items = JSONObject(response.toString()).getJSONObject("tracks").getJSONArray("items")
                for (i in 0 until items.length()) {
                    listItems.add(ModelPlayListDetail(JSONObject(items[i].toString()), "SEARCH"))
                }
                playListDetail.value = listItems
            }

            override fun error(volleyError: VolleyError) {
                error.value = volleyError.networkResponse.statusCode.toString()
            }

        }

        searchTrackApi(getApplication(), callbackGetPlayListDetail, name )
    }
}