package com.meanwhile.mindfulness.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.migration.Migration
import android.content.Context
import com.meanwhile.mindfulness.model.Session

/**
 * Database schema that holds the list of repos.
 */
@Database(
        entities = [Session::class],
        version = 1,
        exportSchema = false
)
abstract class SessionDatabase : RoomDatabase() {

    abstract fun sessionsDao(): SessionDao

    companion object {

        @Volatile
        private var INSTANCE: SessionDatabase? = null

        fun getInstance(context: Context): SessionDatabase =
                INSTANCE ?: synchronized(this) {
                    INSTANCE
                            ?: buildDatabase(context).also { INSTANCE = it }
                }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext,
                        SessionDatabase::class.java, "Sessions.db")
                        .build()

        //TODO see if we can prepoluate some values, maybe with migrations
    }
}