package com.android.greimul.exammaker.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "problem_db")
data class Problems(@PrimaryKey(autoGenerate = true) val id:Int, val title:String="", val description:String="",
                    val choice1:String="", val choice2:String="", val choice3:String="", val choice4:String="", val choice5:String="",
                    val answer:String="", val explanation:String="")