package de.scisertec.admin.account.security;

import de.scisertec.admin.account.model.Group;
import de.scisertec.admin.account.model.User;
import de.scisertec.admin.account.qualifier.ActiveUser;
import de.scisertec.admin.account.service.GroupCatalog;
import org.jboss.seam.security.annotations.Secures;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;

public class Restrictions {

    @Inject
    @ActiveUser
    Instance<User> user;

    @Inject
    GroupCatalog groupCatalog;

    @Secures
    @LoggedOut
    public Boolean isLoggedOut() {
        return user.get() == null;
    }

    @Secures
    @LoggedIn
    public Boolean isLoggedIn() {
        return !isLoggedOut();
    }

    @Secures
    @UserRestricted
    public Boolean isUser() {
        User user = this.user.get();
        Group system = groupCatalog.findByIdentifier("system");
        return user != null && user.hasRole(system, "USER");
    }

    @Secures
    @AdminRestricted
    public Boolean isAdmin() {
        User user = this.user.get();
        Group system = groupCatalog.findByIdentifier("system");
        return user != null && user.hasRole(system, "ADMIN");
    }

}
