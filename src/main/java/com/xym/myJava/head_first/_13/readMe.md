## 复合模式：结合2个或以上的模式，组成一个解决方案，解决一再发生的`一般性`问题

## 复合模式示意图I
![复合模式示意图I](http://ww1.sinaimg.cn/mw690/6ad5a571ly1g270xuzk8yj20oj0od403.jpg)


## 复合模式变体类图（其实不是真正的复合模式，只能算是多个模式组合使用）
![复合模式类图](http://ww1.sinaimg.cn/mw690/6ad5a571ly1g2712jvps5j20mm0mtq4i.jpg)

## M（Model）V（View）C（Controller）模式
真正的复合模式，地标最强，mvc模式结合了观察者模式、策略模式、和组合模式。
- 模型使用观察者模式，以便观察者更新，同时保持二者之间解耦
- 控制器是视图的策略，视图可以使用不同的控制器实现，得到不同的行为。
- 视图使用组合模式实现用户界面，用户界面通常组合了嵌套的组件，像面板、框架和按钮。