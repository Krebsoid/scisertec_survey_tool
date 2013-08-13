'use strict';

var surveyModule = angular.module('SurveyModule',['ngResource','ChartModule']);

surveyModule.controller('SurveyForwardingController', ['$scope', '$routeParams',
    function($scope, $routeParams) {
        $scope.surveyType = $routeParams.surveyType;
    }
]);

surveyModule.controller('SurveyController', ['$scope','$location','$routeParams', '$timeout', 'MailTaskService', 'SurveyService',
    function($scope, $location, $routeParams, $timeout, MailTaskService, SurveyService) {

        SurveyService.surveyType.setSurveyType($routeParams.surveyType);
        $scope.selectedSurveyType = $routeParams.surveyType;

        $scope.surveys = SurveyService.survey.query();

        SurveyService.rememberedSurvey();



        $scope.activateSurvey = function(survey) {
            $location.search("id", survey.id);
        };

        $scope.activeSurveyClass = function(survey) {
            if(angular.isDefined($scope.activeSurvey)) {
                if(survey.id == $scope.activeSurvey.id) {
                    return 'active';
                }
            }
        };

        $scope.refreshSurveys = function() {
            $scope.surveys = SurveyService.survey.query();
        };

        $scope.refreshSurvey = function(callback) {
            var search = $location.search();
            SurveyService.survey.get(search.id,
                function(survey) {
                    computeSurvey(survey);
                    if(callback) {
                        callback();
                    }
                },
                function() {
                    $location.search("id", null);
                }
            );
        };

        $scope.initializeSurvey = function() {
            var search = $location.search();
            if(angular.isDefined(search) && angular.isDefined(search.id)) {
                SurveyService.survey.get(search.id,
                    function(survey) {
                        if(survey.surveyType == 'PERSONAL') {
                            $scope.mailTasks = SurveyService.mailtask.query(survey, function() {
                                computeSurvey(survey);
                            });
                        }
                        else {
                            computeSurvey(survey);
                        }
                        if(survey.status != 'NOT_STARTED_YET') {
                            $scope.results = SurveyService.results.retrieve(survey);
                        }
                        SurveyService.rememberSurvey(survey);
                    },
                    function() {
                        $location.search("id", null);
                    }
                );
            }
        };

        $scope.$on('$routeUpdate', function() {
            $scope.initializeSurvey();
        });


        $scope.createSurvey = function() {
            SurveyService.survey.create(function(survey) {
                $scope.refreshSurveys();
                $scope.activateSurvey(survey);
            });
        };

        $scope.deleteSurvey = function(survey) {
            SurveyService.survey.delete(survey, function() {
                if(survey.id == $scope.activeSurvey.id) {
                    $scope.activeSurvey = undefined;
                    $location.search("id", null);
                }
            });
        };

        $scope.saveSurvey = function() {
            var selectedMailTask = $scope.activeSurvey.mailTask;
            var mailTaskId = selectedMailTask && selectedMailTask.id ? selectedMailTask.id : undefined;
            SurveyService.survey.save(
                $scope.activeSurvey,
                function() {
                    $scope.refreshSurveys();
                    $scope.refreshSurvey();
                }
            );
        };


        $scope.startSurvey = function() {
            SurveyService.survey.start($scope.activeSurvey, function() {
                $scope.refreshSurveys();
                $scope.refreshSurvey();
            })
        };


        function computeSurvey(survey) {
            $scope.activeSurvey = survey;
            var date = new Date(survey.expirationDate);
            date.setMilliseconds(0);
            date.setSeconds(0);
            date.setMinutes(0);
            date.setUTCHours(0);
            $scope.activeSurvey.expirationDate = date;
            if(survey.mailTask != undefined) {
                angular.forEach($scope.mailTasks, function(mailTask) {
                    if(mailTask.id === $scope.activeSurvey.mailTask.id) {
                        $scope.activeSurvey.mailTask = mailTask;
                    }
                });
            }
        }

        $scope.initializeSurvey();
    }
]);

surveyModule.controller('SheetController', ['$scope','SurveyService',
    function($scope, SurveyService) {
        $scope.$watch(
            function() {
                return SurveyService.survey.sheets;
            },
            function() {
                $scope.sheets = SurveyService.survey.sheets;
            }
        );

        $scope.$watch(
            function() {
                return SurveyService.results.data;
            },
            function() {
                $scope.data = SurveyService.results.data;
                $scope.$emit('refresh');
            }
        );

        $scope.sheetTableClass = function(sheet) {
            if(sheet.submissionDate != undefined) {
                return 'success';
            }
        };

        $scope.sheetOrdering = function(sheet) {
            return sheet.id;
        };
    }
]);


surveyModule.factory('SurveyService',['$resource', '$http', '$location', 'Survey', 'SurveySuitableMailTasks', 'SurveyResults',

    function($resource, $http, $location, Survey, SurveySuitableMailTasks, SurveyResults) {

        function searchForCookie() {
            if($.cookie("survey_" + surveyType.name)) {
                $location.search("id", $.cookie("survey_" + surveyType.name));
            }
        }

        function saveInCookie(survey) {
            $.cookie("survey_" + surveyType.name, survey.id);
        }

        var results = {
            data : [],
            retrieve : function(survey) {
                $http.get('/api/survey/'+ surveyType.name + '/'+ survey.id + '/results').success(
                    function(incomingResults) {
                        results.data = incomingResults;
                    }
                );
            }
        };

        var mailtask = {
            query : function(survey, callback) {
                return SurveySuitableMailTasks.query({surveyType : surveyType.name, surveyId : survey.id}, function() {
                    callback();
                });
            }
        };

        var surveyType = {
            name : '',
            setSurveyType : function(surveyType) {
                this.name = surveyType;
            }
        };

        var survey = {
            sheets : [],

            query : function() {
                return Survey.query({surveyType: surveyType.name});
            },
            get : function(surveyId, success, failure) {
                Survey.get(
                    {surveyType: surveyType.name, surveyId : surveyId},
                    function(incomingSurvey) {
                        survey.sheets = incomingSurvey.sheets;
                        success(incomingSurvey);
                    },
                    function() {
                        failure();
                    }
                );
            },
            save : function(survey, callback) {
                Survey.save(
                    {surveyId : survey.id, surveyType : surveyType.name},
                    survey,
                    function(incomingSurvey) {
                        callback(incomingSurvey);
                    }
                );
            },
            create : function(callback) {
                Survey.create(
                    {surveyType: surveyType.name},{},
                    function(incomingSurvey) {
                        callback(incomingSurvey);
                    }
                );
            },
            delete : function(survey, callback) {
                Survey.delete(
                    {surveyType: surveyType.name, surveyId : survey.id},
                    function() {
                        callback(survey);
                    }
                );
            },


            start : function(survey, callback) {
                if(survey.timeRemaining > 0) {
                    if(survey.surveyType == 'PERSONAL') {
                        if(survey.mailTask) {
                            $http.post('/api/survey/'+ surveyType.name + '/'+ survey.id + '/start').success(callback);
                        }
                    }
                    if(survey.surveyType == 'ANONYMOUS') {
                        $http.post('/api/survey/'+ surveyType.name + '/'+ survey.id + '/start').success(callback);
                    }
                }
            }
        };


        return {
            survey : survey,
            mailtask : mailtask,
            results : results,
            surveyType : surveyType,
            rememberedSurvey : function () { searchForCookie(); },
            rememberSurvey : function(survey) { saveInCookie(survey); }
        };

    }
]);

surveyModule.factory('Survey', ['$resource', function($resource) {
    return $resource('api/survey/:surveyType/:surveyId', {surveyType: '@type', surveyId: '@id'}, {
        create: {method: 'POST'},
        save: {method: 'PUT'},
        delete: {method: 'DELETE'}
    });
}]);

surveyModule.factory('SurveyResults', ['$resource', function($resource) {
    return $resource('api/survey/:surveyType/:surveyId/results', {surveyType: '@type', surveyId: '@id'}, {
    });
}]);

surveyModule.factory('SurveySuitableMailTasks', ['$resource', function($resource) {
    return $resource('api/survey/:surveyType/:surveyId/mailtaskselection', {surveyType: '@type', surveyId: '@id'});
}]);

surveyModule.filter('submittedSheetsOnly', function() {
    return function(input) {
        if(angular.isDefined(input)) {
            return input.filter(function (sheet) {
                return sheet.submissionDate !== null
            });
        }
    }
});