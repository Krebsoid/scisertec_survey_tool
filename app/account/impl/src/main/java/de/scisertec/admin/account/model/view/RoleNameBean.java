package de.scisertec.admin.account.model.view;

public class RoleNameBean implements RoleName {

    String name;

    public RoleNameBean(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
