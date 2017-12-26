package com.btlm.kotlingitproject.network.support


import com.orhanobut.logger.Logger
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody
import java.io.IOException
import java.util.*
/**
 * Logger拦截器
 * Created by zzq on 2016/12/5.
 */

class LoggerInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        Logger.d(String.format("Sending request %s on %s%n%s",
                request.url(), chain.connection(), request.headers()))
        val t1 = System.nanoTime()
        val response = chain.proceed(chain.request())
        val t2 = System.nanoTime()
        Logger.d(String.format(Locale.getDefault(), "Received response for %s in %.1fms%n%s",
                response.request().url(), (t2 - t1) / 1e6, response.headers()))
        val mediaType = response.body()?.contentType()
        val content = response.body()?.string()
        Logger.json(content)
        return response
                .newBuilder()
                .body(ResponseBody.create(mediaType, content))
                .build()
    }
}
