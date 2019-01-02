/*
 * Copyright (C) 2018 JFITServices, All rights reserved.
 */
package de.jfit.regiokonzept.tools.db.entities;

import de.jfit.regiokonzept.tools.db.entities.impl.BiotoptypEntityImpl;

/**
 * Description:<br>
 *
 * @author minion69
 */
public interface BttFloraEntity extends MappedSuperEntity {

    String FIND_ALL = "BTTFLORA.FIND_ALL";

    String FIND_BY_NAME = "BTTFLORA.FIND_BY_NAME";

    BiotoptypEntityImpl getBiotoptypEntity();

    Boolean getCharaktArt();

    String getName();

    Boolean getPlrelevArt();

    Long getProjectEntityId();

    void setBiotoptypEntity(BiotoptypEntityImpl biotoptypEntity);

    void setCharaktArt(Boolean charaktArt);

    void setName(String name);

    void setPlrelevArt(Boolean plrelevArt);

    void setProjectEntityId(Long projectEntityId);

}
