package com.david.ihungo.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TaskTable")
data class Task (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "title") val title: String="",
    @ColumnInfo(name = "description") val description: String="",
    @ColumnInfo(name = "done") var done: Boolean=false,
    @ColumnInfo(name = "parent") val parent: Int = 0,
    )
fun Task.toDomain() = Task(id,title,description,done,parent)
fun Task.toDatabase() = Task(id,title,description,done,parent)