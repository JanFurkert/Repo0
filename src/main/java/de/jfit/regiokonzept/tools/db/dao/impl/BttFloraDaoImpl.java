/*
 * Copyright (C) 2018 JFITServices, All rights reserved.
 */
package de.jfit.regiokonzept.tools.db.dao.impl;

import static de.jfit.regiokonzept.tools.db.entities.BttFloraEntity.FIND_ALL;
import static de.jfit.regiokonzept.tools.db.entities.BttFloraEntity.FIND_BY_NAME;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.stereotype.Repository;

import de.jfit.regiokonzept.tools.db.dao.BttFloraDao;
import de.jfit.regiokonzept.tools.db.entities.BttFloraEntity;
import de.jfit.regiokonzept.tools.db.entities.impl.BttFloraEntityImpl;

/**
 * Description:<br>
 *
 * @author minion69
 */
@Repository
@Transactional(value = TxType.REQUIRED)
public class BttFloraDaoImpl implements BttFloraDao {

    @PersistenceContext
    private EntityManager entityManager;

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.jfit.regiokonzept.tools.db.dao.BttFloraDao#create(de.jfit.regiokonzept.tools.db.entities.impl.BttFloraEntityImpl)
     */
    @Override
    public BttFloraEntityImpl create(BttFloraEntityImpl bttFloraEntity) {
        entityManager.persist(bttFloraEntity);

        return bttFloraEntity;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.jfit.regiokonzept.tools.db.dao.BttFloraDao#find(de.jfit.regiokonzept.tools.db.entities.BttFloraEntity)
     */
    @Override
    public BttFloraEntityImpl find(BttFloraEntity bttFloraEntity) {
        return entityManager.find(BttFloraEntityImpl.class, bttFloraEntity.getId());
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.jfit.regiokonzept.tools.db.dao.BttFloraDao#findAll()
     */
    @Override
    public List<BttFloraEntityImpl> findAll() {
        return entityManager.createNamedQuery(FIND_ALL, BttFloraEntityImpl.class).getResultList();
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.jfit.regiokonzept.tools.db.dao.BttFloraDao#findByName(de.jfit.regiokonzept.tools.db.entities.BttFloraEntity)
     */
    @Override
    public BttFloraEntityImpl findByName(BttFloraEntity bttFloraEntity) {
        Query queryBttFloraEntity = entityManager.createNamedQuery(FIND_BY_NAME, BttFloraEntityImpl.class);
        queryBttFloraEntity.setParameter("name", bttFloraEntity.getName());

        return (BttFloraEntityImpl) queryBttFloraEntity.getSingleResult();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.jfit.regiokonzept.tools.db.dao.BttFloraDao#remove(de.jfit.regiokonzept.tools.db.entities.impl.BttFloraEntityImpl)
     */
    @Override
    public void remove(BttFloraEntityImpl bttFloraEntity) {
        entityManager.remove(entityManager.contains(bttFloraEntity) ? bttFloraEntity : entityManager.merge(bttFloraEntity));
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.jfit.regiokonzept.tools.db.dao.BttFloraDao#update(de.jfit.regiokonzept.tools.db.entities.impl.BttFloraEntityImpl)
     */
    @Override
    public BttFloraEntityImpl update(BttFloraEntityImpl bttFloraEntity) {
        return entityManager.merge(bttFloraEntity);
    }

}
