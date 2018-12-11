## Java-Type体系
Type是Java 编程语言中所有类型的公共高级接口（官方解释），也就是Java中所有类型的“爹”；其中，“所有类型”的描述尤为值得关注。它并不是我们平常工作中经常使用的 int、String、List、Map等数据类型，而是从Java语言角度来说，对基本类型、引用类型向上的抽象；

Type体系中类型的包括：原始类型(`Class`)、参数化类型(`ParameterizedType`)、数组类型(`GenericArrayType`)、类型变量(`TypeVariable`)、基本类型(`Class`);

* 原始类型，不仅仅包含我们平常所指的类，还包括枚举、数组、注解等； 
- 参数化类型，就是我们平常所用到的泛型List、Map；
- 数组类型，并不是我们工作中所使用的数组String[] 、byte[]，而是带有泛型的数组，即T[] ；
- 基本类型，也就是我们所说的java的基本类型，即int,float,double等

### 1.ParameterizedType
参数化类型，即泛型；例如：List<T>、Map<K,V>等带有参数化的对象;

### 2.TypeVariable
类型变量，即泛型中的变量；例如：T、K、V等变量，可以表示任何类；在这需要强调的是，TypeVariable代表着泛型中的变量，而ParameterizedType则代表整个泛型；

### 3.GenericArrayType
泛型数组类型，用来描述ParameterizedType、TypeVariable类型的数组；即List<T>[] 、T[]等；

### 4.Class
上三者不同，Class是Type的一个实现类，属于原始类型，是Java反射的基础，对Java类的抽象；

### 5.WildcardType
泛型表达式（或者通配符表达式），即？ extend Number、？ super Integer这样的表达式；WildcardType虽然是Type的子接口，但却不是Java类型中的一种；

以上，简单介绍了Java-Type的体系；为了解决泛型，JDK1.5版本开始引入Type接口；在此之前，Java中只有原始类型，所有的原始类型都是通过Class进行抽象；有了Type以后，Java的数据类型得到了扩展，从原始类型扩展为参数化类型(ParameterizedType)、数组类型(GenericArrayType)、类型变量(TypeVariable);

作者：贾博岩
链接：https://www.jianshu.com/p/7649f86614d3
來源：简书
简书著作权归作者所有，任何形式的转载都请联系作者获得授权并注明出处。