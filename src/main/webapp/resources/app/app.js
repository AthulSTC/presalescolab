angular.module('app', ['ngRoute'])

    .run(
    function ( $http) {

        SessionFacade.http = $http;

    })

    .controller('MainController', function($scope, $route, $routeParams, $location) {
        $scope.$route = $route;
        $scope.$location = $location;
        $scope.$routeParams = $routeParams;
    })

    .controller('LoginController', function($scope, $routeParams,$location) {
        $scope.user={};
        $scope.error={};

        $scope.submit = function() {

            SessionFacade.login($scope.user.userID,$scope.user.password, {

                success: function (data) {
                    if (data !="") {
                        $location.url("/dashboard");
                    }
                    else {
                        $scope.error.text = "Invalid User ID and Password";
                    }
                },

                error: function(err) {
                    $scope.error.text="Invalid User ID and Password";
                }
            });

        }
    })

    .controller('DashboardController', function($scope, $routeParams) {

        $scope.projects =[];
        $scope.title = "Project Dashboard"
        SessionFacade.connect({

            success : function(data)
            {
                console.log(data);
                $scope.projects=JSON.parse(data);
                $scope.$apply();
            }


        });
    })


    .controller('ProjectController', function($scope){

        $scope.save = function(){
            SessionFacade.saveProject($scope.project);
            
            stompClient.subscribe("/topic/project/"+$scope.project.projectId, function(message) {
                console.log(JSON.parse(message.body));
                $scope.project=JSON.parse(message.body);
                $scope.$apply();
             });
        };
        


    })

    .config(['$routeProvider',
        function($routeProvider) {
            $routeProvider.
                when('/login', {
                    templateUrl: 'templates/login.html',
                    controller: 'LoginController'
                }).
                when('/dashboard', {
                    templateUrl: 'templates/dashboard.html',
                    controller: 'DashboardController'
                }).
                otherwise({
                    redirectTo: '/login'
                });
        }
    ]);



