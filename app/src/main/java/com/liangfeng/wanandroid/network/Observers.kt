package com.liangfeng.wanandroid.network

import android.util.Log
import com.liangfeng.wanandroid.base.DataContainer
import com.liangfeng.wanandroid.bean.*
import com.liangfeng.wanandroid.features.login.LoginResponseBody
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * Created by mzf on 2018/9/21.
 * Email:liangfeng093@gmail.com
 * Desc:Observer集合
 */
open class Observers {
    open class BaseObserver<T> : Observer<T> {
        open val TAG = this.javaClass.name
        override fun onComplete() {
        }

        override fun onSubscribe(d: Disposable) {
        }

        override fun onError(e: Throwable) {
            Log.e(TAG, "======*********===========>>>>>>>:EXCEPTION:" + e?.toString())
            e?.stackTrace?.forEach {
                Log.e(TAG, "EEEEEE>>>>>>>:" + it)
            }
        }

        override fun onNext(t: T) {
        }

    }


    open class LoginObserver : BaseObserver<LoginResponseBody>() {}
    open class HomeArticleListObserver : BaseObserver<DataContainer<HomeArticleListRespBody>>() {}
    open class HomeBannerObserver : BaseObserver<HomeBannerRespBody>() {}
    open class CommonWebsiteObserver : BaseObserver<CommonWebsiteRespBody>() {}
    open class Search4HotWordsObserver : BaseObserver<Search4HotWordRespBody>() {}
    open class SystemDataObserver : BaseObserver<SystemDataRespBody>() {}
    open class ArticlesUnderTheKnowledgeSystemObserver : BaseObserver<ArticlesUnderTheKnowledgeSystemRespBody>() {}
    open class NavigationObserver : BaseObserver<NavigationRespBody>() {}
    open class TypesOfSectionObserver : BaseObserver<TypesOfSectionRespBody>() {}
    open class ProjectListDataObserver : BaseObserver<ProjectListDataRespBody>() {}


}