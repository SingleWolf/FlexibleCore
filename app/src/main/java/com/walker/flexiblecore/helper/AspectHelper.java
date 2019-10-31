package com.walker.flexiblecore.helper;

import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AspectHelper {
    public static final String TAG="AspectHelper";

    @Around("execution(* android.app.Activity.setContentView(..))")
    public void getContentViewTime(ProceedingJoinPoint point) throws Throwable {
        String name = point.getSignature().toShortString();
        long time = System.currentTimeMillis();
        point.proceed();
        Log.i(TAG, name + " cost: " + (System.currentTimeMillis() - time));
    }

    @Before("execution(* com.walker.flexiblecore.ui.summary.SummaryFragment.listSummary(..))")
    public void testAspectBefore(JoinPoint point){
        Log.i(TAG, point.getSignature().getName()+"-before ");
    }

    @AfterReturning(value = "execution(* com.walker.flexiblecore.ui.summary.SummaryFragment.getLayoutId(..))", returning = "num")
    public void testAspectAfterReturning(int num) {
        //适用于需要获取到返回值的情况
        Log.i(TAG, "getLayoutId-num:" + num);
    }

    @AfterThrowing(value = "execution(* com.walker.flexiblecore.ui.summary.SummaryFragment.listSummary(..))", throwing = "exception")
    public void testAspectAfterThrowing(Exception exception) {
        //适用于收集和监控异常信息
        Log.e(TAG, "AfterThrowing-exception:" + exception.getMessage());
    }

    // 在test2()方法内
    @Pointcut("withincode(* com.walker.flexiblecore.ui.MainActivity.test2(..))")
    public void invoke2() {
    }

    // 调用test()方法的时候
    @Pointcut("call(* com.walker.flexiblecore.ui.MainActivity.test(..))")
    public void invoke() {
    }

    // 同时满足前面的条件，即在test2()方法内调用test()方法的时候才切入
    @Pointcut("invoke() && invoke2()")
    public void invokeOnlyIn2() {
    }

    @Before("invokeOnlyIn2()")
    public void beforeInvokeOnlyIn2(JoinPoint joinPoint) {
        String key = joinPoint.getSignature().toString();
        Log.i(TAG, "beforeInvokeOnlyIn2: " + key);
    }
}
