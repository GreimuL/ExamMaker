package com.android.greimul.exammaker.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.greimul.exammaker.R
import com.android.greimul.exammaker.SetListAdapter
import com.android.greimul.exammaker.SetViewModel
import com.android.greimul.exammaker.readSetFromDir
import kotlinx.android.synthetic.main.activity_showset.*
import kotlinx.android.synthetic.main.setview_item.*
import java.io.File
import java.security.Provider

class SetViewActivity:AppCompatActivity(){
    private lateinit var setViewModel:SetViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_showset)
        var setList = readSetFromDir()
        val recyclerSet = recyclerset
        val adapter = SetListAdapter(this)
        adapter.setSets(setList)
        recyclerSet.adapter = adapter
        recyclerSet.layoutManager = LinearLayoutManager(this)
        refreshBt.setOnClickListener {
            setList = readSetFromDir()
            if(setList.size ==0){
                Toast.makeText(this,"There isn't any problems in folder", Toast.LENGTH_SHORT).show()
            }
            adapter.setSets(setList)
        }
        showSetBt.setOnClickListener{
            startActivity(Intent(this,ProbShowActivity::class.java))
        }
    }
}