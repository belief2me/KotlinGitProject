package com.btlm.kotlingitproject.mvp.presenter.discover


import com.btlm.kotlingitproject.base.BaseSubscriber
import com.btlm.kotlingitproject.base.RxPresenter
import com.btlm.kotlingitproject.bean.discover.GameCenter
import com.btlm.kotlingitproject.mvp.contract.discover.GameCenterContract
import com.btlm.kotlingitproject.rx.rxSchedulerHelper
import com.btlm.kotlingitproject.utils.JsonUtils
import com.google.gson.Gson
import com.google.gson.JsonParser

import io.reactivex.Flowable
import javax.inject.Inject

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/17 18:00
 * * 描述:活动中心resenter
 */

class GameCenterPresenter @Inject constructor() : RxPresenter<GameCenterContract.View>(), GameCenterContract.Presenter<GameCenterContract.View> {

    override fun getGameCenterData() {
        //接口需要登录账号
        addSubscribe(Flowable.just(JsonUtils.readJson("game_center.json"))
                .map {
                    val gson = Gson()
                    val `object` = JsonParser().parse(it).asJsonObject
                    val jsonObject = `object`.getAsJsonObject("data")
                    val gameCenter = gson.fromJson(jsonObject, GameCenter::class.java)
                    gameCenter
                }
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseSubscriber<GameCenter>(mView) {
                    override fun onSuccess(t: GameCenter) {
                        mView?.showGameCenter(t)
                    }
                }))
    }
}
