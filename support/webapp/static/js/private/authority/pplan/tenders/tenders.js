/**
 * Created by artur.tsghunyan on 7/27/2015.
 */

angular.module('ppcm')
    .config(function($routeProvider) {
        $routeProvider

        .when('/', {
            templateUrl : 'contractList'

        })

        .when('/add', {
            templateUrl : 'addContract'

        })

        .otherwise( {
            templateUrl : 'contractList'

        });
    })

