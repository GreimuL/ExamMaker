package com.android.greimul.exammaker.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.android.greimul.exammaker.R
import com.android.greimul.exammaker.probview.ProbViewModel
import kotlinx.android.synthetic.main.activity_testpre.*
import java.io.File

class TestPreActivity:AppCompatActivity(){
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item!=null) {
            when (item.itemId) {
                android.R.id.home -> {
                    setResult(Activity.RESULT_OK, Intent().putExtra("ActVal", 2))
                    finish()
                    return true
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_testpre)
        val file = intent.getStringExtra("File")
        val probnum = intent.getIntExtra("ProbNum",-1)
        val isRand = intent.getBooleanExtra("isRand",false)
        fileView.text = File(file).name
        numView.text = probnum.toString()
        isRandView.text = if(isRand) "OK" else "NO"
        testBt.setOnClickListener {
            startActivity(
                Intent(this, DoTestActivity::class.java)
                    .putExtra("File", file)
                    .putExtra("ProbNum", probnum)
                    .putExtra("isRand", isRand)
            )
        }
    }
}