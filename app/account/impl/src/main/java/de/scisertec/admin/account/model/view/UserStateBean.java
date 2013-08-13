package de.scisertec.admin.account.model.view;

import de.scisertec.admin.account.model.Relationship;

import java.util.HashSet;
import java.util.Set;

public class UserStateBean implements UserState {

    Long id;
    String user;
    Set<RelationshipContent> relationships = new HashSet<RelationshipContent>();

    public UserStateBean(Long id, String user, Set<Relationship> relationships) {
        this.id = id;
        this.user = user;
        for(Relationship rs : relationships) {
            this.relationships.add(rs.asContent());
        }
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getUser() {
        return user;
    }

    @Override
    public Set<RelationshipContent> getRelationships() {
        return relationships;
    }

}
