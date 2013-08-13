package de.scisertec.admin.survey.model.vcongress.view.results;

import de.scisertec.admin.survey.model.view.SurveyResults;

public interface vCongressSurveyResults extends SurveyResults {

    RoleQuestion getRoleQuestion();

    CommonRating getCommonRating();
    PaymentRating getPaymentRating();
    PaperRating getPaperRating();
    SupportRating getSupportRating();
    WebsiteRating getWebsiteRating();
    OrganizerRating getOrganizerRating();
    ReviewRating getReviewRating();

    AverageRating getAverageRatings();

}
