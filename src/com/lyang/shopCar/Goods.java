package com.lyang.shopCar;
/**
 * 商品类
 * @author yanglu
 *
 */
public class Goods {
	private String goodsName;//商品名称
	private Double goodsPrice;//商品单价
	private Double goodsNum;//商品数量
	private String goodsType;//商品所属类型
	/**
	 * @return the goodsName
	 */
	public String getGoodsName() {
		return goodsName;
	}
	/**
	 * @param goodsName the goodsName to set
	 */
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	/**
	 * @return the goodsPrice
	 */
	public Double getGoodsPrice() {
		return goodsPrice;
	}
	/**
	 * @param goodsPrice the goodsPrice to set
	 */
	public void setGoodsPrice(Double goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	/**
	 * @return the goodsNum
	 */
	public Double getGoodsNum() {
		return goodsNum;
	}
	/**
	 * @param goodsNum the goodsNum to set
	 */
	public void setGoodsNum(Double goodsNum) {
		this.goodsNum = goodsNum;
	}
	
	/**
	 * @return the goodsType
	 */
	public String getGoodsType() {
		return goodsType;
	}
	/**
	 * @param goodsType the goodsType to set
	 */
	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}
	public Goods(String goodsName, Double goodsPrice, Double goodsNum) {
		super();
		this.goodsName = goodsName;
		this.goodsPrice = goodsPrice;
		this.goodsNum = goodsNum;
	}
	public Goods() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
