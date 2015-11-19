// here only goes module definition and dependencies

var PPCM = angular.module('ppcm', ['ngRoute', 'ngCookies', 'ui.bootstrap','daterangepicker', 'treeGrid','chart.js', 'xeditable',"angular-uuid"]);
PPCM.run(function(editableOptions) {
    editableOptions.theme = 'bs3';
});