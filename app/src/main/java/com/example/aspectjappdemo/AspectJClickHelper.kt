package com.example.aspectjappdemo

import android.os.SystemClock
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.*

@Aspect
class AspectJClickHelper {
    companion object {
        const val TAG = "AspectJClickHelper"
        const val POINTCUT = "clickPointcut()"
        const val CLICK_MIN_INTERVAL = 300L
        var lastClickTime = 0L
    }

    //    @Pointcut("execution(void android.view.View.OnClickListener+.onClick(android.view.View))")
    @Pointcut("execution(void com.example.aspectjappdemo..*.on*Click*(..))")
    fun clickPointcut() {}

    @Before(POINTCUT)
    fun beforeClick(joinPoint: JoinPoint) {
        println("$TAG, beforeClick, declaringTypeName: ${joinPoint.signature.declaringTypeName}")
        println("$TAG, beforeClick, name: ${joinPoint.signature.name}")
        println("$TAG, beforeClick, longString: ${joinPoint.signature.toLongString()}")
        println("$TAG, beforeClick, shortString: ${joinPoint.signature.toShortString()}")
        println("$TAG, beforeClick, target: ${joinPoint.target}")
        println("$TAG, beforeClick, this: ${joinPoint.`this`}")
        println("$TAG, beforeClick, args: ${joinPoint.args}")
    }

    @Around(POINTCUT)
    fun clickAround(proceedingJoinPoint: ProceedingJoinPoint) {
        println("$TAG, clickAround")
        val elapsedTime = SystemClock.elapsedRealtime()
        if (elapsedTime - lastClickTime > CLICK_MIN_INTERVAL) {
            lastClickTime = elapsedTime
            proceedingJoinPoint.proceed()
        }
    }

    @AfterReturning(value = POINTCUT, returning = "returnVal")
    fun afterReturning(returnVal: Any?) {
        println("$TAG, afterReturning, $returnVal")
    }
}