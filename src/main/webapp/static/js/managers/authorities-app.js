angular.module('ppcm')
    .config(['$routeProvider', '$locationProvider',AuthoritiesPageRouter])
    .service('AuthorityService',  AuthorityService)
    .controller('MainController', ['$scope', '$window', MainController])
    .controller('AuthoritySearchController', AuthoritySearchController)
    .controller('AuthorityEditController', AuthorityEditController)
    .controller('AuthorityViewController',  AuthorityViewController)

function AuthoritiesPageRouter($routeProvider) {
    var searchControllerCfg = {
        templateUrl : 'template-search',
        controller: 'AuthoritySearchController',
        controllerAs: 'search'
    }

    $routeProvider
        .when('/edit/:id?', {
            templateUrl : 'template-edit-authority',
            controller:  'AuthorityEditController'
        })
        .when('/view/:id?', {
            templateUrl : 'template-view-authority',
            controller: 'AuthorityViewController'
        })
        .when('/', searchControllerCfg)
        .otherwise( searchControllerCfg);

}
function AuthorityService($q, $http, $rootScope) {

    var PAGE_SIZE  = 15;

    var self = this;

    self.filter = {}
    self.data = {
        total:0,
        pages:0
    }

     self.search = function(filter, done) {
        filter.pageSize = PAGE_SIZE;
        filter.page = 0;

        $http.post('search/',  filter)
           .success(function(data, status, headers, config) {
                self.filter = filter;
                self.data.rows = data.rows;
                self.data.pages = Math.ceil((data.total || 0)/ PAGE_SIZE);
                self.data.total = data.total;
                self.data.page = 0 ;
                done();
        });

    }

    self.loadAuthority = function(code, ignoreCache) {
        var auth = false;
        if (typeof self.data.rows === 'array') {
            self.data.rows.forEach(function(index, element, arr ){
                if (element.id === parseInt(code)) {
                    auth = element;
                }
            })
            if (auth !== false && !ignoreCache) {
                var deferred = $q.defer();
                $timeout(function(){ deferred.resolve(auth) });
                return deferred.promise;
            }
        }
        return $http.post('get/'+ code);
    }

    self.loadPage = function(page, done) {
        var pageToLoad ;
        if (page === 'next' && self.data.page+1 < self.data.pages) {
            pageToLoad = self.data.page + 1;
        }
        else if (page === 'prev' && self.data.page > 0) {
            pageToLoad = self.data.page - 1;
        }
        else if (typeof page === 'number'){
            pageToLoad = page;
        }

        if (pageToLoad != undefined) {
            self.filter.page = pageToLoad;
            $http.post('search/',  self.filter)
                .success(function(data, status, headers, config) {
                    self.data.rows = data.rows;
                    done();
                });
            self.data.page = pageToLoad;
        }
      }

   }

function AuthoritySearchController($scope, AuthorityService, PageService) {

    $scope.filter = angular.copy(AuthorityService.filter);

    $scope.loading = false;

    function startLoading(){$scope.loading = true;}
    function loadFinished(){$scope.loading = false; }

    $scope.find = function() {
        startLoading();
        $scope.currentPage  = 0;
        $scope.data.rows = [];
        AuthorityService.search($scope.filter, loadFinished);
    }

    $scope.loadPage = function(){
        startLoading();
        AuthorityService.loadPage($scope.currentPage-1, loadFinished);
    }

    $scope.data = AuthorityService.data;
    PageService.setNavigationBreadcrumb([]);
    if (AuthorityService.filter.page != undefined){
        $scope.currentPage = AuthorityService.filter.page+1;
    }
    else {
        $scope.find();
    }
}

function AuthorityEditController($scope, $routeParams, authService) {
    authService
        .loadAuthority($routeParams.id)
        .then(function(data){
            $scope.info = data;
            updateBreadcrumb();
            console.log(data)
        })

    function updateBreadcrumb(){
        $scope.$parent.path = [{
            name : $scope.info.name,
            path : "#/view/"+$scope.info.code
        },
            {
                name : '',
                path : "#/edit/"+$scope.info.code
            }]
    }

}
function AuthorityViewController($scope, $routeParams, AuthorityService, PageService) {

    $scope.info = {};
    AuthorityService
        .loadAuthority($routeParams.id)
        .success(function(data){
            $scope.info = angular.copy(data);
            PageService.setNavigationBreadcrumb([data.shortName]);
            updateBreadcrumb();
        })
    //PageService.setNavigationBreadcrumb([]);
    function updateBreadcrumb(){
        $scope.$parent.path = [{
            name : $scope.info.name,
            path : "#/view/"+$scope.info.code
        }]
    }
}

function MainController ($scope, $window) {
    var self = this;
    $scope.path = [];

    self.activeTab = 'info';
    self.tabCss = function(tab){
        return self.activeTab == tab ? 'active' : '';
    }
    self.setTab = function(tab) {
        self.activeTab = tab;
    }

}