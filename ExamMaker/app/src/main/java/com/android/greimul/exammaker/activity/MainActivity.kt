package com.android.greimul.exammaker.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.greimul.exammaker.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        /*
            ask permission
         */
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showBt.setOnClickListener{
            startActivity(Intent(this, SetViewActivity::class.java))
        }
        testBT.setOnClickListener {
            startActivity(Intent(this, SetViewActivity::class.java))
        }
        shareBT.setOnClickListener {
            startActivity(Intent(this, SetViewActivity::class.java))
        }
        exitBt.setOnClickListener {
            finish()
        }

    }
}
