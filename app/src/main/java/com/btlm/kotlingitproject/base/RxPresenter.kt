package com.btlm.kotlingitproject.base

import com.btlm.kotlingitproject.rx.RxBus
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer

/**
 * Created by Administrator on 2017/12/16.
 */
open class RxPresenter<T : BaseContract.BaseView> : BaseContract.BasePresenter<T>{
    var mView : T? = null
    var mCompositeDisposable : CompositeDisposable? = null

    fun unSubscribe(){
        mCompositeDisposable?.dispose()
    }

    fun addSubscribe(disposable: Disposable){
        if(mCompositeDisposable == null){
            mCompositeDisposable = CompositeDisposable()
        }else{
            mCompositeDisposable?.add(disposable)
        }
    }

    fun <K> addRxBusSubscribe(eventType: Class<K>, act: Consumer<K>) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = CompositeDisposable()
        } else {
            mCompositeDisposable?.add(RxBus.toDefaultFlowable(eventType, act))
        }
    }
    override fun attachView(view: T) {
        this.mView = view
    }

    override fun detachView() {
        this.mView = null
        unSubscribe()
    }

}