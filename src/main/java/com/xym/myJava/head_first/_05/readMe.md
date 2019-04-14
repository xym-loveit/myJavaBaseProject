## 单例模式，免了吧

- 懒汉模式（多线程情况下容易出现多实例）
- 饿汉模式（未使用前提前实例化，白白浪费空间）
- 双重检查锁+`volitile`（稍微麻烦一些）
- 内部静态类模式（通过jvm保证实例化安全，比较完美）
- 枚举（最简单最完美的单例模式，多数人不熟，使用频率不高）