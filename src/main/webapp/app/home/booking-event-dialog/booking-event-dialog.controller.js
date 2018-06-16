(function() {
    'use strict';

    angular.module('sportPlaceReservationApp').controller('BookingEventDialogController', BookingEventDialogController);

    BookingEventDialogController.$inject = ['$uibModalInstance', '$timeout', 'itemId', '$rootScope'];

    function BookingEventDialogController($uibModalInstance, $timeout, itemId, $rootScope) {
        var vm = this;

        vm.closeDialog = closeDialog;
        vm.itemId = itemId;

        var asd = $rootScope.$on('OffModal', function(event, data) {
            $uibModalInstance.dismiss();
        });

        function closeDialog() {
            $uibModalInstance.dismiss();
        }

        $timeout(function() {
            angular.element('#username').focus();
        });

        vm.datepickerOptions = {
            minDate: new Date(), //Disabled date selection before today
            showWeeks: false, //Don't show weeks
            startingDay: 1, //Starting day at Monday
            dateDisabled: myDisabledDates,
            request: {
                id: vm.itemId
            }
        };

        //Disabled dates
        function myDisabledDates(dateAndMode) {
            var date = dateAndMode.date;
            var mode = dateAndMode.mode;
            var day = date.getDate();
            var dayOfWeek = date.getDay();
            var month = date.getMonth();
            //Disable dates on Sundays and from 1 to 15 of August
            return (
                (mode === 'day' && dayOfWeek === 0) ||
                (month === 7 && day in [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15])
            );
        }

        vm.availableHours = [11, 12, 13, 4];
    }
})();
