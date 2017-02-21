angular.module('app', ['ngResource'])
    .controller('AppCtrl', function ($scope, $http, $resource) {
        var sampleRestResource = $resource('sample');
        $scope.content = sampleRestResource.get();
    });