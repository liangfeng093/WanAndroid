package com.liangfeng.wanandroid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * Created by mzf on 2018/7/13.
 * Email:liangfeng093@gmail.com
 * Desc:Fragment的基类，抽取一些重复操作
 */
abstract class BaseFragment<T> : Fragment(), IBaseFragmentView<T>, IBaseView<T> {
    var mPresenter: T? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater!!.inflate(getLayout(), container, false)
        return view
    }

    /**
     * 在onCreateView return view后，在onViewCreate函数中使用Id直接调用，onViewCreate会在onCreateView后执行
     * 直接使用id操作控件，不需要findViewByID
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initListener()
    }
}