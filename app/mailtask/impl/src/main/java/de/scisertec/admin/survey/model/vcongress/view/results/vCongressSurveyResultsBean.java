package de.scisertec.admin.survey.model.vcongress.view.results;

import de.scisertec.admin.survey.model.Sheet;

import java.util.Set;

public class vCongressSurveyResultsBean implements vCongressSurveyResults {

    Set<Sheet> sheets;

    public vCongressSurveyResultsBean(Set<Sheet> sheets) {
        this.sheets = sheets;
    }

    @Override
    public RoleQuestion getRoleQuestion() {
        return new RoleQuestionBean(sheets);
    }

    @Override
    public CommonRating getCommonRating() {
        return new CommonRatingBean(sheets);
    }

    @Override
    public PaymentRating getPaymentRating() {
        return new PaymentRatingBean(sheets);
    }

    @Override
    public PaperRating getPaperRating() {
        return new PaperRatingBean(sheets);
    }

    @Override
    public SupportRating getSupportRating() {
        return new SupportRatingBean(sheets);
    }

    @Override
    public WebsiteRating getWebsiteRating() {
        return new WebsiteRatingBean(sheets);
    }

    @Override
    public OrganizerRating getOrganizerRating() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ReviewRating getReviewRating() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public AverageRating getAverageRatings() {
        return new AverageRatingBean(sheets);
    }
}
