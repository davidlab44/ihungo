package com.david.ihungo.ui.details

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.david.ihungo.model.Task
import eu.wewox.modalsheet.ExperimentalSheetApi
import eu.wewox.modalsheet.ModalSheet

@OptIn(ExperimentalSheetApi::class)
@Composable
fun DetailsModalSheet(detailsViewModel: DetailsViewModel, visible: Boolean, onVisibleChange: (Boolean) -> Unit,taskId:Int) {

    ModalSheet(visible = visible, onVisibleChange = onVisibleChange) {

        var title by rememberSaveable { mutableStateOf("") }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)) {

            Row(horizontalArrangement = Arrangement.Center,) {
                Text(text = "Crear Objeto", modifier=Modifier,fontWeight = FontWeight.SemiBold, fontSize = 20.sp)
            }

            Row(
                modifier = Modifier.fillMaxWidth().padding(top=10.dp)
            ) {
                OutlinedTextField(
                    value = title,
                    onValueChange = {
                        title=it.trim()
                    },
                    label = { androidx.compose.material3.Text("Nombre del Objeto: ") },
                    modifier = Modifier.fillMaxWidth().height(150.dp),
                    singleLine= false,
                    maxLines = 10
                )
            }

            Row(modifier=Modifier
                .fillMaxWidth()
                .padding(top=50.dp,bottom=50.dp)) {
                Button(
                    onClick = {
                        if(title.isNotEmpty()){
                            detailsViewModel.modalVisibility=false
                            detailsViewModel.addObject(Task(title=title,description="",parent=taskId));title=""
                        }
                    },
                    modifier=Modifier
                        .fillMaxWidth()
                        .height(64.dp).background(Color(0xFF222222))
                ) {
                    Text(
                        "Crear Objeto",
                        fontSize = 17.sp
                    )
                }
            }
        }
        BackHandler(enabled = true){
            detailsViewModel.modalVisibility=false
        }
    }
}