package com.liangfeng.wanandroid.features.home

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.liangfeng.wanandroid.BaseFragment
import com.liangfeng.wanandroid.R
import com.liangfeng.wanandroid.bean.HomeArticleListRespBody
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * Created by mzf on 2018/9/20.
 * Email:liangfeng093@gmail.com
 * Desc:
 */
class HomeFragment : BaseFragment<String>() {


    var drawerToggle: ActionBarDrawerToggle? = null

    var adapter: HomeArticleAdapter? = null
    var articles = mutableListOf<HomeArticleListRespBody.Article>()

//    override fun setPresenter(presenter: String) {
//    }

    override fun getLayout(): Int {
        return R.layout.fragment_home
    }

    override fun initData() {


        tool_bar?.setTitle("玩android")
        tool_bar?.setTitleTextColor(Color.WHITE)
        tool_bar?.contentInsetStartWithNavigation

        drawerToggle = ActionBarDrawerToggle(activity, drawer_layout, tool_bar, R.string.app_name, R.string.app_name)

        //通过下面这句实现toolbar和Drawer的联动：如果没有这行代码，箭头是不会随着侧滑菜单的开关而变换的（或者没有箭头），
        // 可以尝试一下，不影响正常侧滑
        drawerToggle?.syncState()
        //去掉侧滑的默认图标（动画箭头图标），也可以选择不去，
        //不去的话把这一行注释掉或者改成true，然后把toolbar.setNavigationIcon注释掉就行了
        drawerToggle?.isDrawerIndicatorEnabled = true

        adapter = HomeArticleAdapter(R.layout.item_article, articles)
        rv_home?.layoutManager = LinearLayoutManager(activity)
        rv_home?.adapter = adapter

    }

    override fun initListener() {
        tool_bar?.setNavigationOnClickListener {
            if (drawer_layout?.isDrawerOpen(GravityCompat.START)!!) {
                drawer_layout.closeDrawer(GravityCompat.START)
            } else {
                drawer_layout.openDrawer(GravityCompat.START)
            }
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

/*

        RemoteDateManger.homeArticleList(0, object : Observers.HomeArticleListObserver() {
            override fun onNext(t: DataContainer<HomeArticleListRespBody>) {
                var body = t?.data
                if (TextUtils.equals(t?.errorCode, "-1")) {
                    LogUtils.e(TAG, ">>>>>>>首页文章列表获取失败:" + body)
                    ToastUtils.showLong("首页文章列表获取失败")
                } else {
                    LogUtils.e(TAG, ">>>>>>>首页文章列表获取成功:" + body)
                    ToastUtils.showLong("首页文章列表获取成功")
                    articles?.clear()
                    articles?.addAll(body?.datas!!)
                    adapter?.notifyDataSetChanged()
                }

            }
        })
*/


    }

    override fun emptyContent() {
    }

    override fun loading() {
    }

    override fun loadSuccess() {
    }

    override fun loadFail() {
    }

    override fun content() {
    }

    override fun networkException() {
    }
}