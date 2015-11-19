function CommonHttpInterceptorFactory($q, $rootScope, $injector, pageConstants,PageService) {
// TODO refactor all common configuration
    var currentActiveCalls = 0;

    NProgress.configure({ trickleRate: 0.02, trickleSpeed: 100, parent: '#ngProgress-container', speed : 100, showSpinner:false });

    function showProgress() {
        currentActiveCalls++;
        if (currentActiveCalls == 1) {
            //progress().reset();
            NProgress.start();
        }
    }

    function hideProgress() {
        currentActiveCalls --;
        if (currentActiveCalls == 0) {
            NProgress.set(1);
        }
    }

    return {
        'request': function (config) {
            if (config.url.charAt(0) === '/') {
                config.url = pageConstants.contextPath + config.url;
            }
            showProgress();
            return config;
        }
        , 'responseError': function (response) {
            hideProgress();
            PageService.addAlertError(getMessage(CONNECTION_REFUSED,pageConstants.currentLang));
            return $q.reject(response);
        }
        , 'response': function (response) {
            hideProgress();
            if ('string' === typeof response.data) {
                if (response.data.indexOf('</html>') > 0) {
                    PageService.autoLogin();   //'You are logged out'
                    return $q.reject(response);
                }
                else {
                    return response;
                }
            }
            else if (response.status != 200) {

                return $q.reject(response)
            }

            else if (response.data.status === 0) {
                response.data = response.data.data;
                //PageService.addAlertSuccess()
                return response;
            }
            else if(response.data.status === 404){
                PageService.addAlertError(getMessage(STATUS_NOT_FOUND,pageConstants.currentLang));
                return $q.reject(response);
            } else if(response.data.status === 403) {
                PageService.addAlertError(getMessage(STATUS_ACCESS_DENIED,pageConstants.currentLang));
                return $q.reject(response);
            }else{

                PageService.addAlertError(getMessage(STATUS_ACCESS_DENIED,pageConstants.currentLang));
                // TODO add error handling if responseCode != 0
                return $q.reject(response);
            }
        }
    };

};

PPCM.config(["$httpProvider", function($httpProvider) {
        $httpProvider.interceptors.push('CommonHttpInterceptorFactory');
    }])
    .factory('CommonHttpInterceptorFactory',CommonHttpInterceptorFactory);
