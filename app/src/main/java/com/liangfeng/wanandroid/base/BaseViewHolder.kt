package com.liangfeng.wanandroid.base

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.SparseArray
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.liangfeng.wanandroid.ImageLoader

/**
 * Created by mzf on 2018/8/16.
 * Email:liangfeng093@gmail.com
 * Desc:封装RecyclerView的ViewHolder基类
 */
open class BaseViewHolder<T : ImageLoader> : RecyclerView.ViewHolder {

    private var context: Context? = null
    //SparseArray代替hashMap节省内存
    private var views: SparseArray<View>? = null
    //整个item对应的View
    private var itemRootView: View? = null
    //
    private var imageLoader: T? = null

    constructor(itemView: View?, context: Context?) : super(itemView) {
        this.context = context
        this.itemRootView = itemView
    }


    /**
     * 缓存item中的控件，避免重复加载view
     */
    fun <T : View> retrieveView(viewId: Int): T {
        var view = views?.get(viewId)
        if (view == null) {
            view = itemRootView?.findViewById<T>(viewId)
            views?.put(viewId, view)
        }
        return view as T
    }


    /**
     * 设置文本
     */
    fun setText(viewId: Int, text: String): BaseViewHolder<T> {
        var view = retrieveView<TextView>(viewId)
        view?.setText(text)
        return this
    }

    /**
     * 设置图片
     */
    fun setImg(viewId: Int, picPath: String): BaseViewHolder<T> {
        var view = retrieveView<ImageView>(viewId)
        //使用glide加载

        return this
    }

    /**
     * 设置图片加载器
     */
    fun setImageLoader(imageLoader: T) {
        this.imageLoader = imageLoader
    }
}