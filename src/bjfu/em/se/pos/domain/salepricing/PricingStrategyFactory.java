package bjfu.em.se.pos.domain.salepricing;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import bjfu.em.se.pos.domain.salepricing.dsl.JavascriptDSLPricingStrategy;

public final class PricingStrategyFactory {
	private static PricingStrategyFactory instance=new PricingStrategyFactory();
	
	private final ScriptEngineManager engineManager=new ScriptEngineManager();
	private final ScriptEngine scriptEngine=engineManager.getEngineByName("js");

	private String name="无折扣";
	private String description="无折扣";
	private String script="var total=sale.preDiscountTotal;";
	/**
	 * 轮流创建不同的定价策略实例
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
