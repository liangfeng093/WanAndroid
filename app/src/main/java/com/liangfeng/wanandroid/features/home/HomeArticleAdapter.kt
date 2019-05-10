package com.liangfeng.wanandroid.features.home

import android.content.Context
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.liangfeng.wanandroid.R
import com.liangfeng.wanandroid.bean.HomeArticleListRespBody

/**
 * Created by mzf on 2018/9/28.
 * Email:liangfeng093@gmail.com
 * Desc:
 */
class HomeArticleAdapter: BaseQuickAdapter<HomeArticleListRespBody.Article, BaseViewHolder> {
    constructor(layoutResId: Int, data: MutableList<HomeArticleListRespBody.Article>?) : super(layoutResId, data)

    override fun convert(helper: BaseViewHolder?, item: HomeArticleListRespBody.Article?) {
        helper?.setText(R.id.tv_title,item?.title)
        helper?.setText(R.id.tv_time,item?.niceDate)
        helper?.setText(R.id.tv_author,item?.author)
    }


}