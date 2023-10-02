package com.david.ihungo.ui

import android.util.Log
import com.david.ihungo.util.BASE_URL
import io.socket.client.IO
import io.socket.client.Socket
import java.net.URISyntaxException

object ISocketHandler {

    lateinit var mSocket: Socket

    @Synchronized
    fun setSocket() {
        try {
            mSocket = IO.socket(BASE_URL)
            Log.e("TAGF",""+ mSocket)
            Log.e("TAG2",""+ mSocket)
        } catch (e: URISyntaxException) {
            Log.e("TAGF",""+ e)
        }
    }

    @Synchronized
    fun getSocket(): Socket {
        return mSocket
    }

    @Synchronized
    fun establishConnection() {
        mSocket.connect()
    }

    @Synchronized
    fun closeConnection() {
        mSocket.disconnect()
    }
}