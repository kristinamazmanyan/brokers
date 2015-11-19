PPCM.config(function ($routeProvider) {
    $routeProvider
        .when('/edit/:id', {
            templateUrl: 'ProgramEditorView.html',
            controller: 'ProgramEditorController'

        })
        .when('/add/:year', {
            templateUrl: 'programAddEditorView.html',
            controller: 'ProgramAddEditorController'

        })
        .when('/list', {
            templateUrl: 'programListView.html',
            controller: 'ProgramListController'

        })
        .otherwise({
            templateUrl: 'programListView.html',
            controller: 'ProgramListController'

        });

}).controller("ProgramListController",   ["$scope", '$routeParams','$window' ,'ProgramService', ProgramListController])
  .controller("ProgramEditorController", ["$scope", '$routeParams', '$window','ProgramService', ProgramEditorController])
  .controller("ProgramAddEditorController", ["$scope", '$routeParams', '$window','ProgramService', ProgramAddEditorController])

function ProgramListController($scope, $routeParams,$window, ProgramService) {
    $scope.eAccount = ""
    $scope.year = '2015';
    $scope.index = 1;

    $scope.page = {index: 1, size: 15}
    $scope.order = {field: 'name_en', ascending: true}

    $scope.countByYear = function () {
        ProgramService.countByYear($scope.year)
            .success(function (data) {
                $scope.total = data;
                console.log("count by year: " +  $scope.total);
            })
            .error(function (data, status, headers, config) {
                console.log("Error " + data[0] + " Status" + status);
            })
    }

    $scope.loadPage = function () {
        $scope.page.index = $scope.index - 1;
        $scope.countByYear();
        ProgramService.listByYear($scope.page, $scope.order, $scope.year)
            .success(function (data) {
                $scope.programs = data;
                console.log("page data: " + data);
            })
            .error(function (data, status, headers, config) {
                console.log("Error " + data[0] + " Status" + status);
            })
    }

    $scope.loadPage();

    $scope.addProgram=function(){
        $window.location.href ='#/add/' + $scope.year ;
    };

}

function ProgramEditorController($scope, $routeParams,$window, ProgramService) {

    $scope.$error = {};
    ProgramService.retreiveByProgramId($routeParams.id)
        .success(function (data) {
            $scope.program = data;
            console.log(data);
        })
        .error(function (data, status, headers, config) {
            console.log("Error " + data[0] + " Status" + status);
        })

    $scope.loadAccounts = function () {
        ProgramService.listAccountsByProgramId($routeParams.id)
            .success(function (data) {
                $scope.accounts = data;
            })
            .error(function (data, status, headers, config) {
                console.log("Error " + data[0] + " Status" + status);
            })
    }

    $scope.showName=function(nameObject, lang){

        console.log(lang)

       if(nameObject.authorityNameEn.name != undefined )
       {
           return nameObject.authorityNameEn.name
       }
               else
       {

           if(lang == 'en')
           return nameObject.authorityNameEn;
           else if(lang = 'hy')
               return nameObject.authorityNameHy;
           else
           return nameObject.authorityNameRu;
       }
    }

    $scope.loadAccounts();

  // Typeahead Asynchron call.
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

            return ProgramService.listAuthorityByNameLike(val)
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
            return ProgramService.listAuthorityByCodeLike(val)
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


    $scope.saveAccount = function (data, index) {
        var authorityId;
        if (data.authorityInfo != undefined && data.accountNo != undefined) {
            if (data.authorityInfo.id == undefined)
                authorityId = $scope.accounts[index].authorityId;
            else
                authorityId = data.authorityInfo.id;

            var account = {};
            account.id = $scope.accounts[index].id;
            account.accountNo = data.accountNo;
            account.authorityId = authorityId;
            account.programId = $routeParams.id;

            ProgramService.saveAccount(account)
                .success(function (data) {
                    $window.location.reload();
                });


        }

    }

    $scope.removeAccount=function(index){
        ProgramService.deleteAccount($scope.accounts[index].id)
            .success(function(data) {
                $window.location.reload();
            });

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

    $scope.addAccount = function () {
        var flag = true;

        $scope.$error.authority = ($scope.selected === undefined || $scope.selected === '');
        $scope.$error.account   = ($scope.accountNo === undefined || $scope.accountNo === '');

        if (isValid($scope.selected)  && isValid($scope.accountNo )) {

            angular.forEach($scope.accounts, function (item) {
                if (item.authorityId == $scope.selected.id) {
                    if (item.accountNo == $scope.accountNo) {
                        alert("An authority exists with the same account number")
                        flag = false;
                    }
                }
            })
            if (flag == true) {
                var account={} ;
                account.id = generateUUID();
                account.accountNo =  $scope.accountNo;
                account.authorityId = $scope.selected.id;
                account.programId = $routeParams.id;

                ProgramService.addAccount(account)
                    .success(function (data) {
                        $window.location.reload();
                    });

            }
        }
    }


    $scope.saveChanges = function() {
        if( (/^\d+$/.test($scope.program.code))
            && isValid($scope.program.nameEn)
            && isValid($scope.program.nameRu)
            && isValid($scope.program.nameHy))
        {
            ProgramService.save($scope.program)
                .success(function (data) {
                    $window.location.reload();
                });
        }
    }

    function isValid(input){
        return (input !== '' &&  input !== undefined && input !== null)
    }

    $('form').validate({
        rules: {
            edit_code: {
                digits: true,
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
    });

}
function ProgramAddEditorController($scope, $routeParams, $window, ProgramService) {
    $scope.$error={};

    function generateUUID() {
        var d = new Date().getTime();
        var uuid = 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
            var r = (d + Math.random()*16)%16 | 0;
            d = Math.floor(d/16);
            return (c=='x' ? r : (r&0x3|0x8)).toString(16);
        });
        return uuid;
    };

    $scope.program={};
    $scope.program.year= $routeParams.year;
    $scope.program.id = generateUUID();
    $scope.program.descriptionEn ='';
    $scope.program.descriptionHy ='';
    $scope.program.descriptionRu ='';



    $scope.addProgram = function () {

        if ( (/^\d+$/.test($scope.program.code))
            && isValid($scope.program.nameEn)
            && isValid($scope.program.nameRu)
            && isValid($scope.program.nameHy))
        {
            ProgramService.add($scope.program)
                .success(function (data) {
                    $window.history.back();
                });
        }
    }

    function isValid(input){
        return (input !== '' &&  input !== undefined && input !== null)
    }

    $('form').validate({
        rules: {
            code: {
                digits: true,
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
    });
}