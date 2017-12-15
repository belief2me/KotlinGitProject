package com.btlm.kotlingitproject.rx

import io.reactivex.Flowable
import io.reactivex.disposables.Disposable
import io.reactivex.processors.FlowableProcessor
import io.reactivex.processors.PublishProcessor
import java.util.function.Consumer

/**
 * Created by Administrator on 2017/12/15.
 */
object RxBus {
    val bus : FlowableProcessor<Any> = PublishProcessor.create<Any>().toSerialized()
    /**
     * 提供了一个新的事件 发射数据
     */
    fun post(o : Any){
        bus.onNext(o)
    }
    /**
     * 根据传递的eventType 类型返回特定类型(eventType)的 被观察者
     */
    fun <T> toFlowable(eventType : Class<T>) : Flowable<T> = bus.ofType(eventType)

    /**
     * 封装默认订阅
     */
    fun <T> toDefaultFlowable(eventType : Class<T>,act : Consumer<T>) : Disposable = bus.ofType(eventType)
            .compose(rxSchedulerHelper<T>()).subscribe()
}