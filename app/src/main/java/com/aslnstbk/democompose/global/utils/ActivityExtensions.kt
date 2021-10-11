package com.aslnstbk.democompose.global.utils

import android.app.Activity
import android.content.Intent
import android.os.Bundle

fun Activity.showModuleActivity(
    path: String,
    args: Bundle? = null
) {
    try {
        val intent = Intent(this, Class.forName(path))
        args?.let {
            intent.putExtras(it)
        }
        startActivity(intent)
    } catch (e: ClassNotFoundException) {
        e.printStackTrace()
    }
}