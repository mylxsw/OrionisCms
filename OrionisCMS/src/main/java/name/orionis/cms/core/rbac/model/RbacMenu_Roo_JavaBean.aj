// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package name.orionis.cms.core.rbac.model;

import name.orionis.cms.core.rbac.model.RbacMenu;
import name.orionis.cms.core.rbac.model.RbacRole;
import name.orionis.cms.core.rbac.model.Status;

privileged aspect RbacMenu_Roo_JavaBean {
    
    public String RbacMenu.getMenuName() {
        return this.menuName;
    }
    
    public void RbacMenu.setMenuName(String menuName) {
        this.menuName = menuName;
    }
    
    public String RbacMenu.getUrl() {
        return this.url;
    }
    
    public void RbacMenu.setUrl(String url) {
        this.url = url;
    }
    
    public Status RbacMenu.getStatus() {
        return this.status;
    }
    
    public void RbacMenu.setStatus(Status status) {
        this.status = status;
    }
    
    public Long RbacMenu.getParentId() {
        return this.parentId;
    }
    
    public void RbacMenu.setParentId(Long parentId) {
        this.parentId = parentId;
    }
    
    public RbacRole RbacMenu.getRbacRole() {
        return this.rbacRole;
    }
    
    public void RbacMenu.setRbacRole(RbacRole rbacRole) {
        this.rbacRole = rbacRole;
    }
    
}
