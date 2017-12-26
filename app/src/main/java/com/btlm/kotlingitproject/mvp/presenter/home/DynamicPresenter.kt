package com.btlm.kotlingitproject.mvp.presenter.home


import com.btlm.kotlingitproject.base.BaseSubscriber
import com.btlm.kotlingitproject.base.RxPresenter
import com.btlm.kotlingitproject.bean.dynamic.Dynamic
import com.btlm.kotlingitproject.bean.dynamic.MulDynamic
import com.btlm.kotlingitproject.mvp.contract.home.DynamicContract
import com.btlm.kotlingitproject.network.helper.RetrofitHelper
import com.btlm.kotlingitproject.rx.rxSchedulerHelper
import com.btlm.kotlingitproject.utils.JsonUtils
import com.google.gson.Gson
import com.google.gson.JsonParser
import io.reactivex.Flowable
import java.util.*
import javax.inject.Inject

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/17 18:00
 * * 描述:直播Presenter
 */

class DynamicPresenter @Inject constructor(private val retrofitHelper: RetrofitHelper) : RxPresenter<DynamicContract.View>(), DynamicContract.Presenter<DynamicContract.View> {

    override fun getMulDynamicData() {
        addSubscribe(Flowable.just(JsonUtils.readJson("dynamic.json"))
                .map {
                    val gson = Gson()
                    val `object` = JsonParser().parse(it).asJsonObject
                    val item = `object`.getAsJsonObject("data").getAsJsonArray("item")
                    val itemBeans = item.mapTo(ArrayList<Dynamic.ItemBean>()) { gson.fromJson(it, Dynamic.ItemBean::class.java) }
                    itemBeans
                }
                .map {
                    val mulDynamics = mutableListOf<MulDynamic>()
                    it.forEach { itemBean ->
                        val dynamic = MulDynamic(group = itemBean,
                                itemTypez = MulDynamic.TYPE_LV0,
                                lv = MulDynamic.TYPE_LV0,
                                flag = getRecent(itemBean).isNotEmpty())
                        dynamic.child = getRecent(itemBean)
                        mulDynamics += dynamic
                    }
                    mulDynamics
                }
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseSubscriber<List<MulDynamic>>(mView) {
                    override fun onSuccess(t: List<MulDynamic>) {
                        mView?.showMulDynamic(t)
                    }
                }))

    }

    private fun getRecent(itemBean: Dynamic.ItemBean): List<MulDynamic> {
        val recent = itemBean.recent
        val list = ArrayList<MulDynamic>()
        recent?.forEach {
            val dynamic = MulDynamic(itemTypez = MulDynamic.TYPE_LV1,
                    recent = it,
                    lv = MulDynamic.TYPE_LV1)
            list += dynamic
        }
        return list
    }
}
