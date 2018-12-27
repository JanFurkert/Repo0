/*
 * Copyright (C) 2018 JFITServices, All rights reserved.
 */
package de.jfit.regiokonzept.tools.db.entities.impl;

import static de.jfit.regiokonzept.tools.db.entities.UserEntity.FIND_ALL;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import de.jfit.regiokonzept.tools.db.entities.UserEntity;

/**
 * Description:<br>
 *
 * @author minion69
 */
@Entity
@Table(name = "user", schema = "regiokonzeptsch", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
@NamedQuery(name = FIND_ALL, query = "SELECT userEntity FROM UserEntityImpl userEntity")
public class UserEntityImpl extends MappedSuperEntityImpl implements UserEntity {

    private String email;

    private String forename;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String password;

    private String surename;

    private String username;

    @Version
    private Integer version;

    public UserEntityImpl() {
        super();
    }

    public UserEntityImpl(Long id) {
        super();
        this.id = id;
    }

    /**
     * @return the eMail
     */
    @Override
    public String getEmail() {
        return email;
    }

    /**
     * @return the forename
     */
    @Override
    public String getForename() {
        return forename;
    }

    /**
     * @return the id
     */
    @Override
    public Long getId() {
        return id;
    }

    /**
     * @return the password
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * @return the surename
     */
    @Override
    public String getSurename() {
        return surename;
    }

    /**
     * @return the username
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * @return the version
     */
    @Override
    public Integer getVersion() {
        return version;
    }

    /**
     * @param eMail
     *            the eMail to set
     */
    @Override
    public void setEmail(String eMail) {
        this.email = eMail;
    }

    /**
     * @param forename
     *            the forename to set
     */
    @Override
    public void setForename(String forename) {
        this.forename = forename;
    }

    /**
     * @param password
     *            the password to set
     */
    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @param surename
     *            the surename to set
     */
    @Override
    public void setSurename(String surename) {
        this.surename = surename;
    }

    /**
     * @param username
     *            the username to set
     */
    @Override
    public void setUsername(String username) {
        this.username = username;
    }
}
