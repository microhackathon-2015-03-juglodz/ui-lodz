'use strict';

/**
 * @ngdoc function
 * # MainCtrl
 */
angular.module('BootstrapApplication.controllers')
        .controller('MainCtrl', ['$scope', 'LoanService', function ($scope, LoanService) {

            $scope.user = {}
            $scope.status = ""
            $scope.offer = ""


            $scope.getLoan = function() {
                LoanService.apply($scope.client)
            };


        }]);
