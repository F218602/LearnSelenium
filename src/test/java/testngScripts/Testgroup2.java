package testngScripts;

import org.testng.annotations.Test;

public class Testgroup2 {

	@Test(groups = {"first"})
	public void Test7(){
		System.out.println("Test 7");
	}
	
	@Test(groups = {"first"})
	public void Test8(){
		System.out.println("Test 8");
	}
	
	@Test(groups = {"second"})
	public void Test9(){
		System.out.println("Test 9");
	}
	
	@Test(groups = {"second"})
	public void Test10(){
		System.out.println("Test 10");
	}
	
	@Test(groups = {"third"})
	public void Test11(){
		System.out.println("Test 11");
	}
	
	@Test(groups = {"third"})
	public void Test12(){
		System.out.println("Test 12");
	}
}
