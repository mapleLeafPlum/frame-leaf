var fs=require("fs");
//var data = fs.readFileSync('D:\\conf.txt');

fs.readFile('D:\\conf.txt', function (err, data) {
    if (err) return console.error(err);
    console.log(data.toString());
});

//console.log(data.toString());
console.log("程序执行结束!");


const  buf = Buffer.alloc(256);
var len = buf.write("www.runoob.com");

const json = JSON.stringify(buf);
console.log("写入字节数 : "+  json);
console.log("写入字节数 : "+  buf.toJSON());
console.log("写入字节数 : "+  len);