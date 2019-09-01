package com.android.greimul.exammaker

import android.os.Environment
import java.io.File

fun readSetFromDir():ArrayList<String>{
    val setArray = ArrayList<String>()
    val setDir = File(Environment.getExternalStorageDirectory(),"/examset/")
    if(!setDir.exists()) {
        setDir.mkdirs()
    }
    val fileList = setDir.listFiles()
    fileList.forEach {
        if(it.extension=="CSV"||it.extension=="csv"){
            setArray.add(it.path)
        }
    }
    return setArray
}