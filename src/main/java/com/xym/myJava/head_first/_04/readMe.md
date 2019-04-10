## 工厂方法模式：所有工厂模式都是用来封装对象的创建。工厂方法模式通过让子类决定该创建的对象是什么，来达到
将对象创建的过程封装的目的。一般包含创建者类（一个抽象创建者类，和一系列具体创建者）和产品类（抽象产品类和一系列具体产品类）。


- `SimplePizzaFactory`为简单工厂类
- 抽象创建者`AbstractPizzaStore`,包含`createPizza`抽象工厂方法被具体子类实现
- 具体创建者，`ChicagoPizzaStore`,`NYPizzaStore`，实现了抽象创建者中的抽象方法，根据条件创建具体的产品

- 抽象产品类`Pizza`，包含了子类要实现的`getName`抽象方法。
- 具体产品类（`CheesePizza`、`ChicagoCheesePizza`）用来实现抽象产品类，通过具体创建者。

工厂方法模式示意图： 
![工厂方法模式](http://ww1.sinaimg.cn/large/005ReCYTly1g1xiamj5qyj30oh0dmdgv.jpg)

抽象工厂模式示意图：  
![抽象工厂模式](http://ww1.sinaimg.cn/large/005ReCYTly1g1xkhli25wj30m90jmq42.jpg)