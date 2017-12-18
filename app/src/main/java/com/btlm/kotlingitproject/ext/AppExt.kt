package com.btlm.kotlingitproject.ext

import android.content.Context
import com.btlm.kotlingitproject.KotlinGitApplication

/**
 * Created by Administrator on 2017/12/18.
 */
fun Context.getComponent() = KotlinGitApplication.instance.appComponent