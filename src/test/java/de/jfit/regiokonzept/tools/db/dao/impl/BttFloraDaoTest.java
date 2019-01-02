/*
 * Copyright (C) 2018 JFITServices, All rights reserved.
 */
package de.jfit.regiokonzept.tools.db.dao.impl;

import static de.jfit.regiokonzept.tools.db.dao.impl.BiotoptypDaoTest.BTT_CODE;
import static de.jfit.regiokonzept.tools.db.dao.impl.BiotoptypDaoTest.NUMBER;
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

import de.jfit.regiokonzept.tools.db.dao.BttFloraDao;
import de.jfit.regiokonzept.tools.db.entities.BttFloraEntity;
import de.jfit.regiokonzept.tools.db.entities.impl.BiotoptypEntityImpl;
import de.jfit.regiokonzept.tools.db.entities.impl.BttFloraEntityImpl;
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
public class BttFloraDaoTest {

    static final Boolean CHARAKT_ART = Boolean.TRUE;

    static final String NAME = "Quercus robur";

    static final Boolean PLRELEV_ART = Boolean.FALSE;

    @Autowired
    private BttFloraDao bttFloraDao;

    @Test
    public void create() {
        BttFloraEntityImpl bttFloraEntity = new BttFloraEntityImpl();

        final ProjectEntityImpl projectEntity = new ProjectEntityImpl();
        projectEntity.setLeitung(LEITUNG);
        projectEntity.setName(ProjectDaoTest.NAME);

        final BiotoptypEntityImpl biotoptypEntity = new BiotoptypEntityImpl();
        projectEntity.addBiotoptypEntity(biotoptypEntity);
        biotoptypEntity.addBttFloraEntity(bttFloraEntity);
        biotoptypEntity.setBttCode(BTT_CODE);
        biotoptypEntity.setNumber(NUMBER);
        biotoptypEntity.setProjectEntity(projectEntity);

        bttFloraEntity.setBiotoptypEntity(biotoptypEntity);
        bttFloraEntity.setName(NAME);
        bttFloraEntity.setCharaktArt(CHARAKT_ART);
        bttFloraEntity.setPlrelevArt(PLRELEV_ART);
        bttFloraEntity.setProjectEntityId(1l);

        // creates new table project if not existing and inserts project-values
        bttFloraEntity = bttFloraDao.create(bttFloraEntity);

        // Entity should be extended by an id
        assertNotNull(bttFloraEntity.getId());

        // in test-szenario (profile 'test') we must have the parent-entity
        assertNotNull(bttFloraEntity.getBiotoptypEntity().getId());
    }

    @Test
    public void delete() {
        BttFloraEntityImpl bttFloraEntity = new BttFloraEntityImpl();

        final ProjectEntityImpl projectEntity = new ProjectEntityImpl();
        projectEntity.setLeitung("Leitung");
        projectEntity.setName("Name");

        final BiotoptypEntityImpl biotoptypEntity = new BiotoptypEntityImpl();
        projectEntity.addBiotoptypEntity(biotoptypEntity);
        biotoptypEntity.addBttFloraEntity(bttFloraEntity);
        biotoptypEntity.setBttCode("bttCode");
        biotoptypEntity.setNumber(1);
        biotoptypEntity.setProjectEntity(projectEntity);

        bttFloraEntity.setBiotoptypEntity(biotoptypEntity);
        bttFloraEntity.setName("Name");
        bttFloraEntity.setProjectEntityId(1l);

        // creates new table project if not existing and inserts project-values
        bttFloraEntity = bttFloraDao.create(bttFloraEntity);
        Long bttFloraEntityId = bttFloraEntity.getId();

        assertNotNull(bttFloraEntityId);

        bttFloraDao.remove(bttFloraEntity);
        final BttFloraEntity bttFloraEntityRead = bttFloraDao.find(new BttFloraEntityImpl(bttFloraEntityId));

        assertNull(bttFloraEntityRead);
    }

    @Test
    public void findAll() {
        final List<BttFloraEntityImpl> bttFloraEntityList = bttFloraDao.findAll();

        assertTrue(bttFloraEntityList.size() > 0);
    }

    @Test
    public void findByName() {
        final BttFloraEntity bttFloraEntity = new BttFloraEntityImpl();
        bttFloraEntity.setName(NAME);
        final BttFloraEntity bttFloraEntityRead = bttFloraDao.findByName(bttFloraEntity);

        assertNotNull(bttFloraEntityRead);
    }

    @Test
    public void read() {
        final BttFloraEntity bttFloraEntityRead = bttFloraDao.find(new BttFloraEntityImpl(1l));

        assertNotNull(bttFloraEntityRead);
    }

    @Test
    public void update() {
        BttFloraEntityImpl bttFloraEntityRead = bttFloraDao.find(new BttFloraEntityImpl(1l));
        final Integer version = bttFloraEntityRead.getVersion();
        assertTrue(bttFloraEntityRead.getCharaktArt());

        bttFloraEntityRead.setCharaktArt(Boolean.FALSE);
        bttFloraEntityRead = bttFloraDao.update(bttFloraEntityRead);

        assertTrue(bttFloraEntityRead.getVersion() == version + 1);
        assertFalse(bttFloraEntityRead.getCharaktArt());
    }

}
