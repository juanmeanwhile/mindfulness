package com.meanwhile.mindfulness.player

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.meanwhile.mindfulness.R
import com.meanwhile.mindfulness.ViewModelFactory
import com.meanwhile.mindfulness.model.Session
import kotlinx.android.synthetic.main.activity_player.*

/**
 * Activity to display a Session content, including audio player. When a session is not marked as purchased,
 * then UI hides play controls and displays purchase buttons, configuring the billing properly.
 */
class PlayerActivity : AppCompatActivity() {

    private lateinit var viewModel: PlayerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)

        //Get ViewModel
        val factory = ViewModelFactory.getInstance(application)
        viewModel = ViewModelProviders.of(this, factory).get(PlayerViewModel::class.java)

        val sessionId = intent.getLongExtra(ARG_SESSION_ID, 0)
        viewModel.getSession(sessionId).observe(this, Observer {
            setSession(it!!)
        } )

        setViews()
    }

    private fun setSession(it: Session) {
        sessionNumber.text = it.id.toString()
        sessionTitle.text = it.title
        progressBar.progress = 0
        progressBar.max = 100 //TODO add duration to the Session object or get it from player
    }

    private fun setViews() {
        //playToggle.setOnCheckedChangeListener {
            //TODO inform ViewModel of the press so it can play or pause the audio
        //}

    }

    companion object {
        val ARG_SESSION_ID = "sessionId"

        fun newIntent(context: Context, id : Long) : Intent {
            var intent = Intent(context, PlayerActivity::class.java)
            intent.putExtra(ARG_SESSION_ID, id)
            return intent;

        }
    }
}
