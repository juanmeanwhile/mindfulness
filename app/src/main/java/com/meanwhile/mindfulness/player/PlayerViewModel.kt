package com.meanwhile.mindfulness.player

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.meanwhile.mindfulness.model.Session
import com.meanwhile.mindfulness.data.SessionRepository

class PlayerViewModel(val repository: SessionRepository) : ViewModel(){


    /**
     * Get a Session byb the id
     */
    fun getSession(id : Long) : LiveData<Session> = repository.getSession(id)
}