package de.scisertec.admin.account.model;

import de.scisertec.admin.account.model.view.RoleName;
import de.scisertec.admin.core.model.TechnicalId;

public interface Role extends TechnicalId {

    String name();
    Role name(String name);

    Role save();

    RoleName asName();

}
