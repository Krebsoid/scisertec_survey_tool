'use strict';

angular.module('scisertec_survey', ['LocaleModule','CommonModule','SurveyModule'],
    ['$routeProvider',
        function ($routeProvider) {

            $routeProvider.
                when('/contact', {
                    template: '<div data-locale-template="contact"></div>'
                }).
                when('/imprint', {
                    template: '<div data-locale-template="imprint"></div>'
                }).

                when('/vcongress/complete', {
                    template: '<div data-locale-template="vcongress/complete"></div>'
                }).
                when('/vcongress/:surveyId/welcome/', {
                    redirectTo: function(route) {
                        var surveyId = route.surveyId;
                        return '/vcongress/' + surveyId + '/'
                    }
                }).
                when('/vcongress/:surveyId/:sheetUuid', {
                    template: '<div data-locale-template="vcongress/survey"></div>'
                }).

                when('/scisertec/complete', {
                    template: '<div data-locale-template="scisertec/complete"></div>'
                }).
                when('/scisertec/:surveyId/welcome', {
                    template: '<div data-locale-template="scisertec/welcome"></div>'
                }).
                when('/scisertec/:surveyId/:sheetUuid', {
                    template: '<div data-locale-template="scisertec/survey"></div>'
                }).

                otherwise({redirectTo: '/'});

        }]
)
.run(['$rootScope','$window','$route', function ($rootScope,$window,$route) {
    $route.reload();
    $rootScope.$on('$routeChangeSuccess', function(route) {
        var dimensionValue = location.pathname + location.search  + location.hash;
        ga('set', 'page', dimensionValue);
    });
}]);

