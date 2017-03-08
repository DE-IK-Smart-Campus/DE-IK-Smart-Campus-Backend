'use strict';

angular.module('app', [
    'ui.router',
    'ngAnimate',
    'ui.bootstrap'
])
    .config(function ($stateProvider, $urlRouterProvider) {

        $urlRouterProvider.when('/dashboard', '/dashboard/home');
        $urlRouterProvider.otherwise('/login');

        $stateProvider
            .state('base', {
                abstract: true,
                url: '',
                templateUrl: 'views/base.html'
            })
            .state('login', {
                url: '/login',
                parent: 'base',
                templateUrl: 'views/login.html',
                controller: 'LoginCtrl'
            })
            .state('dashboard', {
                url: '/dashboard',
                parent: 'base',
                templateUrl: 'views/dashboard.html',
                controller: 'DashboardCtrl'
            })
            .state('home', {
                url: '/home',
                parent: 'dashboard',
                templateUrl: 'views/dashboard/home.html'
            })
            .state('calendar', {
                url: '/calendar',
                parent: 'dashboard',
                templateUrl: 'views/dashboard/calendar.html',
                controller: 'CalendarCtrl'
            })
            .state('consultingHours', {
                url: '/consulting-hours',
                parent: 'dashboard',
                templateUrl: 'views/dashboard/consulting-hours.html'
            })
            .state('chat', {
                url: '/chat',
                parent: 'dashboard',
                templateUrl: 'views/dashboard/chat.html'
            })
            .state('other', {
                url: '/other',
                parent: 'dashboard',
                templateUrl: 'views/dashboard/other.html'
            })
            .state('aboutUs', {
                url: '/about-us',
                parent: 'dashboard',
                templateUrl: 'views/dashboard/about-us.html'
            });

    }).directive('showSidebarButton', function () {
    return {
        restrict: 'E',
        templateUrl: 'directives/show-sidebar.html'
    };
});
