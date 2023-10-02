package com.david.ihungo.ui.login

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import com.david.ihungo.ui.MainActivity
import com.yeslab.fastprefs.FastPrefs

fun isLogged(mContext: Context):Boolean {
    val prefs = FastPrefs(mContext)
    return prefs.get("login",false)!!
}

fun logout(mContext: Activity) {
    val prefs = FastPrefs(mContext)
    prefs.remove("login")
    mContext.startActivity(Intent(mContext, LoginActivity::class.java))
    mContext.finish()
}