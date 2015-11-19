/**
 * Created by artur.tsghunyan on 8/26/2015.
 */

angular.module('ppcm')
    .config(['$routeProvider', '$locationProvider',ReportPageRoutes])
    .controller("ReportListController", ["$scope", '$routeParams', 'ReportService', ReportListController])
    .controller('Controller', ['$scope', '$http', '$location', '$window',

        function ($scope, $http, $location, $window) {
            $scope.options = {
                tooltipEvents: [],
                showTooltips: true,
                tooltipCaretSize: 0,
                onAnimationComplete: function () {
                    this.showTooltip(this.segments, true);
                }
            };

            var diskDataJson = {
                "data": [33, 22, 41],
                "labels": ["TOTAL BIDDERS", "SUCCESSFUL BIDDERS", "AVERAGE PRICE PER TENDER"],
                "colours": ['#ebd3d2', '#eb11d2', '#ac15d2']
            };

            $scope.pieDiskData = diskDataJson;
        }]);
    ;

function ReportPageRoutes($routeProvider, $locationProvider) {
    $routeProvider

        .when('/graphical-report', {
            templateUrl : 'template-graphical-reports.html',
            controller: 'ReportListController'
        })

        .otherwise( {
            templateUrl : 'template-graphical-reports.html',
            controller: 'ReportListController'

        });
};
function ReportListController($scope, $routeParams, ReportService) {
    $scope.reportMetadataList = [];
    $scope.reportList = [];
    $scope.parametersList= [];
    $scope.outputFormatsList= [];

    $scope.jData=[
        {"type":"AVERAGE BIDDERS PER TENDER","val":5.00},
        {"type":"TOTAL BIDDERS","val":"33"},
        {"type":"AVERAGE PRICE PER TENDER","val":"567.00"},
        {"type":"SUCCESSFUL BIDDERS","val":"22"}

    ];

    $scope.pie=function ($scope, $http, $location, $window) {
        $scope.options = {
            tooltipEvents: [],
            showTooltips: true,
            tooltipCaretSize: 0,
            onAnimationComplete: function () {
                this.showTooltip(this.segments, true);
            }
        };

        var diskDataJson = {
            "data": [33, 22],
            "labels": ["TOTAL BIDDERS", "SUCCESSFUL BIDDERS"],
            "colours": ['#ebd3d2', '#eb11d2']
        };

        $scope.pieDiskData = diskDataJson;
    }



    $scope.list = function(){
        ReportService.list()
            .success(function(data){
                $scope.reportMetadataList = data;
            })
    };

    //console.log("Data Report " +  BlockView($scope.jData));
    $("#mainDiv").append(BlockView($scope.jData));
    //$("#mainDiv").append(PieChart());





    // BlockView HTML Template
    function BlockView(jdata){
        var htmlString='<div class=\"col-md-6\" style=\"padding-top: 1em\" >';
                htmlString = htmlString + '<table class=\"table table-bordered\" >' +
                '<tr><td>'+jdata[0].type+'<p><h2>'+jdata[0].val+'</h2></p></td>' +
                '<td>'+jdata[1].type+'<p><h2>'+jdata[1].val+'</h2></p></td></tr>' +
                '<tr><td>'+jdata[2].type+'<p><h2>'+jdata[2].val+'</h2></p></td>' +
                '<td>'+jdata[3].type+'<p><h2>'+jdata[3].val+'</h2></p></td></tr>' +
                '</table>';
        return htmlString+'</div>';
    }


};
