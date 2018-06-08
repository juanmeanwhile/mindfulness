package com.meanwhile.mindfulness.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.meanwhile.mindfulness.model.Session

/**
 * Room data access object for accessing the [Session] table.
 */
@Dao
interface SessionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(posts: List<Session>)

    @Query("SELECT * FROM sessions ORDER BY id ASC")
    fun sessions(): LiveData<List<Session>>

    @Query("SELECT * FROM sessions WHERE id LIKE :id ")
    fun session(id : Long) : LiveData<Session>

}