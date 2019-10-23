package com.android.greimul.exammaker.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.android.greimul.exammaker.R
import com.android.greimul.exammaker.algorithms.randomList
import com.android.greimul.exammaker.db.Problems
import com.android.greimul.exammaker.fileio.importSet
import com.android.greimul.exammaker.probview.ProbViewModel
import kotlinx.android.synthetic.main.activity_dotest.*
import kotlinx.android.synthetic.main.dialog_score.*
import kotlinx.android.synthetic.main.dialog_score.view.*
import java.io.File
import java.util.*
import kotlin.collections.ArrayList

class DoTestActivity: AppCompatActivity(){
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
        setContentView(R.layout.activity_dotest)
        val dialog = AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.dialog_ready,null)
        val file = intent.getStringExtra("File")
        val probList = importSet(File(file),this)
        val probnum = intent.getIntExtra("ProbNum",-1)
        val isRand = intent.getBooleanExtra("isRand",false)
        val orderList = randomList(probnum,isRand,probList.size)
        val ansList = Array(probnum,{"false false false false false"})
        var piv = 0
        var correct = 0
        allCnt.text = probnum.toString()
        curCnt.text = 1.toString()
        dialog.setView(dialogView).setPositiveButton("Start!"){
                dialog,i->
                    setProb(probList[orderList[piv]],ansList,piv)
        }.show()
        nextBt.setOnClickListener {
            curCnt.text = (piv+1).toString()
            ansList[piv] = "${choice1.isChecked} ${choice2.isChecked} ${choice3.isChecked} ${choice4.isChecked} ${choice5.isChecked}"
            piv++
            if(piv==probnum){
                for(i in 0 until probnum){
                    if(ansList[i] == (probList[orderList[i]].answer)){
                        correct++
                    }
                }
                val dialog2 = AlertDialog.Builder(this)
                val dialogView2 = layoutInflater.inflate(R.layout.dialog_score,null)
                dialogView2.scoreView.text = "${correct} / ${probnum}"
                dialog2.setView(dialogView2).setPositiveButton("Finish"){
                        dialog,i->
                            finish()
                }.show()
            }
            else {
                curCnt.text = (piv + 1).toString()
                setProb(probList[orderList[piv]],ansList,piv)
            }
        }
        preBt.setOnClickListener {
            ansList[piv] = "${choice1.isChecked} ${choice2.isChecked} ${choice3.isChecked} ${choice4.isChecked} ${choice5.isChecked}"
            if(piv>0) piv--
            curCnt.text = (piv+1).toString()
            setProb(probList[orderList[piv]],ansList,piv)
        }
    }
    fun setProb(problem:Problems,ansList:Array<String>,cur:Int){
        titleEdit.text = problem.title
        description.text = problem.description.replace(" ","\n")
        val token = ansList[cur].split(" ")
        choice1.isChecked = token[0].toBoolean()
        choice2.isChecked = token[1].toBoolean()
        choice3.isChecked = token[2].toBoolean()
        choice4.isChecked = token[3].toBoolean()
        choice5.isChecked = token[4].toBoolean()

        choice1.text = problem.choice1
        choice2.text = problem.choice2
        choice3.text = problem.choice3
        choice4.text = problem.choice4
        choice5.text = problem.choice5
    }
}