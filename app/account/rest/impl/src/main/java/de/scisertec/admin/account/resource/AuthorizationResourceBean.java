package de.scisertec.admin.account.resource;

import de.scisertec.admin.account.model.LoginRequest;
import de.scisertec.admin.account.service.Entrance;
import de.scisertec.admin.account.model.view.UserState;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class AuthorizationResourceBean implements AuthorizationResource {

    @Inject
    Entrance entrance;

    public UserState login(LoginRequest loginRequest) {
        return entrance.login(loginRequest);
    }


    public Boolean logout() {
        return entrance.logout();
    }


    public UserState state() {
        return entrance.activeState();
    }

}
