package de.scisertec.admin.account.startup.data;

import de.scisertec.admin.account.model.Group;
import de.scisertec.admin.account.model.Relationship;
import de.scisertec.admin.account.model.Role;
import de.scisertec.admin.account.model.User;
import de.scisertec.admin.account.service.GroupCatalog;
import de.scisertec.admin.account.service.RelationshipCatalog;
import de.scisertec.admin.account.service.RoleCatalog;
import de.scisertec.admin.account.service.UserCatalog;
import org.jboss.seam.transaction.Transactional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class UserImport {

    @Inject
    UserCatalog userCatalog;

    @Inject
    GroupCatalog groupCatalog;

    @Inject
    RoleCatalog roleCatalog;

    @Inject
    RelationshipCatalog relationshipCatalog;

    Group system;

    User user;
    User admin;

    Role userRole;
    Role adminRole;

    @Transactional
    public void initialize() {


        userRole = roleCatalog.create().name("USER").save();
        adminRole = roleCatalog.create().name("ADMIN").save();

        system = groupCatalog.create()
                .name("System")
                .identifier("system")
                .addRole(userRole)
                .addRole(adminRole)
                .defaultRole(userRole);


        Relationship userRelationship = relationshipCatalog.create(system);


        user = userCatalog.create()
                .name("user")
                .password("user")
                .addRelationship(userRelationship)
                .save();

        Relationship adminRelationship = relationshipCatalog.create(system)
                .addRole(adminRole)
                .save();

        admin = userCatalog.create()
                .name("admin")
                .password("admin")
                .addRelationship(adminRelationship)
                .save();

    }

    public User getUser() {
        return user;
    }

    public User getAdmin() {
        return admin;
    }

}
