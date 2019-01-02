/*
 * Copyright (C) 2018 JFITServices, All rights reserved.
 */
package de.jfit.regiokonzept.tools.db.entities.impl;

import static de.jfit.regiokonzept.tools.db.entities.ProjectEntity.FIND_ALL;
import static de.jfit.regiokonzept.tools.db.entities.ProjectEntity.FIND_BY_NAME;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import de.jfit.regiokonzept.tools.db.entities.ProjectEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * Description:<br>
 *
 * @author minion69
 */
@Entity
@Table(name = "project", schema = "regiokonzeptsch", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
@NamedQueries({
    @NamedQuery(name = FIND_ALL, query = "SELECT projectEntity FROM ProjectEntityImpl projectEntity"),
    @NamedQuery(name = FIND_BY_NAME,
                query = "SELECT projectEntity FROM ProjectEntityImpl projectEntity WHERE projectEntity.name = :name")})
public class ProjectEntityImpl extends MappedSuperEntityImpl implements ProjectEntity {

    // https://vladmihalcea.com/the-best-way-to-map-a-onetomany-association-with-jpa-and-hibernate/
    @OneToMany(mappedBy = "projectEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    @Getter
    @Setter
    private List<BiotoptypEntityImpl> biotoptypEntityList = new LinkedList<BiotoptypEntityImpl>();

    @NotEmpty(message = "ProjectEntity.firma:NotEmpty")
    @Length(max = 50, message = "ProjectEntity.firma:Length")
    @Getter
    @Setter
    private String firma = "Regiokonzept GmbH & CoKG";

    @NotEmpty(message = "ProjectEntity.leitung:NotEmpty")
    @Length(max = 100, message = "ProjectEntity.leitung:Length")
    @Getter
    @Setter
    private String leitung;

    @NotEmpty(message = "ProjectEntity.name:NotEmpty")
    @Length(max = 100, message = "ProjectEntity.name:Length")
    @Getter
    @Setter
    private String name;

    public ProjectEntityImpl() {
        super();
    }

    public ProjectEntityImpl(final Long id) {
        super();
        this.id = id;
    }

    @Override
    public void addBiotoptypEntity(final BiotoptypEntityImpl biotoptypEntity) {
        if (!biotoptypEntityList.contains(biotoptypEntity)) {
            biotoptypEntityList.add(biotoptypEntity);
        }
    }

    @Override
    public void removeBiotoptypEntity(final BiotoptypEntityImpl biotoptypEntity) {
        if (biotoptypEntityList.contains(biotoptypEntity)) {
            biotoptypEntityList.remove(biotoptypEntity);
        }
    }

}
