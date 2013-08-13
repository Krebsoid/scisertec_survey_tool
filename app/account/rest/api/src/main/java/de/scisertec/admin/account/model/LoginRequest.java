package de.scisertec.admin.account.model;

import de.scisertec.admin.account.model.view.Login;

public class LoginRequest implements Login {

    String userName;
    String password;

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public String getPassword() {
        return password;
    }
}
