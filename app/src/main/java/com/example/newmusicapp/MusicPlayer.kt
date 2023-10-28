

package com.example.newmusicapp

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuInflater
import android.widget.ImageButton
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.lang.String
import java.util.concurrent.TimeUnit

class MusicPlayer : AppCompatActivity() {
    @SuppressLint("DefaultLocale")
    override fun onCreate(savedInstanceState: Bundle?) {

        lateinit var runnable: Runnable
        var handler = Handler()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.music_player)

        var forwardTime=5000
        var backwardTime=5000
        var startTime = 0
        var finalTime = 0
        val tv_pass = findViewById<TextView>(R.id.tv_pass)
        val playButton = findViewById<ImageButton>(R.id.playBtn)
        val backButton = findViewById<ImageButton>(R.id.previousBtn)
        val forwardButton = findViewById<ImageButton>(R.id.nextButton)
        val tx1 = findViewById<TextView>(R.id.textView2);
        val tx2 = findViewById<TextView>(R.id.textView3);
        // val myHandler = Handler()
        // creating an employee list
        // of type Employee class
        var emplist: MusicList? = null
        // val iterator: ListIterator<MusicList> = emplist.listIterator()

        if (intent.hasExtra(MainActivity.NEXT_SCREEN)) {
            // get the Serializable data model class with the details in it
            emplist =
                intent.getSerializableExtra(MainActivity.NEXT_SCREEN) as MusicList
        }
        if (emplist != null) {
            tv_pass?.text = emplist!!.name   // displaying name
//            binding?.displayEmail?.text=emplist!!.type
//            binding?.displayEmail?.text=emplist!!.time
            // displaying email
        }



        var mediaPlayer: MediaPlayer = MediaPlayer.create(this, emplist!!.uri)
        val seekBar = findViewById<SeekBar>(R.id.seek_bar)
        seekBar.progress = 0;
        seekBar.max = mediaPlayer.duration

        finalTime=mediaPlayer.duration
        startTime=mediaPlayer.currentPosition



        val UpdateSongTime: Runnable = object : Runnable {
            override fun run() {
                startTime = mediaPlayer.currentPosition
                tx1.text = kotlin.String.format(
                    "%d min, %d sec",
                    TimeUnit.MILLISECONDS.toMinutes(startTime.toLong()),
                    TimeUnit.MILLISECONDS.toSeconds(startTime.toLong()) - TimeUnit.MINUTES.toSeconds(
                        TimeUnit.MILLISECONDS.toMinutes(startTime.toLong())
                    )
                )
                seekBar.setProgress(startTime)
                handler.postDelayed(this, 100)
            }
        }

        fun playsong ()
        {
            if (!mediaPlayer.isPlaying) {

                mediaPlayer.start();
                playButton.setImageResource(R.drawable.pause2)
            } else {
                mediaPlayer.pause()
                playButton.setImageResource(R.drawable.baseline_play_arrow_24)
            }
            tx2.text = String.format(
                "%d min, %d sec",
                TimeUnit.MILLISECONDS.toMinutes(finalTime.toLong()),
                TimeUnit.MILLISECONDS.toSeconds(finalTime.toLong()) - TimeUnit.MINUTES.toSeconds(
                    TimeUnit.MILLISECONDS.toMinutes(finalTime.toLong())
                )
            )

            tx1.text = String.format(
                "%d min, %d sec",
                TimeUnit.MILLISECONDS.toMinutes(startTime.toLong()),
                TimeUnit.MILLISECONDS.toSeconds(startTime.toLong()) - TimeUnit.MINUTES.toSeconds(
                    TimeUnit.MILLISECONDS.toMinutes(startTime.toLong())
                )
            )


            seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(p0: SeekBar?, pos: Int, changed: Boolean) {
                    if (changed) {
                        mediaPlayer.seekTo(pos)
                    }
                }

                override fun onStartTrackingTouch(p0: SeekBar?) {

                }

                override fun onStopTrackingTouch(p0: SeekBar?) {

                }

            })

            runnable = Runnable {
                seekBar.progress = mediaPlayer.currentPosition
                handler.postDelayed(runnable, 1000)
            }
            handler.postDelayed(runnable, 1000)
            mediaPlayer.setOnCompletionListener {

                seekBar.progress = 0


            }

            seekBar.setProgress(startTime)
            handler.postDelayed(UpdateSongTime,100);

        }

        playsong()
        playButton.setOnClickListener {
            if (!mediaPlayer.isPlaying) {
                mediaPlayer.start();
//                finalTime=mediaPlayer.duration
//                startTime=mediaPlayer.currentPosition
                /*   tx2.text = String.format(
                       "%d min, %d sec",
                       TimeUnit.MILLISECONDS.toMinutes(finalTime.toLong()),
                       TimeUnit.MILLISECONDS.toSeconds(finalTime.toLong()) - TimeUnit.MINUTES.toSeconds(
                           TimeUnit.MILLISECONDS.toMinutes(finalTime.toLong())
                       )
                   )

                   tx1.text = String.format(
                       "%d min, %d sec",
                       TimeUnit.MILLISECONDS.toMinutes(startTime.toLong()),
                       TimeUnit.MILLISECONDS.toSeconds(startTime.toLong()) - TimeUnit.MINUTES.toSeconds(
                           TimeUnit.MILLISECONDS.toMinutes(startTime.toLong())
                       )
                   )*/
                //   seekBar.setProgress(startTime)
                //   myHandler.postDelayed(UpdateSongTime,100);

                playButton.setImageResource(R.drawable.pause2)

            } else {
                mediaPlayer.pause()
                playButton.setImageResource(R.drawable.baseline_play_arrow_24)
            }



            /*   backButton.setOnClickListener {
                if (!mediaPlayer.isPlaying) {
                    mediaPlayer.start();
                    backButton.setImageResource(R.drawable.baseline_skip_previous_24)
                } else {
                    mediaPlayer.pause()
                    backButton.setImageResource(R.drawable.baseline_skip_next_24)
                }
                }
         */




            // it the emplist is not null the it has some data and display it

            seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(p0: SeekBar?, pos: Int, changed: Boolean) {
                    if (changed) {
                        mediaPlayer.seekTo(pos)
                    }
                }

                override fun onStartTrackingTouch(p0: SeekBar?) {

                }

                override fun onStopTrackingTouch(p0: SeekBar?) {

                }

            })

            runnable = Runnable {
                seekBar.progress = mediaPlayer.currentPosition
                handler.postDelayed(runnable, 1000)
            }
            handler.postDelayed(runnable, 1000)
            mediaPlayer.setOnCompletionListener {

                seekBar.progress = 0


            }


            /* onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true)
              {
                  override fun handleOnBackPressed() {
                      println("Back button pressed")
                      mediaPlayer.pause()
                      this@MusicPlayer.finish()
  //                val intent = Intent(this@MusicPlayer, MusicPlayer::class.java)
                      // Passing the data to the
                      // EmployeeDetails Activity
  //                startActivity(intent)
                      // Code that you need to execute on back press, e.g. finish()
                  }
              })*/


        }
        backButton.setOnClickListener {
            val musicList=Constants.getEmployeeData()
            val index = musicList.indexOfFirst{
                it.name == emplist!!.name
            }
            if(index != 0){
                val element = musicList.get(index-1)
                emplist = element
                println("Index  :  ${index}")
                println("Element :  ${element}")
                tv_pass?.text=emplist!!.name

                mediaPlayer.reset()
                mediaPlayer = MediaPlayer.create(this,element!!.uri)
                mediaPlayer.start()
                playButton.setImageResource(R.drawable.pause2)
                seekBar.progress = 0;
                seekBar.max = mediaPlayer.duration
                finalTime=mediaPlayer.duration
                startTime=mediaPlayer.currentPosition
                tx2.text = String.format(
                    "%d min, %d sec",
                    TimeUnit.MILLISECONDS.toMinutes(finalTime.toLong()),
                    TimeUnit.MILLISECONDS.toSeconds(finalTime.toLong()) - TimeUnit.MINUTES.toSeconds(
                        TimeUnit.MILLISECONDS.toMinutes(finalTime.toLong())
                    )
                )

                tx1.text = String.format(
                    "%d min, %d sec",
                    TimeUnit.MILLISECONDS.toMinutes(startTime.toLong()),
                    TimeUnit.MILLISECONDS.toSeconds(startTime.toLong()) - TimeUnit.MINUTES.toSeconds(
                        TimeUnit.MILLISECONDS.toMinutes(startTime.toLong())
                    )
                )


            }else{
                mediaPlayer.pause()
                mediaPlayer.start()
            }




        }
        forwardButton.setOnClickListener {
            val musicList=Constants.getEmployeeData()
            val index = musicList.indexOfFirst{
                it.name == emplist!!.name
            }

            val element = musicList.get(index+1)
            emplist = element
            println("Index  :  ${index}")
            println("Element :  ${element}")
            tv_pass?.text=emplist!!.name

            mediaPlayer.reset()
            mediaPlayer = MediaPlayer.create(this,element!!.uri)
            mediaPlayer.start()
            playButton.setImageResource(R.drawable.pause2)
            seekBar.progress = 0;
            seekBar.max = mediaPlayer.duration
            finalTime=mediaPlayer.duration
            startTime=mediaPlayer.currentPosition
            tx2.text = String.format(
                "%d min, %d sec",
                TimeUnit.MILLISECONDS.toMinutes(finalTime.toLong()),
                TimeUnit.MILLISECONDS.toSeconds(finalTime.toLong()) - TimeUnit.MINUTES.toSeconds(
                    TimeUnit.MILLISECONDS.toMinutes(finalTime.toLong())
                )
            )

            tx1.text = String.format(
                "%d min, %d sec",
                TimeUnit.MILLISECONDS.toMinutes(startTime.toLong()),
                TimeUnit.MILLISECONDS.toSeconds(startTime.toLong()) - TimeUnit.MINUTES.toSeconds(
                    TimeUnit.MILLISECONDS.toMinutes(startTime.toLong())
                )
            )



        }
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, pos: Int, changed: Boolean) {
                if (changed) {
                    mediaPlayer.seekTo(pos)
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }

        })
        runnable = Runnable {
            seekBar.progress = mediaPlayer.currentPosition
            handler.postDelayed(runnable, 1000)
        }
        handler.postDelayed(runnable, 1000)
        mediaPlayer.setOnCompletionListener {

            seekBar.progress = 0


        }

    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.play_list_menu, menu)
        return true

    }

}

 