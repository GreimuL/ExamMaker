package com.android.greimul.exammaker.fileio

import android.os.Environment
import java.io.File

fun setDir():File{
    val setdir = File(Environment.getExternalStorageDirectory(),"examset")
    if(!setdir.exists()) {
        setdir.mkdirs()
    }
    return setdir
}
fun readSetFromDir():ArrayList<File>{
    val setdir = setDir()
    val setArray = ArrayList<File>()
    val fileList = setdir.listFiles()
    fileList.forEach {
        if(it.extension=="CSV"||it.extension=="csv"){
            setArray.add(it)
        }
    }
    return setArray
}
fun exportSet(name:String){
    val setdir = setDir()
    val setfile = File(setdir.path,"${name}.csv")
    setfile.createNewFile()
    CSVHeadWrite(setfile)
}