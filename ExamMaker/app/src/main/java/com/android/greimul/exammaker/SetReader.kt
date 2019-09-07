package com.android.greimul.exammaker

import android.os.Environment
import android.widget.Toast
import java.io.File

fun readSetFromDir():ArrayList<File>{
    val setArray = ArrayList<File>()
    val setDir = File(Environment.getExternalStorageDirectory(),"/examset/")
    if(!setDir.exists()) {
        setDir.mkdirs()
    }
    val fileList = setDir.listFiles()
    fileList.forEach {
        if(it.extension=="CSV"||it.extension=="csv"){
            setArray.add(it)
        }
    }
    return setArray
}