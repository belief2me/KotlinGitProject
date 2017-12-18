package com.btlm.kotlingitproject.base

import com.btlm.kotlingitproject.network.exception.ApiException
import com.btlm.kotlingitproject.utils.AppUtils
import com.btlm.kotlingitproject.utils.LogUtils
import com.btlm.kotlingitproject.utils.net.NetworkUtils
import io.reactivex.subscribers.ResourceSubscriber
import retrofit2.HttpException
import java.net.SocketTimeoutException

/**
 * Created by Administrator on 2017/12/18.
 */
abstract class BaseSubscriber<T>(private val view : BaseContract.BaseView?) : ResourceSubscriber<T>() {
    private var msg : String? = null

    abstract fun onSuccess(t : T)

    constructor(view : BaseContract.BaseView?,msg : String?) : this(view){
        this.msg = msg
    }

    open fun onFailure(code : Int,message : String){

    }

    override fun onStart() {
        super.onStart()
        if(!NetworkUtils.isConnected(AppUtils.getAppContext())){

        }else{

        }
    }

    override fun onComplete() {

    }

    override fun onNext(t: T) {
        view?.let {
            it.complete()
            onSuccess(t)
        }?: return@onNext
    }

    override fun onError(t: Throwable?) {
        view?.let {
            if(!msg.isNullOrEmpty()){
                it.showError(msg!!)
            }else{
                when(t){
                    is ApiException -> it.showError(t.toString())
                    is SocketTimeoutException -> it.showError("服务器响应超时ヽ(≧Д≦)ノ")
                    is HttpException -> it.showError("数据加载失败ヽ(≧Д≦)ノ")
                    else ->{
                        view.showError("未知错误ヽ(≧Д≦)ノ")
                        LogUtils.e("MYERROR:" + t.toString())
                    }
                }

            }
        }?:return
    }
}