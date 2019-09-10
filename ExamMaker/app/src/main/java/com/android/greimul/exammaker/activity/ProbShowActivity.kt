package com.android.greimul.exammaker.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.greimul.exammaker.ProbListAdapter
import com.android.greimul.exammaker.ProbViewModel
import com.android.greimul.exammaker.R
import kotlinx.android.synthetic.main.activity_showprob.*
import kotlinx.android.synthetic.main.recyclerview_item.*

class ProbShowActivity:AppCompatActivity(){
    /*companion object{
        const val newProbActivityRequestCode = 1
    }*/
    private lateinit var probViewModel: ProbViewModel
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
        addprobBt.setOnClickListener {
            startActivity(Intent(this,AddActivity::class.java))
        }
    }
}