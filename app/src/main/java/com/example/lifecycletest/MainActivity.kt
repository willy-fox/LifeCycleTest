package com.example.lifecycletest

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lifecycletest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var mBind:ActivityMainBinding
    val anim:MyAnim by lazy {
        val objAnim=ObjectAnimator.ofFloat(mBind.viewRect,"alpha",1f,0f,1f).apply {
            setDuration(500)
            repeatMode=ValueAnimator.REVERSE
            repeatCount=ValueAnimator.INFINITE
        }
        MyAnim(objAnim)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBind=ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBind.root)
        lifecycle.addObserver(anim)
        initListener()
    }

    fun initListener(){
        mBind.tvBtnStart.setOnClickListener { anim.canStartAnimator=true }
        mBind.tvBtnStop.setOnClickListener { anim.canStartAnimator=false }
        mBind.tvBtnNext.setOnClickListener {
            val intent =Intent(this,SecondActivity::class.java)
            startActivity(intent)
        }
    }
}