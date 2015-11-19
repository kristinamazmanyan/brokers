/**
 * Created by Student2 on 8/24/2015.
 */

PPCM
 //   .config(['$routeProvider', departmentRoute])
 .controller("DepartmentListController", ["$scope", 'DepartmentUserService', DepartmentListController])
 .directive('modal', function () {
        return {
            template: '<div class="modal fade">' +
            '<div class="modal-dialog">' +
            '<div class="modal-content">' +
            '<div class="modal-header">' +
            '<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>' +
            '<h4 class="modal-title">{{ title }}</h4>' +
            '</div>' +
            '<div class="modal-body" ng-transclude></div>' +
            '</div>' +
            '</div>' +
            '</div>',
            restrict: 'E',
            transclude: true,
            replace: true,
            scope: true,
            link: function postLink(scope, element, attrs) {
                scope.title = attrs.title;

                scope.$watch(attrs.visible, function (value) {
                    if (value == true)
                        $(element).modal('show');
                    else
                        $(element).modal('hide');
                });

                $(element).on('shown.bs.modal', function () {
                    scope.$apply(function () {
                        scope.$parent[attrs.visible] = true;
                    });
                });

                $(element).on('hidden.bs.modal', function () {
                    scope.$apply(function () {
                        scope.$parent[attrs.visible] = false;
                    });
                });
            }
        };
    })

/*function departmentRoute($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl: 'department',
            controller: DepartmentListController
        })
        .otherwise({
            templateUrl: 'department',
            controller: DepartmentListController

        });
}*/

function DepartmentListController($scope, DepartmentUserService) {
    $scope.departmnet=[];

    DepartmentUserService.departmnet()
        .success(function (data) {
            $scope.departmnet = data;
        });

    $scope.showAdd = false;
    $scope.toggleModal = function () {
        $scope.showAdd = !$scope.showAdd;
    }

    /*$scope.showEdit = false;
    $scope.toggleModal1 = function (editRole) {
        $scope.showEdit = !$scope.showEdit;
        $scope.editRole = editRole;
    }

    $scope.cancel = function () {
        $scope.showAdd = false;

    }
    $scope.addDepartment =function(){
        $scope.departmnets.id= generateUUID().toString();
        DepartmentUserService.addDepartment($scope.departmnets)
            .success(function(data){
                $scope.cancel();
            })
    }*/

    /*$scope.saveDepartment = function () {
        DepartmentUserService.saveDepartment($scope.departmnets)
            .success(function (data) {
                $scope.cancel();
            })
    }
   function generateUUID() {
        var d = new Date().getTime();
        var uuid = 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
            var r = (d + Math.random() * 16) % 16 | 0;
            d = Math.floor(d / 16);
            return (c == 'x' ? r : (r & 0x3 | 0x8)).toString(16);
        });
        return uuid;
    };*/
}