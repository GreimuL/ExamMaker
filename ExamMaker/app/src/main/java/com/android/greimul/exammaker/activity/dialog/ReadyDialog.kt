package com.android.greimul.exammaker.activity.dialog

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.greimul.exammaker.R

class ReadyDialog:AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_ready)
    }
}