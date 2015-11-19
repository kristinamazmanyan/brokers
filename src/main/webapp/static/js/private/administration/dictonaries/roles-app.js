/**
 * Created by Student2 on 7/31/2015.
 */
angular.module('ppcm')
    .config(['$routeProvider', RolesRoute])
    .controller("RolesListController", ["$scope", "$window", 'RolePermissionService', RolesListController])
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
    });

function RolesRoute($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl: 'role',
            controller: RolesListController
        })
        .otherwise({
            templateUrl: 'role',
        });

}

function RolesListController($scope, RolePermissionService) {
    $scope.roles = [];
    $scope.role = {id: '', nameHy: '', nameRu: '', nameEn: ''};
    $scope.assignedPermissions = [];
    $scope.availablePermissions = [];
    $scope.Permissions = [];
    $scope.allPermissions = [];
    $scope.assignedPerm = [];

    RolePermissionService.roles()
        .success(function (data) {
            $scope.roles = data;
        });

    $scope.selectedIndex = 0;
    $scope.itemClicked = function ($index) {
        $scope.selectedIndex = $index;
    }

    RolePermissionService.permissions()
        .success(function (data) {
        $scope.allPermissions = data;
            console.log(data);
    });

    $scope.showAdd = false;
    $scope.toggleModal = function () {
        $scope.showAdd = !$scope.showAdd;
    }

    $scope.showEdit = false;
    $scope.toggleModal1 = function (editRole) {
        $scope.showEdit = !$scope.showEdit;
        $scope.editRole = editRole;
    }

    $scope.cancel = function () {
        $scope.showAdd = false;

    }

    $scope.cancel1 = function () {
        $scope.showEdit = false;

    }

    $scope.roleAdd = function () {
        $scope.role.id = generateUUID().toString();
        RolePermissionService.addRole($scope.role)
            .success(function (data) {
                $scope.cancel();
            });
    }

    $scope.saveRole = function () {
        RolePermissionService.saveRole($scope.editRole)
            .success(function (data) {
                $scope.cancel1();
            })
    }

   $scope.available = function(){
       $scope.Permissions=[];
        $scope.exist = false;
        for(var i = 0; i<$scope.allPermissions.length; i++){
            for(var j = 0;j<$scope.assignedPermissions.length;j++){
                if($scope.allPermissions[i].id === $scope.assignedPermissions[j].id){
                    $scope.exist = true;
                }
            }
            if(!$scope.exist){
                $scope.Permissions.push($scope.allPermissions[i]);
            }
            $scope.exist = false;
        }
    }

    $scope.roleId;
    $scope.getPermission = function (roleId) {
        $scope.roleId = roleId;
        RolePermissionService.permissionsByRole(roleId)
            .success(function (data) {
                $scope.assignedPermissions = data;
                $scope.available();
            });
    }

    $scope.OnAvailableChange = function (listId) {
        if (listId.length > 1) {
            for (var i = 0; i < listId.length; i++) {
                for (var j = 0; j < $scope.Permissions.length; j++) {
                    if (listId[i] === $scope.Permissions[j].id) {
                        $scope.assignedPermissions.push($scope.Permissions[j]);
                        $scope.Permissions.splice(j, 1);
                    }
                }
                RolePermissionService.addPermission($scope.roleId,listId[i]);
            }
        }
    }

    $scope.OnAvailableClick = function (object) {
        for (var i = 0; i < $scope.Permissions.length; i++) {
            if (object.availablePermissions[0] === $scope.Permissions[i].id) {
                $scope.assignedPermissions.push($scope.Permissions[i]);
                $scope.Permissions.splice(i, 1);
            }
        }
        RolePermissionService.addPermission($scope.roleId, object.availablePermissions[0]);
    };

    $scope.OnAssignedChange = function (listId) {
        if (listId.length > 1) {
            for (var i = 0; i < listId.length; i++) {
                for (var j = 0; j < $scope.assignedPermissions.length; j++) {
                    if (listId[i] === $scope.assignedPermissions[j].id) {
                        $scope.Permissions.push($scope.assignedPermissions[j]);
                        $scope.assignedPermissions.splice(j, 1);
                    }
                }
                RolePermissionService.removePermission($scope.roleId,listId[i]);
            }
        }
    }

    $scope.removeRole = function(roleId){
        RolePermissionService.remove(roleId);
        RolePermissionService.roles()
            .success(function (data) {
                $scope.roles = data;
            });
    }

    $scope.OnAssignedClick = function (object) {
        for (var i = 0; i < $scope.assignedPermissions.length; i++) {
            if (object.assignedPerm[0] === $scope.assignedPermissions[i].id) {
                $scope.Permissions.push($scope.assignedPermissions[i]);
                $scope.assignedPermissions.splice(i, 1);
            }
        }
        RolePermissionService.removePermission($scope.roleId, object.assignedPerm[0]);
    }

    function generateUUID() {
        var d = new Date().getTime();
        var uuid = 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
            var r = (d + Math.random() * 16) % 16 | 0;
            d = Math.floor(d / 16);
            return (c == 'x' ? r : (r & 0x3 | 0x8)).toString(16);
        });
        return uuid;
    };
};