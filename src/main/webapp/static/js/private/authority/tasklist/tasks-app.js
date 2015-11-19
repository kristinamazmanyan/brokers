angular.module('ppcm')
    .config(['$routeProvider', '$locationProvider',TasksPageRoutes])
    .controller("TasksListController", ["$scope", '$routeParams','TaskService', TasksListController])
    .controller("CalendarController", ["$scope",'$routeParams', '$window', 'TaskService', CalendarController])
;

function TasksPageRoutes($routeProvider, $locationProvider) {
    $routeProvider

        .when('/', {
            templateUrl : 'template-tasklist',
            controller: 'TasksListController'
        })

        .when('/calendar', {
            templateUrl : 'template-calendar',
            controller: 'CalendarController'
        })

        .otherwise( {
            templateUrl : 'template-tasklist',
            controller: 'TasksListController'

        });
}



function TasksListController($scope, $routeParams, TaskService) {

    $scope.list = function () {
        // Getting count of Users By Authority ID in Table
        TaskService.countByAssigneeId()
            .success(function (data) {
                console.log("Count = " + data);
                $scope.totalCount = data;
                $scope.itemsPerPage = 10;
                $scope.page = {index: ($scope.currentPage - 1), size: $scope.itemsPerPage};
                //$scope.order = {field: 'id', ascending: true};

                $scope.taskList = [];
                TaskService.list($scope.page)
                    .success(function (data) {
                        console.log(data);
                        $scope.taskList = data;
                    })

            })
    }
};

function CalendarController($scope, $routeParams, $window, TaskService) {

};