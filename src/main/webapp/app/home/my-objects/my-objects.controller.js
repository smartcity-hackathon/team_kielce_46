(function() {
    'use strict';

    angular.module('sportPlaceReservationApp').controller('MyObjectsController', MyObjectsController);

    MyObjectsController.$inject = ['Principal', 'ObjectsResource'];

    function MyObjectsController(Principal, ObjectsResource) {
        var vm = this;

        vm.getFullData = getFullData;

        vm.getFullData();

        function getFullData() {
            return Principal.identity().then(function(account) {
                return (vm.reservationData = ObjectsResource.get({ userId: account.id }).$promise.then(function(resp) {
                    vm.fullListObjects = resp;
                    console.log(vm.fullListObjects);
                }));
            });
        }
    }
})();
