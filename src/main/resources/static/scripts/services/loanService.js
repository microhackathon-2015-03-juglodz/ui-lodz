'use strict';

angular.module('BootstrapApplication.services')
    .factory('LoanService', ['$http', function($http) {

        return {
            apply: function(client){
                return $http.post('/loan',client);
            }
        };
    }
]);
