angular.module('Test', ['ui.bootstrap'])
    .controller('C', function($scope, $http, $q , $timeout){
    $scope.inputValue = '';
    $scope.loader = function() {
        var searchValue = $scope.inputValue;
        var defered = $q.defer();

        $timeout(function(){
            defered.resolve($scope.loader2())
        }, 1000)

        return defered.promise;
    }

    $scope.loader2  = function() {
        var searchValue = $scope.inputValue;
        var ret = [];
        for (var i = 0; i<8; i++) {
            ret.push(searchValue + ' ' + i);
            ret.push(i + ' - ' +searchValue + ' ' + i);
        }

        return ret;
    }
})
.directive('my-autocomplete',function($scope, $http, $q , $timeout){

    })