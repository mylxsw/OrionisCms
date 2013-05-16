// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package name.orionis.cms.core.rbac.model;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import name.orionis.cms.core.rbac.model.RbacRole;
import org.springframework.transaction.annotation.Transactional;

privileged aspect RbacRole_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager RbacRole.entityManager;
    
    public static final EntityManager RbacRole.entityManager() {
        EntityManager em = new RbacRole().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long RbacRole.countRbacRoles() {
        return entityManager().createQuery("SELECT COUNT(o) FROM RbacRole o", Long.class).getSingleResult();
    }
    
    public static List<RbacRole> RbacRole.findAllRbacRoles() {
        return entityManager().createQuery("SELECT o FROM RbacRole o", RbacRole.class).getResultList();
    }
    
    public static RbacRole RbacRole.findRbacRole(Long id) {
        if (id == null) return null;
        return entityManager().find(RbacRole.class, id);
    }
    
    public static List<RbacRole> RbacRole.findRbacRoleEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM RbacRole o", RbacRole.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void RbacRole.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void RbacRole.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            RbacRole attached = RbacRole.findRbacRole(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void RbacRole.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void RbacRole.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public RbacRole RbacRole.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        RbacRole merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}