<%--
  Created by IntelliJ IDEA.
  User: nothing
  Date: 2018/4/14
  Time: 14:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        td{
            border: 1px solid red;
        }
    </style>
</head>
   <table style="border: 1px solid red;">
       <tr>
           <td><div id="content" style="width:300px; height: 400px;"></div></td>
           <td><div id="users" style="width: 300px; height: 400px;"></div></td>
       </tr>
       <tr>
           <td colspan="2"><div>
               <input type="text" id="messageText" />
               <input type="button" onclick="message()" value="sentMessage" />
               <input type="button" onclick="close()" value="close" />
               <input type="button" onclick="connect()" value="login" />
               <input type="text" id="user" />
           </div></td>
       </tr>
   </table>
<body>


</body>
<script type="application/javascript" src="js/jquery-1.4.1.min.js"></script>
<script type="application/javascript">
    var ws=null;
    function connect() {
        var target = "ws://localhost:8080/echo?name=";
        if (target == '') {
            alert('Please select server side connection implementation.');
            return;
        }
        target+=$("#user").val();
        if ('WebSocket' in window) {
            ws = new WebSocket(target);
        } else if ('MozWebSocket' in window) {
            ws = new MozWebSocket(target);
        } else {
            alert('WebSocket is not supported by this browser.');
            return;
        }
        ws.onopen = function () {
           // setConnected(true);
           // alert("onopen");
            //log('Info: WebSocket connection opened.');
        };
        ws.onmessage = function (event) {
           // alert("onmessage:"+event.data);
            var info=event.data;
          //  alert(info);
            eval("var data="+info+";");
          //  alert(data);
            $("#content").append(data.message+"</br>")
            $("#messageText").val("");
            var userList=""
            $(data.userList).each(function (i,ele) {
                //alert(i+","+ele);
                userList+=ele+"</br>"
            })
            $("#users").html(userList);

            //log('Received: ' + event.data);
        };
        ws.onclose = function (event) {
           // alert("onclose");
            //setConnected(false);
            //log('Info: WebSocket connection closed, Code: ' + event.code + (event.reason == "" ? "" : ", Reason: " + event.reason));
        };
    }

    function close() {
        if(ws!=null){
            ws.close();
            ws=null;
        }
    }

    function message() {
        if(ws!=null){
          ws.send($("#messageText").val());
        }
    }
</script>
</html>
