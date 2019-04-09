## 装饰者模式：动态地将责任附加到对象上，想要扩展功能，装饰者提供有别于继承是的另一种选择。
### 在JDK中`IO API`系列中是经典的装饰者模式的使用,装饰器基类为`FilterInputStream`,被装饰者为`InputStream`,装饰者具体类为各个
`FilterInputStream`的子类，如`BufferedInputStream`、`DataInputStream`等。

- 装饰者和被装饰者是同一种类型