/*
 * Copyright (C) 2018 JFITServices, All rights reserved.
 */
package de.jfit.regiokonzept.tools.db.entities;

import java.util.List;

import de.jfit.regiokonzept.tools.db.entities.impl.BiotoptypEntityImpl;

public interface ProjectEntity extends MappedSuperEntity {

    String FIND_ALL = "PROJECT.FIND_ALL";

    String FIND_BY_NAME = "PROJECT.FIND_BY_NAME";

    void addBiotoptypEntity(BiotoptypEntityImpl biotoptypEntity);

    List<BiotoptypEntityImpl> getBiotoptypEntityList();

    String getFirma();

    String getLeitung();

    String getName();

    void removeBiotoptypEntity(BiotoptypEntityImpl biotoptypEntity);

    void setBiotoptypEntityList(List<BiotoptypEntityImpl> BiotoptypEntityImplList);

    void setFirma(String firma);

    void setLeitung(String leitung);

    void setName(String name);

}
