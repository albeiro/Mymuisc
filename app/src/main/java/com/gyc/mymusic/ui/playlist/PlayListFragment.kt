package com.gyc.mymusic.ui.playlist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
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
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_play_list.*
import kotlinx.android.synthetic.main.fragment_play_list.view.*

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

        root.context.also { rootContex ->
            if (MainActivity.idPlaylist != null) {

                vmplayList.getPlayListDetails()
                val playlist =MainActivity.idPlaylist!!

                root.tv_title_play_list.text = playlist.title
                root.tv_by_play_list.text = playlist.description

                Picasso.get()
                    .load(playlist.images)
                    .resize(80, 80)
                    .centerCrop()
                    .into(root.civ_images_play_list)

                observerGetPlayListDetails()

                adapterRV = AdapterRecyclerView(context, this)

                val mRecyclerView = root.findViewById(R.id.rv_tracks_play_list) as RecyclerView
                mRecyclerView.apply {
                    //setHasFixedSize(true)
                    layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    adapter = adapterRV
                }

            } else {
                Toast.makeText(context, "Select a play list", Toast.LENGTH_LONG).show()
            }

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