package bjfu.em.se.pos.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import bjfu.em.se.pos.domain.payment.Payment;
import bjfu.em.se.pos.domain.payment.PaymentFactory;
import bjfu.em.se.pos.domain.payment.PaymentType;
import bjfu.em.se.pos.domain.salepricing.ISalePricingStrategy;
import bjfu.em.se.pos.domain.salepricing.PricingStrategyFactory;

/**
 * 销售类
 * 一个Sale对象对应现实中的一次购物
 * @author Roy
 *
 */
public class Sale {
	private boolean isComplete=false;
	private List<SalesLineItem> lineItems;
	private Payment payment=null;
	private Date date;
	private ISalePricingStrategy pricingStrategy ;
	public Sale() {
		lineItems=new ArrayList<SalesLineItem> ();
		date = new Date(); 
		PricingStrategyFactory factory=PricingStrategyFactory.getInstance();
		pricingStrategy = factory.createPricingStrategy();
	}
	/**
	 * 输入新的购买商品
	 * @param desc 商品信息
	 * @param qty 购买数量
	 */
	public void makeLineItem(ProductDescription desc, int qty) {
		SalesLineItem sl=new SalesLineItem(desc,qty);

		lineItems.add(sl);
	}
	
	/**
	 * 标记商品输入结束
	 */
	public void becomeComplete() {
		isComplete=true;		
	}
	/**
	 * 计算折后总价
	 * @return
	 */
	public int getDiscountedTotal() {
		return pricingStrategy.calculate(this);
	}
	public int makePayment(int amount, PaymentType type) {
		payment=PaymentFactory.createPayment(amount, type);
		return amount-getDiscountedTotal();
	}
	public List<SalesLineItem> getLineItems() {
		return Collections.unmodifiableList(lineItems);
	}
	public Payment getPayment() {
		return payment;
	}
	/**
	 * 计算找零
	 * @return
	 */
	public int getBalance() {
		return payment.getAmount()-getDiscountedTotal();
	}
	public Date getDate() {
		return date;
	}
	/**
	 * 计算折前总价	 * 
	 * @return
	 */
	public int getPreDiscountTotal() {
		int total=0;
		//JavaSE 5引入的集合遍历语法
		for (SalesLineItem item:lineItems){
			total+=item.getSubTotal();
		}
		return total;
	}
	public ISalePricingStrategy getPricingStrategy() {
		return pricingStrategy;
	}	
}
