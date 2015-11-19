PPCM.service('CpvService', function($http, PPCMPermissions) {
    self = this;
    
    self.callList = function(parent) {
        console.log('call CpvService.list(...)');
        console.log('  parent: ' + parent);
        return $http.post('/private/administration/dictionaries/cpv/list', { parent: parent });
    }
    self.callList.canCall = function(){return PPCMPermissions.indexOf('/private/administration/dictionaries/cpv') > -1};
    
    self.callGet = function(id) {
        console.log('call CpvService.get(...)');
        console.log('  id: ' + id);
        return $http.post('/private/administration/dictionaries/cpv/get', { id: id });
    }
    self.callGet.canCall = function(){return PPCMPermissions.indexOf('/private/administration/dictionaries/cpv') > -1};
    
    self.callPath = function(id) {
        console.log('call CpvService.path(...)');
        console.log('  id: ' + id);
        return $http.post('/private/administration/dictionaries/cpv/path', { id: id });
    }
    self.callPath.canCall = function(){return PPCMPermissions.indexOf('/private/administration/dictionaries/cpv') > -1};
    
    self.callCount = function() {
        console.log('call CpvService.count(...)');
        return $http.post('/private/administration/dictionaries/cpv/count', {  });
    }
    self.callCount.canCall = function(){return PPCMPermissions.indexOf('/private/administration/dictionaries/cpv') > -1};
    
    self.callAdd = function(cpv) {
        console.log('call CpvService.add(...)');
        console.log('  cpv: ' + cpv);
        return $http.post('/private/administration/dictionaries/cpv/add', { cpv: cpv });
    }
    self.callAdd.canCall = function(){return PPCMPermissions.indexOf('/private/administration/dictionaries/cpv') > -1};
    
    self.callSave = function(cpvcode) {
        console.log('call CpvService.save(...)');
        console.log('  cpvcode: ' + cpvcode);
        return $http.post('/private/administration/dictionaries/cpv/save', { cpvcode: cpvcode });
    }
    self.callSave.canCall = function(){return PPCMPermissions.indexOf('/private/administration/dictionaries/cpv') > -1};
    
    return {
        list: self.callList,
        get: self.callGet,
        path: self.callPath,
        count: self.callCount,
        add: self.callAdd,
        save: self.callSave,
        _dummy_: undefined
    }
}
);

