PPCM
    .service('PageService', function ($rootScope,$timeout,$injector) {

        var self = this;



        $rootScope.navigationBreadcrumb = [];
        self.setNavigationBreadcrumb = function(bc) {
            var nav = [];
            var e = [];
            bc.forEach(function(el, i, arr) {
                if (typeof el === 'string') {
                    el = {name: el, href:'', class:''};
                }
                if (i==arr.length-1) {
                    el.listClass = el.listClass  ? el.listClass  +' active' : 'active'
                }

                    nav.push(el);
                if(el.name != ''){
                    self.parent = el;
                }
            });
            $rootScope.navigationBreadcrumb = nav;



        }

        $rootScope.alerts = [];

        var alert = {
            type: 'success'
        };
        self.addAlertError = function (masage) {
            $rootScope.alerts.push({type: 'danger', msg: masage});

        };



        $rootScope.closeAlert = function (index) {
            $rootScope.alerts.splice(index, 1);
        };
        self.addAlertSuccess = function (masage) {
            $rootScope.alerts.push({type: 'success', msg: masage});
            $timeout(function () {
                $rootScope.alerts.splice($rootScope.alerts.indexOf(alert), 1);
            }, 3000);

        };

        // modal window
        self.autoLogin = function (size) {
            $injector.get('$modal').open({
                animation: true,
                templateUrl: 'autoLoginDialog',
                controller: 'AutoLoginCtrl',
                size: size

            });
        }
    })
    .controller("PageCtrl", function (PageService, $scope, localization, $location) {


        var self = this;
        self.refs = function(name){ return localization[name] };
        self.refKeys = function(name){ return Object.keys(self.refs(name)) };
        self.$location = $location;
        self.path = function(defaultPath){ return $location.path() === '' ? defaultPath : $location.path()}


           // PPCMPermissions

    })
    .controller('AutoLoginCtrl', function ($scope, $modalInstance, $location, $http, $window, pageConstants,$q) {

        $scope.isHidden = false;
        $scope.isHiddenInf = false;
        $scope.isError = false;

        $scope.login = function () {

            var transform = function (data) {
                return $.param(data);
            }
            var data = {username: pageConstants.username, password: $scope.password};
            $http.post("/in", data, {
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
                            ,'X-AJAX-LOGIN' : '1'
                },
                transformRequest: transform
            }).success(function () {
                $scope.isHidden = true;
            }).error(function(data){
                $scope.isHiddenInf =true;
                $scope.isError = true;
                return $q.reject(data);
            })
        };
        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
            $window.location.href = $location.protocol() + "://" + $location.host() + ":" + $location.port() + pageConstants.contextPath;
        };

        $scope.ok = function(){
            $modalInstance.dismiss('ok');
        }
    });

