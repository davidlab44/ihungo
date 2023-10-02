package com.david.ihungo.ui.login

import android.content.Intent
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.david.ihungo.model.Authenticable
import com.david.ihungo.ui.ISocketHandler
import com.david.ihungo.ui.MainActivity
import com.google.gson.Gson
import com.yeslab.fastprefs.FastPrefs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    var email by mutableStateOf<String>("davidlab44@gmail.com")
    var password by mutableStateOf<String>("clopez2023")

    fun validateLogin(user:String, password:String, context: LoginActivity){
        val jsonObject=createJsonObject(user,password)
        launchBackgroundTask(jsonObject,context)
    }

    private fun createJsonObject(user:String, password:String):JSONObject{
        val jsonObject = JSONObject()
        jsonObject.put("email", user)
        jsonObject.put("password", password)
        return jsonObject
    }

    private fun launchBackgroundTask(message:JSONObject,context:LoginActivity){
        CoroutineScope(Dispatchers.IO).launch {
            ISocketHandler.setSocket()
            ISocketHandler.establishConnection()
            val mSocket = ISocketHandler.getSocket()
            mSocket.emit("login",message)
            Log.e("THW",""+message)
            //}

            mSocket.on("login") { args ->
                Log.e("THt2",""+args)
                //if (args[0] == null)
                    //return@on
                try{
                    val stringedResponse = args[0].toString()
                    var gson = Gson()
                    val authenticable: Authenticable = gson.fromJson(stringedResponse, Authenticable::class.java)
                    login(authenticable,context)
                }catch (e: Exception){
                    login(Authenticable(false,""),context)
                }
            }
        }
    }

    private fun login(authenticable:Authenticable,context:LoginActivity){
        val prefs = FastPrefs(context)
        prefs.set("login",authenticable.ok)
        prefs.setString("token",authenticable.token)
        if(authenticable.ok == true){
            context.startActivity(Intent(context, MainActivity::class.java))
            context.finish()
        }
    }

}