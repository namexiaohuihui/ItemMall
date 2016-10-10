package panhui.cn.bwf.home.bean;

import java.io.Serializable;

public class ProductBean  implements Serializable {
//颜色
	private ColorBean color;
	//男女装
	private GendarBean gendar;
//	部位
	private PartBean part;
//	商品库存
	private int productAmount;
//	商品ID
	private int productId;
	//商品图片
	private String productImage;
//
	private String productInfo;
	//商品名称也叫标题
	private String productName;
//	商品原价
	private float productPrice1;
	//商品现价
	private float productPrice2;
//	商品在购物车的数量
	private int count = 1;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public ColorBean getColor() {
		return color;
	}
	public void setColor(ColorBean color) {
		this.color = color;
	}
	public GendarBean getGendar() {
		return gendar;
	}
	public void setGendar(GendarBean gendar) {
		this.gendar = gendar;
	}
	public PartBean getPart() {
		return part;
	}
	public void setPart(PartBean part) {
		this.part = part;
	}
	public int getProductAmount() {
		return productAmount;
	}
	public void setProductAmount(int productAmount) {
		this.productAmount = productAmount;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductImage() {
		return productImage;
	}
	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}
	public String getProductInfo() {
		return productInfo;
	}
	public void setProductInfo(String productInfo) {
		this.productInfo = productInfo;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public float getProductPrice1() {
		return productPrice1;
	}
	public void setProductPrice1(float productPrice1) {
		this.productPrice1 = productPrice1;
	}
	public float getProductPrice2() {
		return productPrice2;
	}
	public void setProductPrice2(float productPrice2) {
		this.productPrice2 = productPrice2;
	}


	@Override
	public String toString() {
		return "ProductBean{" +
				"color=" + color +
				", gendar=" + gendar +
				", part=" + part +
				", productAmount=" + productAmount +
				", productId=" + productId +
				", productImage='" + productImage + '\'' +
				", productInfo='" + productInfo + '\'' +
				", productName='" + productName + '\'' +
				", productPrice1=" + productPrice1 +
				", productPrice2=" + productPrice2 +
				'}';
	}
}
