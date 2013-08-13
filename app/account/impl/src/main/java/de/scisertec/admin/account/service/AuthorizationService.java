package de.scisertec.admin.account.service;

import de.scisertec.admin.account.model.User;
import de.scisertec.admin.account.qualifier.ActiveUser;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import java.io.Serializable;

@SessionScoped
public class AuthorizationService implements Serializable {

    User activeUser;

    @Produces
    @ActiveUser
    public User getActiveUser() {
        return activeUser;
    }

    public void setActiveUser(User activeUser) {
        this.activeUser = activeUser;
    }


    public void observeLogin(@Observes LoginAction login) {
        setActiveUser(login.user);
    }

    public void observeLogout(@Observes LogoutAction logout) {
        setActiveUser(null);
    }


}
