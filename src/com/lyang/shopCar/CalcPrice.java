package com.lyang.shopCar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 计算价格类
 * @author yanglu
 *
 */
public class CalcPrice {
	private Map<String,String[]> initParam = new HashMap<String,String[]>();
	private List<GoodsType> goodsTypeList = new ArrayList<GoodsType>();   //商品类型
	private List<Goods> goodsList = new ArrayList<Goods>();               //商品集合
	private Coupon coupon;                                                //优惠卷
	private Date clearDate;                                               //结算日期
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
	
	public CalcPrice() {
		initParam.put("电子", new String[]{"ipad","iphone","显示器","笔记本电脑","键盘"});
		initParam.put("食品", new String[]{"面包","饼干","蛋糕","牛肉","鱼","蔬菜 "});
		initParam.put("日用品", new String[]{"餐巾纸","收纳箱","咖啡杯","雨伞"});
		initParam.put("酒类", new String[]{"啤酒","白酒","伏特加"});
	}
	/**
	 * 解析输入的文本
	 * @param inputFilePath
	 */
	public void analysisInput(String inputFilePath){
		try {
			File file = new File(inputFilePath);
			if(file.exists()){
				InputStreamReader read = new InputStreamReader(new FileInputStream(file),"UTF-8");//考虑到编码格式
				BufferedReader br = new BufferedReader(read);
				String data="";
				int type = 0;//按照空行分割，type分为3类，1，促销信息  2，商品信息  3，优惠卷信息
				int lineNumber=0;
				while((data = br.readLine())!=null){
					//读到空行
					if(data.length()==0){
						type++;
					}
					//读第一行的时候判断如果输入的信息里面如果没有促销信息，则直接跳转到下一项
					else if(data.indexOf("|")<0 && lineNumber==0){
						type++;
					}
					execFunction(type,data);
					lineNumber++;
				}
			}else{
				throw new Exception("该文件不存在");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	/**
	 * 根据类别选择执行函数
	 * @param type
	 * @param text
	 * @throws Exception
	 */
	private  void execFunction(int type,String text) throws Exception{
		switch(type){
			case 0:
				createDiscountGoodsType(text);
				break;
			case 1:
				createGoods(text);
				break;
			case 2:
				createCoupon(text);
				break;
			default:
				break;
		}
	}
	/**
	 * 根据读取到文本创建商品分类类
	 * @param text
	 * @throws Exception
	 */
	private void createDiscountGoodsType(String text) throws Exception{
		if(text!=null && text.length()>0){
			text = text.replaceAll("\\s*", "");
			String [] params = text.split("\\|");
			GoodsType goodsType = new GoodsType(params[2], Double.parseDouble(params[1]), sdf.parse(params[0]));
			goodsTypeList.add(goodsType);
		}
	}
	/**
	 * 根据读取到文本创建商品类
	 * @param text
	 */
	private void createGoods(String text){
		if(text!=null && text.length()>0){
			text = text.replaceAll("\\s*", "");
			String number = text.split("\\*")[0];
			String goodsName = text.split("\\*")[1].split(":")[0];
			String price = text.split("\\*")[1].split("\\:")[1];
			Goods goods = new Goods(goodsName, Double.parseDouble(price), Double.parseDouble(number));
			ok:
			for(String key:initParam.keySet()){
				String [] goodsArr = initParam.get(key);
				for(String name:goodsArr){
					if(goodsName.equals(name)){
						goods.setGoodsType(key);
						break ok;
					}
				}
			}
			goodsList.add(goods);
		}
	}
	/**
	 * 根据读取到文本创建优惠卷类
	 * @param text
	 * @throws ParseException
	 */
	private void createCoupon(String text) throws ParseException{
		if(text!=null && text.length()>0){
			String [] textArr = text.split("\\s+");
			if(textArr!=null && textArr.length>1){
				String date = textArr[0];
				Double totalPrice = Double.parseDouble(textArr[1]);
				Double discountPrice =  Double.parseDouble(textArr[2]);
				coupon = new Coupon(sdf.parse(date), totalPrice, discountPrice);
			}else{
				clearDate = sdf.parse(text);
			}
		}
	}
	/**
	 * 计算价格
	 */
	public String calc(){
		double totalPrice = 0.0;
		for(Goods good:goodsList){
			double discount = getDisCountByType(good.getGoodsType());
			double price =  good.getGoodsNum()*good.getGoodsPrice()*discount;
			totalPrice+=price;
		}
		if(coupon!=null && totalPrice>=coupon.getTotalPrice()){
			totalPrice -= coupon.getDiscountPrice();
		}
		DecimalFormat df = new DecimalFormat("#.00");   
		String result = df.format(totalPrice);
		return result;
	}
	/**
	 * 根据商品类别获取折扣信息
	 * @param type
	 * @return
	 */
	public Double getDisCountByType(String type){
		for(GoodsType goodsType:goodsTypeList){
			if(goodsType.getTypeName().equals(type) && goodsType.getDate().equals(clearDate)){
				return goodsType.getDiscount();
			}
		}
		return 1.0;
	}
	public static void main(String[] args) {
		String inputFilePath = Class.class.getClass().getResource("/").getPath()+"input2.txt";
		CalcPrice calcPrice = new CalcPrice();
		calcPrice.analysisInput(inputFilePath);
		String result = calcPrice.calc();
		System.out.println(result);
	}
}
