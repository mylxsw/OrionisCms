// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package name.orionis.cms.extensions.content.model;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import name.orionis.cms.extensions.content.model.Category;
import org.springframework.transaction.annotation.Transactional;

privileged aspect Category_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager Category.entityManager;
    
    public static final EntityManager Category.entityManager() {
        EntityManager em = new Category().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long Category.countCategorys() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Category o", Long.class).getSingleResult();
    }
    
    public static List<Category> Category.findAllCategorys() {
        return entityManager().createQuery("SELECT o FROM Category o", Category.class).getResultList();
    }
    
    public static Category Category.findCategory(Long id) {
        if (id == null) return null;
        return entityManager().find(Category.class, id);
    }
    
    public static List<Category> Category.findCategoryEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Category o", Category.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void Category.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void Category.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Category attached = Category.findCategory(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void Category.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void Category.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public Category Category.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Category merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
