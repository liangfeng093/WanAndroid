package com.liangfeng.wanandroid.features.login

import android.util.Log
import com.blankj.utilcode.util.LogUtils
import com.liangfeng.wanandroid.bean.*
import com.liangfeng.wanandroid.network.Observers
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by mzf on 2018/7/13.
 * Email:liangfeng093@gmail.com
 * Desc:远程数据管理者。主要管理与web端的数据交互
 */
class RemoteDateManger {
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
//        logInterceptor.level = HttpLoggingInterceptor.Level.BASIC
        logInterceptor.level = HttpLoggingInterceptor.Level.BODY
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
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//配置rxjava转换
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
        var remoteDataManager: RemoteDateManger? = null
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
        fun login(body: LoginRequestBody, observer: Observers.LoginObserver) {
            val TAG = "RemoteDateManger_login"
            LogUtils.e(TAG, ">>>>>>>LoginRequestBody:" + body)
            retrofit?.login(body?.username, body?.password)
                    ?.onErrorReturn {
                        //打印异常信息
                        LogUtils.e(TAG, ">>>>>>>EXCEPTION:" + it)
                        it?.stackTrace?.forEach { element ->
                            LogUtils.e(TAG, ">>>>>>>EXCEPTION_STACK:" + element)
                        }
                        //出现异常时，返回这个对象
                        LoginResponseBody(null, -1, "error")
                    }
                    ?.subscribeOn(Schedulers.io())//IO线程订阅
                    ?.observeOn(AndroidSchedulers.mainThread())//主线程回调
                    ?.subscribe(observer)
        }

        /**
         * 首页文章列表
         */
        fun homeArticleList(pageNumber: Int, observer: Observers.HomeArticleListObserver) {
            val TAG = "RemoteDateManger_homeArticleList"
            retrofit?.homeArticleList(pageNumber)
                    ?.subscribeOn(Schedulers.io())//IO线程订阅
                    ?.observeOn(AndroidSchedulers.mainThread())//主线程回调
                    ?.subscribe(observer)
        }

        /**
         * 首页banner
         */
        fun homeBanner(observer: Observers.HomeBannerObserver) {
            val TAG = "RemoteDateManger_homeBanner"
            retrofit?.homeBanner()
                    ?.onErrorReturn {
                        //打印异常信息
                        LogUtils.e(TAG, ">>>>>>>EXCEPTION:" + it)
                        it?.stackTrace?.forEach { element ->
                            LogUtils.e(TAG, ">>>>>>>EXCEPTION_STACK:" + element)
                        }
                        //出现异常时，返回这个对象
                        HomeBannerRespBody(null, -1, "error")
                    }
                    ?.subscribeOn(Schedulers.io())//IO线程订阅
                    ?.observeOn(AndroidSchedulers.mainThread())//主线程回调
                    ?.subscribe(observer)
        }

        /**
         * 常用网站
         */
        fun commonWebsite(observer: Observers.CommonWebsiteObserver) {
            val TAG = "RemoteDateManger_commonWebsite"
            retrofit?.commonWebsite()
                    ?.onErrorReturn {
                        //打印异常信息
                        LogUtils.e(TAG, ">>>>>>>EXCEPTION:" + it)
                        it?.stackTrace?.forEach { element ->
                            LogUtils.e(TAG, ">>>>>>>EXCEPTION_STACK:" + element)
                        }
                        //出现异常时，返回这个对象
                        CommonWebsiteRespBody(null, -1, "error")
                    }
                    ?.subscribeOn(Schedulers.io())//IO线程订阅
                    ?.observeOn(AndroidSchedulers.mainThread())//主线程回调
                    ?.subscribe(observer)

        }

        /**
         * 搜索热词
         */
        fun search4HotWords(observer: Observers.Search4HotWordsObserver) {
            val TAG = "RemoteDateManger_commonWebsite"
            retrofit?.search4HotWords()
                    ?.onErrorReturn {
                        //打印异常信息
                        LogUtils.e(TAG, ">>>>>>>EXCEPTION:" + it)
                        it?.stackTrace?.forEach { element ->
                            LogUtils.e(TAG, ">>>>>>>EXCEPTION_STACK:" + element)
                        }
                        //出现异常时，返回这个对象
                        Search4HotWordRespBody(null, -1, "error")
                    }
                    ?.subscribeOn(Schedulers.io())//IO线程订阅
                    ?.observeOn(AndroidSchedulers.mainThread())//主线程回调
                    ?.subscribe(observer)

        }

        /**
         * 体系数据
         */
        fun systemData(observer: Observers.SystemDataObserver) {
            val TAG = "RemoteDateManger_commonWebsite"
            retrofit?.systemData()
                    ?.onErrorReturn {
                        //打印异常信息
                        LogUtils.e(TAG, ">>>>>>>EXCEPTION:" + it)
                        it?.stackTrace?.forEach { element ->
                            LogUtils.e(TAG, ">>>>>>>EXCEPTION_STACK:" + element)
                        }
                        //出现异常时，返回这个对象
                        SystemDataRespBody(-1, "error", null)
                    }
                    ?.subscribeOn(Schedulers.io())//IO线程订阅
                    ?.observeOn(AndroidSchedulers.mainThread())//主线程回调
                    ?.subscribe(observer)

        }

        /**
         * 知识体系下的文章
         */
        fun articlesUnderTheKnowledgeSystem(pageNumber: Int, cid: String, observer: Observers.ArticlesUnderTheKnowledgeSystemObserver) {
            val TAG = "RemoteDateManger_articlesUnderTheKnowledgeSystem"
            retrofit?.articlesUnderTheKnowledgeSystem(pageNumber, cid)
                    ?.onErrorReturn {
                        //打印异常信息
                        LogUtils.e(TAG, ">>>>>>>EXCEPTION:" + it)
                        it?.stackTrace?.forEach { element ->
                            LogUtils.e(TAG, ">>>>>>>EXCEPTION_STACK:" + element)
                        }
                        //出现异常时，返回这个对象
                        ArticlesUnderTheKnowledgeSystemRespBody(null, -1, "error")

                    }
                    ?.subscribeOn(Schedulers.io())//IO线程订阅
                    ?.observeOn(AndroidSchedulers.mainThread())//主线程回调
                    ?.subscribe(observer)

        }

        /**
         * 导航
         */
        fun navigation(observer: Observers.NavigationObserver) {
            val TAG = "RemoteDateManger_navigation"
            retrofit?.navigation()
                    ?.onErrorReturn {
                        //打印异常信息
                        LogUtils.e(TAG, ">>>>>>>EXCEPTION:" + it)
                        it?.stackTrace?.forEach { element ->
                            LogUtils.e(TAG, ">>>>>>>EXCEPTION_STACK:" + element)
                        }
                        //出现异常时，返回这个对象
                        NavigationRespBody(-1, "error", null)

                    }
                    ?.subscribeOn(Schedulers.io())//IO线程订阅
                    ?.observeOn(AndroidSchedulers.mainThread())//主线程回调
                    ?.subscribe(observer)

        }

        /**
         * 项目分类
         */
        fun typesOfSection(observer: Observers.TypesOfSectionObserver) {
            val TAG = "RemoteDateManger_typesOfSection"
            retrofit?.typesOfSection()
                    ?.onErrorReturn {
                        //打印异常信息
                        LogUtils.e(TAG, ">>>>>>>EXCEPTION:" + it)
                        it?.stackTrace?.forEach { element ->
                            LogUtils.e(TAG, ">>>>>>>EXCEPTION_STACK:" + element)
                        }
                        //出现异常时，返回这个对象
                        TypesOfSectionRespBody(-1, "error", null)

                    }
                    ?.subscribeOn(Schedulers.io())//IO线程订阅
                    ?.observeOn(AndroidSchedulers.mainThread())//主线程回调
                    ?.subscribe(observer)

        }

        /**
         * 项目列表数据
         */
        fun projectListData(pageNumber: Int, cid: String, observer: Observers.ProjectListDataObserver) {
            retrofit?.projectListData(pageNumber, cid)
                    ?.onErrorReturn {
                        //打印异常信息
                        LogUtils.e(TAG, ">>>>>>>EXCEPTION:" + it)
                        it?.stackTrace?.forEach { element ->
                            LogUtils.e(TAG, ">>>>>>>EXCEPTION_STACK:" + element)
                        }
                        //出现异常时，返回这个对象
                        ProjectListDataRespBody(null, -1, "error")

                    }
                    ?.subscribeOn(Schedulers.io())//IO线程订阅
                    ?.observeOn(AndroidSchedulers.mainThread())//主线程回调
                    ?.subscribe(observer)

        }

        /**
         * 搜索
         */
//        fun search() {
//            retrofit?.search()
//        }
    }
}