angular.module('ppcm')
    .directive('pwCheck', [PasswordVerify])
    .directive('checkLanguage', CheckLanguage)
    .controller("ProfileEditorController", ["$scope", '$routeParams', '$window', 'ProfileService', 'pageConstants','PageService', ProfileEditorController]);


function CheckLanguage() {
    return {
        require: 'ngModel',
        link: function (scope, element, attrs, ngModel) {
            return (attrs.checkLanguage == scope.preferredLang);
        }
    };
}

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


function ProfileEditorController($scope, $routeParams, $window, ProfileService, pageConstants,PageService) {


    $scope.currentPassword;
    $scope.password;
    $scope.isError = false;


    ProfileService.get()
        .success(function (data, status, headers, config) {
            $scope.profile = data;
        });

    $scope.changePassword = function () {
        console.log($scope.currentPassword);
        if($scope.currentPassword === undefined){
            PageService.addAlertError(getMessage(profilePasswordIncorrectMessages,pageConstants.currentLang));
            return;
        }
        ProfileService.change($scope.currentPassword, $scope.password)
            .success(function (data, status, headers, config) {
                if(data === 'true'){

                    PageService.addAlertSuccess(getMessage(profilePasswordCorrectChanges,pageConstants.currentLang))
                }else{
                    PageService.addAlertError(getMessage(profilePasswordIncorrectMessages,pageConstants.currentLang));

                }
            })


    }

    $scope.update = function () {
        ProfileService.save($scope.profile)
            .success(function (data, status, headers, config) {
                PageService.addAlertError(getMessage(profileSavedSuccess,pageConstants.currentLang));
            });

    }

    $scope.cancel = function () {
        $window.location.href = pageConstants.contextPath;
    }

}
