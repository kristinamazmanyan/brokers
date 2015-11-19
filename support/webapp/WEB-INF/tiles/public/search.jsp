<%@ include file="/WEB-INF/tiles/common/layout/definitions.jsp" %>
<tiles:insertDefinition name="default" flush="true">

    <tiles:putAttribute name="title.page">Տեղեկատվություն</tiles:putAttribute>
    <tiles:putAttribute name="body" cascade="true">

        <ul class="nav nav-tabs">
            <li role="presentation" ng-class="{active: page.path('/search-contract')=='/search-contract'}"><a
                    href="#search-contract">Պայմանագրեր</a></li>
            <li role="presentation" ng-class="{active: page.path('/search-contract')=='/search-plan'}"><a
                    href="#search-plan">Գնումների պլան</a></li>
        </ul>

        <div class="row">
            <ng-view/>
        </div>


        <script type="text/ng-template" id="search-plan-view">
            <div class="col-sm-12 ng-cloak">

                    <form id="searchPlanView" method="post">

                        <div class="form-group">
                            <input type="text" ng-model="selected"
                                   typeahead="authority for authority in authorities | filter:$viewValue | limitTo:8"
                                   class="form-control"
                                   placeholder="Պատվիրատու">
                        </div>

                        <div class="form-group">
                            <input type="text" ng-model="uu"
                                   typeahead="cpv as cpv.name for cpv in cpvlist | filter:$viewValue"
                                   typeahead-editable="false"
                                   class="form-control"
                                   placeholder="գնման առարկա"
                                   typeahead-template-url="cpvTemplate.html">
                        </div>

                        <div class="form-group">
                            <select class="form-control" id="buying-methods" ng-model="filter.subject">
                                <option value="" disabled selected>գնման Ձեվէր</option>
                                <option ng-repeat="s in methods" value="{{a}}">{{a}}</option>
                            </select>
                        </div>

                        <div class="form-group ">
                            <select class="form-control" id="Year-list" ng-model="filter.contractDate">
                                <option value="" disabled selected>Տարի</option>
                                <option ng-repeat="s in years" value="{{a}}">{{a}}</option>
                            </select>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-offset-6 ">
                                <button type="submit" class="btn btn-default" ng-click="">Որոնել</button>
                                <button type="submit" class="btn btn-default" ng-click="cancel()">Չեղարկ</button>
                            </div>
                        </div>

                    </form>

            </div>
            <div class="col-sm-12">
                <tree-grid col_defs="col_defs" expand-level="1" tree-data="data"></tree-grid>
            </div>

        </script>


        <script type="text/ng-template" id="search-contract-view">
            <div class="col-sm-12">
                <form id="searchContractView" method="post">

                    <div class="form-group">
                        <input type="text" ng-model="selected"
                               typeahead="authority for authority in authorities | filter:$viewValue | limitTo:8"
                               class="form-control"
                               placeholder="Պատվիրատու">
                    </div>

                    <div class="form-group">
                        <select class="form-control" id="operator-list" ng-model="filter.operator">
                            <option value="" disabled selected>տնտէսական օպէրատոր</option>
                            <option ng-repeat="a in operators" value="{{a}}">{{a}}</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <input type="text" ng-model="uu"
                               typeahead="cpv as cpv.name for cpv in cpvlist | filter:$viewValue"
                               typeahead-editable="false"
                               class="form-control"
                               placeholder="գնման առարկա"
                               typeahead-template-url="cpvTemplate.html">
                    </div>


                    <div class="form-group">
                        <select class="form-control" id="state-list" ng-model="filter.subject">
                            <option value="" disabled selected>գնման կարգավիճակ</option>
                            <option value="">Pending</option>
                            <option value="">Active</option>
                            <option value="">Closed</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <input date-range-picker class="form-control date-picker" type="text" ng-model="date"
                               placeholder="պայմանագրի կնքման ամսաթիվ"/>
                    </div>


                    <div class="form-group">
                        <div class="col-sm-offset-4 ">
                            <button type="submit" class="btn btn-default" ng-click="">Որոնել</button>
                            <button type="submit" class="btn btn-default" ng-click="">Չեղարկ</button>
                        </div>
                    </div>

                </form>
            </div>
            <div class="col-sm-12">
                <tree-grid col_defs="col_defs" expand-level="1" tree-data="data"></tree-grid>
            </div>



        </script>

        <script type="text/ng-template" id="cpvTemplate.html">
            <a>
                Name: <span style="color:lightblue"
                            bind-html-unsafe="match.model.name | typeaheadHighlight:query"></span>
                &nbsp; code: <span style="color:darkgray"
                                   bind-html-unsafe="match.model.code | typeaheadHighlight:query"></span>
            </a>
        </script>


        <script src="${pageContext.request.contextPath}/static/js/public/search.js"></script>
    </tiles:putAttribute>
</tiles:insertDefinition>

