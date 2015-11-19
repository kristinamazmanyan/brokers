PPCM.service('ActivationService', function($http, PPCMPermissions) {
    self = this;
    
    self.callResetPassword = function(ticket, password) {
        console.log('call ActivationService.resetPassword(...)');
        console.log('  ticket: ' + ticket);
        console.log('  password: ' + password);
        return $http.post('/public/users/activation/reset-password', { ticket: ticket, password: password });
    }
    self.callResetPassword.canCall = function(){return PPCMPermissions.indexOf('/public/users/activation') > -1};
    
    return {
        resetPassword: self.callResetPassword,
        _dummy_: undefined
    }
}
);

