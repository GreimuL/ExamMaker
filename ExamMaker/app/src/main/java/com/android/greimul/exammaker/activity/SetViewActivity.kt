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
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.greimul.exammaker.*
import com.android.greimul.exammaker.db.ProbDAO
import com.android.greimul.exammaker.fileio.exportSet
import com.android.greimul.exammaker.fileio.importSet
import com.android.greimul.exammaker.fileio.readSetFromDir
import com.android.greimul.exammaker.probview.ProbViewModel
import com.android.greimul.exammaker.setview.SetListAdapter
import com.android.greimul.exammaker.setview.SetViewModel
import kotlinx.android.synthetic.main.activity_showset.*
import kotlinx.android.synthetic.main.dialog_addset.view.*
import kotlinx.android.synthetic.main.dialog_testset.*
import kotlinx.android.synthetic.main.dialog_testset.view.*
import java.io.File

class SetViewActivity:AppCompatActivity(){
    private lateinit var setViewModel: SetViewModel
    var actVal:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_showset)
        ViewModelProvider(this)[(ProbViewModel::class.java)].deleteAll()
        actVal = intent.getIntExtra("ActVal",0)
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
                    ViewModelProvider(this@SetViewActivity)[ProbViewModel::class.java].deleteAll()
                    startActivityForResult(Intent(this@SetViewActivity,ProbShowActivity::class.java).putExtra("File",file.toString()).putExtra("ActVal",1),0)
                }
            }
        }
        else if(actVal==2){
            adapter.setClick = object: SetListAdapter.SetClick{
                override fun onClick(view: View, pos: Int, file: File) {
                    val dialog = AlertDialog.Builder(this@SetViewActivity)
                    val dialogView = layoutInflater.inflate(R.layout.dialog_testset,null)
                    dialog.setView(dialogView).setPositiveButton("Test!"){
                        dialog,i->
                        ViewModelProvider(this@SetViewActivity)[ProbViewModel::class.java].deleteAll()
                        startActivityForResult(Intent(this@SetViewActivity,TestPreActivity::class.java)
                            .putExtra("File",file.toString())
                            .putExtra("ActVal",2)
                            .putExtra("ProbNum",dialogView.probNum.text.toString().toInt())
                            .putExtra("isRand",dialogView.selRand.isChecked),2)
                    }.setNegativeButton("Cancle"){
                        dialog,i->
                    }.show()
                }
            }
        }
        else if(actVal==3){
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
            val dialogView = layoutInflater.inflate(R.layout.dialog_addset,null)
            dialog.setView(dialogView).setPositiveButton("MakeSet"){
                    dialog,i->
                    exportSet(dialogView.setName.text.toString(),this,this)
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
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(data!=null)
            actVal = data.getIntExtra("ActVal", 0)
    }

}