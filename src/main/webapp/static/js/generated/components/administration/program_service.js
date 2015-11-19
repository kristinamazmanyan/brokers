PPCM.service('ProgramService', function($http, PPCMPermissions) {
    self = this;
    
    self.callRetreiveByProgramId = function(programId) {
        console.log('call ProgramService.retreiveByProgramId(...)');
        console.log('  programId: ' + programId);
        return $http.post('/private/administration/dictionaries/programs/retreive-by-program-id', { programId: programId });
    }
    self.callRetreiveByProgramId.canCall = function(){return PPCMPermissions.indexOf('/private/administration/dictionaries/programs') > -1};
    
    self.callList = function(page, order) {
        console.log('call ProgramService.list(...)');
        console.log('  page: ' + page);
        console.log('  order: ' + order);
        return $http.post('/private/administration/dictionaries/programs/list', { page: page, order: order });
    }
    self.callList.canCall = function(){return PPCMPermissions.indexOf('/private/administration/dictionaries/programs') > -1};
    
    self.callListByYear = function(page, order, year) {
        console.log('call ProgramService.listByYear(...)');
        console.log('  page: ' + page);
        console.log('  order: ' + order);
        console.log('  year: ' + year);
        return $http.post('/private/administration/dictionaries/programs/list-by-year', { page: page, order: order, year: year });
    }
    self.callListByYear.canCall = function(){return PPCMPermissions.indexOf('/private/administration/dictionaries/programs') > -1};
    
    self.callCountByYear = function(year) {
        console.log('call ProgramService.countByYear(...)');
        console.log('  year: ' + year);
        return $http.post('/private/administration/dictionaries/programs/count-by-year', { year: year });
    }
    self.callCountByYear.canCall = function(){return PPCMPermissions.indexOf('/private/administration/dictionaries/programs') > -1};
    
    self.callAdd = function(program) {
        console.log('call ProgramService.add(...)');
        console.log('  program: ' + program);
        return $http.post('/private/administration/dictionaries/programs/add', { program: program });
    }
    self.callAdd.canCall = function(){return PPCMPermissions.indexOf('/private/administration/dictionaries/programs') > -1};
    
    self.callSave = function(program) {
        console.log('call ProgramService.save(...)');
        console.log('  program: ' + program);
        return $http.post('/private/administration/dictionaries/programs/save', { program: program });
    }
    self.callSave.canCall = function(){return PPCMPermissions.indexOf('/private/administration/dictionaries/programs') > -1};
    
    self.callListAccountsByProgramId = function(programId) {
        console.log('call ProgramService.listAccountsByProgramId(...)');
        console.log('  programId: ' + programId);
        return $http.post('/private/administration/dictionaries/programs/list-accounts-by-program-id', { programId: programId });
    }
    self.callListAccountsByProgramId.canCall = function(){return PPCMPermissions.indexOf('/private/administration/dictionaries/programs') > -1};
    
    self.callListAuthorityByNameLike = function(nameLike) {
        console.log('call ProgramService.listAuthorityByNameLike(...)');
        console.log('  nameLike: ' + nameLike);
        return $http.post('/private/administration/dictionaries/programs/list-authority-by-name-like', { nameLike: nameLike });
    }
    self.callListAuthorityByNameLike.canCall = function(){return PPCMPermissions.indexOf('/private/administration/dictionaries/programs') > -1};
    
    self.callListAuthorityByCodeLike = function(code) {
        console.log('call ProgramService.listAuthorityByCodeLike(...)');
        console.log('  code: ' + code);
        return $http.post('/private/administration/dictionaries/programs/list-authority-by-code-like', { code: code });
    }
    self.callListAuthorityByCodeLike.canCall = function(){return PPCMPermissions.indexOf('/private/administration/dictionaries/programs') > -1};
    
    self.callAddAccount = function(account) {
        console.log('call ProgramService.addAccount(...)');
        console.log('  account: ' + account);
        return $http.post('/private/administration/dictionaries/programs/add-account', { account: account });
    }
    self.callAddAccount.canCall = function(){return PPCMPermissions.indexOf('/private/administration/dictionaries/programs') > -1};
    
    self.callSaveAccount = function(account) {
        console.log('call ProgramService.saveAccount(...)');
        console.log('  account: ' + account);
        return $http.post('/private/administration/dictionaries/programs/save-account', { account: account });
    }
    self.callSaveAccount.canCall = function(){return PPCMPermissions.indexOf('/private/administration/dictionaries/programs') > -1};
    
    self.callDeleteAccount = function(accountId) {
        console.log('call ProgramService.deleteAccount(...)');
        console.log('  accountId: ' + accountId);
        return $http.post('/private/administration/dictionaries/programs/delete-account', { accountId: accountId });
    }
    self.callDeleteAccount.canCall = function(){return PPCMPermissions.indexOf('/private/administration/dictionaries/programs') > -1};
    
    return {
        retreiveByProgramId: self.callRetreiveByProgramId,
        list: self.callList,
        listByYear: self.callListByYear,
        countByYear: self.callCountByYear,
        add: self.callAdd,
        save: self.callSave,
        listAccountsByProgramId: self.callListAccountsByProgramId,
        listAuthorityByNameLike: self.callListAuthorityByNameLike,
        listAuthorityByCodeLike: self.callListAuthorityByCodeLike,
        addAccount: self.callAddAccount,
        saveAccount: self.callSaveAccount,
        deleteAccount: self.callDeleteAccount,
        _dummy_: undefined
    }
}
);

