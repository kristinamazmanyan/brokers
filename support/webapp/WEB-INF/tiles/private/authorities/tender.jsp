<%--
  Created by IntelliJ IDEA.
  User: sevak.kerobyan
  Date: 7/24/2015
  Time: 2:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/tiles/common/layout/definitions.jsp" %>
<tiles:insertDefinition name="default" flush="true">

    <tiles:putAttribute name="title.page">Tender</tiles:putAttribute>


    <tiles:putAttribute name="body" cascade="false">
        <div class="row" ng-controller="TenderController">

            <div class="col-lg-offset-1 col-sm-10 ">

                <form class="form-horizontal" id="tenderForm" method="post" novalidate name="Tender">

                    <div class="form-group">
                        <label class="col-sm-4 control-label" for="title">Վերնագիր:</label>

                        <div class="col-sm-8">
                            <div class="input-group">
                                <input id="title" class="form-control " type="text" onfocus="this.select();"
                                       name="title" autocomplete="off" value="Թեսթ">
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-4 control-label" for="code">Ընթացակարգի ծածկագիր:</label>

                        <div class="col-sm-8">
                            <div class="input-group">
                                <input id="code" class="form-control " type="text" onfocus="this.select();" name="code"
                                       autocomplete="off" value="Թեսթ">
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-4 control-label" for="description">Նկարագրություն:</label>

                        <div class="col-sm-8">
                            <div class="input-group">
                                <textarea id="description" class="form-control " type="text" onfocus="this.select();"
                                       name="description"  autocomplete="off" >test </textarea>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-4 control-label" for="Gtype">Գնումների տիպը:</label>

                        <div class="col-sm-4 selectContainer">
                            <select name="Gtype" id="Gtype" disabled class="form-control">
                                <option >Ապրանքներ</option>
                                <option >Ծառայություններ</option>
                                <option >Աշխատանք</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-4 control-label" for="procedure">Ընթացակարգ:</label>

                        <div class="col-sm-4 selectContainer">
                            <select name="procedure" disabled id="procedure" class="form-control">
                                <option >Բաց</option>
                                <option >Պարզեցված</option>
                                <option >Բանակցային </option>
                                <option >Թեսթ</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-4 control-label" for="cpv">ԳԸԲ կոդեր:</label>

                        <div class="col-sm-8">
                            <div class="input-group">
                                <textarea id="cpv" class="form-control " type="text" onfocus="this.select();"
                                       name="cpv" autocomplete="off"  disabled> test</textarea>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-4 control-label" for="cpv">Ամսաթիվ,Ժամանակ:</label>


                                <div class='col-sm-4'>
                                    <div class="form-group">
                                        <div class='input-group date' id='datetimepicker1'>
                                            <input type='text' class="form-control" />
                    <span class="input-group-addon">
                        <span class="glyphicon glyphicon-calendar"></span>
                    </span>
                                        </div>
                                    </div>
                                </div>
                                <script type="text/javascript">
                                    $(function () {
                                        $('#datetimepicker1').datetimepicker();
                                    });
                                </script>

                    </div>
<%--
                    <div class="form-group">
                        <label class="col-sm-4 control-label" for="lots">Չափաբաժիններով շնորհված պայմանագիր:</label>

                        <div class="col-sm-8">
                            <label class="radio-inline">
                                <input name="radioGroup" id="radio1" value="option1" type="radio" ng-click="open()"> այո
                            </label>
                            <label class="radio-inline">
                                <input name="radioGroup" id="radio2" value="option2" checked="" type="radio"  ng-click="close()">  ոչ
                            </label>
                        </div>
                    </div>

                    <div ng-show="lot">
                    <div class="form-group">
                        <label class="col-sm-4 control-label" for="LotsNumber">Չափաբաժինների քանակ:</label>

                        <div class="col-sm-8">
                            <div class="input-group">
                                <input id="LotsNumber" class="form-control " type="number" onfocus="this.select();"
                                       name="LotsNumber" autocomplete="off" value="1">
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-4 control-label" >Մրցույթներ չափաբաժինների համար:</label>

                        <div class="col-sm-8">
                            <label class="radio-inline">
                                <input name="radioGroup" id="radio3" value="option1" type="radio" ng-click="open()"> Միայն մեկ չափաբաժին
                            </label>
                            <label class="radio-inline">
                                <input name="radioGroup" id="radio4" value="option2" checked="" type="radio" >  Մեկ կամ ավելի չափաբաժիններ
                            </label>

                            <label class="radio-inline">
                                <input name="radioGroup" id="radio5" value="option3" checked="" type="radio" >  Բոլոր չափաբաժինները
                            </label>
                        </div>
                    </div>
                    </div>--%>

                    <div class="form-group text-center">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn ">Պահպանել</button>
                            <button type="submit" class="btn ">Չեղարկել</button>
                        </div>
                    </div>
                </form>

            </div>


        </div>

        <script>
            PPCM.controller("TenderController",function($scope){

                $scope.lot=false;
                $scope.open = function(){
                    $scope.lot=true;
                }

                $scope.close = function(){
                    $scope.lot = false;
                }
            })

        </script>
    </tiles:putAttribute>
</tiles:insertDefinition>