BeanDefinition， spring中定义bean的配置元信息接口。
1. 类名
2. 行为配置元素，如作用域、生命周期回调、自动绑定的模式。
3. 配置设置。
4. 其他bean的引用或依赖。

[WS] 感觉类似Class类。

bean的元信息
class
name
scope
constructor arguments
properties
autowiring mode   自动绑定模式。 byName/byType
lazy initialization mode
initialization mode
destruction method


如何构建：
    方式1. BeanDefinitionBuilder   ps. 一般通过xml的方式进行构建。
    方式2. AbstractBeanDefinition


bean实例化：
    ServiceLoader： https://www.jianshu.com/p/7601ba434ff4
    
bean初始化：
    初始化方式：（注意以下三种方式的顺序）
        1. @PostConstruct
        2. 实现InitializingBean的afterPropertiesSet方法。
        3. 自定义initMethod
    
    延迟初始化：
        延迟的意思是说bean在容器启动后才进行初始化。
        
bean销毁
    销毁方式：（跟初始化方式相对应）
        1. @PreDestroy
        2. 实现DisposableBean的destroy方法。 
        3. 自定义destroy方法   
    
    







