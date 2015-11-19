PPCM.service('PlanImportService', function($http, PPCMPermissions) {
    self = this;
    
    self.callList = function(page, order) {
        console.log('call PlanImportService.list(...)');
        console.log('  page: ' + page);
        console.log('  order: ' + order);
        return $http.post('/private/administration/pplan-import/list', { page: page, order: order });
    }
    self.callList.canCall = function(){return PPCMPermissions.indexOf('/private/administration/pplan-import') > -1};
    
    return {
        list: self.callList,
        _dummy_: undefined
    }
}
);

