package com.gyc.mymusic.ui.home


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gyc.mymusic.MainActivity.Companion.idPlaylist
import com.gyc.mymusic.R
import com.gyc.mymusic.adapter.AdapterRecyclerView
import com.gyc.mymusic.model.ModelRecyclerView
import com.gyc.mymusic.ui.playlist.PlayListViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment(), AdapterRecyclerView.OnRecyclerClickListener {


    private lateinit var vmHome: HomeViewModel
    private lateinit var vmPlayList: PlayListViewModel
    private lateinit var adapterRV: AdapterRecyclerView
    private var loadPlayList = false
    private var loadAccount = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vmHome =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)

        vmPlayList =
            ViewModelProviders.of(this).get(PlayListViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_home, container, false)


        initializeRecyclerView()
        vmHome.getAccount()
        vmPlayList.getPlayList()

        observerAccount()
        observerPlaylist()


        root.context.also { rootContex ->
            adapterRV = AdapterRecyclerView(rootContex, this)

            val mRecyclerView = root.findViewById(R.id.rv_play_list_account) as RecyclerView
            mRecyclerView.apply {
                //setHasFixedSize(true)
                layoutManager =
                    LinearLayoutManager(rootContex, LinearLayoutManager.VERTICAL, false)

                adapter = adapterRV
            }
        }

        return root
    }


    private fun initializeRecyclerView() {


    }

    private fun observerPlaylist() {

        vmPlayList.playList.observe(viewLifecycleOwner, Observer {
            adapterRV.creteListItems(it)
            adapterRV.notifyDataSetChanged()
            loadPlayList = true
            hideProgressBar()
        })
    }

    private fun observerAccount() {

        vmHome.account.observe(viewLifecycleOwner, Observer {
            tv_name_account.text = it.name
            tv_email_account.text = it.email
            b_follower_account.text = it.followers
            b_country_account.text = it.country
            b_product_account.text =it.product

            Picasso.get()
                .load(it.images)
                .resize(200, 200)
                .centerCrop()
                .into(iv_images_account)

            loadAccount = true
            hideProgressBar()
        })
    }


    override fun selectItem(item: Any?) {
        val playlist = item as ModelRecyclerView
        idPlaylist = playlist
        findNavController().navigate(R.id.action_navigation_home_to_navigation_dashboard)

    }

    private fun hideProgressBar() {
        if(loadAccount && loadPlayList){
            pb_home.isVisible = false
        }
    }
}