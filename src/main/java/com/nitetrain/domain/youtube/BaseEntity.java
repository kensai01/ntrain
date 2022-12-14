package com.nitetrain.domain.youtube;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

public abstract class BaseEntity implements Serializable {

    private long id;
    private String createdBy;
    private String updatedBy;
    private Date created;
    private Date updated;

    public BaseEntity() {}

    public BaseEntity(long id, String createdBy, String updatedBy, Date created, Date updated) {
        this.id = id;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.created = created;
        this.updated = updated;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
    //    @PrePersist
    //    protected void onCreate(){
    //        created = new Date();
    //    }
    //
    //    @PreUpdate
    //    protected void onUpdate(){
    //        updated = new Date();
    //    }
}
