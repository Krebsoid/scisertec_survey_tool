'use strict';

var surveyModule = angular.module('SurveyModule',['ngResource']);

surveyModule.controller('SurveyController', ['$routeParams', '$scope', '$location', 'Survey', 'Locale', '$timeout', 'SurveySubmission',
    function($routeParams, $scope, $location, Survey, Locale, $timeout, SurveySubmission) {

        $scope.surveyType = $location.url().split('/')[1];
        $scope.uuid = $routeParams.sheetUuid;
        $scope.id = $routeParams.surveyId;
        if($scope.uuid !== '') {
            initSheet();
        }
        else {
            Survey.create(
                {surveyType : $scope.surveyType, surveyId: $scope.id}, {},
                function(survey) {
                    $location.url($scope.surveyType + "/" + $scope.id + "/" + survey.uuid);
                }
            );
        }


        $scope.localeEn = Locale.getLocale.availableLocales[1];
        $scope.localeDe = Locale.getLocale.availableLocales[0];
        $scope.switchLocale = function(locale) {
            Locale.changeLocale(locale);
        };

        $scope.saveData = function(key, value) {
            Survey.save(
                {surveyType : $scope.surveyType, surveyId: $scope.id, sheetUuid: $scope.uuid},
                {key : key, value : value}, function(survey) {
                $scope.$broadcast('survey_update',survey);
            });
        };

        var timer;
        $scope.saveDataDeferred = function(key, value) {
            $timeout.cancel(timer);
            timer = $timeout(function() {
                $scope.saveData(key, value);
            }, 400);
        };

        $scope.submitSurvey = function() {
            SurveySubmission.submit(
                {surveyType : $scope.surveyType, surveyId: $scope.id, sheetUuid: $scope.uuid}, {}, function() {
                $location.url($scope.surveyType + "/complete");
            });
        };

        function initSheet() {
            $scope.survey = Survey.get(
                {surveyType : $scope.surveyType, surveyId: $scope.id, sheetUuid: $scope.uuid},
                function(survey) {
                    if(survey.submissionDate != null) {

                    }
                    $scope.$broadcast('survey_update',survey);
                }
            );
        }
    }]
);

surveyModule.controller('SurveyStartController', ['$routeParams', '$scope', '$location', 'Survey', 'Locale',
    function($routeParams, $scope, $location, Survey, Locale) {

        $scope.surveyType = $location.url().split('/')[1];
        $scope.id = $routeParams.surveyId;

        $scope.localeEn = Locale.getLocale.availableLocales[1];
        $scope.localeDe = Locale.getLocale.availableLocales[0];
        $scope.switchLocale = function(locale) {
            Locale.changeLocale(locale);
        };

        $scope.startSurvey = function() {
            Survey.create(
                {surveyType : $scope.surveyType, surveyId: $scope.id}, {},
                function(survey) {
                    $location.url($scope.surveyType + "/" + $scope.id + "/" + survey.uuid);
                }
            );
        };
    }]
);

surveyModule.controller('SciSerTecSurveyController', ['$routeParams', '$scope', 'SurveySubmission', '$location',
    function($routeParams, $scope, SurveySubmission, $location) {

        $scope.$on('survey_update', function(event, survey) {
            updateVisibility(survey);
        });

        $scope.focus = function(element) {
            $('#'+element).focus();
        };


        $scope.submitSurvey = function() {
            $scope.person = $scope.person ? $scope.person : {};
            SurveySubmission.submit(
                {surveyType : $scope.surveyType, surveyId: $scope.id, sheetUuid: $scope.uuid}, $scope.person, function() {
                    $location.url($scope.surveyType + "/complete");
                });
        };

        function updateVisibility(survey) {
            $scope.visibility = {
                question1 : true,
                question2 : angular.isDefined(survey.data.question1),
                question3_1 : survey.data.question2 === true,
                question3_1_1 : survey.data.question2 === true && survey.data.question3_1 === true,
                question3_2_1 : survey.data.question2 === true && survey.data.question3_1 === true && survey.data.question3_1_1,
                question3_3_1 : survey.data.question2 === true && survey.data.question3_1 === true && survey.data.question3_2_1,
                question4_1 : survey.data.question2 === true && (survey.data.question3_3_1 || survey.data.question3_1 === false),
                question5_1 : survey.data.question2 === true && angular.isDefined(survey.data.question4_1),
                question5_1_1 : survey.data.question2 === true && (survey.data.question5_1 === 1 || survey.data.question5_1 === 2),
                question6_1 : survey.data.question2 === true && (angular.isDefined(survey.data.question5_1_1) || survey.data.question5_1 === 3 || survey.data.question5_1 === 4),
                question6_1_1 : survey.data.question2 === true && survey.data.question6_1 === 1,
                question6_2_1 : survey.data.question2 === true && survey.data.question6_1 === 1 && angular.isDefined(survey.data.question6_1_1),
                question7_1 : survey.data.question2 === true && (angular.isDefined(survey.data.question6_2_1) || survey.data.question6_1 === 2 || survey.data.question6_1 === 3),
                question8_1 : survey.data.question2 === true && (survey.data.question7_1_a ||
                    survey.data.question7_1_b || survey.data.question7_1_c || survey.data.question7_1_d || survey.data.question7_1_e),
                question9_1 : survey.data.question2 === true && survey.data.question8_1,
                question10_1 : survey.data.question2 === true && survey.data.question9_1,

                question3_2 : survey.data.question2 === false,
                question3_1_2 : survey.data.question2 === false && survey.data.question3_2 === 1,
                question4_2 : survey.data.question2 === false && (angular.isDefined(survey.data.question3_1_2) || survey.data.question3_2 === 2 || survey.data.question3_2 === 3),
                question5_2 : survey.data.question2 === false && (survey.data.question4_2_a ||
                    survey.data.question4_2_b || survey.data.question4_2_c || survey.data.question4_2_d || survey.data.question4_2_e),
                question6_2 : survey.data.question2 === false && (survey.data.question5_2_a ||
                    survey.data.question5_2_b || survey.data.question5_2_c || survey.data.question5_2_e),
                question7_2 : survey.data.question2 === false && angular.isDefined(survey.data.question6_2),
                question8_2 : survey.data.question2 === false && (survey.data.question7_2_a ||
                    survey.data.question7_2_b || survey.data.question7_2_c || survey.data.question7_2_d || survey.data.question7_2_e),
                question8_1_2 : survey.data.question2 === false && survey.data.question8_2 === 1,
                question8_2_2 : survey.data.question2 === false && survey.data.question8_2 === 1 && angular.isDefined(survey.data.question8_1_2),
                question9_2 : survey.data.question2 === false && (angular.isDefined(survey.data.question8_2_2) || survey.data.question8_2 === 2 || survey.data.question8_2 === 3),

                questionA : angular.isDefined(survey.data.question10_1) || (survey.data.question9_2_a || survey.data.question9_2_b || survey.data.question9_2_c || survey.data.question9_2_d),
                questionB : angular.isDefined(survey.data.questionA),
                questionC : angular.isDefined(survey.data.questionB),

                submitButton : angular.isDefined(survey.data.questionB)
            };
        }
    }]
);



surveyModule.factory('Survey', ['$resource', function($resource) {
    return $resource('/api/survey/:surveyType/:surveyId/sheet/:sheetUuid',
        {surveyType: '@surveyType', surveyId: '@surveyId', sheetUuid : '@sheetUuid'}, {
        save: {method: 'PUT'},
        create: {method: 'POST'}
    });
}]);

surveyModule.factory('SurveySubmission', ['$resource', function($resource) {
    return $resource('/api/survey/:surveyType/:surveyId/sheet/:sheetUuid/submit',
        {surveyType: '@surveyType', surveyId: '@surveyId', sheetUuid : '@sheetUuid'}, {
        submit: {method: 'POST'}
    });
}]);
