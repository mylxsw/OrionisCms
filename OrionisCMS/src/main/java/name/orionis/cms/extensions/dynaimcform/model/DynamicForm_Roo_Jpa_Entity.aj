// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package name.orionis.cms.extensions.dynaimcform.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import name.orionis.cms.extensions.dynaimcform.model.DynamicForm;

privileged aspect DynamicForm_Roo_Jpa_Entity {
    
    declare @type: DynamicForm: @Entity;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long DynamicForm.id;
    
    @Version
    @Column(name = "version")
    private Integer DynamicForm.version;
    
    public Long DynamicForm.getId() {
        return this.id;
    }
    
    public void DynamicForm.setId(Long id) {
        this.id = id;
    }
    
    public Integer DynamicForm.getVersion() {
        return this.version;
    }
    
    public void DynamicForm.setVersion(Integer version) {
        this.version = version;
    }
    
}
