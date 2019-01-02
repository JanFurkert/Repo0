/*
 * Copyright (C) 2018 JFITServices, All rights reserved.
 */
package de.jfit.regiokonzept.tools.db.entities.impl;

import static de.jfit.regiokonzept.tools.db.entities.BttFloraEntity.FIND_ALL;
import static de.jfit.regiokonzept.tools.db.entities.BttFloraEntity.FIND_BY_NAME;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import de.jfit.regiokonzept.tools.db.entities.BttFloraEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "bttflora", schema = "regiokonzeptsch", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
@NamedQueries({
    @NamedQuery(name = FIND_ALL, query = "SELECT bttFloraEntity FROM BttFloraEntityImpl bttFloraEntity"),
    @NamedQuery(name = FIND_BY_NAME,
                query = "SELECT bttFloraEntity FROM BttFloraEntityImpl bttFloraEntity WHERE bttFloraEntity.name = :name")
})
public class BttFloraEntityImpl extends MappedSuperEntityImpl implements BttFloraEntity {

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "biotoptypEntityId")
    @NotNull(message = "BttFloraEntity.biotoptypEntity:NotNull")
    @Getter
    @Setter
    private BiotoptypEntityImpl biotoptypEntity;

    @Getter
    @Setter
    private Boolean charaktArt;

    @NotEmpty(message = "BttFloraEntity.name:NotEmpty")
    @Length(max = 100, message = "BttFloraEntity.name:Length")
    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private Boolean plrelevArt;

    @NotNull(message = "BttFloraEntity.projectEntityId:NotNull")
    @Getter
    @Setter
    private Long projectEntityId;

    public BttFloraEntityImpl() {
        super();
    }

    public BttFloraEntityImpl(final Long id) {
        super();
        this.id = id;
    }

}
