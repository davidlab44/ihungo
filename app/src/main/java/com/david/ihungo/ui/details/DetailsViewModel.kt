package com.david.ihungo.ui.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import com.david.ihungo.localdata.TaskRepository
import com.david.ihungo.model.Task
import kotlinx.coroutines.launch
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val taskRepository: TaskRepository,
) : ViewModel() {

    var task by mutableStateOf<Task>(Task(title="",description=""))
    var detailsList by mutableStateOf<List<Task>>(emptyList())
    var modalVisibility by mutableStateOf<Boolean>(false)

    fun getOneTask(taskId:Int){
        CoroutineScope(Dispatchers.IO).launch {
            task=taskRepository.getOneTask(taskId)
        }
    }

    fun getDetails(parentId:Int){
        CoroutineScope(Dispatchers.IO).launch {
            detailsList=taskRepository.getDetails(parentId)
        }
    }

    fun addObject(task:Task){
        CoroutineScope(Dispatchers.IO).launch {
            taskRepository.insertTask(task)
        }
    }

    fun updateSubtaskState(){
        CoroutineScope(Dispatchers.IO).launch {
            detailsList.forEach{subtask->
                if(subtask.done)
                    taskRepository.markAsDone(subtask.id)
            }
            getDetails(task.id)
        }
    }
}