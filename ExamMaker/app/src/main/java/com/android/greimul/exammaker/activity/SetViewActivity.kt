package com.android.greimul.exammaker.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.core.content.FileProvider.getUriForFile
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.greimul.exammaker.*
import com.android.greimul.exammaker.fileio.exportSet
import com.android.greimul.exammaker.fileio.readSetFromDir
import com.android.greimul.exammaker.setview.SetListAdapter
import com.android.greimul.exammaker.setview.SetViewModel
import kotlinx.android.synthetic.main.activity_addset.view.*
import kotlinx.android.synthetic.main.activity_showset.*
import java.io.File

class SetViewActivity:AppCompatActivity(){
    private lateinit var setViewModel: SetViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_showset)
        var actVal = intent.getIntExtra("ActVal",0)
        if(actVal==0) {
            Toast.makeText(this,"Activity Error",Toast.LENGTH_SHORT).show()
            finish()
        }
        var setList = readSetFromDir()
        val recyclerSet = recyclerset
        val adapter = SetListAdapter(this)
        adapter.setSets(setList)
        if(actVal==1){
            adapter.setClick = object: SetListAdapter.SetClick{
                override fun onClick(view:View,pos:Int,file:File){

                }
            }
        }
        if(actVal==3){
            adapter.setClick = object: SetListAdapter.SetClick{
                override fun onClick(view:View,pos:Int,file:File){
                    val shareIntent = Intent().apply{
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_STREAM,getUriForFile(this@SetViewActivity, BuildConfig.APPLICATION_ID,file))
                        type = "text/csv"
                    }
                    startActivity(Intent.createChooser(shareIntent,"Choose File"))
                }
            }
        }

        recyclerSet.adapter = adapter
        recyclerSet.layoutManager = LinearLayoutManager(this)
        refreshBt.setOnClickListener {
            setList = readSetFromDir()
            adapter.setSets(ckList(setList))
        }
        addSetBt.setOnClickListener{
            val dialog = AlertDialog.Builder(this)
            val dialogView = layoutInflater.inflate(R.layout.activity_addset,null)
            dialog.setView(dialogView).setPositiveButton("MakeSet"){
                    dialog,i->
                    exportSet(dialogView.setName.text.toString())
                    setList = readSetFromDir()
                    adapter.setSets(ckList(setList))
                }
                .setNegativeButton("Cancel"){
                    dialog,i->
                }
                .show()
        }
    }
    fun ckList(setList:ArrayList<File>):ArrayList<File>{
        if(setList.size ==0){
            Toast.makeText(this,"There isn't any problems in folder", Toast.LENGTH_SHORT).show()
            return ArrayList()
        }
        return setList
    }
}