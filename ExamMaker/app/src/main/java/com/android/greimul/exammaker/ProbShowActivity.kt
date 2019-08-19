package com.android.greimul.exammaker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_showprob.*

class ProbShowActivity:AppCompatActivity(){
    companion object{
        const val newProbActivityRequestCode = 1
    }
    private lateinit var probViewModel:ProbViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_showprob)
        val recyclerView = recyclerview
        val adapter = ProbListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        probViewModel = ViewModelProviders.of(this).get(ProbViewModel::class.java)
        probViewModel.allProb.observe(this, Observer{probs->
            probs?.let{adapter.setProbs(it)}
        })
    }
}