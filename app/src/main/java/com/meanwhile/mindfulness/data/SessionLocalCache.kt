package com.meanwhile.mindfulness.data

import android.arch.lifecycle.LiveData
import android.util.Log
import com.meanwhile.mindfulness.db.SessionDao
import com.meanwhile.mindfulness.model.Session
import java.util.concurrent.Executor

class SessionLocalCache(
        private val sessionDao: SessionDao,
        private val ioExecutor: Executor
) {

    /**
     * Insert a list of repos in the database, on a background thread.
     */
    fun insert(sessions: List<Session>, insertFinished: ()-> Unit) {
        ioExecutor.execute {
            Log.d("GithubLocalCache", "inserting ${sessions.size} repos")
            sessionDao.insert(sessions)
            insertFinished()
        }
    }

    /**
     * Request a LiveData<List<Repo>> from the Dao, based on a repo name. If the name contains
     * multiple words separated by spaces, then we're emulating the GitHub API behavior and allow
     * any characters between the words.
     * @param name repository name
     */
    fun sessions(): LiveData<List<Session>> {
        return sessionDao.sessions()
    }

    fun session(id : Long) = sessionDao.session(id)
}