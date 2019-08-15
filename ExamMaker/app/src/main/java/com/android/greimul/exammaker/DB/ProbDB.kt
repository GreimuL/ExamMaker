package com.android.greimul.exammaker.DB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Problems::class],version = 1)
public abstract class ProbDatabase: RoomDatabase(){
    abstract fun probDAO(): ProbDAO
    companion object{
        @Volatile
        private var INSTANCE:ProbDatabase? = null
        fun getDB(context: Context):ProbDatabase{
            val tmpInstance = INSTANCE
            if(tmpInstance!=null){
                return tmpInstance
            }
            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext,ProbDatabase::class.java,"Prob_DB").build()
                INSTANCE = instance
                return instance
            }
        }
    }
}