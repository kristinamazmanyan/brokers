PPCM.service('ProfileService', function($http, PPCMPermissions) {
    self = this;
    
    self.callGet = function() {
        console.log('call ProfileService.get(...)');
        return $http.post('/private/profile/get', {  });
    }
    self.callGet.canCall = function(){return PPCMPermissions.indexOf('/private/profile') > -1};
    
    self.callChange = function(currentPassword, newPassword) {
        console.log('call ProfileService.change(...)');
        console.log('  currentPassword: ' + currentPassword);
        console.log('  newPassword: ' + newPassword);
        return $http.post('/private/profile/change', { currentPassword: currentPassword, newPassword: newPassword });
    }
    self.callChange.canCall = function(){return PPCMPermissions.indexOf('/private/profile') > -1};
    
    self.callSave = function(user) {
        console.log('call ProfileService.save(...)');
        console.log('  user: ' + user);
        return $http.post('/private/profile/save', { user: user });
    }
    self.callSave.canCall = function(){return PPCMPermissions.indexOf('/private/profile') > -1};
    
    return {
        get: self.callGet,
        change: self.callChange,
        save: self.callSave,
        _dummy_: undefined
    }
}
);

