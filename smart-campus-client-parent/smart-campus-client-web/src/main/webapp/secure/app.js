var app = angular.module('SmartCampus', ['ngRoute'])
    .config(function ($routeProvider) {
        $routeProvider
            .when('/test1', {
                templateUrl: 'test1/test1-tpl.html',
                controller: 'Test1Ctrl'
            })
            .when('/test2', {
                templateUrl: 'test2/test2-tpl.html',
                controller: 'Test2Ctrl'
            })
            .otherwise({
                           templateUrl: 'test3/test3-tpl.html',
                           controller: 'Test3Ctrl'
                       })
    });