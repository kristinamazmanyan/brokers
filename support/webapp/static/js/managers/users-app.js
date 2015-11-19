/**
 * Created by artur.tsghunyan on 6/29/2015.
 */
angular.module('ppcm')
    .config(['$routeProvider', '$locationProvider',UserPageRoutes])
    .service("UserService", ["$http" ,UserService])
    .controller("UserListController", ["$scope", '$routeParams','UserService', UserListController])
    .controller("UserFormController", ["$scope",'$routeParams', '$window', 'UserService', UserFormController])
    .controller("UserViewController", ["$scope",'$routeParams', '$window', 'UserService', UserViewController]);

function UserPageRoutes($routeProvider, $locationProvider) {
    $routeProvider

        .when('/list', {
            templateUrl : 'template-list-users',
            controller: 'UserListController',
            controllerAs: 'userListCtrl'

        })

        .when('/edit/:id',{
            templateUrl: 'template-form-users',
            controller: 'UserFormController'
        })

        .when('/view/:id',{
            templateUrl: 'template-view-users',
            controller: 'UserViewController'
        })

        .when('/add',{
            templateUrl: 'template-form-users',
            controller: 'UserFormController'
        })

        .otherwise( {
            templateUrl : 'template-list-users',
            controller: 'UserListController'

        });
}

function UserFormController($scope, $routeParams, $window, userService) {

    $scope.langs = ['en','ru','hy'];
    $scope.user ={};
    $scope.$error = {};

    var currentUserID = $routeParams.id;
    if(currentUserID !== undefined){
        userService.get(currentUserID)
            .success(function(data){
                $scope.user = data;
            })
    }


    $scope.save = function($event ) {
        $scope.$error.firstnameRequired = isValid($scope.user.firstnameHy);
        $scope.$error.lastnameRequired = isValid($scope.user.lastnameHy);
        $scope.$error.userNameRequired = isValid($scope.user.username);
        $scope.$error.emailRequired = isValid($scope.user.email);
        $scope.$error.departmentRequired = isValid($scope.user.department);
        $scope.$error.prefLangRequired = isValid($scope.user.language);
        $scope.$error.roleRequired = isValid($scope.user.roleId);

        if (!($scope.$error.firstnameRequired || $scope.$error.lastnameRequired || $scope.$error.userNameRequired ||
            $scope.$error.emailRequired || $scope.$error.departmentRequired || $scope.$error.prefLangRequired || $scope.$error.roleRequired)) {



            if($scope.user.id === undefined){
                $scope.user.state = "PENDING";
                $scope.user.activationCode = generateUUID().toString();
                $scope.user.id = generateUUID();

                userService.add($scope.user)
                    .success(function(data) {
                        $scope.cancel();
                    });
            }else{
                userService.save($scope.user)
                    .success(function(data) {
                        $scope.cancel();
                    });
            }
        }
    }
    $scope.cancel = function() {
        $window.history.back();
    }

    function isValid(input){
        return(input === '' || input === undefined || input === null)
    }

    function generateUUID() {
        var d = new Date().getTime();
        var uuid = 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
            var r = (d + Math.random()*16)%16 | 0;
            d = Math.floor(d/16);
            return (c=='x' ? r : (r&0x3|0x8)).toString(16);
        });
        return uuid;
    };
}

function UserViewController($scope, $routeParams, $window, userService) {

    var currentUserID = $routeParams.id;
    if(currentUserID !== undefined){
        userService.get(currentUserID)
            .success(function(data){
                $scope.user = data;
            })
    }
    $scope.cancel = function() {
        $window.history.back();
    }
}

function UserListController($scope, $routeParams, userService) {
    $scope.userList =[];

    userService.list()
        .success(function(data){
            $scope.userList = data;
        })
}

function UserService($http) {

    function listUser() {
        return $http.post('list/');
    }

    function getUser(id) {
        return $http.post('get?id='+id);
    }

    function addUser(user) {
        return $http.post('add/', user);
    }

    function saveUser(user) {
        return $http.post('save/', user);
    }

    return {
        list :   listUser,
        get  :   getUser,
        add  :   addUser,
        save :   saveUser

    }
}
;