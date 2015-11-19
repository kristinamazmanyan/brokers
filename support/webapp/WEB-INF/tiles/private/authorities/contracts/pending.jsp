<%@ include file="/WEB-INF/tiles/common/layout/definitions.jsp" %>
<tiles:insertDefinition name="default" flush="true">

    <tiles:putAttribute name="title.page">Առկախված Պայմանագրեր</tiles:putAttribute>
    <tiles:putAttribute name="body" cascade="true">


        <div class="col-sm-10 ng-cloak col-sm-offset-1">
            <div ng-controller="pendingContractsControlle">
                <table class="table table-hover col-sm-12" tt-table tt-params="expand_params">
                    <thead>
                    <tr>
                        <th>Մրցույթ</th>
                        <th>մատակարար</th>
                        <th>ամսաթիվ</th>
                        <th>ղեկավար</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr ng-repeat="data in pendingList">
                        <td>{{data.tender}}</td>
                        <td>{{data.supplier}}</td>
                        <td>{{data.date}}</td>
                        <td>{{data.manager}}</td>
                        <td><span title="Assign a manager" data-toggle="modal" data-target="#myModal"
                                  class="glyphicon glyphicon-edit" ng-click="setIndex($index)"></span></td>
                    </tr>
                    </tbody>
                </table>

                <!-- Modal -->
                <div class="modal fade " id="myModal" role="dialog">
                    <div class="modal-dialog">

                        <!-- Modal content-->
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                <h4 class="modal-title">Manager Assignment</h4>
                            </div>
                            <div class="modal-body">
                                <div class="form-group">
                                    <select class="form-control" id="manager-list" ng-model="manager">
                                        <option value="" disabled selected>Managers</option>
                                        <option ng-repeat="a in managers" value="{{a}}">{{a}}</option>
                                    </select>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn" data-dismiss="modal"
                                        ng-click="setManager(manager)">Assign
                                </button>
                            </div>
                        </div>

                    </div>
                </div>
            </div>

        </div>

    </tiles:putAttribute>
</tiles:insertDefinition>

<script>
    PPCM.controller('pendingContractsControlle', function ($scope) {

        $scope.pendingList = [{
            tender: "Մրցույթ 01",
            supplier: "մատակարար 01",
            date: "28/07/2015",
            manager: ""
        }, {
            tender: "Մրցույթ 02",
            supplier: "մատակարար 02",
            date: "01/04/2015",
            manager: ""
        }, {
            tender: "Մրցույթ 03",
            supplier: "մատակարար 03",
            date: "08/07/2015",
            manager: ""
        }, {
            tender: "Մրցույթ 04",
            supplier: "մատակարար 04",
            date: "02/01/2014",
            manager: ""
        }];
        $scope.setIndex = function (index) {
            $scope.ind = index;
        };
        $scope.setManager = function () {
            $scope.pendingList[$scope.ind].manager = $scope.manager;
            $scope.manager = '';
        };
        $scope.managers = ['Ղեկավար 01', 'Ղեկավար 02', 'Ղեկավար 03', 'Ղեկավար 04'];


    });
</script>