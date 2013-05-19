package name.orionis.cms.core.dict.test;


import java.util.List;

import javax.annotation.Resource;
import javax.persistence.TypedQuery;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import name.orionis.cms.SpringTest;
import name.orionis.cms.core.dict.model.Dictionary;
import name.orionis.cms.core.rbac.model.RbacRole;
import name.orionis.cms.core.rbac.model.RbacUser;
import name.orionis.cms.core.rbac.model.Status;
import name.orionis.cms.core.rbac.service.RbacUserService;
import name.orionis.cms.utils.Encrypt;

public class TestDictService extends SpringTest {

	@Resource
	private RbacUserService userService;
	@Test
	
	public void testTwo(){
		RbacUser user = new RbacUser();
		user.setRealName("testtest4");
		user.setUserName("testtest4");
		user.setPassword(Encrypt.encryptPassword("testtest4", "testtest4"));
//		RbacRole role = new RbacRole();
//		role.setRoleName("vip");
//		user.setRbacRole(role);
		user.setRbacRole(RbacRole.findRbacRole(2L));
		user.setStatus(Status.ENABLED);
		
		user.persist();
		
		List<RbacUser> users = RbacUser.findAllRbacUsers();
		for(RbacUser u: users){
			System.out.println(u.getUserName());
		}
	}
	
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
