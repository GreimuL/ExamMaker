package com.android.greimul.exammaker.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.android.greimul.exammaker.probview.ProbViewModel
import com.android.greimul.exammaker.R
import com.android.greimul.exammaker.db.Problems
import kotlinx.android.synthetic.main.activity_addprob.*

class AddActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addprob)
        addBt.setOnClickListener {
            ViewModelProviders.of(this).get(ProbViewModel::class.java).insert(Problems(0,titleEdit.text.toString()))
        }
    }
}