package com.android.greimul.exammaker.algorithms

import android.util.Log
import kotlin.random.Random
fun randomList(size:Int,isRand:Boolean,probnum:Int):IntArray{
    val list = IntArray(size)
    for(i in 0 until size){
        list[i] = i%probnum
    }
    if(!isRand){
        return list
    }
    var piv:Int = 1
    for (i in 0 until size-1) {
        var rand = Random.nextInt(piv, size)
        Log.d("Rand",i.toString())
        list[i] = list[rand].also{list[rand]=list[i]}
        piv++
    }
    return list
}