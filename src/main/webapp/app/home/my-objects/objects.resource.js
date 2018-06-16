(function() {
    'use strict';

    angular.module('sportPlaceReservationApp').factory('ObjectsResource', ObjectsResource);

    ObjectsResource.$inject = ['$resource'];

    function ObjectsResource($resource) {
        return $resource(
            'api/objectdefinitionsbyuser',  
            {},
            {
                get: { method: 'GET', isArray: true },
                params: { userId: '@userId' }
            }
        );
    }
})();
