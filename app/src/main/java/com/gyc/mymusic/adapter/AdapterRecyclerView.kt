package com.gyc.mymusic.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.gyc.mymusic.R
import com.gyc.mymusic.databinding.PlayListCardBinding
import com.gyc.mymusic.model.ModelPlayListDetail
import com.gyc.mymusic.model.ModelRecyclerView
import com.squareup.picasso.Picasso

class AdapterRecyclerView(
    private val context: Context?,
    private val mlistener: OnRecyclerClickListener
) : RecyclerView.Adapter<AdapterRecyclerView.viewHolderRv>() {

    private var listItems = ArrayList<Any>()

    inner class viewHolderRv(
        private val binding: PlayListCardBinding,
        private val listener: OnRecyclerClickListener
    ) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        var objeto: Any? = null
        fun addItem(objeto: Any, posicion: Int) {
            this.objeto = objeto

            var item: Any
            var clase: ModelRecyclerView? = null
            var color: Int? = 0

            when (objeto) {
                is ModelRecyclerView -> {
                    item = objeto as ModelRecyclerView
                    clase = ModelRecyclerView(
                        item.title,
                        item.description,
                        item.id,
                        item.images
                    )
                    Picasso.get()
                        .load(item.images)
                        .resize(200,200)
                        .centerCrop()
                        .into(binding.civImagesPlayList);
                }
                is ModelPlayListDetail -> {
                    item = objeto as ModelPlayListDetail
                    clase = ModelRecyclerView(
                        item.title,
                        item.description,
                        item.id,
                        item.images
                    )

                }
            }
            binding.recycler = clase


            itemView.setOnClickListener(this)
        }


        override fun onClick(p0: View?) {
            listener.selectItem(objeto)
        }

    }

    interface OnRecyclerClickListener {
        fun selectItem(item: Any?)
    }
    fun creteListItems(list:Any){
        listItems= list as ArrayList<Any>
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolderRv {
        val inflater = LayoutInflater.from(context)
        val binding: PlayListCardBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.play_list_card,
            parent,
            false
        )
        return viewHolderRv(binding, mlistener)
    }

    override fun getItemCount(): Int {
        return listItems.size
    }


    override fun onBindViewHolder(holder: viewHolderRv, position: Int) {
        val estudio = listItems[position]
        holder.addItem(estudio, position)
    }



}
