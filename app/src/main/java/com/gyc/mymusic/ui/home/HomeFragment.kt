package com.gyc.mymusic.ui.home


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gyc.mymusic.R
import com.gyc.mymusic.adapter.AdapterRecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment(),AdapterRecyclerView.OnRecyclerClickListener  {
    private val vmHome: HomeViewModel by lazy {
        ViewModelProvider(this@HomeFragment).get(HomeViewModel::class.java)
    }

    private lateinit var adapterRVCesantias: AdapterRecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)



        initializeRecyclerView()
        vmHome.getAccount()
        vmHome.getPlayList()

        observerAccount()
        observerPlaylist()

        root.context.also {

            adapterRVCesantias = AdapterRecyclerView(it, this)
            val layoutManager  = LinearLayoutManager(context)
            layoutManager.orientation=LinearLayoutManager.HORIZONTAL
            val mRecyclerView = root.findViewById(R.id.rv_play_list_account) as RecyclerView
            mRecyclerView.layoutManager = layoutManager
        }


        return root
    }


    private fun initializeRecyclerView() {




    }

    private fun observerPlaylist() {

        vmHome.getPlayList()
        vmHome.playList.observe(viewLifecycleOwner, Observer {
            adapterRVCesantias.creteListItems(it)
            adapterRVCesantias.notifyDataSetChanged()
        })
    }

    private fun observerAccount() {

        vmHome.account.observe(viewLifecycleOwner, Observer {
            tv_name_account.text= it.name
            tv_email_account.text= it.email
            b_followe_account.text = it.followers
            b_country_account.text = it.country


            Picasso.get()
                .load(it.images)
                .resize(200,200)
                .centerCrop()
                .into(iv_images_account);
        })
    }

    override fun selectItem(item: Any?) {
        TODO("Not yet implemented")
    }
}