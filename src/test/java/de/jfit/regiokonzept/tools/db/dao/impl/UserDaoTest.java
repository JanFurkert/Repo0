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

import de.jfit.regiokonzept.tools.db.dao.UserDao;
import de.jfit.regiokonzept.tools.db.entities.UserEntity;
import de.jfit.regiokonzept.tools.db.entities.impl.UserEntityImpl;

/**
 * Description:<br>
 *
 * @author minion69
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void create() {
        UserEntityImpl userEntity = new UserEntityImpl();
        userEntity.setEmail("jan.furkert@gmail.com");
        userEntity.setForename("Jan");
        userEntity.setPassword("pwd");
        userEntity.setSurename("Furkert");
        userEntity.setUsername("Minion69");

        // creates new table user if not existing and inserts user-values
        userEntity = userDao.create(userEntity);

        // Entity should be extended by an id
        assertNotNull(userEntity.getId());
    }

    @Test
    public void delete() {
        UserEntityImpl userEntity = new UserEntityImpl();
        userEntity.setEmail("user.temp@gmail.com");
        userEntity.setForename("forename");
        userEntity.setPassword("password");
        userEntity.setSurename("surename");
        userEntity.setUsername("username");

        userEntity = userDao.create(userEntity);
        Long userEntityId = userEntity.getId();

        assertNotNull(userEntityId);

        userDao.remove(userEntity);
        final UserEntity userEntityRead = userDao.find(new UserEntityImpl(userEntityId));

        assertNull(userEntityRead);
    }

    @Test
    public void findAll() {
        final List<UserEntityImpl> userEntityList = userDao.findAll();

        assertEquals(1, userEntityList.size());
    }

    @Test
    public void read() {
        final UserEntity userEntityRead = userDao.find(new UserEntityImpl(1l));

        assertNotNull(userEntityRead);
    }

    @Test
    public void update() {
        final String username = "Balduin";
        UserEntityImpl userEntityRead = userDao.find(new UserEntityImpl(1l));
        final Integer version = userEntityRead.getVersion();
        assertNotEquals(username, userEntityRead.getUsername());

        userEntityRead.setUsername(username);
        userEntityRead = userDao.update(userEntityRead);

        assertTrue(userEntityRead.getVersion() == version + 1);
        assertEquals(username, userEntityRead.getUsername());
    }
}
