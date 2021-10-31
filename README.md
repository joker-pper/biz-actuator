## biz-actuator

### 背景介绍

+ 尝试能够解决在已有复杂业务逻辑的实现下进行代码优化

+ 尝试解决在复杂业务编码时能够在一定程度上减少内部实现方法形参过多和过多临时参数所导致后期难以维护的问题

+ 能按照一定规范进行统一某类相对复杂业务的逻辑处理,使用简单且易上手

+ 能按照实现业务所分解的核心步骤来进行相应方法的显示调用

+ 能提供通用能力且灵活性高,能支持复杂场景的自定义处理(例:解析器可注册为spring bean不影响所依赖框架的使用)

+ 尝试方案记录加备份(重点->备份)...

### 使用介绍

原理: 通过业务参数和业务上下文对象在该业务的解析器中进行拆解流程实现对应的业务功能

调用方式: 选择业务执行器,并通过传入业务参数,业务上下文对象以及指定该业务的解析器获取到实例后进行执行并获取返回结果

```
    //方式一: 不指定过程的调用(全部实现逻辑内部进行把控)
    String result = BizActuator.of(bizParameter, bizContext, bizResolver).execute();
    
    //方式二: 指定过程(即所分解步骤)显示的执行调用(注: appendConsumer只添加非重写的自定义顺序方法)
    String result = BizRichActuator.of(bizParameter, bizContext, bizResolver)
                    .appendConsumer(bizResolver::first)
                    .appendConsumer(bizResolver::second)
                    .appendConsumer(bizResolver::last)
                    .execute();
```

### 简单示例

- [BizActuatorExample](src/test/java/com/joker17/bizactuator/example/BizActuatorExample.java)

- [BizRichActuatorExample](src/test/java/com/joker17/bizactuator/example/BizRichActuatorExample.java)

### 小生浅谈

+ 简单的事情容易复杂化,复杂的事情不容易简单化.

+ 追求简单,但事情往往没有那么简单.

+ 面对复杂的业务场景和产品阴晴不定的需求,能快速实现功能的方式后期却不一定好维护,一定要给自己留好后路.

+ 每一行代码都有着它独特的意义,也蕴含着他人的人生时光.

+ 别只顾着低头匆忙赶路,也要抬起头看看天空.

