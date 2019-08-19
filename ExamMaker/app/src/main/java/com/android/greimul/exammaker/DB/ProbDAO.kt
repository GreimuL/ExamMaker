package com.android.greimul.exammaker.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ProbDAO{
    @Query("SELECT * from problem_db")
    fun getAllProbs():LiveData<List<Problems>>

    @Query("DELETE FROM problem_db")
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(problems: Problems)

    @Update
    suspend fun update(problems: Problems)

    @Delete
    suspend fun delete(problems: Problems)
}