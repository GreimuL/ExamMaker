package com.android.greimul.exammaker.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.android.greimul.exammaker.R
import com.android.greimul.exammaker.db.Problems
import kotlinx.android.synthetic.main.activity_addprob.*
import kotlinx.android.synthetic.main.activity_prob.*
import kotlinx.android.synthetic.main.activity_prob.titleEdit
import kotlinx.android.synthetic.main.dialog_showexp.view.*

class ProbActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prob)
        val problem = intent.getParcelableArrayListExtra<Problems>("Problem")[0]
        titleEdit.text = problem.title
        description.text = problem.description
        choice1.text = problem.choice1
        choice2.text = problem.choice2
        choice3.text = problem.choice3
        choice4.text = problem.choice4
        choice5.text = problem.choice5
        showAnsBt.setOnClickListener {
            var ans = "Answer: "
            val token = problem.answer.split(" ")
            token.forEachIndexed{i,it->
                if(it=="true"){
                    ans += "Choice${i+1} "
                }
            }
            Toast.makeText(this,ans,Toast.LENGTH_SHORT).show()
        }
        showExBt.setOnClickListener {
            val dialog = AlertDialog.Builder(this)
            val dialogView = layoutInflater.inflate(R.layout.dialog_showexp,null)
            dialogView.expView.text = problem.explanation
            dialog.setView(dialogView).setPositiveButton("OK"){
                dialog,i->
            }.show()
        }
    }
}