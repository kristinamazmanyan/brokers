/**
 * Created by artur.tsghunyan on 7/30/2015.
 */
angular.module('ppcm')
    .config(['$routeProvider', ArticleRoutes])
    .controller("ArticleListController", ["$scope", "$http", 'ArticleService', ArticleListController])
    .controller("ArticleAddController", ["$scope", '$routeParams', '$window', 'ArticleService', ArticleAddController]);

function ArticleRoutes($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl : 'articleList',
            controller: ArticleListController
        })

        .when('/add', {
            templateUrl : 'addArticle',
            controller: ArticleAddController
        })

        .when('/edit/:id',{
            templateUrl: 'addArticle',
            controller: 'ArticleAddController'
        })

        .otherwise( {
            templateUrl : 'articleList',
            controller: ArticleListController
        });

}

    function ArticleListController($scope, $http, ArticleService) {

        $scope.list = function () {

            // Getting total count of Articles in Table
            ArticleService.count()
                .success(function (data) {
                    console.log("Count = " + data);
                    $scope.totalCount = data;
                })


            $scope.itemsPerPage = 10;
            $scope.page = {index: ($scope.currentPage - 1), size: $scope.itemsPerPage}
            $scope.order = {field: 'code', ascending: true}

            $scope.articles = [];

            ArticleService.list($scope.page, $scope.order)
                .success(function (data) {
                    console.log(data);
                    $scope.articles = data;
                })

        }
};


function ArticleAddController($scope, $routeParams, $window,  ArticleService) {

    $scope.article ={};
    var currentArticleID = $routeParams.id;
    console.log(" ID = " + currentArticleID);
    if(currentArticleID !== undefined){
        ArticleService.get(currentArticleID)
            .success(function(data){
                $scope.article = data;
            })
    }


        $scope.add = function ($event) {

            var errorCode = isValid($scope.article.code) || (!(/^\d+$/.test($scope.article.code)));
            var errorNameHy = isValid($scope.article.nameHy);
            var errorNameEn = isValid($scope.article.nameEn);
            var errorNameRu = isValid($scope.article.nameRu);

            if(!(errorCode || errorNameHy || errorNameEn || errorNameRu)){

            if($scope.article.id === undefined) {
                $scope.article.id = generateUUID().toString();
                $scope.article.obsolete = 0;

                ArticleService.add($scope.article)
                    .success(function(data) {
                        $scope.cancel();
                    });
             }
            else{
                ArticleService.save($scope.article)
                    .success(function(data){
                        $scope.cancel();
                    })

            }
            }
        }

    $scope.cancel = function() {
        $window.history.back();
    }

    function isValid(input){
        return (input === '' || input === undefined || input === null)
    }


    function generateUUID() {
        var d = new Date().getTime();
        var uuid = 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
            var r = (d + Math.random()*16)%16 | 0;
            d = Math.floor(d/16);
            return (c=='x' ? r : (r&0x3|0x8)).toString(16);
        });
        return uuid;
    };
    // Form Validation
    $('form').validate({
        rules: {
            code: {
                digits: true,
                required: true
            },
            nameHy:{
                required: true
            },
            nameEn:{
                required: true
            },
            nameRu:{
                required: true
            }

        },
        highlight: function(element) {
            $(element).closest('.form-group').addClass('has-error');
        },
        unhighlight: function(element) {
            $(element).closest('.form-group').removeClass('has-error');
        },
        errorElement: 'span',
        errorClass: 'help-block',
        errorPlacement: function(error, element) {
            if(element.parent('.input-group').length) {
                error.insertAfter(element.parent());
            } else {
                error.insertAfter(element);
            }
        }
    });

};


