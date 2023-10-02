package com.david.ihungo.localdata

import com.david.ihungo.localdata.database.dao.TaskDao
import com.david.ihungo.model.Task
import com.david.ihungo.model.toDomain
import javax.inject.Inject

class TaskRepository @Inject constructor(
    private val taskDao: TaskDao
) {

    suspend fun getAllTasks():List<Task>{
        val response: List<Task> = taskDao.getAll()
        return response.map { it.toDomain() }
    }

    suspend fun getDetails(parentId:Int):List<Task>{
        val response: List<Task> = taskDao.getDetails(parentId)
        return response.map { it.toDomain() }
    }

    suspend fun getOneTask(taskId:Int):Task{
        return taskDao.getOneTask(taskId)
    }

    suspend fun insertRecipes(recipes:List<Task>){
        taskDao.insertAll(recipes)
    }

    suspend fun insertTask(task:Task){
        return taskDao.insertOne(task)
    }

    suspend fun clearRecipes(){
        taskDao.deleteAll()
    }

    suspend fun markAsDone(taskId:Int){
        return taskDao.markAsDone(taskId)
    }

    suspend fun deleteTask(taskId:Int){
        return taskDao.deleteTask(taskId)
    }

}
