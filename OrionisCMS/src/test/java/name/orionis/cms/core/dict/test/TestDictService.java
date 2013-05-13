package name.orionis.cms.core.dict.test;


import java.util.List;

import javax.persistence.TypedQuery;

import org.junit.Test;

import name.orionis.cms.SpringTest;
import name.orionis.cms.core.dict.model.Dictionary;

public class TestDictService extends SpringTest {
	
	
	@Test
	public void testOne(){
		Dictionary dict = new Dictionary();
		dict.setCode("DEMO_CODE");
		dict.setValue("1");
		dict.setExplaination("实例-A");
		
		dict.persist();
		
		TypedQuery<Dictionary> findDictionarysByCodeEquals = Dictionary.findDictionarysByCodeEquals("DEMO_CODE");
		
		List<Dictionary> list = findDictionarysByCodeEquals.getResultList();
		
		for(Dictionary d: list){
			System.out.println(d.getExplaination());
		}
	}
}
