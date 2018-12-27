/*
 * Copyright (C) 2018 JFITServices, All rights reserved.
 */
package de.jfit.regiokonzept.tools.db.dao;

import java.util.List;

import de.jfit.regiokonzept.tools.db.entities.UserEntity;
import de.jfit.regiokonzept.tools.db.entities.impl.UserEntityImpl;

public interface UserDao {

    UserEntityImpl create(UserEntityImpl userEntity);

    UserEntityImpl find(UserEntity userEntity);

    List<UserEntityImpl> findAll();

    void remove(UserEntityImpl userEntity);

    UserEntityImpl update(UserEntityImpl userEntity);

}