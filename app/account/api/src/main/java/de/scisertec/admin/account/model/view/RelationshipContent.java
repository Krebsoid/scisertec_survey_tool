package de.scisertec.admin.account.model.view;

import java.util.Set;

public interface RelationshipContent {

    GroupIdentifier getGroup();
    Set<RoleName> getRoles();

}
