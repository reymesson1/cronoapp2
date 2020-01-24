package co.cronoapp20.myapplication

import android.os.Bundle
import android.os.CountDownTimer
import android.content.Intent
import android.media.MediaPlayer
import android.media.audiofx.BassBoost
import android.provider.Settings
import kotlinx.android.synthetic.main.activity_main.*
import androidx.appcompat.app.AppCompatActivity
import android.provider.Settings.System.DEFAULT_RINGTONE_URI




class MainActivity : AppCompatActivity() {

    private var countDownTimer: CountDownTimer? = null
    private var timeLeftInMilliseconds: Long = 60000 //10min
    private var timeRunning: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startStop()




    }

    private fun startStop() {

        if (timeRunning) {
            stopTimer()
        } else {
            startTimer()
        }
    }

    private fun stopTimer() {

        countDownTimer!!.cancel()
        timeRunning = false
    }

    private fun startTimer() {
        countDownTimer = object : CountDownTimer(timeLeftInMilliseconds, 1000) {
            override fun onTick(l: Long) {
                timeLeftInMilliseconds = l
                updateTimer()
            }

            override fun onFinish() {

            }
        }.start()
        timeRunning = true
    }

    fun updateTimer() {
        val minutes = timeLeftInMilliseconds as Long / 60000
        val seconds = timeLeftInMilliseconds as Long % 60000 / 1000

        var timeLeftText: String

        timeLeftText = "" + minutes
        timeLeftText += ":"
        if (seconds < 10) timeLeftText += "0"
        timeLeftText += seconds

        nameTXT.setText(""+"00:00:0"+timeLeftText)

        if (timeLeftText == "0:01") {

            val player = MediaPlayer.create(
                this@MainActivity,
                Settings.System.DEFAULT_RINGTONE_URI
            )

            player.start()

            val homeIntent = Intent(this@MainActivity, MainActivity::class.java)
            startActivity(homeIntent)
//            finish()

        }

    }
}
