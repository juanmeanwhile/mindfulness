package com.meanwhile.mindfulness

import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.meanwhile.mindfulness.model.Session
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_item_session.view.*
import android.arch.lifecycle.ViewModelProviders
import com.meanwhile.mindfulness.player.PlayerActivity


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter : SessionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Get ViewModel
        val factory = ViewModelFactory.getInstance(application)
        viewModel = ViewModelProviders.of(this, factory).get(MainViewModel::class.java)

        //Get Sessions LiveData
        viewModel.getSessions().observe(this, Observer {
            adapter.setData(it!!)
        })

        configRecyclerView()
    }

    private fun configRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = SessionAdapter {
            Log.d("MainActivity", "OnSessionClicked: ${it.title}")
            if (it.purchased) {
                startActivity(PlayerActivity.newIntent(this, it.id))
            } else {
                viewModel.onNotPurchasedClicked(it)
            }
        }
        recyclerView.adapter = adapter;
    }

    class SessionAdapter(var items: List<Session> = ArrayList(), val listener : (Session) -> Unit) : RecyclerView.Adapter<SessionHolder>() {

        fun setData(session : List<Session>){
            items = session
            notifyDataSetChanged()
            //TODO change to ListAdapter to try the new change content animation for free
        }

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
           sessionTitle.text = session.title
           //imageView.imageView;

           setOnClickListener{listener(session)}
       }
    }
}
