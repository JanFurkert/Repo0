/*
 * Copyright (C) 2018 JFITServices, All rights reserved.
 */
package de.jfit.regiokonzept.tools.db.dao;

import java.util.List;

import de.jfit.regiokonzept.tools.db.entities.BiotoptypEntity;
import de.jfit.regiokonzept.tools.db.entities.impl.BiotoptypEntityImpl;

public interface BiotoptypDao {

    BiotoptypEntityImpl create(BiotoptypEntityImpl biotoptypEntity);

    BiotoptypEntityImpl find(BiotoptypEntity biotoptypEntity);

    List<BiotoptypEntityImpl> findAll();

    BiotoptypEntityImpl findByProjectIdNumber(BiotoptypEntity biotoptypEntity);

    void remove(BiotoptypEntityImpl biotoptypEntity);

    BiotoptypEntityImpl update(BiotoptypEntityImpl biotoptypEntity);

}
