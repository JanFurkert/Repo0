/*
 * Copyright (C) 2018 JFITServices, All rights reserved.
 */
package de.jfit.regiokonzept.tools.db.entities.impl;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@MappedSuperclass
public class MappedSuperEntityImpl {

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created")
    private Date creationDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update")
    private Date lastUpdateDate;

    /**
     * @return the creationDate
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * @return the lastUpdateDate
     */
    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }
}
