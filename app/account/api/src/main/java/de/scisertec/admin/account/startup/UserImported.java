package de.scisertec.admin.account.startup;

import de.scisertec.admin.account.model.User;

public class UserImported {
    public User user;
    public User admin;

    public UserImported() {
    }

    public UserImported(User user, User admin) {
        this.user = user;
        this.admin = admin;
    }
}
