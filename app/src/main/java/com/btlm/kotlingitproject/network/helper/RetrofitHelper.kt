package com.yoyiyi.soleil.network.helper


import com.btlm.kotlingitproject.bean.app.Splash
import com.yoyiyi.soleil.network.api.AppService

import io.reactivex.Flowable


/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/10 17:57
 * * 描述:RetrofitHelper 帮助类
 */

class RetrofitHelper(private val appService: AppService
                     ) {


    fun getSplash(): Flowable<Splash> = appService.getSplash()



}
