package bjfu.em.se.pos.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import bjfu.em.se.pos.domain.accounting.DemoSAPAccountingAdapter;
import bjfu.em.se.pos.domain.accounting.IAccountingAdapter;

/**
 * 超市类
 * @author Roy
 *
 */
public class Store {
	private String name;
	private List<Sale> completedSales;
	private IAccountingAdapter accountingAdapter;
	private static Store instance=new Store("学清路美廉美");
	
	private Store(String name) {
		this.name=name;
		completedSales=new ArrayList<Sale>();
	}
	
	
	public void setAccountingAdapter(IAccountingAdapter accountingAdapter) {
		this.accountingAdapter = accountingAdapter;
	}

	public IAccountingAdapter getAccountingAdapter() {
		return accountingAdapter;
	}


	/**
	 * 添加已处理完毕的销售
	 * @param sale 
	 */
	public void addSale(Sale sale){
		completedSales.add(sale);
	}
	
	/**
	 * 返回已处理的销售列表
	 * @return
	 */
	public List<Sale> getSales() {
		return Collections.unmodifiableList(completedSales);
	}
	
	public static Store getInstance() {
		return instance;
	}
	
	//Java的静态初始化语法,在Java第一次载入类的class文件时自动执行
	static {
		//使用金蝶财务系统
		//instance.setAccountingAdapter(new DemoKingdeeAccountingAdapter());
		instance.setAccountingAdapter(new DemoSAPAccountingAdapter());
	}
}
