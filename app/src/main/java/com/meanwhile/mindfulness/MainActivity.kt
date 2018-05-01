package com.meanwhile.mindfulness

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.meanwhile.mindfulness.repo.Session
import com.meanwhile.mindfulness.repo.SessionRepository
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_item_session.view.*
import android.arch.lifecycle.ViewModelProviders



class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Get ViewModel
        val factory = ViewModelFactory.getInstance(application);
        viewModel = ViewModelProviders.of(this, factory).get(MainViewModel::class.java);

        //config recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = SessionAdapter(viewModel.getSessions()) {
            Log.d("MainActivity", "OnSessionClicked: ${it.title}")
            viewModel.onSessionClicked(it);

            //TODO load new activty
        }
    }

    class SessionAdapter(val items: List<Session>, val listener : (Session) -> Unit) : RecyclerView.Adapter<SessionHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SessionHolder{
            return SessionHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_session, parent, false))
        }

        override fun getItemCount(): Int = items.size


        override fun onBindViewHolder(holder: SessionHolder, position: Int) {
            holder.bind(items.get(position), listener)
        }
    }

    class SessionHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

       fun bind(session: Session, listener: (Session) -> Unit) = with(itemView) {
           title.text = session.title
           //imageView.imageView;

           setOnClickListener{listener(session)}
       }
    }
}
