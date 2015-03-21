'use strict';

angular.module('BootstrapApplication.controllers')
    .controller('FooterCtrl', ['$scope', 'InfoService', function ($scope, InfoService) {

        $scope.applicationInfo = {};
        //$scope.data = {}
        //
        //InfoService.loadApplicationInfo(function(data) {
        //    $scope.applicationInfo = {
        //        lastCommit: data.app['last-commit'],
        //        buildDate: data.app['build-date-time']
        //    };
        //});
    }]);
