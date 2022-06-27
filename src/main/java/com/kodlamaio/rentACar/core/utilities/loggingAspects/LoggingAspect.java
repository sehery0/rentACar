package com.kodlamaio.rentACar.core.utilities.loggingAspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;



@Aspect
@Component
public class LoggingAspect {
	@Before("execution( * com.kodlamaio.rentACar.business.concretes.BrandManager.deleteById(int))")
	public void logBefore() {
		System.out.println("log mesajı");
	}
	//brandmanager'daki deletebyid için
		@Before("cutForBrand()")
		public void BeforeAdviceLogging() {
			System.out.println("before advice run. brand manager delete is executed");
		}
		
		@After("cutForBrand()")
		public void AfterAdviceLogging() {
			System.out.println("after advice run. brand manager delete is executed");
		}
		
		@Pointcut("execution(* com.kodlamaio.rentACar.business.concretes.BrandManager.deleteById(int))")
		public void cutForBrand() {};


		@Pointcut("execution(* com.kodlamaio.rentACar.business.concretes.BrandManager.*(..))")
		public void allBrandManagerMethods() {};
		
		
		//it is more readable
		//within parametreyi sınıf adı olarak alırken execution method adı olarak alıyor.
		@Pointcut("within(com.kodlamaio.rentACar.business.concretes.BrandManager)")
		public void allBrandManagerMethodsWithin() {};	
		
		@Pointcut("execution(* get*())")
		public void allGetters() {
			System.out.println("get metodu çalıştı");
		};	
		
		@Around("cutForBrand()")
		public void around(ProceedingJoinPoint proceedingJoinPoint)
		{
			try {
				System.out.println("before advice");
				proceedingJoinPoint.proceed();
				System.out.println("after returning");
			} catch (Throwable e) {
				System.out.println("after throwing");
				e.printStackTrace();
			}
			System.out.println("after finally");
		}
		
		

		/*
		 * @Before("execution(* com.kodlamaio.rentACar.api.*.delete(..))") public void
		 * logBefore(JoinPoint joinPoint) { System.out.println(joinPoint.getArgs()[0]);
		 * System.out.println(joinPoint.getTarget());
		 * System.out.println(joinPoint.getSignature());
		 * System.out.println("before logged...."); }
		 * 
		 * 
		 * @After("execution(* com.kodlamaio.rentACar.api.*.*(..))") public void
		 * logAfter() { System.out.println("getall executed...."); }
		 * 
		 */

	}


