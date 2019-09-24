package com.android.greimul.exammaker.fileio

import android.content.Context
import android.os.Environment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelStoreOwner
import com.android.greimul.exammaker.db.Problems
import java.io.File
import java.io.FileWriter

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
fun exportSet(name:String,context: ViewModelStoreOwner,context2: LifecycleOwner){
    val header = arrayOf("id","title","description","choice1","choice2","choice3","choice4","choice5","answer","explanation")
    val setdir = setDir()
    val setfile = File(setdir.path,"${name}.csv")
    setfile.createNewFile()
    val csvWriter = FileWriter(setfile)
    header.forEachIndexed{i,it->
        csvWriter.append(it)
        if(i!=header.size-1)
            csvWriter.append(",")
    }
    csvWriter.close()
}
fun importSet(file:File,context: Context):List<Problems>{
    return CSVRead(file,context)
}