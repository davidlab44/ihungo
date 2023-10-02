package com.david.ihungo.ui

import android.app.Activity
import android.content.Intent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import com.david.ihungo.localdata.TaskRepository
import com.david.ihungo.model.Task
import kotlinx.coroutines.launch
import android.util.Log
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.david.ihungo.ui.details.DetailsActivity
import com.yeslab.fastprefs.FastPrefs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val taskRepository: TaskRepository,
) : ViewModel() {
    //Seeders
    var taskList by mutableStateOf<List<Task>>(emptyList())
    var taskDetail by mutableStateOf<Boolean>(false)
    var task by mutableStateOf<Task>(Task(title="",description=""))

    fun createSeeder(){
        CoroutineScope(Dispatchers.IO).launch {
            taskRepository.insertTask(Task(title="Task 1", description= LoremIpsum(30).toString()))
            taskRepository.insertTask(Task(title="Task 2", description= LoremIpsum(30).toString()))
        }
    }

    fun getAllTasks(){
        CoroutineScope(Dispatchers.IO).launch {
            taskList=taskRepository.getAllTasks()
        }
    }

    fun createTask(task:Task){
        CoroutineScope(Dispatchers.IO).launch {
           taskRepository.insertTask(task)
            Log.e("task",""+task)
            Log.e("tosk",""+task)
        }
    }

    fun markAsDone(taskId:Int){
        CoroutineScope(Dispatchers.IO).launch {
            val subtasks:List<Task> = taskRepository.getDetails(taskId)
            if(subtasks.isNotEmpty()&&getSubtasksWithStateDone(subtasks)==subtasks.size)
                taskRepository.markAsDone(taskId)
            getAllTasks()
        }
    }

    fun getSubtasksWithStateDone(subtasks:List<Task>):Int{
        var couter=0
        subtasks.forEach{subtask->
            if(subtask.done)
                couter++
        }
        return couter
    }

    fun deleteTask(taskId:Int){
        CoroutineScope(Dispatchers.IO).launch {
            taskRepository.deleteTask(taskId)
            getAllTasks()
        }
    }

    fun navigateToDetails(contex:Activity,taskId:Int){
        val prefs = FastPrefs(contex)
        prefs.set("taskId",taskId)
        contex.startActivity(Intent(contex, DetailsActivity::class.java))
    }
}