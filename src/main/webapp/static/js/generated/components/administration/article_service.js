PPCM.service('ArticleService', function($http, PPCMPermissions) {
    self = this;
    
    self.callList = function(page, order) {
        console.log('call ArticleService.list(...)');
        console.log('  page: ' + page);
        console.log('  order: ' + order);
        return $http.post('/private/administration/dictionaries/articles/list', { page: page, order: order });
    }
    self.callList.canCall = function(){return PPCMPermissions.indexOf('/private/administration/dictionaries/articles') > -1};
    
    self.callGet = function(articleId) {
        console.log('call ArticleService.get(...)');
        console.log('  articleId: ' + articleId);
        return $http.post('/private/administration/dictionaries/articles/get', { articleId: articleId });
    }
    self.callGet.canCall = function(){return PPCMPermissions.indexOf('/private/administration/dictionaries/articles') > -1};
    
    self.callCount = function() {
        console.log('call ArticleService.count(...)');
        return $http.post('/private/administration/dictionaries/articles/count', {  });
    }
    self.callCount.canCall = function(){return PPCMPermissions.indexOf('/private/administration/dictionaries/articles') > -1};
    
    self.callAdd = function(article) {
        console.log('call ArticleService.add(...)');
        console.log('  article: ' + article);
        return $http.post('/private/administration/dictionaries/articles/add', { article: article });
    }
    self.callAdd.canCall = function(){return PPCMPermissions.indexOf('/private/administration/dictionaries/articles') > -1};
    
    self.callSave = function(article) {
        console.log('call ArticleService.save(...)');
        console.log('  article: ' + article);
        return $http.post('/private/administration/dictionaries/articles/save', { article: article });
    }
    self.callSave.canCall = function(){return PPCMPermissions.indexOf('/private/administration/dictionaries/articles') > -1};
    
    return {
        list: self.callList,
        get: self.callGet,
        count: self.callCount,
        add: self.callAdd,
        save: self.callSave,
        _dummy_: undefined
    }
}
);

