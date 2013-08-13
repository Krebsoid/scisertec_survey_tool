'use strict';

angular.module('main', ['LocaleModule','CommonModule','LoginModule','UserModule','$strap.directives'],
        ['$routeProvider', '$locationProvider', function ($routeProvider, $locationProvider) {
        $locationProvider.hashPrefix('!');
        $routeProvider
            .when('/', {
                template: '<div data-locale-template="home"></div>'
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
        })
    })

    .run(['$route', function ($route) {
        $route.reload();
    }]);

 