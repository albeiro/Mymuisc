package com.gyc.mymusic.ui.home

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.VolleyError
import com.gyc.mymusic.api.IResponseServer
import com.gyc.mymusic.api.getAccountApi
import com.gyc.mymusic.api.getPlayListApi
import com.gyc.mymusic.model.Account
import com.gyc.mymusic.model.ModelRecyclerView
import com.gyc.mymusic.utils.BaseViewModel
import org.json.JSONObject

class HomeViewModel(application: Application) : BaseViewModel(application) {

    val account = MutableLiveData<Account>()
    val playList = MutableLiveData<List<ModelRecyclerView>>()
    val error = MutableLiveData<String>()

    fun getAccount() {

        val callbackGetAccount = object : IResponseServer {

            override fun success(response: Any?) {
                account.value = Account(JSONObject(response.toString()))
            }

            override fun error(volleyError: VolleyError) {
                error.value = volleyError.networkResponse.statusCode.toString()
            }

        }

        getAccountApi(getApplication(), callbackGetAccount)
    }

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

}