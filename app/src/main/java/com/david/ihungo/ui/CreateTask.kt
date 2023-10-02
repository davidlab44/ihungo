package com.david.ihungo.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.david.ihungo.model.Task

@Composable
fun CreateTask(contextActivity: MainActivity, taskViewModel: TaskViewModel) {
    var title by rememberSaveable { mutableStateOf("") }
    var description by rememberSaveable { mutableStateOf("") }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)) {
        Row() {
            Text(text = "Ingresa los datos del nuevo Grow", modifier=Modifier.fillMaxWidth(),fontWeight = FontWeight.SemiBold, fontSize = 20.sp)
        }

        Row(
            modifier = Modifier.fillMaxWidth().padding(top=10.dp)
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = {newName->
                    title=newName
                },
                label = { androidx.compose.material3.Text("Nombre del grow: ") },
                modifier = Modifier.fillMaxWidth()
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth().padding(top=10.dp)
        ) {
            OutlinedTextField(
                value = description,
                onValueChange = {newName->
                    description=newName
                },
                label = { androidx.compose.material3.Text("Descripción del Grow: ") },
                modifier = Modifier.fillMaxWidth().height(150.dp),
                singleLine= false,
                maxLines = 10
            )
        }

        Row(modifier=Modifier
            .fillMaxWidth()
            .padding(top = 200.dp)) {
            Button(
                onClick = {
                    if(title.isNotEmpty()&&description.isNotEmpty())
                        taskViewModel.createTask(Task(title=title,description=description));title="";description=""
                },
                modifier=Modifier
                    .fillMaxWidth()
                    .height(64.dp).background(Color(0xFF222222))
            ) {
                Text(
                    "Crear Grow",
                    fontSize = 17.sp
                )
            }
        }
    }
}