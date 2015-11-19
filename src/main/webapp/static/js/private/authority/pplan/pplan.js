PPCM.controller('pplanController', function ($scope, $routeParams,PplanService) {

 /*  console.log(ProgramList);*/


/*
    var programMap = {};
    var nameAttr = 'name' + pageConstants.currentLang.charAt(0).toUpperCase() +  pageConstants.currentLang.charAt(1) ;
    ${f:jsonify(programList)}.forEach(function(el){
        programMap[el.id] = el[nameAttr] || ;
    })


    PPCM.value('localization').programName =programMap;


    {{ id | i18n:programMap:name:name_hy }}
    PPCM.value('DICT_PROGRAM_LIST', ${f:jsonify(programList)})
        .filter('programName', function(DICT_PROGRAM_LIST, pageConstants){

            return function(obj,attrName, failback) {
                var nameAttr = attrName + pageConstants.currentLang.charAt(0).toUpperCase() +  pageConstants.currentLang.charAt(1) ;
                return programMap[nameAttr] || obj[failback];
            }
        })*/

    PplanService.list().success(function(data){
        console.log(data);
    })

})