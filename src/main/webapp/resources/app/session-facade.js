var stompClient = null;

var SessionFacade = {

    root: '/presales/',

    http:  {},
    initialized: false,
    location: {},
    session: {},
    timeoutWarningDelay: 1000*60*(60*2 - 2), // 2 hours - 2 minutes
    timeoutDelay:  1000*60*2,// 2 minutes
    timeout: {},
    lastCall: -1,
    timer: null,
    errorHandler: {},



    login:
        function(email, pass, callback) {
            SessionFacade.post('api/user/login', {username:email,password:pass}, callback);
        },

    connect:
        function(callback) {
              //  var socket = new SockJS("http://localhost:8080/presales/project");
    			var socket = new SockJS(window.location.protocol+"//"+window.location.host+ SessionFacade.root+"project")
                stompClient = Stomp.over(socket);
                stompClient.connect({}, function(frame) {

                    stompClient.subscribe("/api/project/list", function(message) {
                        callback.success(message.body);
                    });
                  }, function(error) {
                    console.log("STOMP protocol error " + error);
                });
        },

    saveProject :
        function(project){
    	
    	
        var project = {
             	"projectName": project.projectName,
            	"projectId":project.projectId
              };
    	
    	
          stompClient.send("/api/update", {}, JSON.stringify(project));
          
/*          stompClient.subscribe("/topic/project/"+project.projectId, function(message) {
             console.log(JSON.parse(message.body));
            });*/
          
           //stompClient.send("/api/project/ggg", {}, {});*/
    	
/*        var trade = {
                "action" : "Sell",
                "ticker" : "CTX",
                "shares" : 13
              };
            console.log(trade);
            stompClient.send("/api/update", {}, JSON.stringify(trade));*/
        },

    get:
        function (url,callback) {
            return SessionFacade.getWithConfig(url,{headers:{'Accept': 'application/json;','If-Modified-Since': '0'}},callback);
        },


    post:
        function (url,obj,callback) {

            SessionFacade.http
                .post(SessionFacade.root+url,obj)
                .success(function (data, status, headers, config) {

                    if (status != 200) {
                        callback.success(data);
                    }

                    callback.success(data);
                })
                .error(
                function(err, status, headers, config){


                    callback.error(err);
                });
        }


};
