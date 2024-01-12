package com.yash.musicapp

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import androidx.core.net.toUri
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_playing.*
import kotlinx.coroutines.delay
import java.util.*

class Playing : AppCompatActivity() {
    lateinit var mediaPlayer: MediaPlayer;
    var find = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playing)

        val songPhoto = intent.getStringExtra("songPhoto")
        val link = intent.getStringExtra("link")
        val title = intent.getStringExtra("title")

        Picasso.get().load(songPhoto).into(img)
        name.text = title.toString()

        if(find){
             mediaPlayer = MediaPlayer.create(this, link?.toUri())
            start.setImageResource(R.drawable.play);
            mediaPlayer.start()
            find = false
        }

        start.setOnClickListener {
            if(find){
                start.setImageResource(R.drawable.get);
                find = false;
                mediaPlayer.pause()
            }
            else{
                find = true
                start.setImageResource(R.drawable.play);
                mediaPlayer.start()
            }
        }
    }
    override fun onBackPressed() {
        mediaPlayer.stop()
        super.onBackPressed()
        // Close the current activity when the user presses the back button
        finish()
    }
}