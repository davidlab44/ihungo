package com.david.ihungo.ui.details

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.filled.LocalFlorist
import androidx.compose.material.icons.filled.Logout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.david.ihungo.ui.theme.IhungoTheme
import com.yeslab.fastprefs.FastPrefs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsActivity : ComponentActivity() {

    private val detailsViewModel: DetailsViewModel by viewModels()

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IhungoTheme(darkTheme = false) {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFFedf3a4)),
                    color = MaterialTheme.colors.background
                ) {
                    val scaffoldState = rememberScaffoldState()
                    val coroutineScope = rememberCoroutineScope()
                    val detailsViewModel = viewModel<DetailsViewModel>()
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        scaffoldState = scaffoldState,
                        topBar = {
                            TopAppBarReloadableAcivity(this@DetailsActivity,detailsViewModel) {
                                coroutineScope.launch {
                                    scaffoldState.drawerState.open()
                                }
                            }
                        },
                        //bottomBar = { BottomNavigationBar(navController) },
                        content = { padding ->
                            Box(modifier = Modifier.padding(padding)) {
                                DetailScreen(this@DetailsActivity,detailsViewModel)
                                //ReloadableNavigationBotomMenu(this@ReloadableActivity,reloadableViewModel,authenticableViewModel,navController = navController)
                            }
                        },
                    )
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        detailsViewModel.updateSubtaskState()
    }
}

@Composable
fun TopAppBarReloadableAcivity(nContext:DetailsActivity, detailsViewModel: DetailsViewModel, onNavIconClick: () -> Unit) {
    val mContext = LocalContext.current.applicationContext
    TopAppBar(
        title = { Text(text = "SeedGrower") },
        actions = {
            IconButton(onClick = { /* doSomething() */
                //reloadableViewModel.saveReloadableListToSync()
                Log.e("TAG","TAGTAG")
            }) {
                Icon(
                    imageVector = Icons.Filled.Logout,
                    contentDescription = "Logout"
                )
            }
        },
        backgroundColor = Color(0xFF0D5788),
        contentColor = Color.White
    )
}

@Composable
fun DetailScreen(contextActivity:DetailsActivity,detailsViewModel: DetailsViewModel) {
    val prefs = FastPrefs(LocalContext.current)
    detailsViewModel.getOneTask(prefs.get("taskId",0)!!)
    Column(modifier=Modifier.fillMaxSize()) {
        Row(modifier= Modifier
            .height(150.dp)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(170.dp)
                    .padding(8.dp),
                elevation = 10.dp,
                content = {
                    Row(){
                        Column(modifier= Modifier
                            .width(50.dp)
                            .background(Color.Yellow)
                        ){
                            IconButton(
                                onClick = {},
                                modifier = Modifier
                                    .fillMaxSize()
                                    .fillMaxHeight()
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.LocalFlorist,
                                    contentDescription = "Flower Icon",
                                    tint = Color(0xFFFFFFFF)
                                )
                            }
                        }

                        Column(
                            modifier = Modifier
                                .fillMaxHeight()
                        ) {
                            Row(
                                modifier = Modifier.padding(all = 5.dp),horizontalArrangement = Arrangement.Center
                            ) {
                                Box(
                                ) {
                                    Text(text = "Titulo: "+detailsViewModel.task.title, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                                }
                            }
                            Row(
                                modifier = Modifier.padding(all = 5.dp),horizontalArrangement = Arrangement.Center
                            ) {
                                Box(
                                ) {
                                    Text(text = ""+detailsViewModel.task.description)
                                }
                            }
                        }
                    }
                }
            )
        }

        Row(modifier= Modifier
            .height(300.dp)
        ) {
            val mContext=LocalContext.current
            detailsViewModel.getDetails(prefs.get("taskId",0)!!)
            if(detailsViewModel.modalVisibility){
                DetailsModalSheet(detailsViewModel,visible=true,onVisibleChange={detailsViewModel.modalVisibility=false},prefs.get("taskId",0)!!)
            }
            val listModifier = Modifier
                .background(Color.White)
                .padding(top = 10.dp)
                //.align(Alignment.CenterHorizontally)
            LazyColumn(modifier = listModifier) {
                items(detailsViewModel.detailsList) { task ->
                    Card(
                        modifier = Modifier.fillMaxWidth().padding(8.dp).clickable{},
                        elevation = 10.dp,
                        content = {
                            Column( horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.fillMaxWidth().padding(2.dp)) {
                                Row(
                                    modifier = Modifier.padding(all = 2.dp),horizontalArrangement = Arrangement.Center
                                ){
                                    Text(
                                        text = task.title,
                                        textAlign = TextAlign.Center, color = Color.Black, fontSize = 24.sp)
                                    Box(
                                        modifier = Modifier.fillMaxSize().padding(1.dp),
                                        contentAlignment = Alignment.CenterEnd
                                    ) {
                                        val checked = remember { mutableStateOf(task.done) }
                                        Checkbox(
                                            checked = checked.value,
                                            onCheckedChange = {
                                                checked.value = it
                                                task.done=true
                                                detailsViewModel.updateSubtaskState()
                                            }
                                        )
                                    }
                                }

                            }
                        }
                    )
                }
            }
        }

        Row(modifier= Modifier.padding(top=50.dp).align(Alignment.End)) {
            Text(text="+ Nuevo Objeto", modifier= Modifier.padding(end=20.dp).clickable {
                detailsViewModel.modalVisibility=!detailsViewModel.modalVisibility
            }, fontSize=18.sp, fontStyle = FontStyle.Italic, style = TextStyle(textDecoration = TextDecoration.Underline))
        }
        Row(modifier= Modifier.padding(all=20.dp)) {
            Button(
                onClick = {contextActivity.finish()},
                modifier=Modifier.fillMaxWidth()
            ) {
                Text(text = "Terminar",modifier=Modifier.padding(16.dp))
            }
        }
    }
}