package com.lyang.shopCar;

import java.util.Date;
import java.util.List;

/**
 * 商品类型类
 * @author yanglu
 *
 */
public class GoodsType {
	private String typeName;//商品分类名称
	private Double discount;//折扣信息
	private Date date;//打折日期
	/**
	 * @return the typeName
	 */
	public String getTypeName() {
		return typeName;
	}
	/**
	 * @param typeName the typeName to set
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	/**
	 * @return the discount
	 */
	public Double getDiscount() {
		return discount;
	}
	/**
	 * @param discount the discount to set
	 */
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	
	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	
	public GoodsType(String typeName, Double discount, Date date) {
		super();
		this.typeName = typeName;
		this.discount = discount;
		this.date = date;
	}
	public GoodsType() {
		super();
		// TODO Auto-generated constructor stub
	}
}
