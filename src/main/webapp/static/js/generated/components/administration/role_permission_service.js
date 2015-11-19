PPCM.service('RolePermissionService', function($http, PPCMPermissions) {
    self = this;
    
    self.callRoles = function() {
        console.log('call RolePermissionService.roles(...)');
        return $http.post('/private/administration/dictionaries/roles/roles', {  });
    }
    self.callRoles.canCall = function(){return PPCMPermissions.indexOf('/private/administration/dictionaries/roles') > -1};
    
    self.callPermissions = function() {
        console.log('call RolePermissionService.permissions(...)');
        return $http.post('/private/administration/dictionaries/roles/permissions', {  });
    }
    self.callPermissions.canCall = function(){return PPCMPermissions.indexOf('/private/administration/dictionaries/roles') > -1};
    
    self.callPermissionsByRole = function(roleId) {
        console.log('call RolePermissionService.permissionsByRole(...)');
        console.log('  roleId: ' + roleId);
        return $http.post('/private/administration/dictionaries/roles/permissions-by-role', { roleId: roleId });
    }
    self.callPermissionsByRole.canCall = function(){return PPCMPermissions.indexOf('/private/administration/dictionaries/roles') > -1};
    
    self.callAddRole = function(role) {
        console.log('call RolePermissionService.addRole(...)');
        console.log('  role: ' + role);
        return $http.post('/private/administration/dictionaries/roles/add-role', { role: role });
    }
    self.callAddRole.canCall = function(){return PPCMPermissions.indexOf('/private/administration/dictionaries/roles') > -1};
    
    self.callSaveRole = function(role) {
        console.log('call RolePermissionService.saveRole(...)');
        console.log('  role: ' + role);
        return $http.post('/private/administration/dictionaries/roles/save-role', { role: role });
    }
    self.callSaveRole.canCall = function(){return PPCMPermissions.indexOf('/private/administration/dictionaries/roles') > -1};
    
    self.callRemove = function(roleId) {
        console.log('call RolePermissionService.remove(...)');
        console.log('  roleId: ' + roleId);
        return $http.post('/private/administration/dictionaries/roles/remove', { roleId: roleId });
    }
    self.callRemove.canCall = function(){return PPCMPermissions.indexOf('/private/administration/dictionaries/roles') > -1};
    
    self.callAddPermission = function(roleId, permissionId) {
        console.log('call RolePermissionService.addPermission(...)');
        console.log('  roleId: ' + roleId);
        console.log('  permissionId: ' + permissionId);
        return $http.post('/private/administration/dictionaries/roles/add-permission', { roleId: roleId, permissionId: permissionId });
    }
    self.callAddPermission.canCall = function(){return PPCMPermissions.indexOf('/private/administration/dictionaries/roles') > -1};
    
    self.callRemovePermission = function(roleId, permissionId) {
        console.log('call RolePermissionService.removePermission(...)');
        console.log('  roleId: ' + roleId);
        console.log('  permissionId: ' + permissionId);
        return $http.post('/private/administration/dictionaries/roles/remove-permission', { roleId: roleId, permissionId: permissionId });
    }
    self.callRemovePermission.canCall = function(){return PPCMPermissions.indexOf('/private/administration/dictionaries/roles') > -1};
    
    self.callClearPermissions = function(roleId) {
        console.log('call RolePermissionService.clearPermissions(...)');
        console.log('  roleId: ' + roleId);
        return $http.post('/private/administration/dictionaries/roles/clear-permissions', { roleId: roleId });
    }
    self.callClearPermissions.canCall = function(){return PPCMPermissions.indexOf('/private/administration/dictionaries/roles') > -1};
    
    return {
        roles: self.callRoles,
        permissions: self.callPermissions,
        permissionsByRole: self.callPermissionsByRole,
        addRole: self.callAddRole,
        saveRole: self.callSaveRole,
        remove: self.callRemove,
        addPermission: self.callAddPermission,
        removePermission: self.callRemovePermission,
        clearPermissions: self.callClearPermissions,
        _dummy_: undefined
    }
}
);

