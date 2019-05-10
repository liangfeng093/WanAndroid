package com.liangfeng.wanandroid.features.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import com.liangfeng.wanandroid.R
import com.liangfeng.wanandroid.network.Observers
import kotlinx.android.synthetic.main.fragment_login.*

/**
 * Created by mzf on 2018/9/20.
 * Email:liangfeng093@gmail.com
 * Desc:
 */
class LoginFragment : androidx.fragment.app.Fragment() {
    val TAG = this.javaClass.name
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        var view = inflater?.inflate(R.layout.fragment_login, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_login?.setOnClickListener {
            RemoteDateManger?.login(LoginRequestBody("liangfeng093@gmail.com", "093liangfeng")
                    , object : Observers.LoginObserver() {
                override fun onNext(body: LoginResponseBody) {
                    if (body?.errorCode == -1) {//登录异常
                        LogUtils.e(TAG, ">>>>>>>登录异常:" + body)
                        ToastUtils.showLong("登录异常")
                    } else {//登录成功
                        LogUtils.e(TAG, ">>>>>>>登录成功:" + body)
                        ToastUtils.showLong("登录成功")
                    }
                }
            })
        }
    }
}