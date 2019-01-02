/*
 * Copyright (C) 2018 JFITServices, All rights reserved.
 */
package de.jfit.regiokonzept.tools.db.entities.impl;

import static de.jfit.regiokonzept.tools.db.entities.UserEntity.FIND_ALL;
import static de.jfit.regiokonzept.tools.db.entities.UserEntity.FIND_BY_EMAIL;
import static de.jfit.regiokonzept.tools.db.entities.UserEntity.FIND_BY_NAME;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import de.jfit.regiokonzept.tools.db.entities.UserEntity;

/**
 * Description:<br>
 *
 * @author minion69
 */
@Entity
@Table(name = "user",
        schema = "regiokonzeptsch",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"email"}), @UniqueConstraint(columnNames = {"name"})})
@NamedQueries({
    @NamedQuery(name = FIND_ALL, query = "SELECT userEntity FROM UserEntityImpl userEntity"),
    @NamedQuery(name = FIND_BY_EMAIL,
                query = "SELECT userEntity FROM UserEntityImpl userEntity WHERE userEntity.email = :email"),
    @NamedQuery(name = FIND_BY_NAME,
                query = "SELECT userEntity FROM UserEntityImpl userEntity WHERE userEntity.name = :name")
})
public class UserEntityImpl extends MappedSuperEntityImpl implements UserEntity {

    @NotEmpty(message = "UserEntity.email:NotEmpty")
    @Length(min = 3, max = 100, message = "UserEntity.email:Length")
    private String email;

    @Length(max = 25, message = "UserEntity.forename:Length")
    private String forename;

    @NotEmpty(message = "UserEntity.name:NotEmpty")
    @Length(max = 50, message = "UserEntity.name:Length")
    private String name;

    @Length(max = 50, message = "UserEntity.password:Length")
    private String password;

    @Length(max = 50, message = "UserEntity.surename:Length")
    private String surename;

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
     * @return the username
     */
    @Override
    public String getName() {
        return name;
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
     * @param username
     *            the username to set
     */
    @Override
    public void setName(String username) {
        this.name = username;
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

}
