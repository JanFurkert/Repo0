/*
 * Copyright (C) 2018 JFITServices, All rights reserved.
 */
package de.jfit.regiokonzept.tools.db.dao;

import java.util.List;

import de.jfit.regiokonzept.tools.db.entities.ProjectEntity;
import de.jfit.regiokonzept.tools.db.entities.impl.ProjectEntityImpl;

/**
 * Description:<br>
 *
 * @author minion69
 */
public interface ProjectDao {

    ProjectEntityImpl create(ProjectEntityImpl projectEntity);

    ProjectEntityImpl find(ProjectEntity projectEntity);

    List<ProjectEntityImpl> findAll();

    ProjectEntityImpl findByName(ProjectEntity projectEntity);

    void remove(ProjectEntityImpl projectEntity);

    ProjectEntityImpl update(ProjectEntityImpl projectEntity);

}
