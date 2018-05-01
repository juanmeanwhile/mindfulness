package com.meanwhile.mindfulness

import android.arch.lifecycle.ViewModel
import com.meanwhile.mindfulness.repo.Session
import com.meanwhile.mindfulness.repo.SessionRepository

/**
 * ViewModel for the MainActivity
 */
class MainViewModel(val repository: SessionRepository) : ViewModel() {

    fun getSessions() : List<Session> = repository.getSessions()

}