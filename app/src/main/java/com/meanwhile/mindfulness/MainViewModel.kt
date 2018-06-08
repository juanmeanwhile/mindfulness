package com.meanwhile.mindfulness

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.meanwhile.mindfulness.model.Session
import com.meanwhile.mindfulness.data.SessionRepository

/**
 * ViewModel for the MainActivity
 */
class MainViewModel(val repository: SessionRepository) : ViewModel() {

    fun getSessions() : LiveData<List<Session>> = repository.getSessions()

    fun onNotPurchasedClicked(session: Session) {
        //TODO launch billing and stuff
    }

}