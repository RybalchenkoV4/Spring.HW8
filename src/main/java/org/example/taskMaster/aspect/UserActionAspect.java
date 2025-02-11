package org.example.taskMaster.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class UserActionAspect {

    @Before("@annotation(TrackUserAction)")
    public void logBeforeMethodExecution(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getMethod().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        Object [] args = joinPoint.getArgs();

        System.out.println("Before executing method: " + className + "." + methodName);
        System.out.println("Arguments: " + Arrays.toString(args));
    }

    @After("@annotation(TrackUserAction)")
    public void logAfterMethodExecution(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getMethod().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();

        System.out.println("After executing method: " + className + "." + methodName);
    }
}
