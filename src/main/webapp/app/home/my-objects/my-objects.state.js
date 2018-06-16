(function() {
    'use strict';

    angular.module('sportPlaceReservationApp').config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('my-objects', {
            parent: 'app',
            url: '/',
            data: {
                authorities: ['ROLE_ADMIN']
            },
            views: {
                'content@': {
                    templateUrl: 'app/home/my-objects/my-objects.html',
                    controller: 'MyObjectsController',
                    controllerAs: 'vm'
                }
            }
        });
    }
})();
