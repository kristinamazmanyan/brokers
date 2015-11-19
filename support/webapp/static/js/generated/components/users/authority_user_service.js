PPCM.service('AuthorityUserService', function($http, PPCMPermissions) {
    self = this;
    
    self.callList = function(page, order) {
        console.log('call AuthorityUserService.list(...)');
        console.log('  page: ' + page);
        console.log('  order: ' + order);
        return $http.post('/private/users/list', { page: page, order: order });
    }
    self.callList.canCall = function(){return PPCMPermissions.indexOf('/private/users/list') > -1};
    
    self.callListByAuthorityId = function(page, order) {
        console.log('call AuthorityUserService.listByAuthorityId(...)');
        console.log('  page: ' + page);
        console.log('  order: ' + order);
        return $http.post('/private/users/list-by-authority-id', { page: page, order: order });
    }
    self.callListByAuthorityId.canCall = function(){return PPCMPermissions.indexOf('/private/users/list_by_authority_id') > -1};
    
    self.callListAuthorityByNameLike = function(nameLike) {
        console.log('call AuthorityUserService.listAuthorityByNameLike(...)');
        console.log('  nameLike: ' + nameLike);
        return $http.post('/private/users/list-authority-by-name-like', { nameLike: nameLike });
    }
    self.callListAuthorityByNameLike.canCall = function(){return PPCMPermissions.indexOf('/private/users') > -1};
    
    self.callListAuthorityByCodeLike = function(code) {
        console.log('call AuthorityUserService.listAuthorityByCodeLike(...)');
        console.log('  code: ' + code);
        return $http.post('/private/users/list-authority-by-code-like', { code: code });
    }
    self.callListAuthorityByCodeLike.canCall = function(){return PPCMPermissions.indexOf('/private/users') > -1};
    
    self.callRegister = function(user) {
        console.log('call AuthorityUserService.register(...)');
        console.log('  user: ' + user);
        return $http.post('/private/users/register', { user: user });
    }
    self.callRegister.canCall = function(){return PPCMPermissions.indexOf('/private/users/register') > -1};
    
    self.callRegisterByAuthorityId = function(user) {
        console.log('call AuthorityUserService.registerByAuthorityId(...)');
        console.log('  user: ' + user);
        return $http.post('/private/users/register-by-authority-id', { user: user });
    }
    self.callRegisterByAuthorityId.canCall = function(){return PPCMPermissions.indexOf('/private/users/register_by_authority_id') > -1};
    
    self.callUpdate = function(user) {
        console.log('call AuthorityUserService.update(...)');
        console.log('  user: ' + user);
        return $http.post('/private/users/update', { user: user });
    }
    self.callUpdate.canCall = function(){return PPCMPermissions.indexOf('/private/users/update') > -1};
    
    self.callUpdateByAuthorityId = function(user) {
        console.log('call AuthorityUserService.updateByAuthorityId(...)');
        console.log('  user: ' + user);
        return $http.post('/private/users/update-by-authority-id', { user: user });
    }
    self.callUpdateByAuthorityId.canCall = function(){return PPCMPermissions.indexOf('/private/users/update_by_authority_id') > -1};
    
    self.callGet = function(userId) {
        console.log('call AuthorityUserService.get(...)');
        console.log('  userId: ' + userId);
        return $http.post('/private/users/get', { userId: userId });
    }
    self.callGet.canCall = function(){return PPCMPermissions.indexOf('/private/users') > -1};
    
    self.callRoles = function() {
        console.log('call AuthorityUserService.roles(...)');
        return $http.post('/private/users/roles', {  });
    }
    self.callRoles.canCall = function(){return PPCMPermissions.indexOf('/private/users') > -1};
    
    self.callCount = function() {
        console.log('call AuthorityUserService.count(...)');
        return $http.post('/private/users/count', {  });
    }
    self.callCount.canCall = function(){return PPCMPermissions.indexOf('/private/users') > -1};
    
    self.callCountByAuthorityId = function() {
        console.log('call AuthorityUserService.countByAuthorityId(...)');
        return $http.post('/private/users/count-by-authority-id', {  });
    }
    self.callCountByAuthorityId.canCall = function(){return PPCMPermissions.indexOf('/private/users') > -1};
    
    return {
        list: self.callList,
        listByAuthorityId: self.callListByAuthorityId,
        listAuthorityByNameLike: self.callListAuthorityByNameLike,
        listAuthorityByCodeLike: self.callListAuthorityByCodeLike,
        register: self.callRegister,
        registerByAuthorityId: self.callRegisterByAuthorityId,
        update: self.callUpdate,
        updateByAuthorityId: self.callUpdateByAuthorityId,
        get: self.callGet,
        roles: self.callRoles,
        count: self.callCount,
        countByAuthorityId: self.callCountByAuthorityId,
        _dummy_: undefined
    }
}
);

