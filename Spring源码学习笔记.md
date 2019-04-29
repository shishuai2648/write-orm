穷举法：将生活中所见所闻全部归纳到我们所学的知识体系中来，加以思考总结，变成自己的东西。

类比法：用自己的熟悉的方法(利用自己已有的知识体系)，去对比学习新的知识。

学习最好的方法，就是重复

# Spring怎么学习？

1、如何从0到1去学习？

1. 官方文档
2. 网上找资料参考(有一个基本的概念，产生很多问题，很多猜想)
3. 看项目结构(更加具体的了解，还是会产生很多猜想)
4. 为什么会存在Spring这种框架
   1. 做了什么
   2. 能做什么
   3. 如果要我来做，我应该怎么来做。

IOC、AOP、DI、BeanFactory等一系列零碎的概念。

Spring能做什么？

能做什么，spring就是java中的万能胶

做了什么，将Java中的Bean能够实现无缝对接。

要我来做，我该怎么做：作为一个框架而言，在框架编写代码的时候，所需要对接的Bean是不存在的。要等到用户使用框架的时候，Bean才能被读取到。

通过预设规则，按照一定的规则去顺序加载或识别所需要对接的Bean(反射，通过字符串类全名，字符串可以找到并创建一个Bean的实例)。

规定配置文件的默认名字。

IOC：		缓存的地方，需要将类对象存起来(Map容器)

DI：		getter/setter、constructor(反射、invoker)

AOP：		为了增强原始Bean的功能(代理技术)

# 没有Spring将如何开发

1. 人是懒惰的，一切高端技术的发明都是源于人类懒惰，何必重复造轮子。
2. 没有Spring，到Spring出现以前的开发？
   1. 很多设计模式需要自己去整合，当然你也可以选择不用设计模式(只不过多走点弯路而已)
   2. 没有spring，所有的封装、继承、多态、增强、依赖、模块管理等等工作都需要自己制定统一的规则(百花齐放、百家争鸣，规则不同，整合困难)
   3. 所有的创建对象、配置依赖关系、资源的重复利用、扩展性都需要手动操作

Spring出现以后，Java程序员的春天真的来了，解放了。

Spring中的Bean是否线程安全，Spring只是对象实例管理的容器，并不干涉线程安全。

# Spring架构

![1551618140229](images\1551618140229.png)

IOC、DI

IOC将控制权反转，将创建对象的权利交给Spring进行管理。

DI，维护对象之间管理。

# Spring核心容器体系结构

![1551619616921](images\1551619616921.png)

![1551619729421](images\1551619729421.png)

Spring中的BeanFactory的基本工作流程：定位、加载、注册。

为什么会有BeanDefinition？spring中所有的Bean都是通过BeanDefinition来进行描述的。是装饰器模式，是对原有类的封装。在扩展之后保证OOP原则。原来的类是什么就是什么，但是使用了装饰器模式，包装原有的东西，可以对类功能进行增强。



BeanDefinitionReader：主要是用来读取XML、properties文件，负责这些文件的读取，解析，定位，将内容转换成BeanDefinition，主要是用来进行资源定位。

IOC包括：BeanDefinition和Resource(xml、propertiose的资源整合)



Context 入口

Spring中：

定位：Reader 结尾

加载：BeanDefinition(加载与保存类信息，OOP关系)

注册：Factory、Context	就是把用户所定义的Bean放到IOC容器中(实际上就是个Map)



ClassPathXmlApplicationContext	通过main方法启动

DispatchServlet

FileSystem

Plugin

Lisenter



围绕Bean来展开BeanFactory；

# 以ClassPathXmlApplicationContext为例阐述整个流程信息。

## 初始阶段

容器启动时，会调用`ClassPathXmlApplicationContext`的构造方法，会调用其`AbstractApplicationContext`的`refresh()`方法，`refresh()`方法，会调其自身的`obtainFreshBeanFactory()`方法,该方法会执行已创建对象的`refreshBeanFactory()`方法,也就是这里的`ClassPathXmlApplicationContext`对象方法。`(注：该方法是抽象方法，根据类型不同，进行不同的类工厂刷新机制，其进行销毁主要是将其赋值为null，交由类加载器去进行数据销毁。)`。在`AbstractRefreshableApplicationContext`的`refreshBeanFactory()`中会调用`createBeanFactory()`方法，创建默认容器。

## 定位阶段

当默认容器创建完成之后，在该方法中调用`loadBeanDefinitions()`方法，由不同的容器的`BeanDefinitionReader`进行不同的加载，`loadBeanDefinitions()`中有同名类加载器，进行资源的加载。`XmlBeanDefinitionReader`对象会调用其自身的`loadBeanDefinitions`方法，之后会使用`XmlBeanDefinitionReader`的真正执行器`doLoadBeanDefinitions`正式进入xml文件的解析。

## 加载阶段

调用`doLoadDocument`将真正的解析过程交由`DocumentLoader`进行，将对应的xml文件解析成对应的`Document`类对象。之后调用`registerBeanDefinitions()`方法，进而调用`doRegisterBeanDefinitions()`方法，使用`BeanDefinitionDocumentReader`的子类`DefaultBeanDefinitionDocumentReader`的`registerBeanDefinitions()`与`doRegisterBeanDefinitions` 之后调用其自身的`parseBeanDefinitions()`的执行`parseDefaultElement()`方法根据标签的不同进行不同规则的解析，主要分为`导入元素，别名元素、普通Bean元素` ，这里主要说的是`普通Bean元素`，主要的解析方法是`processBeanDefinition()`方法，该方法会调`BeanDefinitionParserDelegate`的 `parseBeanDefinitionElement()`方法，使用委派模式，将对应的节点`Element`转换成 `BeanDefinitionHolder`对象，之后调用`BeanDefinitionReaderUtils`的`registerBeanDefinition()`方法进入注册环节。

## 注册阶段

在`DefaultListableBeanFactory`类中的`registerBeanDefinition()`方法，会将已经加载完成的`BeanDefinition`，放入到beanDefinitinonMap中。完成整个注册功能。

## 初始化结构图

![1552204864816](images\1552204864816.png)



初始化完成后，我们的IOC容器中存储的是BeanDefinition对象，并不是实际可用对象，而是一个相对而言的配置文件信息，如果需要可调用对象，是通过getBean或者是注解的Autowire进行对象注入的时候新建对象而产生的。所以我们所谓的IOC容器，并没有想象中的那么简单。



关于资源的加载是在初始化时进行注册的`configResources`,即初始化行数据传入的。

进行容器的创建，若是已经存在，则会先将之前的容器删除。但同时会保留容器中的序列化ID赋值给新的容器。

这两步实际上并不是特别复杂，只是因为涉及到方法的抽取，会不断调用父类方法，同时调用自身或者父级类方法，所以显得特别绕，这里主要是方法的抽取所影响。

容器初始化异常：

加载配置文件出错的时候，解析类出错的时候，初始化抛出不可预知异常的时候。

会导致容器初始化异常。

定位：资源配置、import、classpath。

加载：解析配置文件，将xml或者其他类型的Bean配置，包装成BeanDefinition对象。

注册：将已经初始化的BeanDefinition对象放入到IOC容器当中。

Spring中的对象，默认是单例的，scope single

Spring中对象init-lazy默认是false。

BeanDefinition相当于是保存在内存中的配置文件，脑村了所有的跟类属性相关的信息。依赖注入，就是把BeanDefinition中的信息读出来，利用反射机制或者代理机制创建对象。

这个新创建的对象不会放到我们印象中的IOC容器中，它会存入到另外一个cache容器中。



Warpper是对原生对象的包装，Spring通过构造方法存储原始对象，然后真正放入cache容器的只是Warpper。使用的包装器模式。之所以这样的是为了减少代码侵入。能够在原生的基础上，再进行扩展。如扩展代码，监听器，回调，等等。

# SpringMvc源码解析

Model：将传输数据封装成一个完整的载体

View：视图，用来展示或者数据的模块。(HTML、JSP、JSON数据、String、Swing.....)

Controller：控制交互的一个中间组件，由他来根据用户请求分发不同的任务从而得到不同的结果。

MVC：基于项目开发的设计模式，用来解决用户和后台交互的问题。



J2EE标准，JSP页面是一个万能的组件，可以写HTML、可以写JS、写Java逻辑、写SQL语句。

MVC框架孕育而生：Struts1、Struts2、Webwork、SpringMVC。

SpringMVC：只是MVC设计模式的应用典范，给MVC的实现制定了一套标准。

M：支持将url参数、POST参数自动封装成一个Object或者Map。

V：自己只有默认的Template、支持扩展、自定义View、而且能够自定义解析器。

C：做到把限制放宽了，任何一个类都有可能是一个Controller。

## SpringMvc

![1552204864816](images\springmvc.jpg)

# Spring事务

1、数据库操作都会通过事务来管理

ACID，最大的问题就是一致性。

分布式：分布式事务，处理瞬时一致性做不到了，只能保证最终的一致性(异步核对，主流的方式就是通过日志)。

事务只是一种思想，该如何用技术实现呢

事务的操作流程：

1、开启事务

2、准备好业务数据

![1555160762795](images\1555160762795.png)

	# ORM

ORM(对象关系映射) Object Relation Mapping，说的是将已经持久化的数据内容转换为一个Java对象，用Java对象描述对象与对象之间的关系和数据内容

Hibernate Mybatis JPA SpringJDBC

Hibernate	全自动档			不需要写一句SQL语句，但是性能下降

Mybatis 		半自动挡			支持单表映射，多变关联需要配置，轻量级

SpringJDBC	手动挡			包括SQL语句，映射都是自己实现的

## 为什么要写ORM框架

1、解决实际业务问题(根据业务需要)

2、自定义需求，如果要直接第三方开源框架的话，需要进行二次开发

3、解决团队成员之间水品参差不齐的问题

4、可以实现统一的管理、监控、排错等等一系列的底层操作



// 大数据监测系统

1、数据吞吐量大

2、数据存储方式多样化

3、数据源需要频繁切换

4、API无法统一



最底层的类：

1、统一方法名

1. select*
2. insert*
3. delete*
4. update*

// 约定：如果是删改，以ID作为唯一检索条件，如果没有ID，那么要先查出ID，再操作

2、统一参数			

1. 如果做添加查询	QueryRule(自己封装)
2. 批量更新和插入，方法名以All结尾，参数为List
3. 如果增删改，参数使用T

	、统一返回结果		

	. 所有的分页操作返回	Page对象
	. 所有的集合查询返回	List
	. 返回的单条查询返回	T
4. 所有的ID采用Long类型
5. 所有的删除、修改、增加操作返回boolean

对外输出都用ResultMsg



ORM将数据表中的记录要复制到Java中的Object中。

反射机制

自动赋值 



java extends		Java扩展

core			核心

common		公共的



javax.core.common.utils		操作工具包

javax.core.common.config		统一配置

javax.core.common.doc		文档生成

javax.core.common.jdbc		JDBC依赖

javax.core.common.redis		Redis



约定：

只要是Spring相关的配置都以application-开头，建议不要把所有的东西写在一个文件中，这样不便于团队开发的维护。

aop			配置代理规则

beans		配置单例对象

common	通用配置

context		主入口

db			数据库相关

web		页面相关(拦截器、过滤器、监听器、模板)





初衷：单表查询，不写一句SQL，自动生成，查询结果自动转换成Java对象。

基于Spring5实现ORM

1、我要实现一个复杂的查询，怎么传》

想到了用对象来传，但是有问题

1. 做不到跨表联查的条件
2. 无法携带判断逻辑的运算符
3. or 或者 and 无法区分

1、跨表联查的



问：selectbysql就相当于没有使用框架，不符合之前统一参数，统一查询方法。

答：不推荐使用，极少使用多表关联查询

问：这个PK主键传过来怎么用？有什么好处？

答：PK传与不传不影响功能，唯一的功能在于：返回结构不需要再手动的强制类型转换





如果这个框架组装SQL，复杂查询的话，会不会难以组装，比如查询某个表的字段是另外一个表的条件，以此类推多个表的话。

连接操作，如果表没有主键PK，还需要传？