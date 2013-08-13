package de.scisertec.admin.survey.model.scisertec.view.results;

import de.scisertec.admin.survey.model.Sheet;
import de.scisertec.admin.survey.model.view.QuestionResult;

import java.util.Set;

public class SciSerTecSurveyResultsBean implements SciSerTecSurveyResults {

    Set<Sheet> sheets;

    public SciSerTecSurveyResultsBean(Set<Sheet> sheets) {
        this.sheets = sheets;
    }

    @Override
    public QuestionResult getQuestion1() {
        return new Question1Bean(sheets);
    }

    @Override
    public QuestionResult getQuestion2() {
        return new Question2Bean(sheets);
    }

    @Override
    public QuestionResult getQuestion3_1() {
        return new Question3_1Bean(sheets);
    }

    @Override
    public QuestionResult getQuestion4_1() {
        return new Question4_1Bean(sheets);
    }

    @Override
    public QuestionResult getQuestion5_1() {
        return new Question5_1Bean(sheets);
    }

    @Override
    public QuestionResult getQuestion5_1_1() {
        return new Question5_1_1Bean(sheets);
    }

    @Override
    public QuestionResult getQuestion6_1() {
        return new Question6_1Bean(sheets);
    }

    @Override
    public QuestionResult getQuestion6_1_1() {
        return new Question6_1_1Bean(sheets);
    }

    @Override
    public QuestionResult getQuestion6_2_1() {
        return new Question6_2_1Bean(sheets);
    }

    @Override
    public QuestionResult getQuestion7_1() {
        return new Question7_1Bean(sheets);
    }

    @Override
    public QuestionResult getQuestion8_1() {
        return new Question8_1Bean(sheets);
    }

    @Override
    public QuestionResult getQuestion9_1() {
        return new Question9_1Bean(sheets);
    }

    @Override
    public QuestionResult getQuestion10_1() {
        return new Question10_1Bean(sheets);
    }

    @Override
    public QuestionResult getQuestion3_2() {
        return new Question3_2Bean(sheets);
    }

    @Override
    public QuestionResult getQuestion4_2() {
        return new Question4_2Bean(sheets);
    }

    @Override
    public QuestionResult getQuestion5_2() {
        return new Question5_2Bean(sheets);
    }

    @Override
    public QuestionResult getQuestion5_2_c() {
        return new Question5_2_cBean(sheets);
    }

    @Override
    public QuestionResult getQuestion7_2() {
        return new Question7_2Bean(sheets);
    }

    @Override
    public QuestionResult getQuestion8_2() {
        return new Question8_2Bean(sheets);
    }

    @Override
    public QuestionResult getQuestion8_1_2() {
        return new Question8_1_2Bean(sheets);
    }

    @Override
    public QuestionResult getQuestion8_2_2() {
        return new Question8_2_2Bean(sheets);
    }

    @Override
    public QuestionResult getQuestion9_2() {
        return new Question9_2Bean(sheets);
    }
}
