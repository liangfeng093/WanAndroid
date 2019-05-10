package com.liangfeng.wanandroid.features.home

import android.content.Context
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

/**
 * Created by mzf on 2018/9/28.
 * Email:liangfeng093@gmail.com
 * Desc:
 */
class HomeArticleViewHolder: BaseQuickAdapter<String, BaseViewHolder> {
    constructor(layoutResId: Int, data: MutableList<String>?) : super(layoutResId, data)

    override fun convert(helper: BaseViewHolder?, item: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}