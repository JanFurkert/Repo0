/*
 * Copyright (C) 2018 JFITServices, All rights reserved.
 */
package de.jfit.regiokonzept.tools.db.dao.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import de.jfit.regiokonzept.tools.db.dao.ProjectDao;
import de.jfit.regiokonzept.tools.db.entities.ProjectEntity;
import de.jfit.regiokonzept.tools.db.entities.impl.BiotoptypEntityImpl;
import de.jfit.regiokonzept.tools.db.entities.impl.ProjectEntityImpl;

/**
 * Description: Basic tests concerning 'crud'<br>
 *
 * @author minion69
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
public class ProjectDaoTest {

    static final String FIRMA = "Regiokonzept GmbH & CoKG";

    static final String LEITUNG = "Birgit Furkert";

    static final String NAME = "Stromtrasse Kelsterbach - Rödermark";

    @Autowired
    private ProjectDao projectDao;

    @Test
    public void create() {
        ProjectEntityImpl projectEntity = new ProjectEntityImpl();

        final BiotoptypEntityImpl biotoptypEntity = new BiotoptypEntityImpl();
        biotoptypEntity.setBttCode("10.510");
        biotoptypEntity.setNumber(1);
        biotoptypEntity.setProjectEntity(projectEntity);

        projectEntity.addBiotoptypEntity(biotoptypEntity);
        projectEntity.setFirma(FIRMA);
        projectEntity.setLeitung(LEITUNG);
        projectEntity.setName(NAME);

        // creates new table project if not existing and inserts project-values
        projectEntity = projectDao.create(projectEntity);

        // entity should be extended by an id
        assertNotNull(projectEntity.getId());

        // in test-szenario (profile 'test') we must have the child-entity
        assertNotNull(projectEntity.getBiotoptypEntityList().get(0).getId());
    }

    @Test
    public void delete() {
        ProjectEntityImpl projectEntity = new ProjectEntityImpl();
        projectEntity.setLeitung("Leitung");
        projectEntity.setName("Name");

        projectEntity = projectDao.create(projectEntity);
        final Long projectEntityId = projectEntity.getId();

        assertNotNull(projectEntityId);

        projectDao.remove(projectEntity);
        final ProjectEntity projectEntityRead = projectDao.find(new ProjectEntityImpl(projectEntityId));

        assertNull(projectEntityRead);
    }

    @Test
    public void findAll() {
        final List<ProjectEntityImpl> projectEntityList = projectDao.findAll();

        assertTrue(projectEntityList.size() > 0);
    }

    @Test
    public void findByName() {
        final ProjectEntity projectEntity = new ProjectEntityImpl();
        projectEntity.setName(NAME);
        final ProjectEntity projectEntityRead = projectDao.findByName(projectEntity);

        assertNotNull(projectEntityRead);
    }

    @Test
    public void read() {
        final ProjectEntity projectEntityRead = projectDao.find(new ProjectEntityImpl(1l));

        assertNotNull(projectEntityRead);
    }

    @Test
    public void update() {
        final String firma = "Regiokonzept";
        ProjectEntityImpl projectEntityRead = projectDao.find(new ProjectEntityImpl(1l));
        final Integer version = projectEntityRead.getVersion();
        assertNotEquals(firma, projectEntityRead.getFirma());

        projectEntityRead.setFirma(firma);
        projectEntityRead = projectDao.update(projectEntityRead);

        assertTrue(projectEntityRead.getVersion() == version + 1);
        assertEquals(firma, projectEntityRead.getFirma());
    }

}
