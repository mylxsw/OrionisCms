// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package name.orionis.cms.extensions.content.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import name.orionis.cms.extensions.content.model.News;

privileged aspect News_Roo_Jpa_Entity {
    
    declare @type: News: @Entity;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long News.id;
    
    @Version
    @Column(name = "version")
    private Integer News.version;
    
    public Long News.getId() {
        return this.id;
    }
    
    public void News.setId(Long id) {
        this.id = id;
    }
    
    public Integer News.getVersion() {
        return this.version;
    }
    
    public void News.setVersion(Integer version) {
        this.version = version;
    }
    
}
