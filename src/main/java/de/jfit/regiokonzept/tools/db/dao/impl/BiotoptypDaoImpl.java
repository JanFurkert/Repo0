/*
 * Copyright (C) 2018 JFITServices, All rights reserved.
 */
package de.jfit.regiokonzept.tools.db.dao.impl;

import static de.jfit.regiokonzept.tools.db.entities.BiotoptypEntity.FIND_ALL;
import static de.jfit.regiokonzept.tools.db.entities.BiotoptypEntity.FIND_BY_PROJECTID_NUMBER;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.stereotype.Repository;

import de.jfit.regiokonzept.tools.db.dao.BiotoptypDao;
import de.jfit.regiokonzept.tools.db.entities.BiotoptypEntity;
import de.jfit.regiokonzept.tools.db.entities.impl.BiotoptypEntityImpl;

/**
 * Description:<br>
 *
 * @author minion69
 */
// TODO: https://vladmihalcea.com/the-best-way-to-handle-the-lazyinitializationexception/
@Repository
@Transactional(value = TxType.REQUIRED)
public class BiotoptypDaoImpl implements BiotoptypDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public BiotoptypEntityImpl create(BiotoptypEntityImpl biotoptypEntity) {
        entityManager.persist(biotoptypEntity);

        return biotoptypEntity;
    }

    @Override
    public BiotoptypEntityImpl find(BiotoptypEntity biotoptypEntity) {
        return entityManager.find(BiotoptypEntityImpl.class, biotoptypEntity.getId());
    }

    @Override
    public List<BiotoptypEntityImpl> findAll() {
        return entityManager.createNamedQuery(FIND_ALL, BiotoptypEntityImpl.class).getResultList();
    }

    @Override
    public BiotoptypEntityImpl findByProjectIdNumber(BiotoptypEntity biotoptypEntity) {
        Query queryBiotoptypEntity = entityManager.createNamedQuery(FIND_BY_PROJECTID_NUMBER, BiotoptypEntityImpl.class);
        queryBiotoptypEntity.setParameter("projectEntityId", biotoptypEntity.getProjectEntityId());
        queryBiotoptypEntity.setParameter("number", biotoptypEntity.getNumber());

        return (BiotoptypEntityImpl) queryBiotoptypEntity.getSingleResult();
    }

    @Override
    public void remove(BiotoptypEntityImpl biotoptypEntity) {
        entityManager
                .remove(entityManager.contains(biotoptypEntity) ? biotoptypEntity : entityManager.merge(biotoptypEntity));
    }

    @Override
    public BiotoptypEntityImpl update(BiotoptypEntityImpl biotoptypEntity) {
        return entityManager.merge(biotoptypEntity);
    }

}
