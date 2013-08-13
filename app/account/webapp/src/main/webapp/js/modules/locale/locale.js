'use strict';

var localeModule = angular.module('LocaleModule',[]);

localeModule.controller('LocaleController',['$scope','Locale', function($scope, Locale) {
    $scope.availableLocales = Locale.getLocale.availableLocales;
    $scope.language = Locale.getLocale.activeLocale;

    $scope.changeLocale = function() {
        Locale.changeLocale($scope.language);
    }
}]);

localeModule.directive('localeString', ['Locale', function(Locale) {
    return {
        restrict:'A',
        scope:{
            localeString:'@localeString'
        },
        link:function (scope, element, attrs) {
            scope.locale = Locale;
            scope.$watch(
                function(scope) {
                    return scope.locale.getLocale.activeLocale;
                },
                function(locale) {
                    scope.outputString = Localization[attrs.localeString][locale.value];
                }
            );
        },
        template:'<span>{{outputString}}</span>'
    }
}]);


localeModule.directive('localeTemplate', ['Locale', function(Locale) {
    return {
        restrict:'A',
        replace:true,
        scope:{
            localeTemplate:'@localeTemplate'
        },
        link:function (scope, element, attrs) {
            scope.locale = Locale;
            scope.$watch(
                function(scope) {
                    return scope.locale.getLocale.activeLocale;
                },
                function(locale) {
                    scope.templatePath = "./partials/locale/"+locale.value+"/"+attrs.localeTemplate+".html";
                }
            );
        },
        template:'<div data-ng-include="templatePath"></div>'
    }
}]);

localeModule.factory('Locale',['$location', function($location) {

    function changeLocale(locale) {
        if(Locale.availableLocales.indexOf(locale) != -1) {
            Locale.activeLocale = locale;
            $.cookie('language',locale.value, { expires: 31, path: '/' });
        }
        else {
            console.log('Locale '+locale+' not available');
        }
    }

    function findLocale(localeShortcut) {
        var foundSupportedLanguage = $.grep(Locale.availableLocales, function(locale) {
            return locale.value == localeShortcut;
        });
        if(foundSupportedLanguage.length == 1) {
            return foundSupportedLanguage[0];
        }
        else {
            return undefined;
        }
    }

    function initLocale() {
        /*var consumedLanguage = consumeLanguageLink();
        var cookieLanguage = checkForCookie();
        var browserLanguage = checkForBrowserLanguage();
        if(angular.isDefined(consumedLanguage)) {
            changeLocale(consumedLanguage);
        }
        else if(angular.isDefined(cookieLanguage)) {
            changeLocale(cookieLanguage);
        }
        else if(angular.isDefined(browserLanguage)) {
            changeLocale(browserLanguage);
        }
        else {
            setDefaultLocale();
        }*/
        setDefaultLocale();
    }

    function checkForBrowserLanguage() {
        var browserLanguage, foundLanguage;
        // Chrome / Firefox
        if(window.navigator.language) {
            browserLanguage = window.navigator.language.substr(0,2);
            foundLanguage = findLocale(browserLanguage);
        }
        // IE
        if(window.navigator.userLanguage) {
            browserLanguage = window.navigator.userLanguage.substr(0,2);
            foundLanguage = findLocale(browserLanguage);
        }
        return foundLanguage;
    }

    function checkForCookie() {
        return findLocale($.cookie('language'));
    }

    function consumeLanguageLink() {
        var consumedLanguage = findLocale($location.search().lang);
        $location.search('');
        return consumedLanguage
    }

    function setDefaultLocale() {
        var defaultLocale = findLocale(Locale.defaultLocale);
        changeLocale(defaultLocale)
    }

    var Locale = {
        availableLocales: [
            {name: 'Deutsch', value: 'de'},
            {name: 'English', value: 'en'}
        ],
        defaultLocale: 'de',
        activeLocale: null
    };

    return {
        getLocale: Locale,
        changeLocale: function(locale) {
            changeLocale(locale);
        },
        initLocale: function() {
            initLocale();
        }
    }

}]);

localeModule.run(['Locale', function(Locale) {
    Locale.initLocale();
}]);



