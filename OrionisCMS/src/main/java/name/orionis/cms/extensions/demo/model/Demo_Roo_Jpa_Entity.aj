// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package name.orionis.cms.extensions.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import name.orionis.cms.extensions.demo.model.Demo;

privileged aspect Demo_Roo_Jpa_Entity {
    
    declare @type: Demo: @Entity;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long Demo.id;
    
    @Version
    @Column(name = "version")
    private Integer Demo.version;
    
    public Long Demo.getId() {
        return this.id;
    }
    
    public void Demo.setId(Long id) {
        this.id = id;
    }
    
    public Integer Demo.getVersion() {
        return this.version;
    }
    
    public void Demo.setVersion(Integer version) {
        this.version = version;
    }
    
}
