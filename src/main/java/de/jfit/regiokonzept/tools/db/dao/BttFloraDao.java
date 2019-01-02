/*
 * Copyright (C) 2018 JFITServices, All rights reserved.
 */
package de.jfit.regiokonzept.tools.db.dao;

import java.util.List;

import de.jfit.regiokonzept.tools.db.entities.BttFloraEntity;
import de.jfit.regiokonzept.tools.db.entities.impl.BttFloraEntityImpl;

/**
 * Description:<br>
 *
 * @author minion69
 */
public interface BttFloraDao {

    BttFloraEntityImpl create(BttFloraEntityImpl bttFloraEntity);

    BttFloraEntityImpl find(BttFloraEntity bttFloraEntity);

    List<BttFloraEntityImpl> findAll();

    BttFloraEntityImpl findByName(BttFloraEntity bttFloraEntity);

    void remove(BttFloraEntityImpl bttFloraEntity);

    BttFloraEntityImpl update(BttFloraEntityImpl bttFloraEntity);

}
