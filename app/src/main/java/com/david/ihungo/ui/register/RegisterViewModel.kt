package com.david.ihungo.ui.register

import android.content.Intent
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.david.ihungo.model.UserResponse
import com.david.ihungo.ui.ISocketHandler
import com.david.ihungo.ui.login.LoginActivity
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor() : ViewModel() {

    var name by mutableStateOf<String>("David Mazo")
    var email by mutableStateOf<String>("davidlab44@gmail.com")
    var password by mutableStateOf<String>("clopez2023")

    fun validateRegister(name:String, user:String, password:String, context: RegisterActivity){
        val jsonObject=createJsonObject(name,user,password)
        launchBackgroundTask(jsonObject,context)
    }

    private fun createJsonObject(name:String, user:String, password:String):JSONObject{
        val jsonObject = JSONObject()
        jsonObject.put("name", name)
        jsonObject.put("email", user)
        jsonObject.put("password", password)
        return jsonObject
    }

    private fun launchBackgroundTask(message:JSONObject,context:RegisterActivity){
        CoroutineScope(Dispatchers.IO).launch {
            ISocketHandler.setSocket()
            ISocketHandler.establishConnection()
            val mSocket = ISocketHandler.getSocket()
            mSocket.emit("register-user",message)
            //Log.d("msg",""+message)
            mSocket.on("register-user") { args ->
                Log.d("args",""+args)
                //if (args[0] == null)
                //return@on
                try{
                    val stringedResponse = args[0].toString()
                    var gson = Gson()
                    val authenticable: UserResponse = gson.fromJson(stringedResponse, UserResponse::class.java)
                    onSuccess(authenticable,context)
                }catch (e: Exception){
                    Log.e("Error","No se pudo registrar el usuario")
                }
            }
        }
    }

    private fun onSuccess(userResponse:UserResponse, context:RegisterActivity){
        if(userResponse.ok==true){
            context.startActivity(Intent(context, LoginActivity::class.java))
            context.finish()
        }else
            Log.e("Error2","No se pudo registrar el usuario")
    }

}