package com.liangfeng.wanandroid.features.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import com.liangfeng.wanandroid.R
import com.liangfeng.wanandroid.bean.HomeArticleListRespBody
import com.liangfeng.wanandroid.features.login.RemoteDateManger
import com.liangfeng.wanandroid.network.Observers

/**
 * Created by mzf on 2018/9/20.
 * Email:liangfeng093@gmail.com
 * Desc:
 */
class HomeFragment : androidx.fragment.app.Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        var view = inflater?.inflate(R.layout.fragment_home, container, false)
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        RemoteDateManger.homeArticleList(0, object : Observers.HomeArticleListObserver() {
            override fun onNext(body: HomeArticleListRespBody) {
                if (body?.errorCode == -1) {//首页文章列表获取失败
                    LogUtils.e(TAG, ">>>>>>>首页文章列表获取失败:" + body)
                    ToastUtils.showLong("首页文章列表获取失败")
                } else {//登录成功
                    LogUtils.e(TAG, ">>>>>>>首页文章列表获取成功:" + body)
                    ToastUtils.showLong("首页文章列表获取成功")
//                    adapter = HomeArticleListAdapter(R.layout.item_home, body?.data?.datas)
                }
            }
        })


    }

}