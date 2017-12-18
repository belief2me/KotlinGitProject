package com.btlm.kotlingitproject.ext

import android.widget.Toast
import com.btlm.kotlingitproject.KotlinGitApplication

/**
 * Created by Administrator on 2017/12/18.
 */
fun String.toast():Unit{
    Toast.makeText(KotlinGitApplication.instance,this,Toast.LENGTH_LONG).show()
}