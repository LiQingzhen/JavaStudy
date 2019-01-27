package personal.throws_advice;

import org.springframework.aop.ThrowsAdvice;

import personal.throws_advice.myexception.PasswordException;
import personal.throws_advice.myexception.UsernameException;

public class MyThrowsAdvice implements ThrowsAdvice {

	// 查看源码：需要重写的方法
	public void afterThrowing(Exception ex){
		System.out.println(ex.getMessage());
	}
	
	// 捕获自定义异常
	public void afterThrowing(UsernameException ex){
		System.out.println(ex.getMessage());
	}
	
	public void afterThrowing(PasswordException ex){
		System.out.println(ex.getMessage());
	}
}
