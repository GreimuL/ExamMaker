package com.android.greimul.exammaker.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.greimul.exammaker.R
import com.android.greimul.exammaker.SetListAdapter
import com.android.greimul.exammaker.readSetFromDir
import kotlinx.android.synthetic.main.activity_showset.*

class SetViewActivity:AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_showset)
        var setList = readSetFromDir()
        val recyclerSet = recyclerset
        val adapter = SetListAdapter(this)
        recyclerSet.adapter = adapter
        recyclerSet.layoutManager = LinearLayoutManager(this)

        refreshBt.setOnClickListener {
            setList = readSetFromDir()
        }
    }
}