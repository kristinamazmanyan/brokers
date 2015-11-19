PPCM.directive('iuPathNavigation', function($location) {
    return {
        restrict: 'A',
        link: function (scope, elem, attrs) {
            $(elem).html('here must be the path navigation component with '+attrs.iuPathNavigation + ' columns')
        }
    };
})
