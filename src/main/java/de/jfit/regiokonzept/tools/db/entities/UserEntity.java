/*
 * Copyright (C) 2018 JFITServices, All rights reserved.
 */
package de.jfit.regiokonzept.tools.db.entities;

public interface UserEntity extends MappedSuperEntity {

    String FIND_ALL = "USER.FIND_ALL";

    String FIND_BY_EMAIL = "USER.FIND_BY_EMAIL";

    String FIND_BY_NAME = "USER.FIND_BY_NAME";

    /**
     * @return the eMail
     */
    String getEmail();

    /**
     * @return the forename
     */
    String getForename();

    /**
     * @return the name
     */
    String getName();

    /**
     * @return the password
     */
    String getPassword();

    /**
     * @return the surename
     */
    String getSurename();

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
     * @param name
     *            the name to set
     */
    void setName(String name);

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

}