PPCM.service('PplanService', function($http, PPCMPermissions) {
    self = this;
    
    self.callList = function() {
        console.log('call PplanService.list(...)');
        return $http.post('/private/authorities/pplan/index/list', {  });
    }
    self.callList.canCall = function(){return PPCMPermissions.indexOf('/private/authorities/pplan/index') > -1};
    
    return {
        list: self.callList,
        _dummy_: undefined
    }
}
);

