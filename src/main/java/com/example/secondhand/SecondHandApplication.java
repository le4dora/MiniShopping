package com.example.secondhand;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



/*
* 本项目采用的是MVC模型，其结构主要包括DAO（Mapper）、Service和Controller三层。
* Mapper（DAO）层：这是数据访问层，主要负责与数据库进行交互。具体来说，对某个表的增删改查等底层数据操作都在这一层实现。每个DAO通常与数据库的一张表对应，封装了针对该表的基本操作如增删改查等。为了保持结构清晰，建议DAO只做原子操作。在这一层，首先设计接口，然后在Spring配置文件中定义接口的实现类。这样，在模块中调用接口时，就不需要关心具体的实现类是哪个。

Service层：这是服务层，用于处理业务逻辑。Service层会调用Mapper层的接口来执行具体的数据库操作。此外，Service层还负责处理一些与业务相关的任务，例如校验、计算和转换等。Service层的结构也包括接口文件和接口实现类文件两部分，接口文件中定义了在Controller层中需要调用的方法；接口实现类文件中则完成这些方法的具体实现。

Controller层：这是控制器层，主要负责处理用户请求和响应。Controller层接收前端的请求，然后调用Service层的方法来处理业务逻辑。处理完成后，Controller层会返回一个视图给客户端，展示相应的数据。
*
*
* */
@SpringBootApplication
public class SecondHandApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecondHandApplication.class, args);
    }

}
