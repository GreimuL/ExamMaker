package com.android.greimul.exammaker.activity

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.android.greimul.exammaker.probview.ProbViewModel
import com.android.greimul.exammaker.R
import com.android.greimul.exammaker.db.Problems
import com.android.greimul.exammaker.fileio.CSVWrite
import kotlinx.android.synthetic.main.activity_addprob.*
import kotlinx.android.synthetic.main.activity_addprob.titleEdit
import kotlinx.coroutines.*
import java.io.File
import java.lang.Exception

class AddActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?)=runBlocking<Unit> {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addprob)
        val fileInfo = intent.getStringExtra("File")
        var viewModelProvider = ViewModelProvider(this@AddActivity)[ProbViewModel::class.java]
        addBt.setOnClickListener {
            var tmpstr= descriptEdit.text.toString().replace("\n"," ")
            val tmp = Problems(0,titleEdit.text.toString(),tmpstr,
                c1Edit.text.toString(),c2Edit.text.toString(),c3Edit.text.toString(),c4Edit.text.toString(),c5Edit.text.toString()
                ,makeAnsStr(),exEdit.text.toString())
            /*runBlocking {
                var job = async {
                    viewModelProvider.insert(tmp)
                    123123
                }
                var debug =0
                debug = job.await()
                if(viewModelProvider.allProb.value !=null)
                    Log.d("Viewmodel Debug", viewModelProvider.allProb.value!!.size.toString())
                CSVWrite(File(fileInfo), viewModelProvider, this@AddActivity,debug)
            }*/
            viewModelProvider.insert(tmp)
            CSVWrite(File(fileInfo), viewModelProvider, this@AddActivity,0)
            finish()
        }
    }
    fun makeAnsStr():String{
        var ans:String = ""
        ans = "${c1.isChecked} ${c2.isChecked} ${c3.isChecked} ${c4.isChecked} ${c5.isChecked}"
        return ans
    }
}