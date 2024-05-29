package com.example.employee_management_system;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
public class AuditLoggingAspect {

    @AfterReturning("execution(* com.example.employee_management_system.service.*.*(..))")
    public void logAfterReturning(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        String methodName = joinPoint.getSignature().getName();
        System.out.println("Method: " + methodName+ " called at " + LocalDateTime.now() + " with arguments: " + args);
    }
}
