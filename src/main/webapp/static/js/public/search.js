PPCM.
    config(function ($routeProvider) {
    $routeProvider
        .when('/search-plan', {
            templateUrl: 'search-plan-view',
            controller: 'searchPlanController'
        })
        .when('/search-contract', {
            templateUrl: 'search-contract-view',
            controller: 'searchContractController'

        })
        .otherwise({
            templateUrl: 'search-contract-view',
            controller: 'searchContractController'
        });

})
    .controller("searchContractController", function ($scope) {

        $scope.date = {startDate: null, endDate: null};


        $scope.authorities = ['ՀՀ նախագահի աշխատակազմ',
            'ՀՀ արտաքին գործերի նախարարություն',
            'ՀՀ տարածքային կառավարման նախարարության միգրացիոն պետական ծառայություն',
            'ՀՀ քննչական կոմիտեի դեպարտամենտ'];

        $scope.cpvlist = [{name:'cpv 01', code:'09211544'},{name:'cpv 02', code:'09228544'},{name:'cpv 03', code:'02421544'},
            {name:'cpv 06', code:'09222544'},{name:'cpv 05', code:'09232544'},{name:'cpv 04', code:'09212444'}]

        $scope.data = [{
            authority: 'Authority 01',
            contractname: '',
            supplier: '',
            manager: '',
            children: [
                {
                    contractname: 'contract 01',
                    supplier: 'supplier 01',
                    manager: 'manager 01'
                },
                {
                    contractname: 'contract 01',
                    supplier: 'supplier 02',
                    manager: 'manager 02'
                }]
        },
            {
                authority: 'Authority 02',
                type: 'folder',
                children: [
                    {
                        contractname: 'contract 01',
                        supplier: 'supplier1',
                        manager: 'manager01'
                    },
                    {
                        contractname: 'contract 01',
                        supplier: 'supplier 02',
                        manager: 'manager 02'
                    }
                ]
            }];


        $scope.col_defs =[{
            field:"authority",
            displayName:"Մարմին"
        },{
            field:"contractname",
            displayName:"Պայմանագրի Անուն"
        },{
            field:"supplier",
            displayName:"մատակարար"
        }, {
                field:"manager",
                displayName:"ղեկավար"
            }]


    }).controller("searchPlanController", function ($scope) {
        $scope.authorities = ['ՀՀ նախագահի աշխատակազմ',
            'ՀՀ արտաքին գործերի նախարարություն',
            'ՀՀ տարածքային կառավարման նախարարության միգրացիոն պետական ծառայություն',
            'ՀՀ քննչական կոմիտեի դեպարտամենտ'];

        $scope.cpvlist = [{name:'cpv 01', code:'09211544'},{name:'cpv 02', code:'09228544'},{name:'cpv 03', code:'02421544'},
            {name:'cpv 06', code:'09222544'},{name:'cpv 05', code:'09232544'},{name:'cpv 04', code:'09212444'}]

        $scope.data = [{
            name: 'Ծրաքիր 01',
            cpv: '',
            buymethod: '',
            unit: '',
            unitcost: '',
            totalcost: '9981',
            quantity: '',
            saving: '',
            children: [
                {
                    name:"papers",
                    cpv: 48712554,
                    buymethod: 'method',
                    unit: 'unit',
                    unitcost: 'unit cost',
                    totalcost: '6415',
                    quantity: '7',
                    saving: ''

                }, {
                    name:"Boxes",
                    cpv: 40112554,
                    buymethod: 'method',
                    unit: 'unit',
                    unitcost: 'unit cost',
                    totalcost: '3566',
                    quantity: '1',
                    saving: ''

                },
            ]
        },
            {
                name: 'Ծրաքիր 02',
                totalcost: '115564',
                children: [
                    {
                        name:"tables",
                        cpv: 48323554,
                        buymethod: 'method',
                        unit: 'unit',
                        unitcost: 'unit cost',
                        totalcost: '96685',
                        quantity: '7',
                        saving: ''

                    }, {
                        name:"oil",
                        cpv: 5432554,
                        buymethod: 'method',
                        unit: 'unit',
                        unitcost: 'unit cost',
                        totalcost: '18879',
                        quantity: '1',
                        saving: ''

                    },
                ]
            }];


        $scope.col_defs = [
            {
                field:"name",
                displayName:"Ծրաքիր"
            },
            {
                field: "cpv",
                displayName: "ԳՄԱ"
            },
            { field: "buymethod",
              displayName: "Գնման ձեւ(ընթացակարգը)",
                sortable : true,
                filterable : true
            },
            { field: "unit",
                displayName: "Չափման միավոր",
                sortable : true,
                filterable : true
            },
            { field: "unitcost",
                displayName: "Միավորի գինը",
                sortable : true,
                filterable : true
            },
            { field: "totalcost",
                displayName: "Ընդամենը ծախսերը (դրամ)",
                sortable : true,
                filterable : true
            },
            { field: "quantity",
                displayName: "Քանակը",
                sortable : true,
                filterable : true
            },
            { field: "saving",
                displayName: "տնտեսում",
                sortable : true,
                filterable : true
            }
        ];

    })

