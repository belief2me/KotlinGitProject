package com.btlm.kotlingitproject.mvp.presenter.home

import com.btlm.kotlingitproject.base.BaseSubscriber
import com.btlm.kotlingitproject.base.RxPresenter
import com.btlm.kotlingitproject.bean.recommend.Recommend
import com.btlm.kotlingitproject.mvp.contract.home.RecommendContract
import com.btlm.kotlingitproject.network.helper.RetrofitHelper
import com.btlm.kotlingitproject.rx.rxSchedulerHelper
import com.btlm.kotlingitproject.utils.JsonUtils
import com.google.gson.Gson
import com.google.gson.JsonParser
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Created by Administrator on 2017/12/21.
 */
class RecommendPresenter @Inject constructor(private val retrofitHelper: RetrofitHelper)
    : RxPresenter<RecommendContract.View>(),RecommendContract.Presenter<RecommendContract.View>{
    override fun getRecommendData() {
        //需登录
        /*BaseListSubscriber<Recommend> subscriber = mRetrofitHelper.getRecommend()
                .compose(RxUtils.rxSchedulerHelper())
                .subscribeWith(new BaseListSubscriber<Recommend>(mView) {
                    @Override
                    public void onSuccess(List<Recommend> recommends) {
                        mView.showRecommend(recommends);
                    }
                });
        addSubscribe(subscriber);*/
        addSubscribe(Flowable.just(JsonUtils.readJson("recommend.json"))
                .map<List<Recommend>> {
                    val gson = Gson()
                    val `object` = JsonParser().parse(it).asJsonObject
                    val array = `object`.getAsJsonArray("data")
                    val recommends = array.map { gson.fromJson(it, Recommend::class.java) }
                    recommends
                }
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseSubscriber<List<Recommend>>(mView) {
                    override fun onSuccess(t: List<Recommend>) {
                        mView?.showRecommend(t)
                    }
                }))
    }
}