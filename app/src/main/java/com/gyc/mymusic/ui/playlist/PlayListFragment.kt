package com.gyc.mymusic.ui.playlist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gyc.mymusic.MainActivity
import com.gyc.mymusic.R
import com.gyc.mymusic.adapter.AdapterRecyclerView
import com.gyc.mymusic.model.ModelPlayListDetail

class PlayListFragment : Fragment(), AdapterRecyclerView.OnRecyclerClickListener {

    private lateinit var vmplayList: PlayListViewModel
    private lateinit var adapterRV: AdapterRecyclerView


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        vmplayList =
                ViewModelProviders.of(this).get(PlayListViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_play_list, container, false)
        Log.i("vmplayList.idPlayList", MainActivity.idPlaylist.toString())

        if (MainActivity.idPlaylist !=null){
            vmplayList.getPlayListDetails()

            observerGetPlayListDetails()

            adapterRV = AdapterRecyclerView(context, this)

            val mRecyclerView = root.findViewById(R.id.rv_tracks_play_list) as RecyclerView
            mRecyclerView.apply {
                //setHasFixedSize(true)
                layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                adapter = adapterRV
            }

        }else{
            Toast.makeText(context,"Select a play list",Toast.LENGTH_LONG).show()
        }


        return root
    }

    private fun observerGetPlayListDetails() {
        vmplayList.playListDetail.observe(viewLifecycleOwner, Observer {
            adapterRV.creteListItems(it)
            adapterRV.notifyDataSetChanged()
        })
    }

    override fun selectItem(item: Any?) {
        val track = item as ModelPlayListDetail
        MainActivity.idtrack = track.id
        findNavController().navigate(R.id.action_navigation_dashboard_to_navigation_notifications)

    }
}