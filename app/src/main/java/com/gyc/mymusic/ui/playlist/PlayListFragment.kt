package com.gyc.mymusic.ui.playlist

import android.os.Bundle
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
import com.gyc.mymusic.ui.track.TrackViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_play_list.*
import kotlinx.android.synthetic.main.fragment_play_list.view.*

class PlayListFragment : Fragment(), AdapterRecyclerView.OnRecyclerClickListener {

    private lateinit var vmplayList: PlayListViewModel
    private lateinit var vmTarck: TrackViewModel
    private lateinit var adapterRV: AdapterRecyclerView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vmplayList =
            ViewModelProviders.of(this).get(PlayListViewModel::class.java)
        vmTarck =
            ViewModelProviders.of(this).get(TrackViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_play_list, container, false)



        root.context.also { rootContex ->
            if (MainActivity.idPlaylist != null) {

                vmplayList.getPlayListDetails()
                val playlist = MainActivity.idPlaylist!!

                root.tv_title_play_list.text = playlist.title
                root.tv_by_play_list.text = playlist.description


                if(!playlist.images.isNullOrEmpty()){
                    Picasso.get()
                        .load(playlist.images)
                        .resize(200, 200)
                        .centerCrop()
                        .into(root.civ_images_list_card)
                }

                observerGetPlayListDetails()

                adapterRV = AdapterRecyclerView(context, this)

                val mRecyclerView = root.findViewById(R.id.rv_tracks_play_list) as RecyclerView
                mRecyclerView.apply {
                    //setHasFixedSize(true)
                    layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    adapter = adapterRV
                }

                root.b_search_track_play_list.setOnClickListener {
                    vmplayList.searchTrack(root.et_search_track_play_list.text.toString())
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
        MainActivity.idtrack = track
        findNavController().navigate(R.id.action_navigation_dashboard_to_navigation_notifications)
    }
}