package com.btlm.kotlingitproject.base

/**
 * Created by Administrator on 2017/12/16.
 */
interface BaseContract {
    interface BaseView{
        /**
         * 请求出错
         */
        fun showError(msg : String)

        /**
         * 请求完成
         */
        fun complete()
    }

    interface BasePresenter<in T>{
        /**
         * 绑定
         */
        fun attachView(view : T)

        /**
         * 解绑
         */
        fun detachView()
    }
}