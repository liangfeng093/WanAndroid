package com.liangfeng.wanandroid

import android.widget.ImageView

/**
 * Created by mzf on 2018/8/16.
 * Email:liangfeng093@gmail.com
 * Desc:图片加载器接口，可以灵活的切换图片加载框架
 */
interface ImageLoader {
    fun loadImg(imgView: ImageView, picPath: String)
}