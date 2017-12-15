package com.btlm.kotlingitproject.network.response

/**
 * Created by Administrator on 2017/12/15.
 */
class HttpListResponse<T> {
    // 数据
    var data : List<T>? = null
    // 数据
    var result : List<T>? = null
    // 信息
    var message : String? = null
    // 服务器状态
    var code : Int = 0

}