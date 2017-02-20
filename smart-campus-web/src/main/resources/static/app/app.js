angular.module('app', ['ngResource'])
    .controller('AppCtrl', function ($scope, $http, $resource) {
        var sampleRestResource = $resource('sample', {body: '@body'});
        $scope.content = sampleRestResource.get();
    });