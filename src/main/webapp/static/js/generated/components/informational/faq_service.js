PPCM.service('FaqService', function($http, PPCMPermissions) {
    self = this;
    
    self.callList = function() {
        console.log('call FaqService.list(...)');
        return $http.post('/public/faq/list', {  });
    }
    self.callList.canCall = function(){return PPCMPermissions.indexOf('/public/faq/list') > -1};
    
    self.callAdd = function(faq) {
        console.log('call FaqService.add(...)');
        console.log('  faq: ' + faq);
        return $http.post('/public/faq/add', { faq: faq });
    }
    self.callAdd.canCall = function(){return PPCMPermissions.indexOf('/public/faq') > -1};
    
    self.callSave = function(faq) {
        console.log('call FaqService.save(...)');
        console.log('  faq: ' + faq);
        return $http.post('/public/faq/save', { faq: faq });
    }
    self.callSave.canCall = function(){return PPCMPermissions.indexOf('/public/faq') > -1};
    
    self.callUp = function(id) {
        console.log('call FaqService.up(...)');
        console.log('  id: ' + id);
        return $http.post('/public/faq/up', { id: id });
    }
    self.callUp.canCall = function(){return PPCMPermissions.indexOf('/public/faq') > -1};
    
    self.callDown = function(id) {
        console.log('call FaqService.down(...)');
        console.log('  id: ' + id);
        return $http.post('/public/faq/down', { id: id });
    }
    self.callDown.canCall = function(){return PPCMPermissions.indexOf('/public/faq') > -1};
    
    self.callRemove = function(id) {
        console.log('call FaqService.remove(...)');
        console.log('  id: ' + id);
        return $http.post('/public/faq/remove', { id: id });
    }
    self.callRemove.canCall = function(){return PPCMPermissions.indexOf('/public/faq') > -1};
    
    return {
        list: self.callList,
        add: self.callAdd,
        save: self.callSave,
        up: self.callUp,
        down: self.callDown,
        remove: self.callRemove,
        _dummy_: undefined
    }
}
);

