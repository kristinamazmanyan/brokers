<%--
  Created by IntelliJ IDEA.
  User: sevak.kerobyan
  Date: 7/28/2015
  Time: 10:17 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/tiles/common/layout/definitions.jsp" %>
<tiles:insertDefinition name="default" flush="true">

    <tiles:putAttribute name="title.page">Contracts</tiles:putAttribute>
    <tiles:putAttribute name="body" cascade="false">
        <div class="row" ng-controller="ContractsController">
        <div class="col-lg-offset-1 col-sm-10 ">

            <div class="col-sm-10">
                <label class="control-label col-sm-4">Ծածկագիր:</label>

                <div class="col-sm-4">
                    <span>ԾԾ14</span>
                </div>

            </div>
            <div class="col-sm-10">
                <label class="control-label col-sm-4">Պատվիրատու:</label>

                <div class="col-sm-4">
                    <span>ՀՀ նախագահի աշխատակազմ</span>
                </div>

            </div>
            <div class="col-sm-10">

                <label class="control-label col-sm-4">Կնքման ամսաթիվ:</label>

                <div class="col-sm-4">
                    <span>24/05/2015</span>
                </div>
            </div>

            <div class="col-sm-10">
                <label class="control-label col-sm-4">Վերջնաժամկետ:</label>

                <div class="col-sm-4">
                    <span>24/05/2016</span>
                </div>

            </div>
            <div class="col-sm-10">
                <label class="control-label col-sm-4">ՏՕ: </label>

                <div class="col-sm-4">
                    <span>"Կապալառու" ՍՊԸ</span>
                </div>
            </div>
        </div>
        <div class="col-lg-offset-1 col-sm-10 ">
            <div class="page-header text-center">
                <h3>Գնման առարկաներ</h3>
            </div>
            <table class="table  table-hover">
                <thead>
                <tr>
                    <th style="width: 60px">ԳՄԱ</th>
                    <th>Անվանում</th>
                    <th>Նախահաշվային գումար</th>
                    <th>Պայմանագրի գումարը</th>

                </tr>
                </thead>
                <tr ng-repeat="data in dataList">
                    <td class="col-sm-2"><a href="#">{{data.cpv_code}}</a></td>

                    <td><a href="#"> {{data.cpv_name}}</a></td>
                    <td><a href="#"> {{data.namount}}</a></td>
                    <td><a href="#"> {{data.amount}}</a></td>

                    </td>
                </tr>
            </table>

            <div class="modal fade" id="myModal" role="dialog">
                <div class="modal-dialog">

                    <!-- Modal content-->
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">Cancel Contract</h4>
                        </div>
                        <div class="modal-body">
                            <p>You want to cancel the contract.</p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn " data-dismiss="modal">Yes</button>
                            <button type="button" class="btn " data-dismiss="modal">No</button>
                        </div>
                    </div>

                </div>
            </div>

            <div class="form-group text-right">
                <div class="col-sm-offset-2 col-sm-10">

                    <button type="submit" class="btn " ng-click="create()">Create Delivary</button>
                    <button type="submit" class="btn "  data-toggle="modal" data-target="#myModal">Cancel</button>
                </div>
            </div>

        </div>


        <div class="col-lg-offset-1 col-sm-10 ">
            <div class="page-header text-center">
                <h3>Մատակարարման Ժամանակացույց</h3>
            </div>
            <accordion ng-repeat="acord in acords">
                <accordion-group heading="{{acord.date}}">
                    <div class="form-group">
                        <div class="col-sm-10">
                            <label class="control-label col-sm-4">ԳՄԱ : </label>

                            <div class="col-sm-4">
                                <span>{{acord.cpv_code}}</span>
                            </div>
                        </div>

                        <div class="col-sm-10">
                            <label class="control-label col-sm-4">Քանակ: </label>

                            <div class="col-sm-4">
                                <span>{{acord.time}}</span>
                            </div>
                        </div>
                        <div class="col-sm-10">
                            <label class="control-label col-sm-4">Գումարը : </label>

                            <div class="col-sm-4">
                                <span>{{acord.amount}}</span>
                            </div>
                        </div>

                        <div class="col-sm-10">
                            <label class="control-label col-sm-4">Կցվա: </label>

                            <div class="col-sm-4">
                                <span><a href="#"> {{acord.atache}}</a></span>
                            </div>
                        </div>
                    </div>
                </accordion-group>

            </accordion>



        </div>


        <script>
            PPCM.controller("ContractsController", function ($scope) {
                $scope.create = function(){


                       /* window.location.href = "cdelivery";*/

                        window.location.href = "delivery";


                }



                $scope.check = function(){
                    $scope.checked = true;


                }
                $scope.check1 = function(){
                    $scope.checked1 = true;
                }



                $scope.acords = [{
                    "date": "05/24/2014",
                    "cpv_code": "03121100",
                    "amount": 15,
                    time: 2,
                    atache: "testLink"
                },
                    {"date": "05/24/2014", "cpv_code": "03110000", "amount": 15, time: 2, atache: "testLink"},
                    {"date": "05/24/2014", "cpv_code": "98510000", "amount": 15, time: 4, atache: "testLink"},
                    {"date": "05/24/2014", "cpv_code": "98361000", "amount": 15, time: 1, atache: "testLink"},
                    {"date": "05/24/2014", "cpv_code": "98361000", "amount": 15, time: 1, atache: "testLink"}]

                $scope.dataList = [{
                    "cpv_code": "03110000",
                    "cpv_name": "գյուղատնտեսական կուլտուրաներ, արդյունաբերական այգեգործության արտադրանք և այգեգործություն",
                    "namount": 54655,
                    "amount": 2879,
                },
                    {
                        "cpv_code": "98510000",
                        "cpv_name": " առևտրային և արդյունաբերական աշխատողների ծառայություններ",
                        "namount": 54655,
                        "amount": 2879,
                    },
                    {
                        "cpv_code": "98361000",
                        "cpv_name": " օվկիանոսագիտական ծառայություններ ",
                        "namount": 54655,
                        "amount": 2879,
                    },
                    {
                        "cpv_code": "03121100",
                        "cpv_name": " կենդանի բույսեր, սոխուկներ, արմատներ, արմատակալներ և ընձյուղներ",
                        "namount": 54655,
                        "amount": 2879,
                    },
                    {
                        "cpv_code": "03121200",
                        "cpv_name": " քաղած ծաղիկներ",
                        "namount": 54655,
                        "amount": 2879,
                    },
                    {
                        "cpv_code": "98341100",
                        "cpv_name": "բնակելի տարածքների կառավարման ծառայություններ",
                        "namount": 54655,
                        "amount": 2879,
                    }]
            })

        </script>
    </tiles:putAttribute>
</tiles:insertDefinition>
