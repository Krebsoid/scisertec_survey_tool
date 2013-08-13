'use strict';

var userModule = angular.module('UserModule',['ngResource']);

userModule.directive('restrictedLoggedIn',['User', function(User) {
    return {
        restrict:'A',
        link:function (scope, element, attrs) {
            scope.$watch(
                function() {
                    return User.getUser.isLoggedIn;
                },
                function(loggedIn) {
                    if(!loggedIn) {
                        element.hide();
                    }
                    else {
                        element.show();
                    }
                }
            )
        }
    }
}]);

userModule.directive('restrictedRoles',['User', function(User) {
    return {
        restrict:'A',
        link:function (scope, element, attrs) {
            scope.roles = [];
            if(attrs.restrictedRoles) {
                scope.roles = attrs.restrictedRoles.split(',')
            }
            scope.$watch(
                function() {
                    return User.getUser.isLoggedIn;
                },
                function() {
                    if(!User.getUser.hasRoles(scope.roles)) {
                        element.hide();
                    }
                    else {
                        element.show();
                    }
                }
            )
        }
    }
}]);

userModule.factory('User',['$rootScope', 'State', function($rootScope, State) {

    function requestState(callback) {
        if(!User.stateRequested) {
            State.request(
                {},
                function(user) {
                    var updatedUser = User.login(user.user, user.relationships);
                    if(callback) {
                        callback(updatedUser);
                    }
                },
                function(error) {
                    var updatedUser = User.logout();
                    if(callback) {
                        callback(updatedUser);
                    }
                }
            );
        }
        if(callback) {
            callback(User);
        }
    }

    function checkConnection() {
        State.request(
            {},
            function(user) {
            },
            function(error) {
                if(User.isLoggedIn == true) {
                    User.logout();
                    angular.element('#connectionAlertModal').modal('show');
                }
            }
        );
    }

    var User = {
        name: "",
        isLoggedIn : false,
        relationships: [],
        login : function(name, relationships) {
            this.name = name;
            this.relationships = relationships;
            this.isLoggedIn = true;
            $rootScope.$broadcast('user-state-changed','loggedIn');
            return this;
        },
        logout : function() {
            this.name = "";
            this.relationships = [];
            this.isLoggedIn = false;
            $rootScope.$broadcast('user-state-changed','loggedOut');
            return this;
        },
        hasRoles : function(requiredRoles) {
            var result = false;
            angular.forEach(this.relationships, function(relationship) {
                angular.forEach(requiredRoles, function(requiredRole) {
                    if(relationship.group.name == "system") {
                        angular.forEach(relationship.roles, function(role) {
                            if(requiredRole == role.name) {
                                result = true;
                            }
                        });
                    }
                });
            });
            return result;
        }
    };

    return {
        getUser: User,
        requestState: function(callback) {
            requestState(callback);
        },
        checkConnection: function() {
            checkConnection();
        }
    };
}]);


userModule.factory('State',['$resource', function($resource) {
    return $resource('api/authorization', {}, {
        request: {method:'GET', params: {}, isArray: false}
    });
}]);

userModule.run(['User',function(User) {
    User.requestState();
    setInterval(function() {
        User.checkConnection();
    }, 5000)
}]);
