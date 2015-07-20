package com.lyang.shopCar;

import java.util.Date;

/**
 * 优惠卷类
 * @author yanglu
 *
 */
public class Coupon {
	private Date enableDate;//优惠卷的有效期
	private Double totalPrice;//满totalPrice减discountPrice
	private Double discountPrice;
	/**
	 * @return the enableDate
	 */
	public Date getEnableDate() {
		return enableDate;
	}
	/**
	 * @param enableDate the enableDate to set
	 */
	public void setEnableDate(Date enableDate) {
		this.enableDate = enableDate;
	}
	/**
	 * @return the totalPrice
	 */
	public Double getTotalPrice() {
		return totalPrice;
	}
	/**
	 * @param totalPrice the totalPrice to set
	 */
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	/**
	 * @return the discountPrice
	 */
	public Double getDiscountPrice() {
		return discountPrice;
	}
	/**
	 * @param discountPrice the discountPrice to set
	 */
	public void setDiscountPrice(Double discountPrice) {
		this.discountPrice = discountPrice;
	}
	public Coupon(Date enableDate, Double totalPrice, Double discountPrice) {
		super();
		this.enableDate = enableDate;
		this.totalPrice = totalPrice;
		this.discountPrice = discountPrice;
	}
	public Coupon() {
		super();
	}
	public Double couponForGoods(Date clearDate,Double tPrice){
		if(clearDate.before(enableDate) && tPrice>this.totalPrice){
			tPrice -= discountPrice;
		}
		return tPrice;
	}
}
