/*
 * Copyright (C) 2018 JFITServices, All rights reserved.
 */
package de.jfit.regiokonzept.tools.db.entities;

public interface UserEntity {

    String FIND_ALL = "USER.FIND_ALL";

    /**
     * @return the eMail
     */
    String getEmail();

    /**
     * @return the forename
     */
    String getForename();

    /**
     * @return the id
     */
    Long getId();

    /**
     * @return the password
     */
    String getPassword();

    /**
     * @return the surename
     */
    String getSurename();

    /**
     * @return the username
     */
    String getUsername();

    /**
     * @return the version
     */
    Integer getVersion();

    /**
     * @param eMail
     *            the eMail to set
     */
    void setEmail(String eMail);

    /**
     * @param forename
     *            the forename to set
     */
    void setForename(String forename);

    /**
     * @param password
     *            the password to set
     */
    void setPassword(String password);

    /**
     * @param surename
     *            the surename to set
     */
    void setSurename(String surename);

    /**
     * @param username
     *            the username to set
     */
    void setUsername(String username);

}