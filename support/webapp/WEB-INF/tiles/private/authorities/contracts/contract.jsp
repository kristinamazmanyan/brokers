<%--
  Created by IntelliJ IDEA.
  User: sevak.kerobyan
  Date: 8/3/2015
  Time: 2:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/tiles/common/layout/definitions.jsp" %>
<tiles:insertDefinition name="default" flush="true">

  <tiles:putAttribute name="title.page">Contract</tiles:putAttribute>
  <tiles:putAttribute name="body" cascade="false">
    <div class="row" ng-controller="ContractController">
    <div class="col-lg-offset-1 col-sm-10 ">


        <form class="form-horizontal" id="contract-form" method="post" novalidate name="Contract">

          <div class="form-group">
            <label class="col-sm-4 control-label" for="title">Պատվիրատուի անվանումը :</label>

            <div class="col-sm-4">
                <input id="title" class="form-control " type="text" onfocus="this.select();"
                       name="title" autocomplete="off" value="Թեսթ">

            </div>
          </div>

          <div class="form-group">
            <label class="col-sm-4 control-label" for="code">Պայմանագրի ծածկագիրը :</label>

            <div class="col-sm-4">
                <input id="code" class="form-control " type="text" onfocus="this.select();" name="code"
                       autocomplete="off" value="Թեսթ">
              </div>
          </div>

          <div class="form-group">
            <label class="col-sm-4 control-label" for="date">Կնքման ամսաթիվը :</label>


            <div class='col-sm-4'>
                <div class='input-group date' id='datetimepicker1'>
                  <input type='text' class="form-control" />
                    <span class="input-group-addon">
                        <span class="glyphicon glyphicon-calendar"></span>
                    </span>
                </div>

            </div>
            <script type="text/javascript">
              $(function () {
                $('#datetimepicker1').datetimepicker();
              });
            </script>

          </div>

          <div class="form-group">
            <label class="col-sm-4 control-label" for="cpv">Գնման առարկան:</label>

            <div class="col-sm-4">
                                <textarea class="form-control " disabled type="text" onfocus="this.select();"
                                          name="cpv"   >test </textarea>

            </div>
          </div>

        <%--  <div class="form-group">
            <label class="col-sm-4 control-label" for="cpv-sum">Գնման առարկայի նախահաշվային գինը :</label>

            <div class="col-sm-4">
                <input id="cpv-sum" class="form-control " type="number"  onfocus="this.select();" name="cpv-sum"
                       autocomplete="off" value="Թեսթ">

            </div>
          </div>--%>

          <div class="form-group">
            <label class="col-sm-4 control-label" for="contract-sum">Պայմանագրի ընդհանուր գումարը :</label>

            <div class="col-sm-4">
                <input id="contract-sum" class="form-control " type="number"  onfocus="this.select();" name="contract-sum"
                       autocomplete="off" value="Թեսթ">

            </div>
          </div>

         <%-- <div class="form-group">
            <label class="col-sm-4 control-label" for="advance">Կանխավճարը :</label>

            <div class="col-sm-4">
                <input id="advance" class="form-control " type="number"  onfocus="this.select();" name="advance"
                       autocomplete="off" value="Թեսթ">

            </div>
          </div>--%>

          <div class="form-group">
            <label class="col-sm-4 control-label" for="financial-source">Ֆինանսավորման աղբյուրը :</label>

            <div class="col-sm-4">
                <input id="financial-source" class="form-control " type="text"  onfocus="this.select();" name="financial-source"
                       autocomplete="off" value="Թեսթ">

            </div>
          </div>


          <div class="form-group">
            <label class="col-sm-4 control-label" for="procedure">Ընթացակարգ:</label>

            <div class="col-sm-4 selectContainer">
              <select name="procedure" aria-disabled="true" disabled id="procedure" class="form-control">
                <option >Բաց</option>
                <option >Պարզեցված</option>
                <option >Բանակցային </option>
                <option >Թեսթ</option>
              </select>
            </div>
          </div>

          <div class="form-group">
            <label class="col-sm-4 control-label" for="description"> Գնման ձևի հիմնավորումը :</label>

            <div class="col-sm-4">

                                <textarea id="description" class="form-control " type="text" onfocus="this.select();"
                                          name="description"  autocomplete="off" >test </textarea>

            </div>
          </div>

          <div class="form-group">
            <label class="col-sm-4 control-label" for="date">Պայմանագրի կատարման վերջնաժամկետը :</label>


            <div class='col-sm-4'>

                <div class='input-group date' id='datetimepicker1'>
                  <input type='text' class="form-control" />
                    <span class="input-group-addon">
                        <span class="glyphicon glyphicon-calendar"></span>
                    </span>

              </div>
            </div>
            <script type="text/javascript">
              $(function () {
                $('#datetimepicker1').datetimepicker();
              });
            </script>

          </div>
          <div class="form-group">
            <label class="col-sm-4 control-label" for="penalty">Տույժ կամ տուգանք :</label>

            <div class="col-sm-4">

                <input id="penalty" class="form-control " type="number"  onfocus="this.select();" name="penalty"
                       autocomplete="off" value="Թեսթ">

            </div>
          </div>


          <div class="form-group">
            <label class="col-sm-4 control-label" for="cp">Վճարման ժամանակացույց:</label>

            <div class="col-sm-4">

              <div class="radio">
                <label><input type="radio" name="optradio" checked>Գրաֆիկ</label>
              </div>
              <div class="radio">
                <label><input type="radio" name="optradio">Ց՝պահանջ</label>
              </div>

            </div>
          </div>


          <div class="page-header text-center">
            <h4>Պայմանագիր կնքած անձի տվյալները</h4>
          </div>

          <div class="form-group">
            <label class="col-sm-4 control-label" for="title1">Անվանումը :</label>

            <div class="col-sm-4">
              <input id="title1" class="form-control " type="text" onfocus="this.select();"
                     name="title1" autocomplete="off" value="Թեսթ">

            </div>
          </div>

          <div class="form-group">
            <label class="col-sm-4 control-label" for="code1">Հարկ վճարողի հաշվառման համարը :</label>

            <div class="col-sm-4">
              <input id="code1" class="form-control " type="text" onfocus="this.select();" name="code1"
                     autocomplete="off" value="Թեսթ">
            </div>
          </div>

          <div class="form-group">
            <label class="col-sm-4 control-label" for="credit">Բանկային հաշիվը :</label>

            <div class="col-sm-4">
              <input id="credit" class="form-control " type="text" onfocus="this.select();" name="credit"
                     autocomplete="off" value="Թեսթ">
            </div>
          </div>

          <div class="form-group text-center">
            <div class="col-sm-offset-2 col-sm-10">
              <button type="submit" class="btn ">Նախորդ</button>
              <button type="submit" class="btn " ng-click="next()">Հաջորդ</button>
            </div>
          </div>
        </form>









    </div>


    <script>
      PPCM.controller("ContractController", function ($scope) {

        $scope.next = function(){
          window.location.href = "schedule";
        }
      })

    </script>
  </tiles:putAttribute>
</tiles:insertDefinition>

