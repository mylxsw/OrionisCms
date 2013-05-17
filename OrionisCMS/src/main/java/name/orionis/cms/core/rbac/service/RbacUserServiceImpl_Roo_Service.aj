// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package name.orionis.cms.core.rbac.service;

import java.util.List;
import name.orionis.cms.core.rbac.model.RbacUser;
import name.orionis.cms.core.rbac.service.RbacUserServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

privileged aspect RbacUserServiceImpl_Roo_Service {
    
    declare @type: RbacUserServiceImpl: @Service;
    
    declare @type: RbacUserServiceImpl: @Transactional;
    
    public long RbacUserServiceImpl.countAllRbacUsers() {
        return RbacUser.countRbacUsers();
    }
    
    public void RbacUserServiceImpl.deleteRbacUser(RbacUser rbacUser) {
        rbacUser.remove();
    }
    
    public RbacUser RbacUserServiceImpl.findRbacUser(Long id) {
        return RbacUser.findRbacUser(id);
    }
    
    public List<RbacUser> RbacUserServiceImpl.findAllRbacUsers() {
        return RbacUser.findAllRbacUsers();
    }
    
    public List<RbacUser> RbacUserServiceImpl.findRbacUserEntries(int firstResult, int maxResults) {
        return RbacUser.findRbacUserEntries(firstResult, maxResults);
    }
    
    public void RbacUserServiceImpl.saveRbacUser(RbacUser rbacUser) {
        rbacUser.persist();
    }
    
    public RbacUser RbacUserServiceImpl.updateRbacUser(RbacUser rbacUser) {
        return rbacUser.merge();
    }
    
}