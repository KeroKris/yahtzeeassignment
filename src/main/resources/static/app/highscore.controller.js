/**
 * Created by Kristoffer on 2017-03-02.
 */

(function () {


    angular.module('app').controller('BookingsController', BookingsController);

    BookingsController.$inject = ['$http'];

    function BookingsController($http) {
        var vm = this;

        vm.bookings = [];
        vm.gameLogList = [];
        vm.playerLogList = [];
        vm.getAll = getAll;
        vm.getGameLog = getGameLog;
        vm.getPlayerLog = getPlayerLog;
        vm.clear = clear;
        vm.toggle = toggle;
        vm.mainViewItem = "loginFragment";

        vm.tableView = "";
        vm.message = "Hello from BookingsController";
        vm.type = "type_1";

        vm.informationView = '/highscores';
        vm.toggleMode = "Hide";
        vm.toggleValue = true;
        vm.searchInput = searchInput;

        init();

        function init() {
            getAll();
        }

        var searchInput = "";

        function getAll() {
            var url = "/highscore";
            vm.informationView = '/highscores';
            var bookingsPromise = $http.get(url);
            bookingsPromise.then(function (response) {
                vm.bookings = response.data;
            });
        }

        function clear() {
            vm.bookings = [];
        }

        function toggle() {

            if (vm.toggleValue) {
                vm.toggleMode = "Show";
            } else {
                vm.toggleMode = "Hide";
            }
            vm.toggleValue = !vm.toggleValue;
        }

        function getGameLog(gameID) {

            if (isFinite(gameID)) {
                var url = "/log/" + gameID;
                vm.informationView = '/gameLogView';
                var gameLogPromise = $http.get(url);
                gameLogPromise.then(function (response) {
                    vm.gameLogList = response.data;
                });
            } else {
                console.log("Please enter a number.")
            }
        }

        function getPlayerLog(playerName) {
            var url = "playerlog/" + playerName;
            vm.informationView = '/playerLogView';
            var playerLogPromise = $http.get(url);
            playerLogPromise.then(function (response) {
                vm.playerLogList = response.data;
            });
            searchInput = "";
        }
    }
})();

