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
            //    CityService
            //        .findCity($scope.coords)
            //        .success(function (data) {
            //            $scope.city = data;
            //        })
            //        .error(function(reason) {
            //            $scope.alerts = [{msg: reason}];
            //        });

            };

            $scope.closeAlert = function (index) {
                $scope.alerts.splice(index, 1);
            };
        }]);
