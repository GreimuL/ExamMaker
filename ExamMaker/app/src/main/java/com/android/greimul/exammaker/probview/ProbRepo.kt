package com.android.greimul.exammaker.probview

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.android.greimul.exammaker.db.ProbDAO
import com.android.greimul.exammaker.db.Problems

class ProbRepo(private val probDAO: ProbDAO){
    val allProb:LiveData<List<Problems>> = probDAO.getAllProbs()
    @WorkerThread
    suspend fun insert(prob:Problems){
        probDAO.insert(prob)
    }
}