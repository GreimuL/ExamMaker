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
        val intent = Intent(this, SetViewActivity::class.java)
        showBt.setOnClickListener{
            startActivity(intent.putExtra("ActVal",1))
        }
        testBT.setOnClickListener {
            startActivity(intent.putExtra("ActVal",2))
        }
        shareBT.setOnClickListener {
            startActivity(intent.putExtra("ActVal",3))
        }
        exitBt.setOnClickListener {
            finish()
        }

    }
}
