'use strict';

angular.module('main', ['LocaleModule','CommonModule','LoginModule','UserModule','MailTaskModule','SurveyModule','$strap.directives'],
        ['$routeProvider', '$locationProvider', function ($routeProvider, $locationProvider) {
        $locationProvider.hashPrefix('!');
        $routeProvider
            .when('/', {
                template: '<div data-locale-template="home"></div>'
            })
            .when('/mailtask', {
                template: '<div data-restricted-roles="ADMIN" data-locale-template="mailtask/overview"></div>',
                reloadOnSearch : false
            })
            .when('/survey/:surveyType', {
                template: '<div data-restricted-roles="ADMIN" data-locale-template="survey/overview"></div>',
                reloadOnSearch : false
            })
            .when('/imprint', {
                template: '<div data-locale-template="imprint"></div>'
            })
            .when('/contact', {
                template: '<div data-locale-template="contact"></div>'
            })
            .when('/logout', {
                template: '<div data-locale-template="home"></div>'
            })
            .otherwise({redirectTo:'/'});
    }])

    .controller('MainController', function($scope, User) {
        $scope.$watch(
        function() {
            return User.getUser;
        },
        function(user) {
            $scope.user = user;
        });
    });

    /*.run(['$route', function ($route) {
        $route.reload();
    }]);*/

 