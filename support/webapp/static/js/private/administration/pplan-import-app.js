/**
 * Created by artur.tsghunyan on 7/22/2015.
 */
angular.module('ppcm')
    .config(['$routeProvider', '$locationProvider',PplanImportPageRoutes]);

function PplanImportPageRoutes($routeProvider, $locationProvider) {
    $routeProvider

        .when('/', {
            templateUrl : 'pplanImportList.html'

        })

        .when('/add', {
            templateUrl : 'pplanImportAdd.html'

        })

        .otherwise( {
            templateUrl : 'pplanImportList.html'

        });
}
