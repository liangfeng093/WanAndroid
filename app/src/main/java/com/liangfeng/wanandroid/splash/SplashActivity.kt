package com.liangfeng.wanandroid.splash

import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.liangfeng.wanandroid.MainActivity
import com.liangfeng.wanandroid.R
import com.liangfeng.wanandroid.app.App
import kotlinx.android.synthetic.main.activity_splash.*

/**
 * Created by mzf on 2018/9/20.
 * Email:liangfeng093@gmail.com
 * Desc:
 */
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        var anim = ObjectAnimator.ofFloat(rl_root, "alpha", 0f, 0.9f)
        anim?.setDuration(3000)
        anim?.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {
            }

            override fun onAnimationEnd(animation: Animator?) {

                var intent = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(intent)
                finish()

                /*if (App.hasToken) {
                    var intent = Intent(this@SplashActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                    finish()
                }*/
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationStart(animation: Animator?) {
            }
        })
        anim?.start()

    }
}