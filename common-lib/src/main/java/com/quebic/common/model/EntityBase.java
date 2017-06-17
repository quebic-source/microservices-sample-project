package com.quebic.common.model;

import org.springframework.data.annotation.Id;

public abstract class EntityBase{

    public static final Integer STATUS_INACTIVE = 0;
    public static final Integer STATUS_ACTIVE = 1;
    public static final Integer STATUS_DELETED = -1;
    
    @Id
    private String id;
    
    private Integer status = 1; //default status

    public EntityBase() {
    }

    public EntityBase(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof EntityBase)) {
            return false;
        }
        EntityBase other = (EntityBase) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "EntityBase{" +
                "id=" + id +
                '}';
    }


}
