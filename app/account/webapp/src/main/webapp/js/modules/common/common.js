'use strict';

var commonModule = angular.module('CommonModule',[]);

commonModule.filter('shorten', function() {
    return function(input, length) {
        if(angular.isDefined(input) && input != null && input.length > length) {
            return input.substring(0, length)+"...";
        }
        else {
            return input;
        }
    };
});

commonModule.directive('popover', function() {
    return {
        restrict:'A',
        link:function (scope, element, attrs) {
            if(angular.isDefined(attrs.popover)) {
                $(element).popover({
                    trigger: 'focus'
                });
            }
        }
    }
});

commonModule.directive('dropDownOnHover', function() {
    return {
        restrict:'A',
        link:function (scope, element, attrs) {
            if(angular.isDefined(attrs.dropDownOnHover)) {
                $(element).dropdownHover(true);
            }
        }
    }
});

commonModule.directive('placeholder', function() {
    return {
        restrict:'A',
        link:function (scope, element, attrs) {
            if(angular.isDefined(attrs.placeholder)) {
                $(element).placeholder();
            }
        }
    }
});

commonModule.directive('bootstrapLink', ['$location', function($location) {
    return {
        restrict:'A',
        transclude:true,
        replace:true,
        scope:{
            path:'@bootstrapLink'
        },
        link:function (scope, element, attrs) {

            scope.$watch(function (scope) {

                if ($location.path() === scope.path) {
                    scope.visibility = 'active'
                } else {
                    scope.visibility = ''
                }

            })

        },
        template:'<li data-ng-class="visibility"><a href="/#!{{path}}" data-ng-transclude></a></li>'
    }
}]);

// Workaround for a DropDown Nav Item, that the item is active css-wise, when a sub item is selected
commonModule.directive('bootstrapDropDownLink',['$location', function($location) {
    return {
        restrict:'A',
        scope:{
            path:'@bootstrapDropDownLink'
        },
        link:function (scope, element, attrs) {

            scope.$watch(function (scope) {

                var partialUrl = "/"+$location.path().split("/")[1];
                if (partialUrl === scope.path) {
                    scope.visibility = 'active'
                } else {
                    scope.visibility = ''
                }

            })

        }
    }
}]);

commonModule.directive('onEnter', function() {
    return function(scope, element, attrs) {
        element.bind("keydown keypress", function(event) {
            if(event.which === 13) {
                scope.$apply(function(){
                    scope.$eval(attrs.onEnter);
                });
                element.focus();
                event.preventDefault();
            }
        });
    };
});

commonModule.directive('onTab', function() {
    return function(scope, element, attrs) {
        element.bind("keydown keypress", function(event) {
            if(event.which === 9) {
                scope.$apply(function(){
                    scope.$eval(attrs.onTab);
                });
                $(attrs.onTabNextFocus).focus();
                event.preventDefault();
            }
        });
    };
});

commonModule.directive('compile', ['$compile', function($compile) {
    return {
        link : function(scope, element, attrs) {
            scope.$watch(
                function(scope) {
                    return scope.$eval(attrs.compile);
                },
                function(value) {
                    element.html(value);
                    $compile(element.contents())(scope);
                }
            );
        }
    }
}]);

commonModule.directive('countDown', ['$timeout', function($timeout) {
    return {
        restrict : 'A',
        replace : true,
        scope : {
            timeRemaining : '@countDown'
        },
        link : function (scope, element, attrs) {

            scope.$watch(function() {
                return scope.timeRemaining;
            }, function() {
                var observation;
                computeTimeRemaining(scope.timeRemaining);
                scope.liveExpiration = function() {
                    if(observation) {
                        $timeout.cancel(observation);
                    }

                    observation = $timeout(function() {
                        scope.timeRemaining = scope.timeRemaining - 1000;
                        computeTimeRemaining(scope.timeRemaining);
                        scope.liveExpiration();
                    }, 1000);
                };

                if(!scope.running){
                    scope.liveExpiration();
                }
                scope.running = true;

                scope.$on('$destroy', function(e) {
                    $timeout.cancel(observation);
                });
            });


            function computeTimeRemaining(timeRemaining) {
                if(timeRemaining > 0) {
                    var days = Math.floor(timeRemaining / 1000 / 60 / 60 / 24);
                    var hours = Math.floor((timeRemaining - (days*24*60*60*1000)) / 60 / 60 / 1000);
                    var minutes = Math.floor((timeRemaining - (days*24*60*60*1000) - (hours * 60 * 60 * 1000)) / 60 / 1000);
                    var seconds = Math.floor((timeRemaining - (days*24*60*60*1000) - (hours * 60 * 60 * 1000) - (minutes * 60 * 1000)) / 1000);

                    scope.time = {
                        days : days,
                        hours : hours,
                        minutes : minutes,
                        seconds : seconds,
                        milliseconds : timeRemaining
                    }
                }
                else {
                    scope.time = {
                        days : 0,
                        hours : 0,
                        minutes : 0,
                        seconds : 0
                    }
                }
            }

        },
        template:'<div>{{time.days}} days {{time.hours}} hours {{time.minutes}} minutes {{time.seconds}} seconds</div>'
    }
}]);