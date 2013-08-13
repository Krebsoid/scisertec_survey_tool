package de.scisertec.admin.account.model.view;

public class GroupIdentifierBean implements GroupIdentifier {

    String name;

    public GroupIdentifierBean(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
