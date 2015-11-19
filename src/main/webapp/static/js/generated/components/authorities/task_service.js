PPCM.service('TaskService', function($http, PPCMPermissions) {
    self = this;
    
    self.callList = function(page) {
        console.log('call TaskService.list(...)');
        console.log('  page: ' + page);
        return $http.post('/private/authorities/tasks/list', { page: page });
    }
    self.callList.canCall = function(){return PPCMPermissions.indexOf('/private/authorities/tasks') > -1};
    
    self.callCountByAssigneeId = function() {
        console.log('call TaskService.countByAssigneeId(...)');
        return $http.post('/private/authorities/tasks/count-by-assignee-id', {  });
    }
    self.callCountByAssigneeId.canCall = function(){return PPCMPermissions.indexOf('/private/authorities/tasks') > -1};
    
    return {
        list: self.callList,
        countByAssigneeId: self.callCountByAssigneeId,
        _dummy_: undefined
    }
}
);

