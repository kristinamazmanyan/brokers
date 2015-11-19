PPCM
    .directive('iuButtonBack', ['$window', function($window) {
        return {
            restrict: 'A',
            link: function (scope, elem, attrs) {
                elem.bind('click', function () {
                    $window.history.back();
                });
            }
        };
    }])

    .directive('iuHome', ['$window','pageConstants', function($window,pageConstants) {
        return {
            restrict: 'A',
            link: function (scope, elem, attrs) {
                elem.bind('click', function () {
                    $window.location.href = pageConstants.contextPath;
                });
            }
        };
    }])
    .directive('iuToggleWidth', function($cookies) {
        var KEY = 'ppcm.setting.width';

        function toggle() {
            var icon = $('.iu-toggle-width i');
            var isFull = icon.hasClass('glyphicon-fullscreen');
            //console.log(isFull)
            if (!isFull) {
                $cookies.remove(KEY);
            }
            else {
                $cookies.put(KEY, true);
            }
            icon.toggleClass('glyphicon-resize-small glyphicon-fullscreen');
            $('.container, .container-fluid').toggleClass('container-fluid container');
            return false;
        }

        if ( $cookies.get(KEY)) {
            setTimeout(toggle, 1);
        }

        return {
            restrict: 'C',
            link: function (scope, elem, attrs) {
                $(elem).bind('click', toggle)
            }
        };
    })
    .directive('iuLang', function(localization) {

        var langMap = localization.Locale;

        return {
            restrict: 'A',
            link: function (scope, elem, attrs) {
                $(elem).addClass('label label-default label-lang').text(langMap[attrs.iuLang])
                scope.$watch(function(){return attrs.iuLang}, function(newValue) {
                    $(elem).addClass('label label-default').text(langMap[newValue])
                })
            }
        };
    })
    .directive('iuSwitchLang', function($location) {
        return {
            restrict: 'A',
            link: function (scope, elem, attrs) {
                $(elem).bind('click', function(){
                    var hashPath = $location.path() ;
                    hashPath = hashPath === '' ? '' : '#' + hashPath;
                    window.location = attrs.href + hashPath ;
                    return false;
                })
            }
        };
    })
;
