package bjfu.em.se.pos.domain.salepricing;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import bjfu.em.se.pos.domain.salepricing.dsl.JavascriptDSLPricingStrategy;

/**
 * 打折策略工厂类
 * 
 * @author Roy
 *
 */
public final class PricingStrategyFactory {
	private static PricingStrategyFactory instance=new PricingStrategyFactory();
	
	private final ScriptEngineManager engineManager;
	private final ScriptEngine scriptEngine;

	private String name="无折扣";
	private String description="无折扣";
	private String script="var total=sale.preDiscountTotal;";
	
	/**
	 * 创建的定价策略实例
	 * 在本demo中，每次创建一个新的JavascriptDSLPricingStrategy实例
	 * @return
	 */
	public ISalePricingStrategy createPricingStrategy() {
		return new JavascriptDSLPricingStrategy(
				name,
				description,
				script,
				scriptEngine);
		
	}
	
	private PricingStrategyFactory(){
		//创建engineManager并获取Javascript引擎
		engineManager=new ScriptEngineManager();
		scriptEngine=engineManager.getEngineByName("js");	
	}
	
	public static PricingStrategyFactory getInstance() {
		return instance;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
	}
	
	

}
