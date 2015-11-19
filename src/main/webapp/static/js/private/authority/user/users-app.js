/**
 * Created by artur.tsghunyan on 6/29/2015.
 */
angular.module('ppcm')
    .config(['$routeProvider', '$locationProvider',UserPageRoutes])
    .controller("UserListController", ["$scope", '$routeParams','AuthorityUserService', UserListController])
    .controller("UserFormController", ["$scope","$filter",'$routeParams', '$window', 'AuthorityUserService', UserFormController])
    .controller("UserViewController", ["$scope", '$routeParams', '$window', 'AuthorityUserService', UserViewController]);

function UserPageRoutes($routeProvider, $locationProvider) {
    $routeProvider

        .when('/list', {
            templateUrl : 'template-list-users',
            controller: 'UserListController'

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

function UserFormController($scope, $filter, $routeParams, $window, AuthorityUserService) {

    $scope.langs = ['en','ru','hy'];
    $scope.user ={};
    $scope.$error = {};
    var authorityId;
    AuthorityUserService.roles()
        .success(function(data){
            $scope.roles = data;
        })


    $scope.getSuperPermission = function(){
       return AuthorityUserService.register.canCall();
    }

    var currentUserID = $routeParams.id;
    if(currentUserID !== undefined){
        AuthorityUserService.get(currentUserID)
            .success(function(data){
                console.log(data);
                $scope.user = data;
                $scope.selected =$filter ("i18n") (data, 'authorityName');
                authorityId = data.authorityId;
            })
    }


    var thisId = generateUUID();
    $scope.save = function($event ) {

        var errorRole = isValid($scope.user.roleId);
        var errorNameHy = isValid($scope.user.forenameHy);
        var errorNameEn = isValid($scope.user.forenameEn);
        var errorNameRu = isValid($scope.user.forenameRu);
        var errorLastNameHy = isValid($scope.user.surnameHy);
        var errorLastNameEn = isValid($scope.user.surnameEn);
        var errorLastNameRu = isValid($scope.user.surnameRu);
        var errorUsername = isValid($scope.user.username);
        var errorEmail = isValid($scope.user.email);/*
        var errorPhone = isValid($scope.user.phone)|| (!(/^\d+$/.test($scope.user.phone)));
        var errorPhoneMobile = isValid($scope.user.phoneMobile)|| (!(/^\d+$/.test($scope.user.phoneMobile)));*/
        if($scope.user.id === undefined){
            $scope.user.state = "PENDING"};
        var errorState = isValid($scope.user.state);

        var errorLanguage = isValid($scope.user.language);


        if (!(errorRole || errorNameHy || errorNameEn || errorNameRu || errorLastNameHy || errorLastNameEn || errorLastNameRu
            || errorUsername || errorEmail  || errorState  || errorLanguage )) {


            if($scope.user.id === undefined){
                $scope.user.id = thisId;
                if(AuthorityUserService.register.canCall()){
                    $scope.user.authorityId = $scope.selected.id;
                    AuthorityUserService.register($scope.user)
                        .success(function(data) {
                            $scope.cancel();
                        });
                } else if(AuthorityUserService.registerByAuthorityId.canCall()){
                    AuthorityUserService.registerByAuthorityId($scope.user)
                        .success(function(data) {
                            $scope.cancel();
                        });
                }


            }else{
                if(AuthorityUserService.update.canCall()){
                    $scope.user.authorityId = $scope.selected.id || authorityId;
                AuthorityUserService.update($scope.user)
                    .success(function(data) {
                        $scope.cancel();
                    });}
                else if(AuthorityUserService.updateByAuthorityId.canCall()) {
                    AuthorityUserService.updateByAuthorityId($scope.user)
                    .success(function(data) {
                        $scope.cancel();
                    });
                }
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

    $scope.getAuthorities = function (val) {
        var englishAlphabet = /[A-Za-z ]/g;
        var armenianAlphabet = /[Ա-Ֆա-ֆ]/g;
        var russianAlphabet = ""
        var lang = "";


        var name = function (item) {
            if (lang == 'hy') {
                return item.nameHy;
            }
            else if (lang == 'en') {
                return item.nameEn;
            }
            else if (lang == 'ru') {
                return item.nameRu;
            }
        }


        if (isNaN(val)) {

            if (armenianAlphabet.test(val)) {
                lang = 'hy'
            }
            else if (englishAlphabet.test(val)) {
                lang = 'en'
            }
            else if (russianAlphabet.test(val)) {
                lang = 'ru'
            }

            return AuthorityUserService.listAuthorityByNameLike(val)
                .then(function (data) {
                    var result = [];
                    angular.forEach(data.data, function (item) {
                        var obj = {};
                        obj.name = name(item);
                        obj.nameEn = item.nameEn;
                        obj.nameHy = item.nameHy;
                        obj.nameRu = item.nameRu;
                        obj.id = item.id;
                        result.push(obj);
                    })

                    return result;
                })
        } else {
            return AuthorityUserService.listAuthorityByCodeLike(val)
                .then(function (data) {
                    var result = [];
                    angular.forEach(data.data, function (item) {
                        var obj = {};
                        obj.name = item.nameHy + ', ' + item.nameEn + ', ' +
                            item.nameRu;
                        obj.nameEn = item.nameEn;
                        obj.nameHy = item.nameHy;
                        obj.nameRu = item.nameRu;
                        obj.id = item.id;
                        result.push(obj);
                    })

                    return result;
                })
        }

    }

    // Form Validation
   /* $('form').validate({
        rules: {
            role: {
                required: true
            },
            nameHy:{
                required: true
            },
            nameEn:{
                required: true
            },
            nameRu:{
                required: true
            },
            lastnameHy:{
                required: true
            },
            lastnameEn:{
                required: true
            },
            lastnameRu:{
                required: true
            },
            username:{
                required: true
            },
            email:{
                required: true,
                email: true
            },
            phone:{
                required: true,
                digits: true
            },
            phoneMobile:{
                required: true,
                digits: true
            },
            state:{
                required: true
            },
            lang:{
                required: true
            },
            accountAuthority:{
                required:true
            }
        },
        highlight: function(element) {
            $(element).closest('.form-group').addClass('has-error');
        },
        unhighlight: function(element) {
            $(element).closest('.form-group').removeClass('has-error');
        },
        errorElement: 'span',
        errorClass: 'help-block',
        errorPlacement: function(error, element) {
            if(element.parent('.input-group').length) {
                error.insertAfter(element.parent());
            } else {
                error.insertAfter(element);
            }
        }
    });*/

}

function UserViewController($scope, $routeParams, $window, AuthorityUserService) {

    var currentUserID = $routeParams.id;
    if(currentUserID !== undefined){
        AuthorityUserService.get(currentUserID)
            .success(function(data){
                $scope.user = data;
            })
    }
    $scope.cancel = function() {
        $window.history.back();
    }
}

function UserListController($scope, $routeParams, AuthorityUserService) {
    $scope.role='';

    $scope.list = function () {

        if(AuthorityUserService.list.canCall()) {

            // Getting total count of Users in Table
            AuthorityUserService.count()
                .success(function (data) {
                    console.log("Count = " + data);
                    $scope.totalCount = data;
                })
            $scope.itemsPerPage = 10;
            $scope.page = {index: ($scope.currentPage - 1), size: $scope.itemsPerPage};
            $scope.order = {field: 'id', ascending: true}

            $scope.userList = [];
            AuthorityUserService.list($scope.page, $scope.order)
                .success(function (data) {
                    console.log(data);
                    $scope.userList = data;
                })
        }
        else if (AuthorityUserService.listByAuthorityId.canCall()){
            // Getting count of Users By Authority ID in Table
            AuthorityUserService.countByAuthorityId()
                .success(function (data) {
                    console.log("Count = " + data);
                    $scope.totalCount = data;
                })
            $scope.itemsPerPage = 10;
            $scope.page = {index: ($scope.currentPage - 1), size: $scope.itemsPerPage};
            $scope.order = {field: 'id', ascending: true};

            $scope.userList = [];
            AuthorityUserService.listByAuthorityId($scope.page, $scope.order)
                .success(function (data) {
                    console.log(data);
                    $scope.userList = data;
                })

        }
    }

    $scope.setRole=function(value){
        $scope.role= value;
    }

};