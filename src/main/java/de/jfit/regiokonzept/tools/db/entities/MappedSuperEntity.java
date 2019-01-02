/*
 * Copyright (C) 2018 JFITServices, All rights reserved.
 */
package de.jfit.regiokonzept.tools.db.entities;

import java.util.Date;

public interface MappedSuperEntity {

    Date getCreationDate();

    Long getId();

    Date getLastUpdateDate();

    Integer getVersion();

}
