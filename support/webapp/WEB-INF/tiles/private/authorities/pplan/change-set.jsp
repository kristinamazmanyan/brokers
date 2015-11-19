<%--
  Created by IntelliJ IDEA.
  User: sevak.kerobyan
  Date: 7/23/2015
  Time: 5:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/tiles/common/layout/definitions.jsp" %>
<tiles:insertDefinition name="default" flush="true">

    <tiles:putAttribute name="title.page">Change Set</tiles:putAttribute>
    <tiles:putAttribute name="body" cascade="false">
        <div class="row" ng-controller="ChangeController">
           <%-- <div class="col-lg-offset-1 col-sm-10 ">--%>

                <table class="table  table-hover">
                    <thead>
                    <tr>
                        <th style="width: 60px">ԳՄԱ</th>
                        <th>Ծրագրի կոդը </th>
                        <th>form name </th>
                        <th>Հոդվածի կոդը</th>
                        <th>amount available</th>
                        <th>amount saved</th>
                        <th>delta price</th>
                        <th>delta quantity</th>
                    </tr>
                    </thead>
                    <tr ng-repeat="data in dataList">
                      <%--  <td class="col-sm-2"><input type="text" class="form-control" id="cpv_code" value="{{data.cpv_code}}"></td>--%>
                          <td class="col-sm-2"><a href="#" editable-text="{{data.cpv_code}}"> </a></td>
                        <td ><input type="number" class="form-control" id="program_code" value="{{data.program_code}}"></td>
                        <td ><input type="text"   class="form-control" id="form_name" value="{{data.form_name}}"></td>
                        <td ><input type="number" class="form-control" id="articles_code" value="{{data.articles_code}}"></td>
                        <td ><input type="number" class="form-control" id="amount_available" disabled value="{{data.amount_available}}"></td>
                        <td ><input type="number" class="form-control" id="amount_saved" disabled value="{{data.amount_saved}}"></td>
                        <td ><input type="number" class="form-control" id="delta_price" disabled value="{{data.delta_price}}"></td>
                        <td ><input type="number" class="form-control" id="delta_quantity" disabled value="{{data.delta_quantity}}"></td>
                    </tr>

                    <tfoot >
                    <tr >
                        <td class="col-sm-2"><input type="text" class="form-control" id="cpv_code_input" placeholder="Input cpv code"></td>
                        <td ><input type="number" class="form-control" id="program_code_input" placeholder="Input program code"></td>
                        <td ><input type="text" class="form-control" id="form_name_input" placeholder="Input form name"></td>
                        <td ><input type="number" class="form-control" id="articles_code_input" placeholder="Input articles"></td>
                        <td ><input type="number" class="form-control" id="amount_available_input"  placeholder="Input amoun available"></td>
                        <td ><input type="number" class="form-control" id="amount_saved_input" placeholder="Input amount saved"></td>
                        <td ><input type="number" class="form-control" id="delta_price_input" placeholder="Input delta price"></td>
                        <td ><input type="number" class="form-control" id="delta_quantity_input" placeholder="Input delta qunatity"></td>
                        <td><a href="#"><i class="glyphicon glyphicon-plus"></i> </a>
                        </td>
                    </tr>
                    </tfoot>
                </table>


            <%--</div>--%>
        </div>

        <script>
            PPCM.controller("ChangeController", function($scope){
                $scope.dataList = [{"cpv_code":"30000000/1/4","program_code":4532,"form_name":"testFormName","articles_code":2879,"amount_available":10,"amount_saved":50,"delta_price":8,"delta_quantity":6},
                    {"cpv_code":"30000000/1/4","program_code":4532,"form_name":"testFormName","articles_code":2879,"amount_available":10,"amount_saved":50,"delta_price":8,"delta_quantity":6},
                    {"cpv_code":"30000000/1/4","program_code":4532,"form_name":"testFormName","articles_code":2879,"amount_available":10,"amount_saved":50,"delta_price":8,"delta_quantity":6},
                    {"cpv_code":"30000000/1/4","program_code":4532,"form_name":"testFormName","articles_code":2879,"amount_available":10,"amount_saved":50,"delta_price":8,"delta_quantity":6},
                    {"cpv_code":"30000000/1/4","program_code":4532,"form_name":"testFormName","articles_code":2879,"amount_available":10,"amount_saved":50,"delta_price":8,"delta_quantity":6},
                    {"cpv_code":"30000000/1/4","program_code":4532,"form_name":"testFormName","articles_code":2879,"amount_available":10,"amount_saved":50,"delta_price":8,"delta_quantity":6}]
            })

        </script>
    </tiles:putAttribute>
</tiles:insertDefinition>
