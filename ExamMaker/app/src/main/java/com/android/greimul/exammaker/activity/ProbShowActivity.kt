package com.android.greimul.exammaker.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.greimul.exammaker.probview.ProbListAdapter
import com.android.greimul.exammaker.probview.ProbViewModel
import com.android.greimul.exammaker.R
import com.android.greimul.exammaker.db.Problems
import com.android.greimul.exammaker.fileio.CSVWrite
import com.android.greimul.exammaker.fileio.importSet
import kotlinx.android.synthetic.main.activity_showprob.*
import java.io.File

class ProbShowActivity:AppCompatActivity(){
    /*companion object{
        const val newProbActivityRequestCode = 1
    }*/
    private lateinit var probViewModel: ProbViewModel

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item!=null) {
            when (item.itemId) {
                android.R.id.home -> {
                    setResult(Activity.RESULT_OK, Intent().putExtra("ActVal", 1))
                    finish()
                    return true
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_showprob)
        val fileInfo = intent.getStringExtra("File")
        val actval = intent.getIntExtra("ActVal",0)
        if(actval==1) {
            initDB(File(fileInfo))
        }
        val recyclerView = recyclerview
        val adapter = ProbListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        probViewModel = ViewModelProvider(this)[(ProbViewModel::class.java)]
        probViewModel.allProb.observe(this, Observer{probs->
            probs?.let{adapter.setProbs(it)}
        })
        addprobBt.setOnClickListener {
            startActivity(Intent(this,AddActivity::class.java).putExtra("File",fileInfo))
        }
        adapter.setClick = object:ProbListAdapter.SetClick{
            override fun onClick(view: View, pos: Int, problem: Problems) {
                startActivity(Intent(this@ProbShowActivity, ProbActivity::class.java)
                    .putParcelableArrayListExtra("Problem", arrayListOf(problem)))
            }
        }
    }
    fun initDB(file: File){
        val viewmodel = ViewModelProvider(this)[ProbViewModel::class.java]
        importSet(file,this).forEach {
            viewmodel.insert(it)
        }
    }
}