package de.scisertec.admin.survey.model.vcongress.view;

import de.scisertec.admin.survey.model.Sheet;
import de.scisertec.admin.survey.model.SheetData;

public class vCongressSheetCsvHolderBean {

    String role;

    Integer common;
    String commonCritics;

    Integer payment;
    String paymentCritics;

    Integer website;
    String websiteCritics;

    Integer paper;
    String paperCritics;

    Integer support;
    String supportCritics;

    Integer review;
    String reviewCritics;

    Integer organizer;
    String organizerCritics;

    String commendation;
    String critics;

    public vCongressSheetCsvHolderBean(Sheet sheet) {
        SheetData role = sheet.data().get("role");
        if(role != null) {
            this.role = (String) role.data();
        }
        SheetData common = sheet.data().get("common");
        if(common != null) {
            this.common = (Integer) common.data();
        }
        SheetData commonCritics = sheet.data().get("commonCritics");
        if(commonCritics != null) {
            this.commonCritics = (String) commonCritics.data();
        }

        SheetData payment = sheet.data().get("payment");
        if(payment != null) {
            this.payment = (Integer) payment.data();
        }
        SheetData paymentCritics = sheet.data().get("paymentCritics");
        if(paymentCritics != null) {
            this.paymentCritics = (String) paymentCritics.data();
        }

        SheetData support = sheet.data().get("support");
        if(support != null) {
            this.support = (Integer) support.data();
        }
        SheetData supportCritics = sheet.data().get("supportCritics");
        if(supportCritics != null) {
            this.supportCritics = (String) supportCritics.data();
        }

        SheetData website = sheet.data().get("website");
        if(website != null) {
            this.website = (Integer) website.data();
        }
        SheetData websiteCritics = sheet.data().get("websiteCritics");
        if(websiteCritics != null) {
            this.websiteCritics = (String) websiteCritics.data();
        }

        SheetData paper = sheet.data().get("paper");
        if(paper != null) {
            this.paper = (Integer) paper.data();
        }
        SheetData paperCritics = sheet.data().get("paperCritics");
        if(paperCritics != null) {
            this.paperCritics = (String) paperCritics.data();
        }

        SheetData review = sheet.data().get("review");
        if(review != null) {
            this.review = (Integer) review.data();
        }
        SheetData reviewCritics = sheet.data().get("reviewCritics");
        if(reviewCritics != null) {
            this.reviewCritics = (String) reviewCritics.data();
        }

        SheetData organizer = sheet.data().get("organizer");
        if(organizer != null) {
            this.organizer = (Integer) organizer.data();
        }
        SheetData organizerCritics = sheet.data().get("organizerCritics");
        if(organizerCritics != null) {
            this.organizerCritics = (String) organizerCritics.data();
        }

        SheetData commendation = sheet.data().get("commendation");
        if(commendation != null) {
            this.commendation = (String) commendation.data();
        }
        SheetData critics = sheet.data().get("critics");
        if(critics != null) {
            this.critics = (String) critics.data();
        }
    }

    public String getRole() {
        return role;
    }

    public Integer getCommon() {
        return common;
    }

    public String getCommonCritics() {
        return commonCritics;
    }

    public Integer getPayment() {
        return payment;
    }

    public String getPaymentCritics() {
        return paymentCritics;
    }

    public Integer getWebsite() {
        return website;
    }

    public String getWebsiteCritics() {
        return websiteCritics;
    }

    public Integer getPaper() {
        return paper;
    }

    public String getPaperCritics() {
        return paperCritics;
    }

    public Integer getSupport() {
        return support;
    }

    public String getSupportCritics() {
        return supportCritics;
    }

    public Integer getReview() {
        return review;
    }

    public String getReviewCritics() {
        return reviewCritics;
    }

    public Integer getOrganizer() {
        return organizer;
    }

    public String getOrganizerCritics() {
        return organizerCritics;
    }

    public String getCommendation() {
        return commendation;
    }

    public String getCritics() {
        return critics;
    }
}
