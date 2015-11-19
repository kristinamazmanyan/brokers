/**
 * Created by Art on 06.08.2015.
 */
angular.module('ppcm')
    .directive('pwCheck', [PasswordVerify])
    .controller("UserActivationController", function($scope,$window,$location,PageService,ActivationService){
        var ticket = /^.*\?ticket=(.*)$/;
        ticket.exec(window.location.href)[1];
        var dateInf = window.location.href.split('/')[7];

        $scope.activation = function () {
            var validate = ($scope.password !=="" || $scope.password !== undefined);
            if($scope.password === $scope.password_c && validate) {
                ActivationService.resetPassword(ticket.exec(window.location.href)[1], $scope.password)
                    .success(function (data, status, headers, config) {
                        PageService.addAlertSuccess("Your Activation was finished");
                        $window.location.href = "/ppcm";
                    });
            }
        }

    });




function PasswordVerify() {
    return {
        require: 'ngModel',
        link: function (scope, elem, attrs, ctrl) {
            var firstPassword = '#' + attrs.pwCheck;
            elem.add(firstPassword).on('keyup', function () {
                scope.$apply(function () {
                    var v = elem.val() === $(firstPassword).val();
                    ctrl.$setValidity('pwmatch', v);
                });
            });
        }
    }
};