package com.david.ihungo.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.LocalFlorist
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun ReadTaskListScreen(contextActivity: MainActivity, taskViewModel: TaskViewModel) {
    val mContext=LocalContext.current
    taskViewModel.getAllTasks()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(2.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var text by rememberSaveable { mutableStateOf("") }
        OutlinedTextField(
            value = text,
            modifier = Modifier
                .fillMaxWidth()
                //.background(Color.White)
                //.background(Color(0xFF22475b))
                .padding(12.dp),
            onValueChange = { newText ->
                text = newText
            },
            label = { Text(text = "Buscar") },
            placeholder = { Text(text = "") }
        )
        //taskViewModel.updateFilteredTaskList(text)
        val listModifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(top = 15.dp)
            .align(Alignment.CenterHorizontally)
        LazyColumn(modifier = listModifier) {
            items(taskViewModel.taskList) { task ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(170.dp)
                        .padding(8.dp),
                    elevation = 10.dp,
                    content = {
                        var taskBackgroundColor=Color.Yellow
                        if(task.done)
                            taskBackgroundColor=Color.Red
                        Row(){
                            Column(modifier= Modifier
                                .width(50.dp)
                                .background(taskBackgroundColor)){
                                IconButton(
                                    onClick = {
                                        taskViewModel.navigateToDetails(contextActivity,task.id)
                                    },
                                    modifier = Modifier.fillMaxSize().fillMaxHeight()
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.LocalFlorist,
                                        contentDescription = "SeedGrower",
                                        tint = Color(0xFFFFFFFF)
                                    )
                                }
                            }

                            Column(
                                modifier = Modifier.fillMaxHeight()
                            ) {
                                Row(
                                    modifier = Modifier.padding(all = 5.dp),horizontalArrangement = Arrangement.Center
                                ) {
                                    Box(
                                    ) {
                                        Text(text = "Titulo: "+task.title, fontWeight = FontWeight.Bold, fontSize = 18.sp,modifier=Modifier.clickable { taskViewModel.navigateToDetails(contextActivity,task.id) })
                                    }
                                }
                                Row(
                                    modifier = Modifier.padding(all = 5.dp),horizontalArrangement = Arrangement.Center
                                ) {
                                    Box(
                                    ) {
                                        Text(text = ""+task.description,modifier=Modifier.clickable { taskViewModel.navigateToDetails(contextActivity,task.id) })
                                    }
                                }
                                Row(
                                    modifier = Modifier.padding(all = 5.dp),horizontalArrangement = Arrangement.Center
                                ) {
                                    Box(modifier=Modifier.width(150.dp)) {
                                    }
                                    Box(modifier=Modifier.width(50.dp)) {
                                        IconButton(
                                            onClick = {
                                                taskViewModel.deleteTask(task.id)
                                            }
                                        ) {
                                            Icon(
                                                imageVector = Icons.Filled.Cancel,
                                                contentDescription = "Delete Task",
                                                tint = Color(0xFFFF6464)
                                            )
                                        }
                                    }
                                    Box(modifier=Modifier.width(50.dp)) {
                                        IconButton(
                                            onClick = {
                                                taskViewModel.markAsDone(task.id)
                                            }
                                        ) {
                                            Icon(
                                                imageVector = Icons.Filled.CheckCircle,
                                                contentDescription = "Task Done",
                                                tint=Color(0xFF0CC273)
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                )
            }
        }
    }
}