/*
 * Copyright (C) 2018 JFITServices, All rights reserved.
 */
package de.jfit.regiokonzept.tools.db.dao.impl;

import static de.jfit.regiokonzept.tools.db.entities.UserEntity.FIND_ALL;
import static de.jfit.regiokonzept.tools.db.entities.UserEntity.FIND_BY_EMAIL;
import static de.jfit.regiokonzept.tools.db.entities.UserEntity.FIND_BY_NAME;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.stereotype.Repository;

import de.jfit.regiokonzept.tools.db.dao.UserDao;
import de.jfit.regiokonzept.tools.db.entities.UserEntity;
import de.jfit.regiokonzept.tools.db.entities.impl.UserEntityImpl;

/**
 * Description:<br>
 *
 * @author minion69
 */
@Repository
@Transactional(value = TxType.REQUIRED)
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    /*
     * (non-Javadoc)
     * 
     * @see de.jfit.regiokonzept.tools.db.dao.impl.UserDao#create(de.jfit.regiokonzept.tools.db.entities.impl.UserEntityImpl)
     */
    @Override
    public UserEntityImpl create(final UserEntityImpl userEntity) {
        entityManager.persist(userEntity);

        return userEntity;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.jfit.regiokonzept.tools.db.dao.UserDao#find(de.jfit.regiokonzept.tools.db.entities.UserEntity)
     */
    @Override
    public UserEntityImpl find(UserEntity userEntity) {
        return entityManager.find(UserEntityImpl.class, userEntity.getId());
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.jfit.regiokonzept.tools.db.dao.impl.UserDao#findAll()
     */
    @Override
    public List<UserEntityImpl> findAll() {
        return entityManager.createNamedQuery(FIND_ALL, UserEntityImpl.class).getResultList();
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.jfit.regiokonzept.tools.db.dao.UserDao#findByEmail(de.jfit.regiokonzept.tools.db.entities.UserEntity)
     */
    @Override
    public UserEntityImpl findByEmail(UserEntity userEntity) {
        Query queryUserEntity = entityManager.createNamedQuery(FIND_BY_EMAIL, UserEntityImpl.class);
        queryUserEntity.setParameter("email", userEntity.getEmail());

        return (UserEntityImpl) queryUserEntity.getSingleResult();
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.jfit.regiokonzept.tools.db.dao.UserDao#findByName(de.jfit.regiokonzept.tools.db.entities.UserEntity)
     */
    @Override
    public UserEntityImpl findByName(UserEntity userEntity) {
        Query queryUserEntity = entityManager.createNamedQuery(FIND_BY_NAME, UserEntityImpl.class);
        queryUserEntity.setParameter("name", userEntity.getName());

        return (UserEntityImpl) queryUserEntity.getSingleResult();
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.jfit.regiokonzept.tools.db.dao.impl.UserDao#remove(de.jfit.regiokonzept.tools.db.entities.impl.UserEntityImpl)
     */
    @Override
    public void remove(final UserEntityImpl userEntity) {
        entityManager.remove(entityManager.contains(userEntity) ? userEntity : entityManager.merge(userEntity));
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.jfit.regiokonzept.tools.db.dao.impl.UserDao#update(de.jfit.regiokonzept.tools.db.entities.impl.UserEntityImpl)
     */
    @Override
    public UserEntityImpl update(final UserEntityImpl userEntity) {
        return entityManager.merge(userEntity);
    }

}
