package de.scisertec.admin.account.service;

import de.scisertec.admin.account.exception.LoginException;
import de.scisertec.admin.account.exception.UserStateException;
import de.scisertec.admin.account.model.User;
import de.scisertec.admin.account.model.view.Login;
import de.scisertec.admin.account.model.view.UserState;
import de.scisertec.admin.account.qualifier.ActiveUser;

import javax.enterprise.event.Event;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

public class EntranceBean implements Entrance {

    @Inject
    Event<LoginAction> loginEvent;

    @Inject
    Event<LogoutAction> logoutEvent;

    @Inject
    UserCatalog userCatalog;

    @Inject
    @ActiveUser
    Instance<User> activeUser;

    @Override
    public UserState login(Login login) {
        User user = userCatalog.findByLogin(login);
        if(user != null && user.credentialCheck(login)) {
            LoginAction loginAction = new LoginAction(user);
            loginEvent.fire(loginAction);
            return user.asState();
        }
        else {
            throw new LoginException();
        }
    }

    @Override
    public Boolean logout() {
        User user = activeUser.get();
        if(user != null) {
            LogoutAction logoutAction = new LogoutAction(user);
            logoutEvent.fire(logoutAction);
            return true;
        }
        return false;
    }

    @Override
    public UserState activeState() {
        User user = activeUser.get();
        if(user != null) {
            return user.asState();
        }
        else {
            throw new UserStateException();
        }
    }

}
