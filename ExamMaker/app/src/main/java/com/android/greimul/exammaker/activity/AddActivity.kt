package com.android.greimul.exammaker.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.android.greimul.exammaker.probview.ProbViewModel
import com.android.greimul.exammaker.R
import com.android.greimul.exammaker.db.Problems
import com.android.greimul.exammaker.fileio.CSVWrite
import kotlinx.android.synthetic.main.activity_addprob.*
import kotlinx.android.synthetic.main.activity_addprob.titleEdit
import java.io.File
import java.lang.Exception

class AddActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addprob)
        val fileInfo = intent.getStringExtra("File")
        addBt.setOnClickListener {
            ViewModelProvider(this)[ProbViewModel::class.java].insert(
                Problems(0,titleEdit.text.toString(),descriptEdit.text.toString(),
                    c1Edit.text.toString(),c2Edit.text.toString(),c3Edit.text.toString(),c4Edit.text.toString(),c5Edit.text.toString()
                ,makeAnsStr(),exEdit.text.toString()))
            CSVWrite(File(fileInfo), this, this)
            finish()
        }
    }
    fun makeAnsStr():String{
        var ans:String = ""
        ans = "${c1.isChecked} ${c2.isChecked} ${c3.isChecked} ${c4.isChecked} ${c5.isChecked}"
        return ans
    }
}