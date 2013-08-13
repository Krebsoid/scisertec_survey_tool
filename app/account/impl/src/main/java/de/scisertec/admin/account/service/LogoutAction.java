package de.scisertec.admin.account.service;

import de.scisertec.admin.account.model.User;

public class LogoutAction {

    User user;

    public LogoutAction(User user) {
        this.user = user;
    }
}
