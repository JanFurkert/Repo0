/*
 * Copyright (C) 2018 JFITServices, All rights reserved.
 */
package de.jfit.regiokonzept.tools.db.entities;

import java.util.List;

import de.jfit.regiokonzept.tools.db.entities.impl.BttFloraEntityImpl;
import de.jfit.regiokonzept.tools.db.entities.impl.ProjectEntityImpl;
import de.jfit.regiokonzept.tools.db.enums.WST;

/**
 * Description:<br>
 *
 * @author minion69
 */
public interface BiotoptypEntity extends MappedSuperEntity {

    String FIND_ALL = "BIOTOPTYP.FIND_ALL";

    String FIND_BY_PROJECTID_NUMBER = "BIOTOPTYP.FIND_BY_PROJECTID_NUMBER";

    void addBttFloraEntity(BttFloraEntityImpl bttFloraEntity);

    void delegateParentKeys();

    String getBemerkung();

    String getBttCode();

    List<BttFloraEntityImpl> getBttFloraEntityList();

    Boolean getGgbt();

    String getLrtCode();

    Integer getNumber();

    ProjectEntityImpl getProjectEntity();

    Long getProjectEntityId();

    WST getWst();

    void removeBttFloraEntity(BttFloraEntityImpl bttFloraEntity);

    void setBemerkung(String bemerkung);

    void setBttCode(String bttCode);

    void setBttFloraEntityList(List<BttFloraEntityImpl> bttFloraEntity);

    void setGgbt(Boolean ggbt);

    void setLrtCode(String lrtCode);

    void setNumber(Integer number);

    void setProjectEntity(ProjectEntityImpl projectEntity);

    void setWst(WST wst);

}
