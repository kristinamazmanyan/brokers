/**
 * Created by gurgen.nersesyan on 6/2/2015.
 */
angular.module('ppcm')
    .config(['$routeProvider', '$locationProvider',CpvRoutes])
    .controller("CpvListController", ["$scope", '$routeParams','$filter','PageService','CpvService', CpvListController])
    .controller("CpvEditorController", ["$scope",'$routeParams', '$window','PageService','CpvService','uuid', CpvEditorController]) ;

function CpvRoutes($routeProvider, $locationProvider) {
    $routeProvider
        .when('/edit/:id', {
            templateUrl : 'cpvAddEditorView.html',
            controller: 'CpvEditorController'

        })
        .when('/add/:parentCode?', {
            templateUrl : 'cpvAddEditorView.html',
            controller: 'CpvEditorController'

        })
        .when('/list/:id', {
            templateUrl : 'cpvListView.html',
            controller: 'CpvListController',
            controllerAs: 'cpvListCtrl'
        })
        .otherwise( {
            templateUrl : 'cpvListView.html',
            controller: 'CpvListController',
            controllerAs: 'cpvListCtrl'
        });

}

function CpvEditorController($scope, $routeParams, $window,PageService,CpvService,uuid) {

    var currentCPVId = $routeParams.id;

    var parentCode  = $routeParams.parentCode;

    $scope.toDo=function () {
        if(parentCode != undefined){
            return false;
        }
        return true;
    }


    if (currentCPVId !== undefined) {
        CpvService.get(currentCPVId)
            .success(function (data, status, headers, config) {
                $scope.cpv = data;
            });
    }
    else {
        //console.log(currentCPVId);
        $scope.cpv = {
            id: uuid.v4(),
            parentId : parentCode
        }
    }
    $scope.$error = {};





    $scope.save = function() {
        $scope.$error.Hy = ($scope.cpv.nameHy ==="" || $scope.cpv.nameHy === undefined);
        $scope.$error.Ru = ($scope.cpv.nameRu ==="" || $scope.cpv.nameRu === undefined);
        $scope.$error.En = ($scope.cpv.nameEn ==="" || $scope.cpv.nameEn === undefined);

        if ( !($scope.$error.Hy || $scope.$error.Ru || $scope.$error.En )){
            CpvService.save($scope.cpv).success(function(data, status, headers, config) {
                $scope.cancel();
            });
            PageService.addAlertSuccess("Your Edit was saved");
        }
    }

    // Getting SPV list
    CpvService.list( parentCode)
        .success(function(data, status, headers, config) {
            $scope.cpvList = data;
        });

    $scope.add = function($event) {


        // Getting Code list in which we need to add child
        var codeList = [];
        for (var i = 0; i < $scope.cpvList.length; i++) {
            codeList[i] = $scope.cpvList[i].code;
        }

        //console.log("codeList "+codeList);

        var posNotNull ;


        parentCode = PageService.parent.name;
            for(var i = parentCode.length-1; i > 0; i--){
            if(parentCode[i] !== '0'){
                posNotNull = i;
                break;
            }
        }
        var childCodePos = [];
        for(var i = 0; i < codeList.length; i++){
            childCodePos[i] = codeList[i][posNotNull+1]
        }

        // Too much dirty, needs to be improved after correct requirement
        $scope.$error.codeRequired = ($scope.cpv.code === '' || $scope.cpv.code === undefined);
        if(!$scope.$error.codeRequired){
            $scope.$error.exist = ((posNotNull === parentCode.length-1) || ($scope.cpv.code.substr(0,posNotNull+1) !== parentCode.substr(0,posNotNull+1))
            || ($scope.cpv.code.substr(posNotNull+2,parentCode.length-posNotNull-2) !== parentCode.substr(posNotNull+2,parentCode.length-posNotNull-2))
            || ( childCodePos.indexOf($scope.cpv.code[posNotNull+1]) > -1) || ($scope.cpv.code === parentCode));

            $scope.$error.digit = !(/^\d{8}$/.test($scope.cpv.code));
            if($scope.$error.digit === true){
                $scope.$error.exist=false;
            }
        }

        $scope.$error.Hy = ($scope.cpv.nameHy ==="" || $scope.cpv.nameHy === undefined);
        $scope.$error.Ru = ($scope.cpv.nameRu ==="" || $scope.cpv.nameRu === undefined);
        $scope.$error.En = ($scope.cpv.nameEn ==="" || $scope.cpv.nameEn === undefined);

        if ( !($scope.$error.Hy || $scope.$error.Ru || $scope.$error.En || $scope.$error.codeRequired ||$scope.$error.digit || $scope.$error.exist)){

            CpvService.add($scope.cpv)
                .success(function(data, status, headers, config) {
                    $scope.cancel();
                });
            PageService.addAlertSuccess("Your Add was saved");
        }
    }



}

function CpvListController($scope, $routeParams, $filter, PageService,CpvService) {
    //var $scope = this;
    $scope.cpvList =[];
    $scope.lang = 'Hy';
    $scope.lang1 = 'Ru';
    $scope.lang2 = 'En';
    $scope.cpvName = function(index) {
        return $scope.cpvList[index]['name'+$scope.lang] ;
    }
    $scope.cpvName1 = function(index) {
        return $scope.cpvList[index]['name'+$scope.lang1] ;
    }
    $scope.cpvName2 = function(index) {
        return $scope.cpvList[index]['name'+$scope.lang2] ;
    }
    $scope.cpvPathName = function(index) {
        return $scope.path[index]['name'+$scope.lang] ;
    }
    $scope.last_0= function(cpv){
        return cpv.code[7] == "0";
    }
    $scope.nullList = function () {
        return $scope.cpvList.length === '0' ;
    }
    $scope.chkbxs = [
        {label:"Հայերեն", val:true, id:1 },
        {label:"Русский", val:false, id:2 },
        {label:"English", val:false, id:3 }
    ];
    $scope.$watch( "chkbxs" , function(n,o){
        $scope.trues = $filter("filter")( n , {val:true} );
        $scope.flag = $scope.trues.length;}, true );
    var showLang = function(id){
        switch (id){
            case 1: $scope.checkHy = true;
                break;
            case 2: $scope.checkRu = true;
                break;
            case 3: $scope.checkEn = true;
        }
    };
    var hideLang = function(id){
        switch (id){
            case 1: $scope.checkHy = false;
                break;
            case 2: $scope.checkRu = false;
                break;
            case 3: $scope.checkEn = false;
        }
    };
    $scope.disable = function(id){
        for (var i = 0; i < $scope.chkbxs.length; i++) {
            if ($scope.chkbxs[i].id == id  ) {
                if($scope.chkbxs[i].val == true){
                    showLang(id);
                }else{
                    hideLang(id);
                }
            }
        }
        if ($scope.flag == 1) {
            for (var i = 0; i < $scope.chkbxs.length; i++) {
                if ($scope.chkbxs[i].id == id && $scope.chkbxs[i].val == true) {
                    return true;
                }
            }
        }
        return false;
    };

    function setupBreadcrumb(path) {
        var bc = path.map(function(el){
            return {
                id: el.id,
                name : el.code,
                href : '#/list/'+el.id
            }
        })
        if (bc.length > 0) {
            bc.push({
                name : '',
                href : '#/add/'+bc[bc.length-1].id,
                class: 'glyphicon glyphicon-plus '
            })
        }
        bc.unshift({
            name : '',
            href : '#/',
            class: 'glyphicon glyphicon-th-list '
        })
        PageService.setNavigationBreadcrumb(bc)
       // $scope.path = data.path;
    }

    CpvService.list( $routeParams.id)
        .success(function(data, status, headers, config) {
            $scope.cpvList = data;


        });

    CpvService.path($routeParams.id)
        .success(function(data, status, headers, config) {
            setupBreadcrumb(data);
        });
}
