'use strict';

var loginModule = angular.module('LoginModule',['UserModule']);


loginModule.controller('LoginController', function($scope, $location, Authorization, User) {
    $scope.login = function() {
        $scope.error = {};
        if(!User.getUser.isLoggedIn) {
            if(!$scope.user.$invalid) {
                Authorization.login.login(
                    {userName: $scope.user.username, password: $scope.user.password},
                    function(response) {
                        User.getUser.login(response.user, response.relationships);
                        $scope.loginSuccessful();
                    },
                    function(response) {
                        if(response.status == 0) {
                            $scope.error = {connectionProblem : true};
                        }
                        else {
                            $scope.error = {loginFailed : true};
                        }
                    }
                );
            }
        }
    };

    $scope.loginSuccessful = function() {
        $location.url($scope.destination);
    };
});

loginModule.factory('LogoutService', function($location, $rootScope, Authorization, User) {
    $rootScope.$on("$routeChangeSuccess", function (current) {
        var currentUrl = $location.url();
        if(currentUrl == '/logout') {
            logout();
            $location.url('/');
        }
    });

    var logout = function() {
        if(User.getUser.isLoggedIn) {
            Authorization.logout.logout(
                {},
                function(response) {
                    User.getUser.logout();
                },
                function(response) {

                }
            );

        }
    };
});


loginModule.factory('Authorization', function($resource) {
    return {
        login : $resource('api/authorization/login', {}, {
            login: {method:'POST', params: {}, isArray: false}
        }),
        logout: $resource('api/authorization/logout', {}, {
            logout: {method:'POST', params: {}, isArray: false}
        })
    }
});

loginModule.run(function(LogoutService) {

});