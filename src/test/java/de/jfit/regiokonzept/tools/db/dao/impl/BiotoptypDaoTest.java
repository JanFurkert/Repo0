/*
 * Copyright (C) 2019 JFITServices, All rights reserved.
 */
package de.jfit.regiokonzept.tools.db.dao.impl;

import static de.jfit.regiokonzept.tools.db.dao.impl.BttFloraDaoTest.CHARAKT_ART;
import static de.jfit.regiokonzept.tools.db.dao.impl.BttFloraDaoTest.PLRELEV_ART;
import static de.jfit.regiokonzept.tools.db.dao.impl.ProjectDaoTest.LEITUNG;
import static org.junit.jupiter.api.Assertions.assertFalse;
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

import de.jfit.regiokonzept.tools.db.dao.BiotoptypDao;
import de.jfit.regiokonzept.tools.db.entities.BiotoptypEntity;
import de.jfit.regiokonzept.tools.db.entities.impl.BiotoptypEntityImpl;
import de.jfit.regiokonzept.tools.db.entities.impl.BttFloraEntityImpl;
import de.jfit.regiokonzept.tools.db.entities.impl.ProjectEntityImpl;
import de.jfit.regiokonzept.tools.db.enums.WST;

/**
 * Description: Basic tests concerning 'crud'<br>
 *
 * @author minion69
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
public class BiotoptypDaoTest {

    static final String BEMERKUNG = "erzeugt durch Test";

    static final String BTT_CODE = "D4.600";

    static final Boolean GGBT = Boolean.TRUE;

    static final String LRT_CODE = "6431";

    static final Integer NUMBER = new Integer(1);

    static final WST WST_C = WST.C;

    @Autowired
    private BiotoptypDao biotoptypDao;

    @Test
    public void create() {
        BiotoptypEntityImpl biotoptypEntity = new BiotoptypEntityImpl();

        final ProjectEntityImpl projectEntity = new ProjectEntityImpl();
        projectEntity.addBiotoptypEntity(biotoptypEntity);
        projectEntity.setLeitung(LEITUNG);
        projectEntity.setName(ProjectDaoTest.NAME);

        final BttFloraEntityImpl bttFloraEntity = new BttFloraEntityImpl();
        bttFloraEntity.setBiotoptypEntity(biotoptypEntity);
        bttFloraEntity.setCharaktArt(CHARAKT_ART);
        bttFloraEntity.setName(BttFloraDaoTest.NAME);
        bttFloraEntity.setPlrelevArt(PLRELEV_ART);
        bttFloraEntity.setProjectEntityId(1l);

        biotoptypEntity.addBttFloraEntity(bttFloraEntity);
        biotoptypEntity.setBemerkung(BEMERKUNG);
        biotoptypEntity.setBttCode(BTT_CODE);
        biotoptypEntity.setGgbt(GGBT);
        biotoptypEntity.setLrtCode(LRT_CODE);
        biotoptypEntity.setNumber(NUMBER);
        biotoptypEntity.setProjectEntity(projectEntity);
        biotoptypEntity.setWst(WST_C);

        // creates new table project if not existing and inserts project-values
        biotoptypEntity = biotoptypDao.create(biotoptypEntity);

        // entity should be extended by an id
        assertNotNull(biotoptypEntity.getId());

        // in test-szenario (profile 'test') we must have the parent and child-entity also
        assertNotNull(biotoptypEntity.getProjectEntity().getId());
        assertNotNull(biotoptypEntity.getBttFloraEntityList().get(0).getId());
    }

    @Test
    public void delete() {
        BiotoptypEntityImpl biotoptypEntity = new BiotoptypEntityImpl();

        final ProjectEntityImpl projectEntity = new ProjectEntityImpl();
        projectEntity.addBiotoptypEntity(biotoptypEntity);
        projectEntity.setLeitung("Leitung");
        projectEntity.setName("Name");
        projectEntity.addBiotoptypEntity(biotoptypEntity);

        biotoptypEntity.setBttCode("4711");
        biotoptypEntity.setNumber(2);
        biotoptypEntity.setProjectEntity(projectEntity);

        biotoptypEntity = biotoptypDao.create(biotoptypEntity);
        final Long biotoptypEntityId = biotoptypEntity.getId();

        assertNotNull(biotoptypEntityId);

        biotoptypDao.remove(biotoptypEntity);
        final BiotoptypEntity biotoptypEntityRead = biotoptypDao.find(new BiotoptypEntityImpl(biotoptypEntityId));

        assertNull(biotoptypEntityRead);
    }

    @Test
    public void findAll() {
        final List<BiotoptypEntityImpl> biotoptypEntityList = biotoptypDao.findAll();

        assertTrue(biotoptypEntityList.size() > 0);
    }

    @Test
    public void findByProjectIdAndNumber() {
        final BiotoptypEntity biotoptypEntity = new BiotoptypEntityImpl();
        biotoptypEntity.setNumber(1);
        biotoptypEntity.setProjectEntity(new ProjectEntityImpl(1l));
        final BiotoptypEntity biotoptypEntityRead = biotoptypDao.findByProjectIdNumber(biotoptypEntity);

        assertNotNull(biotoptypEntityRead);
    }

    @Test
    public void read() {
        final BiotoptypEntity biotoptypEntityRead = biotoptypDao.find(new BiotoptypEntityImpl(1l));

        assertNotNull(biotoptypEntityRead);
    }

    @Test
    public void update() {
        final Boolean ggbt = Boolean.FALSE;
        BiotoptypEntityImpl biotoptypEntityRead = biotoptypDao.find(new BiotoptypEntityImpl(1l));
        final Integer version = biotoptypEntityRead.getVersion();

        assertTrue(biotoptypEntityRead.getGgbt());

        biotoptypEntityRead.setGgbt(ggbt);
        biotoptypEntityRead = biotoptypDao.update(biotoptypEntityRead);

        assertTrue(biotoptypEntityRead.getVersion() == version + 1);
        assertFalse(biotoptypEntityRead.getGgbt());
    }

}
