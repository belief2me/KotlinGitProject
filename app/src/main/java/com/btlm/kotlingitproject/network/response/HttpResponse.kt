package com.btlm.kotlingitproject.network.response

/**
 * Created by Administrator on 2017/12/15.
 */
class HttpResponse<T> {
    // 数据
    var data: T? = null
    // 数据
    var result: T? = null
    // 信息
    var message : String? = null
    // 服务器状态
    var code : Int = 0
    // 只有全区排行 json 有这个字段
    // 数据
    var rank : T? = null
}
