var fun1 = function (name) {
    print('这里是JavaScript的地盘，' + name);
    return "JavaScript返回";
};
var fun2 = function (object) {
    print("JS Class Definition: " + Object.prototype.toString.call(object));
};

var MyJavaClass = Java.type("com.xym.myJava.nashorn.NashornDemo");
var result = MyJavaClass.fun1("xym");
print(result);