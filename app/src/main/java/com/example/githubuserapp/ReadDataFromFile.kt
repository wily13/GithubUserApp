package com.example.githubuserapp

import android.content.Context
import androidx.annotation.RawRes
import java.io.InputStream
import java.util.*

class ReadDataFromFile {

    fun readRawResource(context: Context, @RawRes res: Int): String {
        return readStream(context.getResources().openRawResource(res))
    }

    private fun readStream(`is`: InputStream): String {
        val s = Scanner(`is`).useDelimiter("\\A")
        return if (s.hasNext()) s.next() else ""
    }

}