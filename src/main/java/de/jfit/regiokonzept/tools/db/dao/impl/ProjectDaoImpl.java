/*
 * Copyright (C) 2018 JFITServices, All rights reserved.
 */
package de.jfit.regiokonzept.tools.db.dao.impl;

import static de.jfit.regiokonzept.tools.db.entities.ProjectEntity.FIND_ALL;
import static de.jfit.regiokonzept.tools.db.entities.ProjectEntity.FIND_BY_NAME;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.stereotype.Repository;

import de.jfit.regiokonzept.tools.db.dao.ProjectDao;
import de.jfit.regiokonzept.tools.db.entities.ProjectEntity;
import de.jfit.regiokonzept.tools.db.entities.impl.ProjectEntityImpl;

/**
 * Description:<br>
 *
 * @author minion69
 */
// TODO: https://vladmihalcea.com/the-best-way-to-handle-the-lazyinitializationexception/
@Repository
@Transactional(value = TxType.REQUIRED)
public class ProjectDaoImpl implements ProjectDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ProjectEntityImpl create(ProjectEntityImpl projectEntity) {
        entityManager.persist(projectEntity);

        return projectEntity;
    }

    @Override
    public ProjectEntityImpl find(ProjectEntity projectEntity) {
        return entityManager.find(ProjectEntityImpl.class, projectEntity.getId());
    }

    @Override
    public List<ProjectEntityImpl> findAll() {
        return entityManager.createNamedQuery(FIND_ALL, ProjectEntityImpl.class).getResultList();
    }

    @Override
    public ProjectEntityImpl findByName(ProjectEntity projectEntity) {
        Query queryProjectEntity = entityManager.createNamedQuery(FIND_BY_NAME, ProjectEntityImpl.class);
        queryProjectEntity.setParameter("name", projectEntity.getName());

        ProjectEntityImpl projectEntityImpl = (ProjectEntityImpl) queryProjectEntity.getSingleResult();

        return projectEntityImpl;
    }

    @Override
    public void remove(ProjectEntityImpl projectEntity) {
        entityManager.remove(entityManager.contains(projectEntity) ? projectEntity : entityManager.merge(projectEntity));
    }

    @Override
    public ProjectEntityImpl update(ProjectEntityImpl projectEntity) {
        return entityManager.merge(projectEntity);
    }

}
