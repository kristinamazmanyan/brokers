PPCM.filter('dictionary', function(localization){
        return function(input, map){
            return localization[map][input];
        }
    })
    .filter('i18n', function(pageConstants){
        return function(obj,attrName, failback) {
            var nameAttr = attrName + pageConstants.currentLang.charAt(0).toUpperCase() +  pageConstants.currentLang.charAt(1) ;
            return obj[nameAttr] || obj[failback];
        }
    })