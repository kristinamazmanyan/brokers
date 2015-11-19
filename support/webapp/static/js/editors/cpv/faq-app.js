
PPCM
    .controller("FAQController",["$scope","$sce", "FaqService", FAQController]);
//FAQ------------------------------


function FAQController($scope,$sce, FaqService,PageService) {

    $scope.adm =true;
    $scope.myV = true;
    $scope.myVar = false;
    $scope.myFormError=false;

  $scope.getViewPermission = function(){
        return FaqService.list.canCall();
    }

    $scope.getManagePermission = function(){
        return FaqService.save.canCall();
    }



    $scope.toggle = function(id) {
        $scope.id=id;
        $scope.myVar = !$scope.myVar;
        if( $scope.myVar){
            $scope.myV = false;
        }else
            $scope.myV = true;
    };

    $scope.list = function(){
        if(FaqService.list.canCall() )
        FaqService.list().success(function(data, status, headers, config){
            console.log(data);
            console.log("get the Faq  list = :" + data.list);
            $scope.todos = data;

        })
    };
    $scope.list();

    $scope.convertToHtml = function (htmlVar){
        return $sce.trustAsHtml(markdown.toHTML(htmlVar));
    }

    <!--EDIT function start-->
    $scope.editTodo = function(id,orderNo,questionhy,answerhy,questionru,answerru,questionen,answeren) {
        if( FaqService.save.canCall()) {
            $scope.toggle(id);
            var faq = {
                id: id,
                orderNo: orderNo,
                questionHy: questionhy,
                answerHy: answerhy,
                questionEn: questionen,
                answerEn: answeren,
                questionRu: questionru,
                answerRu: answerru
            };


            //if(    faq.questionHy ===" " || faq.questionHy === undefined
            //    || faq.answerHy ===" "   || faq.answerHy === undefined
            //    || faq.questionEn ===" " || faq.questionEn === undefined
            //    || faq.answerEn ===" "   || faq.answerEn === undefined
            //    || faq.questionRu ===" " || faq.questionRu === undefined
            //    || faq.answerRu ===""    || faq.answerRu === undefined )
            //{ $scope.myFormError =true;}
            //else{
            // PageService.addAlertSuccess("Your Activation was edited");
            FaqService.save(faq).success(function (data, status, headers, config) {

            });
            window.location.href = "/ppcm/public/faq/";
            $scope.todos.push({questionHy: questionhy, answedorHy: answerhy, done: true, ans: false});
            //  }
        }
    };

<!--ADD function start-->
    $scope.addTodo = function(id,questionhy,answerhy,questionru,answerru,questionen,answeren) {
        if( FaqService.add.canCall()) {
        $scope. toggle(id);
        var faq = {
            id :id,
            questionHy:questionhy ,
            answerHy:answerhy ,
            questionEn:questionen  ,
            answerEn:answeren ,
            questionRu:questionru ,
            answerRu:answerru  };

        //
        //if(    faq.questionHy ===" " || faq.questionHy === undefined
        //    || faq.answerHy ===" "   || faq.answerHy === undefined
        //    || faq.questionEn ===" " || faq.questionEn === undefined
        //    || faq.answerEn ===" "   || faq.answerEn === undefined
        //    || faq.questionRu ===" " || faq.questionRu === undefined
        //    || faq.answerRu ===" "   || faq.answerRu === undefined )
        //{ $scope.myFormError =true;}
        //
        //else  {
         //   PageService.addAlertSuccess("Your Activation was edited");


            FaqService.add(faq).success(function (data, status, headers, config) {
            });
            window.location.href = "/ppcm/public/faq/";
        }
      //  }
    };
<!--ADD FUNCTION end-->

    $scope.gotoUp  = function(id) {
        if( FaqService.up.canCall()) {
            FaqService.up(id).success(function (data, status, headers, config) {
            });
            $scope.temp = [];
            if (id !== 1) {

                for (var i = 0; i < $scope.todos.length; i++) {
                    if ($scope.todos[i].id === id) {
                        if (i > 0) {
                            $scope.temp = $scope.todos[i - 1];
                            $scope.todos[i - 1] = $scope.todos[i];
                            $scope.todos[i] = $scope.temp;
                            return $scope.todos[i];
                        }
                    }
                }
                // window.location.href = "http://localhost:8080/public/info/faq/";
            }
        }
    };

    $scope.gotoDown  = function(id){
        if( FaqService.down.canCall()) {
            FaqService.down(id).success(function (data, status, headers, config) {
            });
            for (var i = 0; i < $scope.todos.length; i++) {
                if ($scope.todos[i].id === id) {
                    if (i < $scope.todos.length - 1) {
                        $scope.temp = $scope.todos[i + 1];
                        $scope.todos[i + 1] = $scope.todos[i];
                        $scope.todos[i] = $scope.temp;
                        return $scope.todos[i];
                    }
                }
            }
            //window.location.href = "http://localhost:8080/public/info/faq/";
        }
    };

    $scope.cancel = function () {
        $window.history.back();
    }

    $scope.remove = function(id) {
        if (FaqService.remove.canCall()) {
            for (var i = 0; i < $scope.todos.length; i++) {
                if ($scope.todos[i].id === id) {
                    $scope.todos[i].done = true;
                }
                var oldTodos = $scope.todos;
                $scope.todos = [];
                angular.forEach(oldTodos, function (todo) {
                    if (!todo.done) $scope.todos.push(todo);
                });
            }
            FaqService.remove(id).success(function (data, status, headers, config) {
            });
        }

    }
}


