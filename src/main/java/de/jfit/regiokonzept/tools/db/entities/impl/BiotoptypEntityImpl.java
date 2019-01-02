/*
 * Copyright (C) 2018 JFITServices, All rights reserved.
 */
package de.jfit.regiokonzept.tools.db.entities.impl;

import static de.jfit.regiokonzept.tools.db.entities.BiotoptypEntity.FIND_ALL;
import static de.jfit.regiokonzept.tools.db.entities.BiotoptypEntity.FIND_BY_PROJECTID_NUMBER;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import de.jfit.regiokonzept.tools.db.entities.BiotoptypEntity;
import de.jfit.regiokonzept.tools.db.enums.WST;
import lombok.Getter;
import lombok.Setter;

/**
 * Description:<br>
 *
 * @author minion69
 */
@Entity
@Table(name = "biotoptyp",
        schema = "regiokonzeptsch",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"projectentityid", "number"})})
@NamedQueries({
    @NamedQuery(name = FIND_ALL, query = "SELECT biotoptypEntity FROM BiotoptypEntityImpl biotoptypEntity"),
    @NamedQuery(name = FIND_BY_PROJECTID_NUMBER,
                query = "SELECT biotoptypEntity FROM BiotoptypEntityImpl biotoptypEntity WHERE biotoptypEntity.projectEntity.id = :projectEntityId AND biotoptypEntity.number = :number")})
public class BiotoptypEntityImpl extends MappedSuperEntityImpl implements BiotoptypEntity {

    @Length(max = 1000, message = "BiotoptypEntity.bemerkung:Length")
    @Getter
    @Setter
    private String bemerkung;

    @NotEmpty(message = "BiotoptypEntity.bttCode:NotEmpty")
    @Length(max = 50, message = "BiotoptypEntity.bttCode:Length")
    @Getter
    @Setter
    private String bttCode;

    // https://vladmihalcea.com/the-best-way-to-map-a-onetomany-association-with-jpa-and-hibernate/
    @OneToMany(mappedBy = "biotoptypEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    @Getter
    @Setter
    private List<BttFloraEntityImpl> bttFloraEntityList = new LinkedList<BttFloraEntityImpl>();

    @Getter
    @Setter
    private Boolean ggbt;

    @Length(max = 10, message = "BiotoptypEntity.lrtCode:Length")
    @Getter
    @Setter
    private String lrtCode;

    @NotNull(message = "BiotoptypEntity.number:NotNull")
    @Getter
    @Setter
    private Integer number;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "projectentityid")
    @NotNull(message = "BiotoptypEntity.projectEntity:NotNull")
    @Getter
    @Setter
    private ProjectEntityImpl projectEntity;

    @Enumerated(EnumType.STRING)
    @Getter
    @Setter
    private WST wst;

    public BiotoptypEntityImpl() {
        super();
    }

    public BiotoptypEntityImpl(final Long id) {
        super();
        this.id = id;
    }

    @Override
    public void addBttFloraEntity(final BttFloraEntityImpl bttFloraEntity) {
        if (!bttFloraEntityList.contains(bttFloraEntity)) {
            bttFloraEntityList.add(bttFloraEntity);
        }
    }

    @PrePersist
    @Override
    public void delegateParentKeys() {
        if (!bttFloraEntityList.isEmpty() && getProjectEntity() != null && getProjectEntity().getId() != null) {
            bttFloraEntityList.forEach(entity -> entity.setProjectEntityId(getProjectEntity().getId()));
        }
    }

    public Long getProjectEntityId() {
        return getProjectEntity().getId();
    }

    @Override
    public void removeBttFloraEntity(final BttFloraEntityImpl bttFloraEntity) {
        if (bttFloraEntityList.contains(bttFloraEntity)) {
            bttFloraEntityList.remove(bttFloraEntity);
        }
    }

}
