package de.scisertec.admin.account.model.view;

import de.scisertec.admin.core.model.view.ViewId;

import java.util.Set;

public interface UserState extends ViewId {

    String getUser();
    Set<RelationshipContent> getRelationships();

}
