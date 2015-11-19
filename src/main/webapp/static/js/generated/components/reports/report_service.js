PPCM.service('ReportService', function($http, PPCMPermissions) {
    self = this;
    
    self.callList = function() {
        console.log('call ReportService.list(...)');
        return $http.post('/public/reports/list', {  });
    }
    self.callList.canCall = function(){return PPCMPermissions.indexOf('/public/reports') > -1};
    
    self.callGetReport = function(reportId, values, page, order) {
        console.log('call ReportService.getReport(...)');
        console.log('  reportId: ' + reportId);
        console.log('  values: ' + values);
        console.log('  page: ' + page);
        console.log('  order: ' + order);
        return $http.post('/public/reports/get-report', { reportId: reportId, values: values, page: page, order: order });
    }
    self.callGetReport.canCall = function(){return PPCMPermissions.indexOf('/public/reports') > -1};
    
    return {
        list: self.callList,
        getReport: self.callGetReport,
        _dummy_: undefined
    }
}
);

