package de.scisertec.admin.account.service;

import de.scisertec.admin.account.model.view.Login;
import de.scisertec.admin.account.model.view.UserState;

public interface Entrance {

    UserState login(Login login);
    Boolean logout();

    UserState activeState();

}
