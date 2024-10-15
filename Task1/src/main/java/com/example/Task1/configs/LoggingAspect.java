package com.example.Task1.configs;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
@Aspect
@Component
public class LoggingAspect {
	@Before("execution(* com.example.Task1.service.*.*(..))")
	public void logBefore(JoinPoint joinpoint) {
		System.out.println("Before ::: " + joinpoint.getSignature());
		
	}
	@After("execution(* com.example.Task1.service.*.*(..))")
	public void logAfter(JoinPoint joinpoint) {
		System.out.println("After ::: " + joinpoint.getSignature());
	}
	
}
