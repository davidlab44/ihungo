package com.david.ihungo.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DefaultScreen(contextActivity: MainActivity, taskViewModel: TaskViewModel) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
    ) {
        Row(modifier = Modifier.padding(all = 2.dp)) {
            ReadTaskListScreen(contextActivity,taskViewModel)
        }
    }
}

@Composable
fun AddTaskScreen(contextActivity: MainActivity, taskViewModel: TaskViewModel) {
    CreateTask(contextActivity,taskViewModel)
}