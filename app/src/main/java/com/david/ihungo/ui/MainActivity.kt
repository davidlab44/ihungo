package com.david.ihungo.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.filled.Logout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.david.ihungo.ui.login.LoginActivity
import com.david.ihungo.ui.login.isLogged
import com.david.ihungo.ui.theme.IhungoTheme
//import com.yeslab.fastprefs.FastPrefs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(!isLogged(this)){
            startActivity(Intent(this@MainActivity, LoginActivity::class.java))
            finish()
        }
        setContent {
            IhungoTheme(darkTheme = false) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val scaffoldState = rememberScaffoldState()
                    val coroutineScope = rememberCoroutineScope()
                    val navController = rememberNavController()
                    val taskViewModel = viewModel<TaskViewModel>()
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        scaffoldState = scaffoldState,
                        topBar = {
                            TopAppBarArticleAcivity(this@MainActivity,taskViewModel) {
                                coroutineScope.launch {
                                    scaffoldState.drawerState.open()
                                }
                            }
                        },
                        bottomBar = { BottomNavigationBar(navController) },
                        content = { padding ->
                            Box(modifier = Modifier.padding(padding)) {
                                NavigationBotomMenu(this@MainActivity,taskViewModel,navController = navController)
                            }
                        },
                    )
                }
            }
        }
    }
}

@Composable
fun TopAppBarArticleAcivity(nContext:MainActivity, taskViewModel: TaskViewModel, onNavIconClick: () -> Unit) {
    TopAppBar(
        title = { Text(text = "SeedGrower") },
        actions = {
            IconButton(onClick = {
                /* */
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
fun NavigationBotomMenu(contextActivity:MainActivity, taskViewModel: TaskViewModel, navController: NavHostController) {
    NavHost(navController, startDestination = BotomNavigationItem.Home.route) {
        composable(BotomNavigationItem.Home.route) {
            DefaultScreen(contextActivity,taskViewModel)
        }
        composable(BotomNavigationItem.Create.route) {
            AddTaskScreen(contextActivity,taskViewModel)
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {

    val items = listOf(
        BotomNavigationItem.Home,
        BotomNavigationItem.Create,
    )
    BottomNavigation(
        backgroundColor= Color(0xFF0D5788),
        contentColor = Color.White
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = item.icon), contentDescription = item.title) },
                label = { Text(text = item.title) },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White.copy(0.4f),
                alwaysShowLabel = true,
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}