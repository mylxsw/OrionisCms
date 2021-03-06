// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package name.orionis.cms.extensions.announce.model;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import name.orionis.cms.extensions.announce.model.Announce;
import org.springframework.transaction.annotation.Transactional;

privileged aspect Announce_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager Announce.entityManager;
    
    public static final EntityManager Announce.entityManager() {
        EntityManager em = new Announce().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long Announce.countAnnounces() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Announce o", Long.class).getSingleResult();
    }
    
    public static List<Announce> Announce.findAllAnnounces() {
        return entityManager().createQuery("SELECT o FROM Announce o", Announce.class).getResultList();
    }
    
    public static Announce Announce.findAnnounce(Long id) {
        if (id == null) return null;
        return entityManager().find(Announce.class, id);
    }
    
    public static List<Announce> Announce.findAnnounceEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Announce o", Announce.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void Announce.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void Announce.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Announce attached = Announce.findAnnounce(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void Announce.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void Announce.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public Announce Announce.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Announce merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
