'use strict';

angular.module('app')
    .controller('DashboardCtrl', function ($scope, $state) {

        $scope.$state = $state;

        $scope.sidebarToggle = true;

        $scope.showSidebar = function () {
            $scope.sidebarToggle = true;
        };

        $scope.hideSidebar = function () {
            $scope.sidebarToggle = false;
        }
    });
