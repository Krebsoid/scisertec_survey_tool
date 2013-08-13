package de.scisertec.admin.core.service.jpa;

public class PageConstraint {

    int page;
    int entriesPerPage;

    public PageConstraint(int page, int entriesPerPage) {
        this.page = page;
        this.entriesPerPage = entriesPerPage;
    }

    public int getFirstResult() {
        return (page - 1) * entriesPerPage;
    }

    public int getMaxResults() {
        return entriesPerPage;
    }
}
