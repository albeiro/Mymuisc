package com.gyc.mymusic.ui.track

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.gyc.mymusic.MainActivity
import com.gyc.mymusic.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_track.view.*

class TrackFragment : Fragment() {

    private lateinit var vmtrack: TrackViewModel
    private val mediaPlayer = MediaPlayer();

    override fun onPause() {
        super.onPause()
        mediaPlayer.stop();
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vmtrack =
            ViewModelProviders.of(this).get(TrackViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_track, container, false)

        Picasso.get()
            .load("https://image.freepik.com/vector-gratis/ondas-sonoras-oscilan-luz-azul-oscuro_42077-1745.jpg")
            .resize(1600, 1600)
            .into(root.iv_sound_track)



        if (MainActivity.idtrack != null) {
            val track = MainActivity.idtrack!!
            root.tv_title_track.text = track.title
            root.tv_artists_track.text = track.artists

            vmtrack.getTrack()

            if (track.url != "null") {
                mediaPlayer.setDataSource(track.url);
                playTrack(track.url.toString())
            } else {
                Toast.makeText(context, "Not preview url track", Toast.LENGTH_LONG).show()
            }
            if (track.images != null) {


                Picasso.get()
                    .load(track.images)
                    .resize(200, 200)
                    .centerCrop()
                    .into(root.civ_images_track)
            } else {
                root.civ_images_track.setImageDrawable(root.context.getDrawable(R.drawable.ic_baseline_graphic_eq_24))
            }




            root.iv_play_track.setOnClickListener {
                it.isVisible= false
                playTrack(track.url.toString())
                root.iv_stop_track.isVisible = true
            }
            root.iv_stop_track.setOnClickListener {
                it.isVisible= false
                mediaPlayer.stop()
                root.iv_play_track.isVisible = true
            }

        } else {
            Toast.makeText(context, "Select a track", Toast.LENGTH_LONG).show()
        }
        return root
    }

    private fun playTrack(url:String) {

        mediaPlayer.prepareAsync();
        mediaPlayer.setOnPreparedListener {
            mediaPlayer.start();

        }
    }
}