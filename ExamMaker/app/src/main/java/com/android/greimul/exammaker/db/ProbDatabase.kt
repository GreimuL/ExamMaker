package com.android.greimul.exammaker.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Problems::class],version = 1)
public abstract class ProbDatabase: RoomDatabase(){
    abstract fun probDAO(): ProbDAO
    companion object{
        @Volatile
        private var INSTANCE: ProbDatabase? = null
        fun getDB(context: Context,scope:CoroutineScope): ProbDatabase {
            val tmpInstance = INSTANCE
            if(tmpInstance!=null){
                return tmpInstance
            }
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext,
                    ProbDatabase::class.java,"Prob_DB").addCallback(ProbDBCallBack(scope)).build()
                INSTANCE = instance
                return instance
            }
        }
        private class ProbDBCallBack(private val scope:CoroutineScope):RoomDatabase.Callback(){
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.probDAO())
                    }
                }
            }
        }
        suspend fun populateDatabase(probDAO:ProbDAO){
            probDAO.deleteAll()
        }
    }

}