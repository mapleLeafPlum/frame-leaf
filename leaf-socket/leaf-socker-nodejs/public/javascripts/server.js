var http=require("http");
http.createServer(function (request,response) {
    response.writeHeader(200,{'Content-Type': 'text/plain'});
    response.end("successful \n")
}).listen(8888);

console.info("ok ok");