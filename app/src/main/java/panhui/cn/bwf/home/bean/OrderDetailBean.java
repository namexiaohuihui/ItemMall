package panhui.cn.bwf.home.bean;

/**添加订单详情:
 * 需要添加訂單对象以及本身的信息
 * Created by Administrator on 2016/6/17.
 */
public class OrderDetailBean {


    private OrderBean book;
//商品数量
    private int amount;
//    商品详情id
    private int detailId = 1;
//    商品id
    private int productId;
//    商品价格（现价）
    private float productPrice;


    public OrderBean getBook() {
        return book;
    }

    public void setBook(OrderBean book) {
        this.book = book;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getDetailId() {
        return detailId;
    }

    public void setDetailId(int detailId) {
        this.detailId = detailId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }
}
