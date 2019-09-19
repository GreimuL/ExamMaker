package com.android.greimul.exammaker.probview

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.android.greimul.exammaker.db.ProbDatabase
import com.android.greimul.exammaker.db.Problems
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProbViewModel(application: Application):AndroidViewModel(application){
    private val repo: ProbRepo
    val allProb:LiveData<List<Problems>>
    init{
        val probsDAO = ProbDatabase.getDB(application,viewModelScope).probDAO()
        repo = ProbRepo(probsDAO)
        allProb = repo.allProb
    }
    fun insert(prob:Problems) = viewModelScope.launch(Dispatchers.IO){
        repo.insert(prob)
    }
}