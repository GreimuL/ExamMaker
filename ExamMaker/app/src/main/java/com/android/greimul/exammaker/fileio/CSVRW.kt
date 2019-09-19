package com.android.greimul.exammaker.fileio

import android.content.Context
import android.widget.Toast
import com.android.greimul.exammaker.db.Problems
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.lang.Exception

val header = arrayOf("id","title","description","choice1","choice2","choice3","choice4","choice5","answer","explanation")
fun CSVHeadWrite(file:File){
    val csvWriter = FileWriter(file)
    header.forEach{
        csvWriter.append(it)
        csvWriter.append(",")
    }
    csvWriter.close()
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
        while (curLine != null) {
            curLine = readLine()
            val probToken = curLine.split(",")
            if (probToken.size != header.size) {
                throw Exception("ReadError")
            }
            problemlist.add(Problems(probToken[0].toInt(),probToken[1],probToken[2],
                probToken[3],probToken[4],probToken[5],
                probToken[6],probToken[7],probToken[8],
                probToken[9]))
        }
    }
    catch(e:Exception){
        Toast.makeText(context, "Read File Failed", Toast.LENGTH_SHORT).show()
        return ArrayList()
    }
    return problemlist
}