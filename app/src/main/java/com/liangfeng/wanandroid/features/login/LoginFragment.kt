package com.liangfeng.wanandroid.features.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.SPUtils
import com.blankj.utilcode.util.ToastUtils
import com.liangfeng.wanandroid.R
import com.liangfeng.wanandroid.features.login.RemoteDateManger.Companion.login
import com.liangfeng.wanandroid.network.Observers
import kotlinx.android.synthetic.main.fragment_login.*

/**
 * Created by mzf on 2018/9/20.
 * Email:liangfeng093@gmail.com
 * Desc:
 */
class LoginFragment : Fragment() {

    companion object {
        val USER_NAME = "UserName"
        val PASS_WORD = "PassWord"
    }

    val TAG = this.javaClass.name

    var isSave = false


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


        var instance = SPUtils.getInstance()
        var strUserName = instance?.getString(USER_NAME, "")
        var strPwd = instance?.getString(PASS_WORD, "")
        if (!strUserName?.isEmpty()!!) {
            et_user_name?.setText(strUserName)
        }
        if (strPwd?.isEmpty()!!) {
            checkBox?.isChecked = false
        } else {
            checkBox?.isChecked = true
            et_pass_word?.setText(strPwd)
        }



        btn_login?.setOnClickListener {
            var userName = et_user_name?.text?.toString()?.trim()
            var passWord = et_pass_word?.text?.toString()?.trim()
            if (userName?.isEmpty()!!) {
                ToastUtils.showLong(resources?.getString(R.string.login_tip_1))
                return@setOnClickListener
            }
            if (passWord?.isEmpty()!!) {
                ToastUtils.showLong(resources?.getString(R.string.login_tip_2))
                return@setOnClickListener
            }
            login(userName, passWord)
        }

        checkBox?.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                LogUtils.e("isChecked:" + isChecked)
                isSave = isChecked
            }

        })
    }

    /**
     * 登录
     */
    fun login(userName: String, passWord: String) {
        RemoteDateManger?.login(LoginRequestBody(userName, passWord)
                , object : Observers.LoginObserver() {
            override fun onNext(body: LoginResponseBody) {
                if (body?.errorCode == -1) {//登录异常
                    LogUtils.e(TAG, ">>>>>>>登录异常:" + body)
                    ToastUtils.showLong("登录异常")
                } else {//登录成功
                    LogUtils.e(TAG, ">>>>>>>登录成功:" + body)
                    ToastUtils.showLong("登录成功")
                    rememberUserNameAndPwd(isSave, userName, passWord)
                    Navigation.findNavController(btn_login).navigate(R.id.action_loginFragment_to_homeFragment)
                }
            }
        })
    }

    /**
     * 记住账号密码
     */
    fun rememberUserNameAndPwd(isSave: Boolean, userName: String, passWord: String) {
        var instance = SPUtils.getInstance()
        if (isSave) {
            instance?.put(USER_NAME, userName)
            instance?.put(PASS_WORD, passWord)
        } else {
            instance?.remove(USER_NAME)
            instance?.remove(PASS_WORD)
        }
    }
}