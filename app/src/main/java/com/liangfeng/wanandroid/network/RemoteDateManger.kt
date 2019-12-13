package com.greatwall.multimedia.network

import android.util.Log
import com.blankj.utilcode.util.LogUtils
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @Description:
 * @Author:zhifeng.mo
 * @Date:2019/12/13
 */
class RemoteDateManger  {
    //私有构造,单例模式
    private constructor() {
        initRetrofit()
    }

    /**
     * 初始化Retrofit实例
     */
    private fun initRetrofit() {
        //拦截器（打印网络请求log）
        var logInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor(HttpLogger())
        logInterceptor.level = HttpLoggingInterceptor.Level.BASIC
//        logInterceptor.level = HttpLoggingInterceptor.Level.BODY
        var httpInterceptor = HttpInterceptor()
        var okHttpClient: OkHttpClient = OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)//设置超时时间
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(logInterceptor)//添加拦截器
            .addInterceptor(httpInterceptor)//添加请求头
            .build()

        retrofit = Retrofit.Builder()
//                .baseUrl(RetrofitService.testBaseURL)
            .baseUrl(RetrofitService.BaseURL)
            .addConverterFactory(GsonConverterFactory.create())//配置gson转换
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//配置rxjava转换
            .client(okHttpClient)
            .build()
            .create(RetrofitService::class.java)//创建接口实例
    }

    /**
     * 日志拦截器
     */
    class HttpLogger : HttpLoggingInterceptor.Logger {
        override fun log(message: String?) {
            Log.e("HttpLogInfo", message)
        }

    }

    class HttpInterceptor : Interceptor {

        val TAG = this.javaClass.name

        override fun intercept(chain: Interceptor.Chain?): okhttp3.Response {
//            LogUtils.e(TAG, ">>>>>>>request.url:" + chain?.request()?.url())
            var builder = chain?.request()?.newBuilder()
            //添加请求头
            var request = builder?.addHeader("content-type", "text/html;charset=UTF-8")
                ?.build()
//            var request = builder?.addHeader("content-type", "text/html;charset=UTF-8")?.build()
            LogUtils.e(TAG, ">>>>>>>request.method:" + chain?.request()?.method())
            LogUtils.e(TAG, ">>>>>>>request.body:" + chain?.request()?.body()?.contentType())
            return chain?.proceed(request)!!
        }
    }

    //相当于静态方法
    companion object {
        var retrofit: RetrofitService? = null
        private var remoteDataManager: RemoteDateManger? = null
        val TAG = this.javaClass.name
        //使用单例模式，避免重复创建
        fun getInstance(): RemoteDateManger? {
            if (remoteDataManager == null) {
                synchronized(RemoteDateManger::class.java) {
                    //双锁
                    if (remoteDataManager == null) {
                        remoteDataManager = RemoteDateManger()
                    }
                }
            }
            return remoteDataManager
        }

        /**
         * 登录
         */
        suspend fun login(username: String, password: String) {
            val TAG = "RemoteDateManger_login"
            val loginResponseBody = retrofit?.login(username, password)
            LogUtils.e(TAG, ">>>>>>>loginResponseBody:" + loginResponseBody)
        }

        /**
         * 首页banner
         */
        fun homeBanner() {
            val TAG = "RemoteDateManger_homeBanner"
            val homeBannerRespBody = retrofit?.homeBanner()
            LogUtils.e(TAG, ">>>>>>>homeBannerRespBody:" + homeBannerRespBody)
        }
    }
}