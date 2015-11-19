<%--
  Created by IntelliJ IDEA.
  User: sevak.kerobyan
  Date: 7/30/2015
  Time: 6:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/tiles/common/layout/definitions.jsp" %>
<tiles:insertDefinition name="default" flush="true">

  <tiles:putAttribute name="title.page">New Delivary</tiles:putAttribute>
  <tiles:putAttribute name="body" cascade="false">
    <div class="row" ng-controller="DeliveryController">
         <div class=" col-sm-10 ">
           <form   novalidate class="form-horizontal">
      <table class="table  table-hover">
        <thead>
        <tr>
          <th style="width: 60px">ԳՄԱ</th>
          <th>Date</th>
          <th>Քանակ </th>
          <th>Գումար </th>
          <th>Պատ ստոր եզրակացությունը</th>
          <th>Պայմանագրի արդյունքը
            ֆիքսող  փաստաթուղթը</th>
          <th>Հանձնման-ընդունման արձանագրությունը</th>
          <th>
            հանձնման-ընդունման ավարտական արձանագցրությունը

          </th>
        </tr>
        </thead>
        <tr ng-repeat="data in dataList">
            <td class="col-sm-2"><input type="text" class="form-control" id="cpv_code" value="{{data.cpv_code}}" disabled></td>
          <td ><input type="date" class="form-control" id="date" value="{{data.date}}"></td>
          <td ><input type="number"   class="form-control" id="count" value="{{data.count}}"></td>
          <td ><input type="number" class="form-control" id="sum" disabled value="{{data.sum}}"></td>
          <td ><input type="file" class="form-control" id="file"  ></td>
          <td ><input type="file" class="form-control" id="file1"  ></td>
          <td ><input type="file" class="form-control" id="file2"  ></td>
          <td ><input type="file" class="form-control" id="file3"  ></td>
        </tr>
      </table>

             <div class="form-group text-center">
               <div class="col-sm-offset-2 col-sm-10">
                 <button type="submit" class="btn " ng-click="back()">Add</button>
               </div>
             </div>
             </form>
        </div>
    </div>

    <script>
      PPCM.controller("DeliveryController", function($scope){

        $scope.back = function(){
           window.location.href= "http://localhost:8080/ppcm/private/authority/contracts/index";
        }
        $scope.dataList = [{"cpv_code":"30000000","date":"06.04.2015","count":2,"sum":2879},
          {"cpv_code":"03110000","date":4532,"count":1,"sum":2879},
          {"cpv_code":"98510000","date":4532,"count":1,"sum":2879}]
      })

    </script>
  </tiles:putAttribute>
</tiles:insertDefinition>

