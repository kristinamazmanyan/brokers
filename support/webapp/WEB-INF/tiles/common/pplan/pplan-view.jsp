<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
Mode : ${param.mode} <br/>
authorityId : ${param.authorityId}

<br>
<br>

<div ng-controller="pplanController">

    <c:if test="${param.mode != 'edit'}">

        <div class="row">

            <div class="form-inline">

                <c:if test="${param.authorityId == 'all'}">
                    <div class="form-group col-sm-3">
                        <label for="auth-list">Authority </label>
                        <select class="form-control" id="Auth-list" ng-model="filter.authority">
                            <option ng-repeat="a in authorities" value="{{a}}">{{a}}</option>
                        </select>
                    </div>
                </c:if>

                <div class="form-group col-sm-3">
                    <label for="Year-list">Year </label>
                    <select class="form-control" id="Year-list" ng-model="filter.year">
                        <option ng-repeat="d in years" value="d">{{d}}</option>
                    </select>
                </div>

            </div>

        </div>
    </c:if>

    <c:if test="${param.mode == 'edit'}">
        <div class="col-sm-2 pull-right">
            <label>6 </label>
            <button type="button" class="btn btn-lg">
                <span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>
            </button>
        </div>
    </c:if>

    <br>

    <tree-grid  col-defs="col_defs" expand-level="1" tree-data="data"></tree-grid>

        <script type="application/javascript"
                src="${pageContext.request.contextPath}/static/js/private/authority/pplan/pplan.js"></script>
        <script type="application/javascript"
                src="${pageContext.request.contextPath}/static/js/generated/components/administration/pplan_service.js"></script>

<%--<script>
    PPCM.value("planTableHeader", {

    })

    PPCM.controller('pplanController', function ($scope, $routeParams) {
        $scope.mode = ' ${param.mode}';
        $scope.years = ["2015", "2014", '2013', '2012', '2011', '2010', '2009', '2008'];
        $scope.authorities = ["authority1", "authority2", "authority3"];
 $scope.name =null;
        $scope.col_defs = [
            {
                field:"name",
                displayName:"Ծրագիր"
            },
            {
                field: "dth",
                displayName:"ՏԴՀ",
                sortable : true,
                sortingType : "number",
                filterable : true
            },
            {
                field: "cpv",
                displayName:"<spring:message code='page.cpv.code'/>",
                cellTemplate: '<c:if test="${param.mode == 'edit'}">\
                              <label>\
                               <input ng-show="row.branch[col.field] != null" type="checkbox" name="codes" value="row.branch[col.field]"/> \
                               <span ng-bind="{{ row.branch[col.field] }}" ></span>\
                             </label>\
                               </c:if>',
                sortable : true,
                sortingType : "number",
                filterable : true

            },
            { field: "method",
                displayName:"Գնման ձեւ(ընթացակարգը)",
                sortable : true,
                filterable : true
            },
            { field: "unit",
                displayName:"Չափման միավորը",
                sortable : true,
                filterable : true
            },
            { field: "unitcost",
                displayName:"Միավորի գինը",
                sortable : true,
                filterable : true
            },
            { field: "totalcost",
                displayName:"Ընդամենը ծախսերը (դրամ)",
                sortable : true,
                sortingType : "number",
                filterable : true
            },
            { field: "quantity",
                displayName:"Քանակը",
                sortable : true,
                filterable : true
            },
            { field: "saving",
                displayName:"տնտեսում",
                sortable : true,
                filterable : true
            }
        ];
// authority view
        $scope.data = [
            {
                name: 'Ծրաքիր 01',
                quantity: '',
                cpv: null,
                buymethod: '',
                unit: '',
                unitcost: '',
                totalcost: '3566',
                saving: '',
                children: [
                    {
                        name: 'ԸՆԹԱՑԻԿ ԾԱԽՍԷՐ',
                        children: [
                            {
                                name: 'ՇԱՐՈՒՆԱԿԱԿԱՆ ԾԱԽՍԵՐ',
                                children: [{
                                    name: 'Էներգետիկ ծառայություններ', //dth
                                    dth: '4212',
                                    children: [
                                        {
                                            name: 'Ապրանքներ',
                                            size: '',
                                            children: [
                                                {
                                                    name:'paper',
                                                    cpv: 09655211,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '3566',
                                                    quantity: '7',
                                                    saving: ''

                                                },
                                                {
                                                    name:'box',
                                                    cpv: 04655181,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '9000',
                                                    quantity: '2',
                                                    saving: ''

                                                },
                                                {
                                                    name:'table',
                                                    cpv: 08655471,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '5000',
                                                    quantity: '1',
                                                    saving: ''

                                                }

                                            ] //cpv's

                                        },
                                        {
                                            name: 'Աշխատանքներ',
                                            type: 'folder',
                                            size: '',
                                            children: [
                                                {
                                                    cpv: 5432,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '3566',
                                                    quantity: '7',
                                                    saving: ''

                                                },
                                                {
                                                    cpv: 53245,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '9000',
                                                    quantity: '2',
                                                    saving: ''

                                                },
                                                {
                                                    cpv: 4114444,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '5000',
                                                    quantity: '1',
                                                    saving: ''

                                                }
                                            ]
                                        },
                                        {
                                            name: 'Ծառայություններ',
                                            type: 'folder',
                                            size: '',
                                            children: []
                                        }
                                    ]
                                },
                                    {
                                        name: 'Կոմունալ ծառայություններ',
                                        type: 'folder',
                                        dth: '4213',
                                        children: [
                                            {
                                                name: 'Ապրանքներ',
                                                type: 'folder',

                                                size: '',
                                                children: [
                                                    {
                                                        cpv: 1,
                                                        buymethod: 'method',
                                                        unit: 'unit',
                                                        unitcost: 'unit cost',
                                                        totalcost: '3566',
                                                        quantity: '7',
                                                        saving: ''

                                                    },
                                                    {
                                                        cpv: 2,
                                                        buymethod: 'method',
                                                        unit: 'unit',
                                                        unitcost: 'unit cost',
                                                        totalcost: '9000',
                                                        quantity: '2',
                                                        saving: ''

                                                    },
                                                    {
                                                        cpv: 2,
                                                        buymethod: 'method',
                                                        unit: 'unit',
                                                        unitcost: 'unit cost',
                                                        totalcost: '5000',
                                                        quantity: '1',
                                                        saving: ''

                                                    }

                                                ]

                                            },
                                            {
                                                name: 'Աշխատանքներ',
                                                type: 'folder',

                                                size: '',
                                                children: [
                                                    {
                                                        cpv: 5432,
                                                        buymethod: 'method',
                                                        unit: 'unit',
                                                        unitcost: 'unit cost',
                                                        totalcost: '3566',
                                                        quantity: '7',
                                                        saving: ''

                                                    },
                                                    {
                                                        cpv: 53245,
                                                        buymethod: 'method',
                                                        unit: 'unit',
                                                        unitcost: 'unit cost',
                                                        totalcost: '9000',
                                                        quantity: '2',
                                                        saving: ''

                                                    },
                                                    {
                                                        cpv: 41144,
                                                        buymethod: 'method',
                                                        unit: 'unit',
                                                        unitcost: 'unit cost',
                                                        totalcost: '5000',
                                                        quantity: '1',
                                                        saving: ''

                                                    }
                                                ]
                                            },
                                            {
                                                name: 'Ծառայություններ',
                                                type: 'folder',
                                                size: '',
                                                children: []
                                            }
                                        ]
                                    }
                                ]
                            },
                            {
                                name: 'ԳՈՐԾՈՒՂՄԱՆ ԵՎ ՇՐՋԱԳԱՅՈՒԹՅՈՒՆՆԵՐԻ ԾԱԽՍԵՐ',
                                type: 'folder',
                                children: [{
                                    name: 'Ներքին գործուղումներ', //dth
                                    type: 'folder',
                                    dth: '4221',
                                    children: [
                                        {
                                            name: 'Ապրանքներ',
                                            type: 'folder',
                                            size: '',
                                            children: [
                                                {
                                                    cpv: 1,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '3566',
                                                    quantity: '7',
                                                    saving: ''

                                                },
                                                {
                                                    cpv: 2,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '9000',
                                                    quantity: '2',
                                                    saving: ''

                                                },
                                                {
                                                    cpv: 2,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '5000',
                                                    quantity: '1',
                                                    saving: ''

                                                }

                                            ] //cpv's

                                        },
                                        {
                                            name: 'Աշխատանքներ',
                                            type: 'folder',
                                            size: '',
                                            children: [
                                                {
                                                    cpv: 5432,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '3566',
                                                    quantity: '7',
                                                    saving: ''

                                                },
                                                {
                                                    cpv: 53245,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '9000',
                                                    quantity: '2',
                                                    saving: ''

                                                },
                                                {
                                                    cpv: 4114444,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '5000',
                                                    quantity: '1',
                                                    saving: ''

                                                }
                                            ]
                                        },
                                        {
                                            name: 'Ծառայություններ',
                                            type: 'folder',

                                            size: '',
                                            children: []
                                        }
                                    ]
                                },
                                    {
                                        name: 'Արտասահմանյան գործուղումների գծով ծախսեր',
                                        type: 'folder',
                                        dth: '4222',
                                        children: [
                                            {
                                                name: 'Ապրանքներ',
                                                type: 'folder',

                                                size: '',
                                                children: [
                                                    {
                                                        cpv: 1,
                                                        buymethod: 'method',
                                                        unit: 'unit',
                                                        unitcost: 'unit cost',
                                                        totalcost: '3566',
                                                        quantity: '7',
                                                        saving: ''

                                                    },
                                                    {
                                                        cpv: 2,
                                                        buymethod: 'method',
                                                        unit: 'unit',
                                                        unitcost: 'unit cost',
                                                        totalcost: '9000',
                                                        quantity: '2',
                                                        saving: ''

                                                    },
                                                    {
                                                        cpv: 2,
                                                        buymethod: 'method',
                                                        unit: 'unit',
                                                        unitcost: 'unit cost',
                                                        totalcost: '5000',
                                                        quantity: '1',
                                                        saving: ''

                                                    }

                                                ]

                                            },
                                            {
                                                name: 'Աշխատանքներ',
                                                type: 'folder',
                                                size: '',
                                                children: [
                                                    {
                                                        cpv: 5432,
                                                        buymethod: 'method',
                                                        unit: 'unit',
                                                        unitcost: 'unit cost',
                                                        totalcost: '3566',
                                                        quantity: '7',
                                                        saving: ''

                                                    },
                                                    {
                                                        cpv: 53245,
                                                        buymethod: 'method',
                                                        unit: 'unit',
                                                        unitcost: 'unit cost',
                                                        totalcost: '9000',
                                                        quantity: '2',
                                                        saving: ''

                                                    },
                                                    {
                                                        cpv: 4114444,
                                                        buymethod: 'method',
                                                        unit: 'unit',
                                                        unitcost: 'unit cost',
                                                        totalcost: '5000',
                                                        quantity: '1',
                                                        saving: ''

                                                    }
                                                ]
                                            },
                                            {
                                                name: 'Ծառայություններ',
                                                children: []
                                            }
                                        ]
                                    }
                                ]
                            }]
                    },
                    {
                        name: 'ԿԱՊԻՏԱԼ ԾԱԽՍԵՐ',
                        children: [{
                            name: 'ՇԵՆՔԵՐ ԵՎ ՇԻՆՈՒԹՅՈՒՆՆԵՐ',
                            children: [
                                {
                                    name: 'Շենքերի եւ շինությունների ձեռք բերում', //dth
                                    dth: '5111',
                                    children: [
                                        {
                                            name: 'Ապրանքներ',
                                            children: [
                                                {
                                                    cpv: 1,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '3566',
                                                    quantity: '7',
                                                    saving: ''

                                                },
                                                {
                                                    cpv: 2,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '9000',
                                                    quantity: '2',
                                                    saving: ''

                                                },
                                                {
                                                    cpv: 2,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '5000',
                                                    quantity: '1',
                                                    saving: ''

                                                }

                                            ] //cpv's

                                        },
                                        {
                                            name: 'Աշխատանքներ',
                                            children: [
                                                {
                                                    cpv: 5432,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '3566',
                                                    quantity: '7',
                                                    saving: ''

                                                },
                                                {
                                                    cpv: 53245,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '9000',
                                                    quantity: '2',
                                                    saving: ''

                                                },
                                                {
                                                    cpv: 4114444,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '5000',
                                                    quantity: '1',
                                                    saving: ''

                                                }
                                            ]
                                        },
                                        {
                                            name: 'Ծառայություններ',
                                            children: []
                                        }
                                    ]
                                },
                                {
                                    name: 'Շենքերի եւ շինությունների կառուցում',
                                    type: 'folder',
                                    dth: '5112',
                                    children: [
                                        {
                                            name: 'Ապրանքներ',
                                            type: 'folder',
                                            children: [
                                                {
                                                    cpv: 1,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '3566',
                                                    quantity: '7',
                                                    saving: ''

                                                },
                                                {
                                                    cpv: 2,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '9000',
                                                    quantity: '2',
                                                    saving: ''

                                                },
                                                {
                                                    cpv: 2,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '5000',
                                                    quantity: '1',
                                                    saving: ''

                                                }

                                            ]

                                        },
                                        {
                                            name: 'Աշխատանքներ',
                                            children: [
                                                {
                                                    cpv: 5432,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '3566',
                                                    quantity: '7',
                                                    saving: ''

                                                },
                                                {
                                                    cpv: 53245,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '9000',
                                                    quantity: '2',
                                                    saving: ''

                                                },
                                                {
                                                    cpv: 4114444,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '5000',
                                                    quantity: '1',
                                                    saving: ''

                                                }
                                            ]
                                        },
                                        {
                                            name: 'Ծառայություններ',
                                            children: []
                                        }
                                    ]
                                },
                            ]
                        }, {
                            name: 'ԱՅԼ ՀԻՄՆԱԿԱՆ ՄԻՋՈՑՆԵՐ',
                            children: [
                                {
                                    name: 'Աճեցվող ակտիվներ', //dth
                                    dth: '5131',
                                    children: [
                                        {
                                            name: 'Ապրանքներ',

                                            children: [
                                                {
                                                    cpv: 1,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '3566',
                                                    quantity: '7',
                                                    saving: ''

                                                },
                                                {
                                                    cpv: 2,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '9000',
                                                    quantity: '2',
                                                    saving: ''

                                                },
                                                {
                                                    cpv: 2,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '5000',
                                                    quantity: '1',
                                                    saving: ''

                                                }

                                            ] //cpv's

                                        },
                                        {
                                            name: 'Աշխատանքներ',
                                            children: [
                                                {
                                                    cpv: 5432,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '3566',
                                                    quantity: '7',
                                                    saving: ''

                                                },
                                                {
                                                    cpv: 53245,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '9000',
                                                    quantity: '2',
                                                    saving: ''

                                                },
                                                {
                                                    cpv: 4114444,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '5000',
                                                    quantity: '1',
                                                    saving: ''

                                                }
                                            ]
                                        },
                                        {
                                            name: 'Ծառայություններ',
                                            children: []
                                        }
                                    ]
                                },
                                {
                                    name: 'Նախագծահետազոտական ծախսեր',
                                    dth: '5134',
                                    children: [
                                        {
                                            name: 'Ապրանքներ',
                                            type: 'folder',

                                            children: [
                                                {
                                                    cpv: 1,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '3566',
                                                    quantity: '7',
                                                    saving: ''

                                                },
                                                {
                                                    cpv: 2,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '9000',
                                                    quantity: '2',
                                                    saving: ''

                                                },
                                                {
                                                    cpv: 2,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '5000',
                                                    quantity: '1',
                                                    saving: ''

                                                }

                                            ]

                                        },
                                        {
                                            name: 'Աշխատանքներ',
                                            type: 'folder',

                                            children: [
                                                {
                                                    cpv: 5432,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '3566',
                                                    quantity: '7',
                                                    saving: ''

                                                },
                                                {
                                                    cpv: 53245,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '9000',
                                                    quantity: '2',
                                                    saving: ''

                                                },
                                                {
                                                    cpv: 4114444,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '5000',
                                                    quantity: '1',
                                                    saving: ''

                                                }
                                            ]
                                        },
                                        {
                                            name: 'Ծառայություններ',
                                            children: []
                                        }
                                    ]
                                },
                            ]
                        }
                        ]
                    }]
            },
            {
                name: 'Ծրաքիր 02',
                quantity: '15',
                children: [
                    {
                        name: 'ԸՆԹԱՑԻԿ ԾԱԽՍԷՐ',
                        children: [
                            {
                                name: 'ՇԱՐՈՒՆԱԿԱԿԱՆ ԾԱԽՍԵՐ',
                                type: 'folder',
                                children: [{
                                    name: 'Էներգետիկ ծառայություններ', //dth
                                    type: 'folder',
                                    dth: '4212',
                                    children: [
                                        {
                                            name: 'Ապրանքներ',
                                            type: 'folder',

                                            size: '',
                                            children: [
                                                {
                                                    cpv: 1,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '3566',
                                                    quantity: '7',
                                                    saving: ''

                                                },
                                                {
                                                    cpv: 2,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '9000',
                                                    quantity: '2',
                                                    saving: ''

                                                },
                                                {
                                                    cpv: 2,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '5000',
                                                    quantity: '1',
                                                    saving: ''

                                                }

                                            ] //cpv's

                                        },
                                        {
                                            name: 'Աշխատանքներ',
                                            children: [
                                                {
                                                    cpv: 5432,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '3566',
                                                    quantity: '7',
                                                    saving: ''

                                                },
                                                {
                                                    cpv: 53245,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '9000',
                                                    quantity: '2',
                                                    saving: ''

                                                },
                                                {
                                                    cpv: 4114444,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '5000',
                                                    quantity: '1',
                                                    saving: ''

                                                }
                                            ]
                                        },
                                        {
                                            name: 'Ծառայություններ',
                                            type: 'folder',
                                            size: '',
                                            children: []
                                        }
                                    ]
                                },
                                    {
                                        name: 'Կոմունալ ծառայություններ',
                                        type: 'folder',
                                        dth: '4213',
                                        children: [
                                            {
                                                name: 'Ապրանքներ',
                                                type: 'folder',

                                                size: '',
                                                children: [
                                                    {
                                                        cpv: 1,
                                                        buymethod: 'method',
                                                        unit: 'unit',
                                                        unitcost: 'unit cost',
                                                        totalcost: '3566',
                                                        quantity: '7',
                                                        saving: ''

                                                    },
                                                    {
                                                        cpv: 2,
                                                        buymethod: 'method',
                                                        unit: 'unit',
                                                        unitcost: 'unit cost',
                                                        totalcost: '9000',
                                                        quantity: '2',
                                                        saving: ''

                                                    },
                                                    {
                                                        cpv: 2,
                                                        buymethod: 'method',
                                                        unit: 'unit',
                                                        unitcost: 'unit cost',
                                                        totalcost: '5000',
                                                        quantity: '1',
                                                        saving: ''

                                                    }

                                                ]

                                            },
                                            {
                                                name: 'Աշխատանքներ',
                                                type: 'folder',

                                                size: '',
                                                children: [
                                                    {
                                                        cpv: 5432,
                                                        buymethod: 'method',
                                                        unit: 'unit',
                                                        unitcost: 'unit cost',
                                                        totalcost: '3566',
                                                        quantity: '7',
                                                        saving: ''

                                                    },
                                                    {
                                                        cpv: 53245,
                                                        buymethod: 'method',
                                                        unit: 'unit',
                                                        unitcost: 'unit cost',
                                                        totalcost: '9000',
                                                        quantity: '2',
                                                        saving: ''

                                                    },
                                                    {
                                                        cpv: 41144,
                                                        buymethod: 'method',
                                                        unit: 'unit',
                                                        unitcost: 'unit cost',
                                                        totalcost: '5000',
                                                        quantity: '1',
                                                        saving: ''

                                                    }
                                                ]
                                            },
                                            {
                                                name: 'Ծառայություններ',
                                                type: 'folder',
                                                size: '',
                                                children: []
                                            }
                                        ]
                                    }
                                ]
                            },
                            {
                                name: 'ԳՈՐԾՈՒՂՄԱՆ ԵՎ ՇՐՋԱԳԱՅՈՒԹՅՈՒՆՆԵՐԻ ԾԱԽՍԵՐ',
                                type: 'folder',
                                children: [{
                                    name: 'Ներքին գործուղումներ', //dth
                                    type: 'folder',
                                    dth: '4221',
                                    children: [
                                        {
                                            name: 'Ապրանքներ',
                                            type: 'folder',
                                            size: '',
                                            children: [
                                                {
                                                    cpv: 1,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '3566',
                                                    quantity: '7',
                                                    saving: ''

                                                },
                                                {
                                                    cpv: 2,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '9000',
                                                    quantity: '2',
                                                    saving: ''

                                                },
                                                {
                                                    cpv: 2,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '5000',
                                                    quantity: '1',
                                                    saving: ''

                                                }

                                            ] //cpv's

                                        },
                                        {
                                            name: 'Աշխատանքներ',
                                            type: 'folder',
                                            size: '',
                                            children: [
                                                {
                                                    cpv: 5432,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '3566',
                                                    quantity: '7',
                                                    saving: ''

                                                },
                                                {
                                                    cpv: 53245,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '9000',
                                                    quantity: '2',
                                                    saving: ''

                                                },
                                                {
                                                    cpv: 4114444,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '5000',
                                                    quantity: '1',
                                                    saving: ''

                                                }
                                            ]
                                        },
                                        {
                                            name: 'Ծառայություններ',
                                            type: 'folder',

                                            size: '',
                                            children: []
                                        }
                                    ]
                                },
                                    {
                                        name: 'Արտասահմանյան գործուղումների գծով ծախսեր',
                                        type: 'folder',
                                        dth: '4222',
                                        children: [
                                            {
                                                name: 'Ապրանքներ',
                                                type: 'folder',

                                                size: '',
                                                children: [
                                                    {
                                                        cpv: 1,
                                                        buymethod: 'method',
                                                        unit: 'unit',
                                                        unitcost: 'unit cost',
                                                        totalcost: '3566',
                                                        quantity: '7',
                                                        saving: ''

                                                    },
                                                    {
                                                        cpv: 2,
                                                        buymethod: 'method',
                                                        unit: 'unit',
                                                        unitcost: 'unit cost',
                                                        totalcost: '9000',
                                                        quantity: '2',
                                                        saving: ''

                                                    },
                                                    {
                                                        cpv: 2,
                                                        buymethod: 'method',
                                                        unit: 'unit',
                                                        unitcost: 'unit cost',
                                                        totalcost: '5000',
                                                        quantity: '1',
                                                        saving: ''

                                                    }

                                                ]

                                            },
                                            {
                                                name: 'Աշխատանքներ',
                                                type: 'folder',
                                                size: '',
                                                children: [
                                                    {
                                                        cpv: 5432,
                                                        buymethod: 'method',
                                                        unit: 'unit',
                                                        unitcost: 'unit cost',
                                                        totalcost: '3566',
                                                        quantity: '7',
                                                        saving: ''

                                                    },
                                                    {
                                                        cpv: 53245,
                                                        buymethod: 'method',
                                                        unit: 'unit',
                                                        unitcost: 'unit cost',
                                                        totalcost: '9000',
                                                        quantity: '2',
                                                        saving: ''

                                                    },
                                                    {
                                                        cpv: 4114444,
                                                        buymethod: 'method',
                                                        unit: 'unit',
                                                        unitcost: 'unit cost',
                                                        totalcost: '5000',
                                                        quantity: '1',
                                                        saving: ''

                                                    }
                                                ]
                                            },
                                            {
                                                name: 'Ծառայություններ',
                                                type: 'folder',
                                                size: '',
                                                children: []
                                            }
                                        ]
                                    }
                                ]
                            }]
                    },
                    {
                        name: 'ԿԱՊԻՏԱԼ ԾԱԽՍԵՐ',
                        type: 'folder',

                        children: [{
                            name: 'ՇԵՆՔԵՐ ԵՎ ՇԻՆՈՒԹՅՈՒՆՆԵՐ',
                            type: 'folder',

                            children: [
                                {
                                    name: 'Շենքերի եւ շինությունների ձեռք բերում', //dth
                                    type: 'folder',

                                    dth: '5111',
                                    children: [
                                        {
                                            name: 'Ապրանքներ',
                                            type: 'folder',

                                            size: '',
                                            children: [
                                                {
                                                    cpv: 1,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '3566',
                                                    quantity: '7',
                                                    saving: ''

                                                },
                                                {
                                                    cpv: 2,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '9000',
                                                    quantity: '2',
                                                    saving: ''

                                                },
                                                {
                                                    cpv: 2,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '5000',
                                                    quantity: '1',
                                                    saving: ''

                                                }

                                            ] //cpv's

                                        },
                                        {
                                            name: 'Աշխատանքներ',
                                            type: 'folder',

                                            size: '',
                                            children: [
                                                {
                                                    cpv: 5432,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '3566',
                                                    quantity: '7',
                                                    saving: ''

                                                },
                                                {
                                                    cpv: 53245,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '9000',
                                                    quantity: '2',
                                                    saving: ''

                                                },
                                                {
                                                    cpv: 4114444,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '5000',
                                                    quantity: '1',
                                                    saving: ''

                                                }
                                            ]
                                        },
                                        {
                                            name: 'Ծառայություններ',
                                            type: 'folder',

                                            size: '',
                                            children: []
                                        }
                                    ]
                                },
                                {
                                    name: 'Շենքերի եւ շինությունների կառուցում',
                                    type: 'folder',

                                    dth: '5112',
                                    children: [
                                        {
                                            name: 'Ապրանքներ',
                                            type: 'folder',

                                            children: [
                                                {
                                                    cpv: 1,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '3566',
                                                    quantity: '7',
                                                    saving: ''

                                                },
                                                {
                                                    cpv: 2,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '9000',
                                                    quantity: '2',
                                                    saving: ''

                                                },
                                                {
                                                    cpv: 2,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '5000',
                                                    quantity: '1',
                                                    saving: ''

                                                }

                                            ]

                                        },
                                        {
                                            name: 'Աշխատանքներ',
                                            type: 'folder',

                                            children: [
                                                {
                                                    cpv: 5432,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '3566',
                                                    quantity: '7',
                                                    saving: ''

                                                },
                                                {
                                                    cpv: 53245,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '9000',
                                                    quantity: '2',
                                                    saving: ''

                                                },
                                                {
                                                    cpv: 4114444,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '5000',
                                                    quantity: '1',
                                                    saving: ''

                                                }
                                            ]
                                        },
                                        {
                                            name: 'Ծառայություններ',
                                            type: 'folder',

                                            children: []
                                        }
                                    ]
                                },
                            ]
                        }, {
                            name: 'ԱՅԼ ՀԻՄՆԱԿԱՆ ՄԻՋՈՑՆԵՐ',
                            type: 'folder',

                            children: [
                                {
                                    name: 'Աճեցվող ակտիվներ', //dth
                                    type: 'folder',

                                    dth: '5131',
                                    children: [
                                        {
                                            name: 'Ապրանքներ',
                                            type: 'folder',

                                            size: '',
                                            children: [
                                                {
                                                    cpv: 1,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '3566',
                                                    quantity: '7',
                                                    saving: ''

                                                },
                                                {
                                                    cpv: 2,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '9000',
                                                    quantity: '2',
                                                    saving: ''

                                                },
                                                {
                                                    cpv: 2,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '5000',
                                                    quantity: '1',
                                                    saving: ''

                                                }

                                            ] //cpv's

                                        },
                                        {
                                            name: 'Աշխատանքներ',
                                            type: 'folder',

                                            size: '',
                                            children: [
                                                {
                                                    cpv: 5432,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '3566',
                                                    quantity: '7',
                                                    saving: ''

                                                },
                                                {
                                                    cpv: 53245,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '9000',
                                                    quantity: '2',
                                                    saving: ''

                                                },
                                                {
                                                    cpv: 4114444,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '5000',
                                                    quantity: '1',
                                                    saving: ''

                                                }
                                            ]
                                        },
                                        {
                                            name: 'Ծառայություններ',
                                            type: 'folder',

                                            size: '',
                                            children: []
                                        }
                                    ]
                                },
                                {
                                    name: 'Նախագծահետազոտական ծախսեր',
                                    type: 'folder',

                                    dth: '5134',
                                    children: [
                                        {
                                            name: 'Ապրանքներ',
                                            type: 'folder',

                                            children: [
                                                {
                                                    cpv: 1,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '3566',
                                                    quantity: '7',
                                                    saving: ''

                                                },
                                                {
                                                    cpv: 2,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '9000',
                                                    quantity: '2',
                                                    saving: ''

                                                },
                                                {
                                                    cpv: 2,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '5000',
                                                    quantity: '1',
                                                    saving: ''

                                                }

                                            ]

                                        },
                                        {
                                            name: 'Աշխատանքներ',
                                            type: 'folder',

                                            children: [
                                                {
                                                    cpv: 5432,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '3566',
                                                    quantity: '7',
                                                    saving: ''

                                                },
                                                {
                                                    cpv: 53245,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '9000',
                                                    quantity: '2',
                                                    saving: ''

                                                },
                                                {
                                                    cpv: 4114444,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '5000',
                                                    quantity: '1',
                                                    saving: ''

                                                }
                                            ]
                                        },
                                        {
                                            name: 'Ծառայություններ',
                                            type: 'folder',

                                            children: []
                                        }
                                    ]
                                },
                            ]
                        }
                        ]
                    }]
            },
            {
                name: 'Ծրաքիր 03',
                type: 'folder',
                quantity: '17',
                children: [
                    {
                        name: 'ԸՆԹԱՑԻԿ ԾԱԽՍԷՐ',
                        type: 'folder',
                        children: [
                            {
                                name: 'ՇԱՐՈՒՆԱԿԱԿԱՆ ԾԱԽՍԵՐ',
                                type: 'folder',
                                children: [{
                                    name: 'Էներգետիկ ծառայություններ', //dth
                                    type: 'folder',
                                    dth: '4212',
                                    children: [
                                        {
                                            name: 'Ապրանքներ',
                                            type: 'folder',

                                            size: '',
                                            children: [
                                                {
                                                    cpv: 1,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '3566',
                                                    quantity: '7',
                                                    saving: ''

                                                },
                                                {
                                                    cpv: 2,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '9000',
                                                    quantity: '2',
                                                    saving: ''

                                                },
                                                {
                                                    cpv: 2,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '5000',
                                                    quantity: '1',
                                                    saving: ''

                                                }

                                            ] //cpv's

                                        },
                                        {
                                            name: 'Աշխատանքներ',
                                            type: 'folder',

                                            size: '',
                                            children: [
                                                {
                                                    cpv: 5432,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '3566',
                                                    quantity: '7',
                                                    saving: ''

                                                },
                                                {
                                                    cpv: 53245,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '9000',
                                                    quantity: '2',
                                                    saving: ''

                                                },
                                                {
                                                    cpv: 4114444,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '5000',
                                                    quantity: '1',
                                                    saving: ''

                                                }
                                            ]
                                        },
                                        {
                                            name: 'Ծառայություններ',
                                            type: 'folder',
                                            size: '',
                                            children: []
                                        }
                                    ]
                                },
                                    {
                                        name: 'Կոմունալ ծառայություններ',
                                        type: 'folder',
                                        dth: '4213',
                                        children: [
                                            {
                                                name: 'Ապրանքներ',
                                                type: 'folder',

                                                size: '',
                                                children: [
                                                    {
                                                        cpv: 1,
                                                        buymethod: 'method',
                                                        unit: 'unit',
                                                        unitcost: 'unit cost',
                                                        totalcost: '3566',
                                                        quantity: '7',
                                                        saving: ''

                                                    },
                                                    {
                                                        cpv: 2,
                                                        buymethod: 'method',
                                                        unit: 'unit',
                                                        unitcost: 'unit cost',
                                                        totalcost: '9000',
                                                        quantity: '2',
                                                        saving: ''

                                                    },
                                                    {
                                                        cpv: 2,
                                                        buymethod: 'method',
                                                        unit: 'unit',
                                                        unitcost: 'unit cost',
                                                        totalcost: '5000',
                                                        quantity: '1',
                                                        saving: ''

                                                    }

                                                ]

                                            },
                                            {
                                                name: 'Աշխատանքներ',
                                                type: 'folder',

                                                size: '',
                                                children: [
                                                    {
                                                        cpv: 5432,
                                                        buymethod: 'method',
                                                        unit: 'unit',
                                                        unitcost: 'unit cost',
                                                        totalcost: '3566',
                                                        quantity: '7',
                                                        saving: ''

                                                    },
                                                    {
                                                        cpv: 53245,
                                                        buymethod: 'method',
                                                        unit: 'unit',
                                                        unitcost: 'unit cost',
                                                        totalcost: '9000',
                                                        quantity: '2',
                                                        saving: ''

                                                    },
                                                    {
                                                        cpv: 41144,
                                                        buymethod: 'method',
                                                        unit: 'unit',
                                                        unitcost: 'unit cost',
                                                        totalcost: '5000',
                                                        quantity: '1',
                                                        saving: ''

                                                    }
                                                ]
                                            },
                                            {
                                                name: 'Ծառայություններ',
                                                type: 'folder',
                                                size: '',
                                                children: []
                                            }
                                        ]
                                    }
                                ]
                            },
                            {
                                name: 'ԳՈՐԾՈՒՂՄԱՆ ԵՎ ՇՐՋԱԳԱՅՈՒԹՅՈՒՆՆԵՐԻ ԾԱԽՍԵՐ',
                                type: 'folder',
                                children: [{
                                    name: 'Ներքին գործուղումներ', //dth
                                    type: 'folder',
                                    dth: '4221',
                                    children: [
                                        {
                                            name: 'Ապրանքներ',
                                            type: 'folder',
                                            size: '',
                                            children: [
                                                {
                                                    cpv: 1,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '3566',
                                                    quantity: '7',
                                                    saving: ''

                                                },
                                                {
                                                    cpv: 2,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '9000',
                                                    quantity: '2',
                                                    saving: ''

                                                },
                                                {
                                                    cpv: 2,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '5000',
                                                    quantity: '1',
                                                    saving: ''

                                                }

                                            ] //cpv's

                                        },
                                        {
                                            name: 'Աշխատանքներ',
                                            type: 'folder',
                                            size: '',
                                            children: [
                                                {
                                                    cpv: 5432,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '3566',
                                                    quantity: '7',
                                                    saving: ''

                                                },
                                                {
                                                    cpv: 53245,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '9000',
                                                    quantity: '2',
                                                    saving: ''

                                                },
                                                {
                                                    cpv: 4114444,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '5000',
                                                    quantity: '1',
                                                    saving: ''

                                                }
                                            ]
                                        },
                                        {
                                            name: 'Ծառայություններ',
                                            type: 'folder',

                                            size: '',
                                            children: []
                                        }
                                    ]
                                },
                                    {
                                        name: 'Արտասահմանյան գործուղումների գծով ծախսեր',
                                        type: 'folder',
                                        dth: '4222',
                                        children: [
                                            {
                                                name: 'Ապրանքներ',
                                                type: 'folder',

                                                size: '',
                                                children: [
                                                    {
                                                        cpv: 1,
                                                        buymethod: 'method',
                                                        unit: 'unit',
                                                        unitcost: 'unit cost',
                                                        totalcost: '3566',
                                                        quantity: '7',
                                                        saving: ''

                                                    },
                                                    {
                                                        cpv: 2,
                                                        buymethod: 'method',
                                                        unit: 'unit',
                                                        unitcost: 'unit cost',
                                                        totalcost: '9000',
                                                        quantity: '2',
                                                        saving: ''

                                                    },
                                                    {
                                                        cpv: 2,
                                                        buymethod: 'method',
                                                        unit: 'unit',
                                                        unitcost: 'unit cost',
                                                        totalcost: '5000',
                                                        quantity: '1',
                                                        saving: ''

                                                    }

                                                ]

                                            },
                                            {
                                                name: 'Աշխատանքներ',
                                                type: 'folder',
                                                size: '',
                                                children: [
                                                    {
                                                        cpv: 5432,
                                                        buymethod: 'method',
                                                        unit: 'unit',
                                                        unitcost: 'unit cost',
                                                        totalcost: '3566',
                                                        quantity: '7',
                                                        saving: ''

                                                    },
                                                    {
                                                        cpv: 53245,
                                                        buymethod: 'method',
                                                        unit: 'unit',
                                                        unitcost: 'unit cost',
                                                        totalcost: '9000',
                                                        quantity: '2',
                                                        saving: ''

                                                    },
                                                    {
                                                        cpv: 4114444,
                                                        buymethod: 'method',
                                                        unit: 'unit',
                                                        unitcost: 'unit cost',
                                                        totalcost: '5000',
                                                        quantity: '1',
                                                        saving: ''

                                                    }
                                                ]
                                            },
                                            {
                                                name: 'Ծառայություններ',
                                                type: 'folder',
                                                size: '',
                                                children: []
                                            }
                                        ]
                                    }
                                ]
                            }]
                    },
                    {
                        name: 'ԿԱՊԻՏԱԼ ԾԱԽՍԵՐ',
                        type: 'folder',

                        children: [{
                            name: 'ՇԵՆՔԵՐ ԵՎ ՇԻՆՈՒԹՅՈՒՆՆԵՐ',
                            type: 'folder',

                            children: [
                                {
                                    name: 'Շենքերի եւ շինությունների ձեռք բերում', //dth
                                    type: 'folder',

                                    dth: '5111',
                                    children: [
                                        {
                                            name: 'Ապրանքներ',
                                            type: 'folder',

                                            size: '',
                                            children: [
                                                {
                                                    cpv: 1,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '3566',
                                                    quantity: '7',
                                                    saving: ''

                                                },
                                                {
                                                    cpv: 2,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '9000',
                                                    quantity: '2',
                                                    saving: ''

                                                },
                                                {
                                                    cpv: 2,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '5000',
                                                    quantity: '1',
                                                    saving: ''

                                                }

                                            ] //cpv's

                                        },
                                        {
                                            name: 'Աշխատանքներ',
                                            type: 'folder',

                                            size: '',
                                            children: [
                                                {
                                                    cpv: 5432,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '3566',
                                                    quantity: '7',
                                                    saving: ''

                                                },
                                                {
                                                    cpv: 53245,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '9000',
                                                    quantity: '2',
                                                    saving: ''

                                                },
                                                {
                                                    cpv: 4114444,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '5000',
                                                    quantity: '1',
                                                    saving: ''

                                                }
                                            ]
                                        },
                                        {
                                            name: 'Ծառայություններ',
                                            type: 'folder',

                                            size: '',
                                            children: []
                                        }
                                    ]
                                },
                                {
                                    name: 'Շենքերի եւ շինությունների կառուցում',
                                    type: 'folder',

                                    dth: '5112',
                                    children: [
                                        {
                                            name: 'Ապրանքներ',
                                            type: 'folder',

                                            children: [
                                                {
                                                    cpv: 1,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '3566',
                                                    quantity: '7',
                                                    saving: ''

                                                },
                                                {
                                                    cpv: 2,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '9000',
                                                    quantity: '2',
                                                    saving: ''

                                                },
                                                {
                                                    cpv: 2,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '5000',
                                                    quantity: '1',
                                                    saving: ''

                                                }

                                            ]

                                        },
                                        {
                                            name: 'Աշխատանքներ',
                                            type: 'folder',

                                            children: [
                                                {
                                                    cpv: 5432,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '3566',
                                                    quantity: '7',
                                                    saving: ''

                                                },
                                                {
                                                    cpv: 53245,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '9000',
                                                    quantity: '2',
                                                    saving: ''

                                                },
                                                {
                                                    cpv: 4114444,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '5000',
                                                    quantity: '1',
                                                    saving: ''

                                                }
                                            ]
                                        },
                                        {
                                            name: 'Ծառայություններ',
                                            type: 'folder',

                                            children: []
                                        }
                                    ]
                                },
                            ]
                        }, {
                            name: 'ԱՅԼ ՀԻՄՆԱԿԱՆ ՄԻՋՈՑՆԵՐ',
                            type: 'folder',

                            children: [
                                {
                                    name: 'Աճեցվող ակտիվներ', //dth
                                    type: 'folder',

                                    dth: '5131',
                                    children: [
                                        {
                                            name: 'Ապրանքներ',
                                            type: 'folder',

                                            size: '',
                                            children: [
                                                {
                                                    cpv: 1,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '3566',
                                                    quantity: '7',
                                                    saving: ''

                                                },
                                                {
                                                    cpv: 2,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '9000',
                                                    quantity: '2',
                                                    saving: ''

                                                },
                                                {
                                                    cpv: 2,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '5000',
                                                    quantity: '1',
                                                    saving: ''

                                                }

                                            ] //cpv's

                                        },
                                        {
                                            name: 'Աշխատանքներ',
                                            type: 'folder',

                                            size: '',
                                            children: [
                                                {
                                                    cpv: 5432,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '3566',
                                                    quantity: '7',
                                                    saving: ''

                                                },
                                                {
                                                    cpv: 53245,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '9000',
                                                    quantity: '2',
                                                    saving: ''

                                                },
                                                {
                                                    cpv: 4114444,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '5000',
                                                    quantity: '1',
                                                    saving: ''

                                                }
                                            ]
                                        },
                                        {
                                            name: 'Ծառայություններ',
                                            type: 'folder',

                                            size: '',
                                            children: []
                                        }
                                    ]
                                },
                                {
                                    name: 'Նախագծահետազոտական ծախսեր',
                                    type: 'folder',

                                    dth: '5134',
                                    children: [
                                        {
                                            name: 'Ապրանքներ',
                                            type: 'folder',

                                            children: [
                                                {
                                                    cpv: 1,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '3566',
                                                    quantity: '7',
                                                    saving: ''

                                                },
                                                {
                                                    cpv: 2,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '9000',
                                                    quantity: '2',
                                                    saving: ''

                                                },
                                                {
                                                    cpv: 2,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '5000',
                                                    quantity: '1',
                                                    saving: ''

                                                }

                                            ]

                                        },
                                        {
                                            name: 'Աշխատանքներ',
                                            type: 'folder',

                                            children: [
                                                {
                                                    cpv: 5432,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '3566',
                                                    quantity: '7',
                                                    saving: ''

                                                },
                                                {
                                                    cpv: 53245,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '9000',
                                                    quantity: '2',
                                                    saving: ''

                                                },
                                                {
                                                    cpv: 4114444,
                                                    buymethod: 'method',
                                                    unit: 'unit',
                                                    unitcost: 'unit cost',
                                                    totalcost: '5000',
                                                    quantity: '1',
                                                    saving: ''

                                                }
                                            ]
                                        },
                                        {
                                            name: 'Ծառայություններ',
                                            type: 'folder',

                                            children: []
                                        }
                                    ]
                                },
                            ]
                        }
                        ]
                    }]
            }
        ];



    });
</script>--%>
