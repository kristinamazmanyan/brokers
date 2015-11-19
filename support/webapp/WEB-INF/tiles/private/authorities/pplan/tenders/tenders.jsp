<%--
  Created by IntelliJ IDEA.
  User: artur.tsghunyan
--%>
<%@ include file="/WEB-INF/tiles/common/layout/definitions.jsp" %>
<tiles:insertDefinition name="default" flush="true">
  <tiles:putAttribute name="title.page">Պայմանագրեր</tiles:putAttribute>
  <tiles:putAttribute name="body" cascade="false">


      <div class="col-md-10 col-md-offset-1" ng-view>
      </div>

      <script type="text/ng-template" id="contractList">

        <table class="table  table-hover">
          <thead class="alert-success" style="background-color: #ebddd2">
          <tr>
            <th> &nbsp; </th>
            <th> Կոդ </th>
            <th> Անուն </th>
            <th> Ամսաթիվ  </th>
            <th class="pull-right"> Ավելացնել Նորը  </th>
          </tr>
          </thead>
          <tr data-toggle="collapse" data-target="#demo1" class="accordion-toggle">
            <td> <a href="" class="btn-link"> >> </a> </td>
            <td> Կոդ 1 </td>
            <td> Անուն 1 </td>
            <td> 28.07.2015 </td>
            <td> <button  class="btn btn-default pull-right "><a href="#/add" ><span class="glyphicon glyphicon-plus"/></a></button> </td>
          </tr>
          <tr>
            <td colspan="12" class="hiddenRow">
              <div class="accordian-body collapse" id="demo1">
              <table class="table table-striped">
                <thead>
                <tr>
                  <th>Պայմանագրի համարը</th>
                  <th>Մատակարար</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                  <td> Պայմանագիր 1</td>
                  <td> Մատակարար 1</td>
                </tr>
                </tbody>
              </table>
            </div>
            </td>
          </tr>

          <tr data-toggle="collapse" data-target="#demo2" class="accordion-toggle">
            <td> <a href="" class="btn-link"> >> </a> </td>
            <td> Կոդ 2 </td>
            <td> Անուն 2 </td>
            <td> 28.07.2015 </td>
            <td> <button  class="btn btn-default pull-right "><a href="#/add" ><span class="glyphicon glyphicon-plus"/></a></button> </td>
          </tr>
          <tr>
            <td colspan="12" class="hiddenRow">
              <div class="accordian-body collapse" id="demo2">
                <table class="table table-striped">
                  <thead>
                  <tr>
                    <th>Պայմանագրի համարը</th>
                    <th>Մատակարար</th>
                  </tr>
                  </thead>
                  <tbody>
                  <tr>
                    <td> Պայմանագիր 2</td>
                    <td> Մատակարար 2</td>
                  </tr>
                  </tbody>
                </table>
              </div>
            </td>
          </tr>
          <tr data-toggle="collapse" data-target="#demo3" class="accordion-toggle">
            <td> <a href="" class="btn-link"> >> </a> </td>
            <td> Կոդ 3 </td>
            <td> Անուն 3 </td>
            <td> 29.07.2015 </td>
            <td> <button  class="btn btn-default pull-right "><a href="#/add" ><span class="glyphicon glyphicon-plus"/></a></button> </td>
          </tr>
          <tr>
            <td colspan="12" class="hiddenRow">
              <div class="accordian-body collapse" id="demo3">
                <table class="table table-striped">
                  <thead>
                  <tr>
                    <th>Պայմանագրի համարը</th>
                    <th>Մատակարար</th>
                  </tr>
                  </thead>
                  <tbody>
                  <tr>
                    <td> Պայմանագիր 3</td>
                    <td> Մատակարար 3</td>
                  </tr>
                  </tbody>
                </table>
              </div>
            </td>
          </tr>
          <tr data-toggle="collapse" data-target="#demo4" class="accordion-toggle">
            <td> <a href="" class="btn-link"> >> </a> </td>
           <td> Կոդ 4 </td>
           <td> Անուն 4 </td>
           <td> 30.07.2015 </td>
           <td> <button  class="btn btn-default pull-right "><a href="#/add" ><span class="glyphicon glyphicon-plus"/></a></button> </td>
        </tr>
          <tr>
            <td colspan="12" class="hiddenRow">
              <div class="accordian-body collapse" id="demo4">
                <table class="table table-striped">
                  <thead>
                  <tr>
                    <th>Պայմանագրի համարը</th>
                    <th>Մատակարար</th>
                  </tr>
                  </thead>
                  <tbody>
                  <tr>
                    <td> Պայմանագիր 4</td>
                    <td> Մատակարար 4</td>
                  </tr>
                  </tbody>
                </table>
              </div>
            </td>
          </tr>
        </table>

        <div class="row">
          <div class="col-md-10 col-md-offset-4">
            <ul class="pagination">
              <li class="disabled"><a href=""><span class="glyphicon glyphicon-backward"></span></a></li>
              <li class="active"><a href="">1</a></li>
              <li class="disabled"><a href="#">2</a></li>
              <li class="disabled"><a href="#">3</a></li>
              <li class="disabled"><a href="#">4</a></li>
              <li class="disabled"><a href="#">5</a></li>
              <li class="disabled"><a href=""><span class="glyphicon glyphicon-forward"></span></a></li>
            </ul>

          </div>
        </div> <br/><br/>


    </script>

      <script type="text/ng-template" id="addContract">

        <div class="row" >

          <div class="form-group">
            <label for="number" class="col-md-4 col-md-offset-0 ">Պայմանագրի համարը:</label>
            <div class="col-sm-5">
              <input id="number"  class="form-control" placeholder=" Մուտքագրել " type="text" name="name"  ng-model="article.code" /><br/>
            </div>
          </div>

          <div class="form-group">
            <label for="manager" class="col-md-4 col-md-offset-0">Պայմանագրի վարող:</label>
            <div class="col-sm-5">
              <select id="manager" class="form-control " >
                <option value="" >-- Ընտրել --</option>
                <option value="1">Պայմանագրի վարող 1</option>
                <option value="2">Պայմանագրի վարող 2</option>
                <option value="3">Պայմանագրի վարող 3</option>
              </select><br/>
            </div>
          </div>

          <div class="form-group">
            <label for="type" class="col-md-4 col-md-offset-0">Մատակարարման տիպ:</label>
            <div class="col-sm-5">
              <select id="type" class="form-control " >
                <option value="" >-- Ընտրել --</option>
                <option value="1">Ցպահանջ</option>
                <option value="2">Ժամանակացույց</option>

              </select><br/>
            </div>
          </div>

        </div>

          <label for="supplier" class="col-md-4 col-md-offset-0">Մատակարար:</label>
          <div class="col-sm-5">
            <select id="supplier" class="form-control " ng-model="selected">
              <option value="" >-- Ընտրել Մատակարար --</option>
              <option value="1">Մատակարար 1</option>
              <option value="2">Մատակարար 2</option>
              <option value="3">Մատակարար 3</option>
            </select><br/>
          </div>


        <div class="row" ng-hide="selected ==='' || selected ===undefined ">
          <table class="table  table-hover">
            <thead class="alert-success" style="background-color: #ebddd2">
            <tr>
              <th> Կոդ </th>
              <th> Քանակ </th>
              <th> Գումար  </th>
              <th> Ընդհանուր  </th>
            </tr>
            </thead>

            <tr>
              <td> <input type="checkbox" value=""> Կոդ 1</td>
              <td> Քանակ 1</td>
              <td> Գումար 1 </td>
              <td> Ընդհանուր 1</td>
            </tr>

            <tr>
              <td> <input type="checkbox" value=""> Կոդ 2</td>
              <td> Քանակ 2</td>
              <td> Գումար 2 </td>
              <td> Ընդհանուր 2</td>
            </tr>

            <tr>
              <td> <input type="checkbox" value=""> Կոդ 3</td>
              <td> Քանակ 3</td>
              <td> Գումար 3</td>
              <td> Ընդհանուր 3</td>
            </tr>
          </table>

        <div class="row pull-right ">
          <button  class="btn btn-default "  ><a href="#/" >Ավելացնել</a></button>
          <button  class="btn btn-default "  ><a href="#/" >Չեղարկել</a></button>
        </div>

        </div><br/>

      </script>



    <script src="${pageContext.request.contextPath}/static/js/private/authority/pplan/tenders/tenders.js"></script>

  </tiles:putAttribute>
</tiles:insertDefinition>
