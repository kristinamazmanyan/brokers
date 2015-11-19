PPCM.service('DepartmentUserService', function($http, PPCMPermissions) {
    self = this;
    
    self.callDepartment = function() {
        console.log('call DepartmentUserService.department(...)');
        return $http.post('/private/authorities/department/department', {  });
    }
    self.callDepartment.canCall = function(){return PPCMPermissions.indexOf('/private/authorities/department') > -1};
    
    self.callUser = function() {
        console.log('call DepartmentUserService.user(...)');
        return $http.post('/private/authorities/department/user', {  });
    }
    self.callUser.canCall = function(){return PPCMPermissions.indexOf('/private/authorities/department') > -1};
    
    self.callUserByDepartment = function(departmentId) {
        console.log('call DepartmentUserService.userByDepartment(...)');
        console.log('  departmentId: ' + departmentId);
        return $http.post('/private/authorities/department/user-by-department', { departmentId: departmentId });
    }
    self.callUserByDepartment.canCall = function(){return PPCMPermissions.indexOf('/private/authorities/department') > -1};
    
    self.callAddDepartment = function(department) {
        console.log('call DepartmentUserService.addDepartment(...)');
        console.log('  department: ' + department);
        return $http.post('/private/authorities/department/add-department', { department: department });
    }
    self.callAddDepartment.canCall = function(){return PPCMPermissions.indexOf('/private/authorities/department') > -1};
    
    self.callSaveDepartment = function(department) {
        console.log('call DepartmentUserService.saveDepartment(...)');
        console.log('  department: ' + department);
        return $http.post('/private/authorities/department/save-department', { department: department });
    }
    self.callSaveDepartment.canCall = function(){return PPCMPermissions.indexOf('/private/authorities/department') > -1};
    
    self.callRemove = function(departmentId) {
        console.log('call DepartmentUserService.remove(...)');
        console.log('  departmentId: ' + departmentId);
        return $http.post('/private/authorities/department/remove', { departmentId: departmentId });
    }
    self.callRemove.canCall = function(){return PPCMPermissions.indexOf('/private/authorities/department') > -1};
    
    self.callAddUser = function(departmentId, userId) {
        console.log('call DepartmentUserService.addUser(...)');
        console.log('  departmentId: ' + departmentId);
        console.log('  userId: ' + userId);
        return $http.post('/private/authorities/department/add-user', { departmentId: departmentId, userId: userId });
    }
    self.callAddUser.canCall = function(){return PPCMPermissions.indexOf('/private/authorities/department') > -1};
    
    self.callRemoveUser = function(departmentId, userId) {
        console.log('call DepartmentUserService.removeUser(...)');
        console.log('  departmentId: ' + departmentId);
        console.log('  userId: ' + userId);
        return $http.post('/private/authorities/department/remove-user', { departmentId: departmentId, userId: userId });
    }
    self.callRemoveUser.canCall = function(){return PPCMPermissions.indexOf('/private/authorities/department') > -1};
    
    self.callClearUser = function(departmentId) {
        console.log('call DepartmentUserService.clearUser(...)');
        console.log('  departmentId: ' + departmentId);
        return $http.post('/private/authorities/department/clear-user', { departmentId: departmentId });
    }
    self.callClearUser.canCall = function(){return PPCMPermissions.indexOf('/private/authorities/department') > -1};
    
    return {
        department: self.callDepartment,
        user: self.callUser,
        userByDepartment: self.callUserByDepartment,
        addDepartment: self.callAddDepartment,
        saveDepartment: self.callSaveDepartment,
        remove: self.callRemove,
        addUser: self.callAddUser,
        removeUser: self.callRemoveUser,
        clearUser: self.callClearUser,
        _dummy_: undefined
    }
}
);

