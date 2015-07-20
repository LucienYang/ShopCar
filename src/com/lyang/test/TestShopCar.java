package com.lyang.test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import com.lyang.shopCar.CalcPrice;

public class TestShopCar {
	@Test
	public void testInput1(){
		String inputFilePath = Class.class.getClass().getResource("/").getPath()+"input.txt";
		CalcPrice calcPrice = new CalcPrice();
		calcPrice.analysisInput(inputFilePath);//先解析输入的文本
		String result = calcPrice.calc();//得出计算的结果
		assertEquals("3083.60", result);
	}
	@Test
	public void testInput2(){
		String inputFilePath = Class.class.getClass().getResource("/").getPath()+"input2.txt";
		CalcPrice calcPrice = new CalcPrice();
		calcPrice.analysisInput(inputFilePath);//先解析输入的文本
		String result = calcPrice.calc();//得出计算的结果
		assertEquals("43.54", result);
	}
}
