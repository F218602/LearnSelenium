package testngScripts;

import org.testng.annotations.Test;

public class Testgroup1 {

	@Test(groups = {"first"})
	public void Test1(){
		System.out.println("Test 1");
	}
	
	@Test(groups = {"first"})
	public void Test2(){
		System.out.println("Test 2");
	}
	
	@Test(groups = {"second"})
	public void Test3(){
		System.out.println("Test 3");
	}
	
	@Test(groups = {"second"})
	public void Test4(){
		System.out.println("Test 4");
	}
	
	@Test(groups = {"third"})
	public void Test5(){
		System.out.println("Test 5");
	}
	
	@Test(groups = {"third"})
	public void Test6(){
		System.out.println("Test 6");
	}
}
