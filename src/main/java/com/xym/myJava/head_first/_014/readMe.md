## 原型模式(Prototype  Pattern)：使用原型实例指定创建对象的种类，并且通过拷贝这些原型创建新的对象。原型模式是一种对象创建型模式。

原型模式结构图：

![原型模式结构图](http://ww1.sinaimg.cn/mw690/6ad5a571ly1g2bogzfiktj20kh09n3z5.jpg)

## 原型管理器(Prototype Manager)是将多个原型对象存储在一个集合中供客户端使用，它是一个专门负责克隆对象的工厂，其中定义了一个集合用于存储原型对象，如果需要某个原型对象的一个克隆，可以通过复制集合中对应的原型对象来获得。

![原型管理器](http://ww1.sinaimg.cn/mw690/6ad5a571ly1g2bojl89glj20iy074dgb.jpg)