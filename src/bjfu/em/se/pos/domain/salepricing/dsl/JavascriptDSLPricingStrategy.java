package bjfu.em.se.pos.domain.salepricing.dsl;

import javax.script.Bindings;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

import bjfu.em.se.pos.domain.Sale;
import bjfu.em.se.pos.domain.salepricing.ISalePricingStrategy;

/**
 * 使用javascript脚本计算打折后金额
 * 参考bjfu.em.se.pos.domain.salepricing.PricingStrategyFactory
 * @author Roy
 *
 */
public class JavascriptDSLPricingStrategy implements ISalePricingStrategy{
	private final String script;
	private final ScriptEngine scriptEngine;
	private final String name;
	private final String description;
	public JavascriptDSLPricingStrategy(
			String name,
			String description,
			String script, ScriptEngine scriptEngine) {
		this.script = script;
		this.scriptEngine = scriptEngine;
		this.name=name;
		this.description=description;
	}
	@Override
	public int calculate(Sale s) {
		int result=0;
		//每次执行脚本时使用一个独立的变量空间，以免相互干扰
		Bindings bindings=scriptEngine.createBindings();
		bindings.put("sale", s);
		try {
			//使用javascript引擎和新变量空间执行脚本
			scriptEngine.eval(script,bindings);
			//total变量可能是Integer也可能是Double类型，因此统一直接转换为string后再重新转换为double取整
			double res=Double.parseDouble(bindings.get("total").toString());			
			result=(int)Math.round(res);
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	@Override
	public String getName() {
		return name;
	}
	@Override
	public String getDescription() {
		return description;
	}
	
	
}
