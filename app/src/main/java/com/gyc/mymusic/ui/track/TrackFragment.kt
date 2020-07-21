package com.gyc.mymusic.ui.track

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.gyc.mymusic.MainActivity
import com.gyc.mymusic.R

class TrackFragment : Fragment() {

    private lateinit var vmtrack: TrackViewModel
    private lateinit var buttonPlay: Button

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        vmtrack =
                ViewModelProviders.of(this).get(TrackViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_track, container, false)

        buttonPlay = root.findViewById(R.id.buttonPlay)

        if (MainActivity.idtrack !=null){
            vmtrack.getTrack()
            val mediaPlayer = MediaPlayer();
            mediaPlayer.setDataSource("https://api.spotify.com/v1/me/player/devices?devices=[ {" +
                        "id:2WfaOiMkCvy7F5fcp2zZ8L," +
                        "is_active: false," +
                    "is_private_session: true,n" +
                    "is_restricted: false," +
                    "name:mymusic," +
                   "type:Smartphone," +
                    "volume_percent:100" +
                    "}]");
            mediaPlayer.prepareAsync();

            mediaPlayer.setOnPreparedListener {
                Log.i("suena","suena")
                buttonPlay?.isEnabled = true
                mediaPlayer.start();

            }

        }
        else{
            Toast.makeText(context,"Select a track", Toast.LENGTH_LONG).show()
        }
        return root
    }
}