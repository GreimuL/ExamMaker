package com.android.greimul.exammaker

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addBT.setOnClickListener{
            startActivity(Intent(this,AddActivity::class.java))
        }
        delBt.setOnClickListener{

        }
        showBt.setOnClickListener{
            startActivity(Intent(this,ProbShowActivity::class.java))
        }
    }
}
