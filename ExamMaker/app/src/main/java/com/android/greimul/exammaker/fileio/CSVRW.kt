package com.android.greimul.exammaker.fileio

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.*
import com.android.greimul.exammaker.db.Problems
import com.android.greimul.exammaker.probview.ProbViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.lang.Exception

val header = arrayOf("id","title","description","choice1","choice2","choice3","choice4","choice5","answer","explanation")
fun CSVWrite(file:File,viewModelProvider:ProbViewModel,context2:LifecycleOwner,debug:Int){
    println(debug)
    file.createNewFile()
    var csvStr:StringBuilder = StringBuilder("")
    csvStr.clear()
    header.forEachIndexed{i,it->
        csvStr.append(it)
        if(i!=header.size-1)
            csvStr.append(",")
    }
    var probList:List<Problems> = emptyList()
    viewModelProvider.allProb.observe(context2,
        Observer {/*
            it.forEachIndexed { i, prob ->
                csvStr.append("\n")
                csvStr.append(
                    "${prob.id},${prob.title},${prob.description}" +
                            ",${prob.choice1},${prob.choice2},${prob.choice3},${prob.choice4},${prob.choice5}" +
                            ",${prob.answer},${prob.explanation}"
                )
                if(i==it.size-1) {
                    file.writeText(csvStr.toString())
                    viewModelProvider.allProb.removeObservers(context2)
                }
            }*/
            probList = it
        })
    probList.forEachIndexed {i,prob->
        csvStr.append("\n")
        csvStr.append(
            "${prob.id},${prob.title},${prob.description}" +
                    ",${prob.choice1},${prob.choice2},${prob.choice3},${prob.choice4},${prob.choice5}" +
                    ",${prob.answer},${prob.explanation}")
        if(i==probList.size-1){
            file.writeText(csvStr.toString())
        }
    }
}
fun CSVRead(file: File,context:Context):ArrayList<Problems>{
    val problemlist = ArrayList<Problems>()
    val csvReader = BufferedReader(FileReader(file))
    var curLine = csvReader.readLine()
    val headToken =curLine.split(",")
    try {
        headToken.forEachIndexed { i, s ->
            if (s != header[i]) {
               throw Exception("ReadError")
            }
        }
        curLine = csvReader.readLine()
        while (curLine != null) {
            val probToken = curLine.split(",")
            if (probToken.size != header.size) {
                throw Exception("ReadError")
            }
            problemlist.add(Problems(probToken[0].toInt(),probToken[1],probToken[2],
                probToken[3],probToken[4],probToken[5],
                probToken[6],probToken[7],probToken[8],
                probToken[9]))
            curLine = csvReader.readLine()
        }
    }
    catch(e:Exception){
        Toast.makeText(context, "Read File Failed", Toast.LENGTH_SHORT).show()
        return ArrayList()
    }
    csvReader.close()
    return problemlist
}