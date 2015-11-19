<%--
  Created by IntelliJ IDEA.
  User: sevak.kerobyan
  Date: 8/3/2015
  Time: 4:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/tiles/common/layout/definitions.jsp" %>
<tiles:insertDefinition name="default" flush="true">

    <tiles:putAttribute name="title.page">Ժամանակացույց</tiles:putAttribute>
    <tiles:putAttribute name="body" cascade="false">
        <div class="row" ng-controller="ScheduleController">
        <div class="col-lg-offset-1 col-sm-10 ">
                <%-- <form   novalidate class="form-horizontal">--%>

            <div class="page-header text-center">
                <h3>ԳՄԱ Ցուցակ</h3>
            </div>
            <table class="table  table-hover">
                <thead>
                <tr>
                    <th style="width: 60px">ԳՄԱ</th>
                    <th>Քանակ</th>
                    <th>Գումար</th>
                </tr>
                </thead>

                <tr ng-repeat="data in dataList">
                    <td class="col-sm-2"><input type="text" class="form-control" id="cpv_code" value="{{data.cpv_code}}"
                                                disabled></td>
                    <td><input type="number" class="form-control" id="count" value="{{data.count}}" disabled></td>
                    <td><input type="number" class="form-control" id="sum" disabled value="{{data.sum}}"></td>

                </tr>
            </table>


            <div class="page-header text-center">
                <h3>Մատակարարման ժամանակացույց</h3>
            </div>

            <table class="table table-bordered table-hover table-condensed">
                <tr style="font-weight: bold">
                    <td style="width:20%">ԳՄԱ</td>
                    <td style="width:30%">Date</td>
                    <td style="width:20%">Քանակ</td>
                    <td style="width:20%">Գումարը ենթակա է վճարման</td>
                    <td style="width:5%">Edit</td>
                </tr>
                <tr ng-repeat="data in dataList2">
                    <td>
               <span editable-select="data.cpv" e-name="cpv" e-form="rowform"
                     e-ng-options="s.id as s.cpv_code for s in cpvList">
          {{ showData(data) }}
        </span>

                    </td>
                    <td>
       <span editable-date="data.date" e-form="rowform">
          {{ data.date || 'empty' }}
        </span>
                    </td>
                    <td>
        <span editable-number="data.count" e-name="count" e-form="rowform">
          {{ data.count || 'empty'  }}
        </span>
                    </td>

                    <td>
        <span editable-number="data.rem" e-name="rem" e-form="rowform">
          {{ data.rem || 'Պարտադիր չի'  }}
        </span>
                    </td>
                    <td style="white-space: nowrap">
                        <!-- form -->
                        <form editable-form name="rowform" onbeforesave="saveDataM($data, data.id)"
                              ng-show="rowform.$visible" class="form-buttons form-inline" shown="inserted == data">

                            <button type="submit" ng-disabled="rowform.$waiting">
                                <i class="glyphicon glyphicon-floppy-disk"></i>
                            </button>


                            <a href="#" ng-disabled="rowform.$waiting" ng-click="rowform.$cancel()"> <i
                                    class="glyphicon glyphicon-arrow-left"></i></a>

                        </form>
                        <div class="buttons" ng-show="!rowform.$visible">
                            <a href="#" ng-click="rowform.$show()"> <i class="glyphicon glyphicon-edit"></i></a>
                            <a href="#" ng-click="removeRow($index)"> <i class="glyphicon glyphicon-minus"></i></a>
                        </div>
                    </td>
                </tr>
            </table>
            <a href="#" ng-click="addRowM()"> <i class="glyphicon glyphicon-plus"></i></a>


            <div class="page-header text-center">
                <h3>Վճարման ժամանակացույց</h3>
            </div>
            <table class="table table-bordered table-hover table-condensed">
                <tr style="font-weight: bold">
                        <%-- <td style="width:35%">ԳՄԱ</td>--%>
                    <td style="width:30%">Date</td>
                    <td style="width:20%">Քանակ</td>
                    <td style="width:25%">Վճարման տեսակ</td>
                    <td style="width:20%">Գումար</td>
                    <td style="width:5%">Edit</td>
                </tr>
                <tr ng-repeat="data in dataList3">
                        <%-- <td>
                            <span editable-select="data.cpv" e-name="cpv" e-form="rowform" e-ng-options="s.id as s.cpv_code for s in cpvList">
                       {{ showData(data) }}
                     </span>

                         </td>--%>
                    <td>
       <span editable-date="data.date" e-form="rowform">
          {{ data.date || 'empty' }}
        </span>
                    </td>
                    <td>
        <span editable-number="data.count" e-name="count" e-form="rowform">
          {{ data.count || 'empty'  }}
        </span>
                    </td>

                    <td>
        <span editable-select="data.typeId" e-name="typeId" e-form="rowform"
              e-ng-options="s.id as s.type for s in typeList">
          {{ showType(data)   }}
        </span>

                    </td>

                    <td>
        <span editable-number="data.sum" e-name="sum" e-form="rowform">
          {{ data.sum }}
        </span>

                    </td>
                    <td style="white-space: nowrap">
                        <!-- form -->
                        <form editable-form name="rowform" onbeforesave="saveDataV($data, data.id)"
                              ng-show="rowform.$visible" class="form-buttons form-inline" shown="inserted == data">

                            <button type="submit" ng-disabled="rowform.$waiting">
                                <i class="glyphicon glyphicon-floppy-disk"></i>
                            </button>

                            <a href="#" ng-disabled="rowform.$waiting" ng-click="rowform.$cancel()"> <i
                                    class="glyphicon glyphicon-arrow-left"></i></a>

                        </form>
                        <div class="buttons" ng-show="!rowform.$visible">
                            <a href="#" ng-click="rowform.$show()"> <i class="glyphicon glyphicon-edit"></i></a>
                            <a href="#" ng-click="removeRow($index)"> <i class="glyphicon glyphicon-minus"></i></a>
                        </div>
                    </td>
                </tr>
            </table>
            <a href="#" ng-click="addRowV()"> <i class="glyphicon glyphicon-plus"></i></a>


                <%--<div class="page-header text-center">
                  <h3>Մատակարարման ժամանակացույց</h3>
                </div>
                <table class="table table-bordered table-hover table-condensed">
                  <tr style="font-weight: bold">
                    <td style="width:35%">ԳՄԱ</td>
                    <td style="width:20%">Date</td>
                    <td style="width:20%">Քանակ</td>
                    <td style="width:5%">Edit</td>
                  </tr>
                  <tr ng-repeat="data in dataList">
                    <td>
                      <span editable-select="data.cpv" e-name="cpv" e-form="rowform" e-ng-options="s.id as s.cpv_code for s in cpvList">
                 {{ showData(data) }}
               </span>

                    </td>
                    <td>
              <span editable-date="data.date"  e-form="rowform" >
                 {{ data.date || 'empty' }}
               </span>
                    </td>
                    <td>
               <span editable-number="data.count" e-name="count"  e-form="rowform" >
                 {{ data.count || 'empty'  }}
               </span>
                    </td>


                    <td style="white-space: nowrap">
                      <!-- form -->
                      <form editable-form name="rowform" onbeforesave="saveData($data, user.id)" ng-show="rowform.$visible" class="form-buttons form-inline" shown="inserted == data">

                        <a href="#"  ng-disabled="rowform.$waiting"> <i class="glyphicon glyphicon-floppy-disk"></i></a>


                        <a href="#" ng-disabled="rowform.$waiting" ng-click="rowform.$cancel()"> <i class="glyphicon glyphicon-arrow-left"></i></a>

                      </form>
                      <div class="buttons" ng-show="!rowform.$visible">
                        <a href="#" ng-click="rowform.$show()"> <i class="glyphicon glyphicon-edit"></i></a>
                        <a href="#" ng-click="removeRow($index)"> <i class="glyphicon glyphicon-minus"></i></a>
                      </div>
                    </td>
                  </tr>
                </table>
                <a href="#" ng-click="addRow()"> <i class="glyphicon glyphicon-plus"></i></a>--%>

            <div class="form-group text-center">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn " ng-click="back()">Նախորդ</button>
                    <button type="submit" class="btn ">Պահպանել</button>
                </div>
            </div>
        </div>

        <script>
            PPCM.controller("ScheduleController", function ($scope, $filter, $http) {


                $scope.showData = function (data) {
                    var selected = [];
                    if (data.cpv) {
                        selected = $filter('filter')($scope.cpvList, {id: data.cpv});
                    }
                    return selected.length ? selected[0].cpv_code : 'Not set';
                };

                $scope.showType = function (data) {
                    var selected = [];
                    if (data.typeId) {
                        selected = $filter('filter')($scope.typeList, {id: data.typeId});
                    }
                    return selected.length ? selected[0].type : 'Not set';
                };

                $scope.saveDataM = function (data, id) {
                    angular.extend(data, {id: id});


                    if (data.rem != undefined) {
                        update($scope.dataList2,data);
                    }
                    console.log(data);

                };
                $scope.saveDataV = function (data, id) {
                    angular.extend(data, {id: id});
                    console.log(data);

                };

                var update = function(data, newData){
                    var isUpdate = false;
                    for(var i = 0; i<data.length ; i++){
                        if(data.id === newData.id){
                            isUpdate = true;
                            data.cpv = newData.cpv;
                            data.count = newData.count;
                            data.rem = newData.rem;
                        }

                    }
                    if(!isUpdate){
                        $scope.dataList2.push(data) ;
                    }

                }

                // remove user
                $scope.removeRow = function (index) {
                    $scope.dataList.splice(index, 1);
                };

                // add user
                $scope.addRowM = function () {
                    $scope.inserted = {
                        id: $scope.dataList2.length + 1,
                        cpv: '',
                        date: null,
                        count: '',
                        sum: null
                    };
                    $scope.dataList2.push($scope.inserted);
                };

                $scope.addRowV = function () {
                    $scope.inserted = {
                        id: $scope.dataList3.length + 1,
                        cpv: '',
                        date: null,
                        count: '',
                        sum: null
                    };
                    $scope.dataList3.push($scope.inserted);
                };
                $scope.cpvList = [{"id": 1, "cpv_code": "30000000"},
                    {"id": 2, "cpv_code": "03110000"},
                    {"id": 3, "cpv_code": "98510000"}];


                $scope.typeList = [{"id": 1, "type": "Կանխավճար"}, {"id": 2, "type": "Մատակարարման դիմաց"}, {
                    "id": 3,
                    "type": "Պայմանագրով"
                }]
                $scope.back = function () {
                    window.location.href = "http://localhost:8080/ppcm/private/authority/contracts/contract";
                }
                $scope.dataList = [{"cpv_code": "30000000", "count": 2, "sum": 2879},
                    {"cpv_code": "03110000", "count": 1, "sum": 2879},
                    {"cpv_code": "98510000", "count": 1, "sum": 2879}]

                $scope.dataList2 = [{"id": 1, "cpv": 1, "date": null, "count": 2, "rem": 123, "typeId": 1, "sum": 2879},
                    {"id": 2, "cpv": 2, "date": null, "count": 1, "rem": 554, "typeId": 2, "sum": 2879},
                    {"id": 3, "cpv": 3, "date": null, "count": 1, "rem": 454, "typeId": 3, "sum": 2879}]
                $scope.dataList3 = [{
                    "id": 1,
                    "cpv": 1,
                    "date": null,
                    "count": 2,
                    "rem": false,
                    "typeId": 1,
                    "sum": 2879
                },
                    {"id": 2, "cpv": 2, "date": null, "count": 1, "rem": false, "typeId": 2, "sum": 2879},
                    {"id": 3, "cpv": 3, "date": null, "count": 1, "rem": false, "typeId": 3, "sum": 2879}]
            })

        </script>
    </tiles:putAttribute>
</tiles:insertDefinition>
