package bjfu.em.se.pos.domain.salepricing.dsl;

import javax.script.Bindings;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

import bjfu.em.se.pos.domain.Sale;
import bjfu.em.se.pos.domain.salepricing.ISalePricingStrategy;

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
		Bindings bindings=scriptEngine.createBindings();
		bindings.put("sale", s);
		try {
			scriptEngine.eval(script,bindings);
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
