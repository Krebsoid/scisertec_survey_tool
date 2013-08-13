package de.scisertec.admin.account.service;

import de.scisertec.admin.account.model.Group;
import de.scisertec.admin.account.model.User;
import de.scisertec.admin.account.model.view.Login;

import java.util.List;

public interface UserCatalog {

    User create();

    User findById(Long id);
    User findByLogin(Login login);
    List<User> findByGroup(Group group);
    User findByMailAddress(String mailAddress);

}
