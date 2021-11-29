package com.example.lifecycletest

import android.animation.ObjectAnimator
import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import kotlin.properties.Delegates

class MyAnim(val animation:ObjectAnimator?): DefaultLifecycleObserver {

    var canStartAnimator:Boolean by Delegates.observable(false){
        _,old,new->run{
            Log.i("test","old:"+old+" new:"+new)
            if(new){
                animation?.start()
            }else{
                animation?.pause()
            }
        }
    }
    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        if(canStartAnimator){
            Log.i("test","restar anim")
            animation?.resume()
        }
    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
        Log.i("test","stop anim")
        animation?.pause()
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        animation?.let {
            it.end()
            it.cancel()
        }
    }
}