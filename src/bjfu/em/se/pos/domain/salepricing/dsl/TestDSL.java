package bjfu.em.se.pos.domain.salepricing.dsl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import bjfu.em.se.pos.domain.Sale;

public class TestDSL {

	public static void main(String[] args) {
		Sale s=new Sale();
		ScriptEngineManager scriptEngineManager=new ScriptEngineManager();
		ScriptEngine scriptEngine=scriptEngineManager.getEngineByName("js");
		
		InputStream inputStream=TestDSL.class.getClassLoader().getResourceAsStream("dsl.js");
		try {
			Reader reader=new BufferedReader(new InputStreamReader(inputStream));
			scriptEngine.put("sale",s);
			scriptEngine.eval(reader);
			int total=(int)scriptEngine.get("total");
			System.out.println(total);
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		

	}

}
