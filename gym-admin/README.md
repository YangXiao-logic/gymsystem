


### 组织结构

``` lua
com.gym
├── common -- 工具类及通用代码
├── component -- springsecurity component bean
├── config -- swagger config, spring security config and mybatis config
├── controller -- controller 函数
├── dao -- Data access object
├── dto -- Data transfer object
├── mbg -- MyBatisGenerator生成的model和mapper
├── service -- service 函数

resources
├── com.gym.mbg.mapper -- mbg 生成的 mapper xml 文件
├── mapper 自定义 mapper 文件
├── appication.yml 项目配置文件
├── generator.properties -- MyBatisGenerator property file
├── generatorConfig.xml -- MyBatisGenerator config file
```



#### 后端技术

| 技术                | 说明                | 官网                                           |
| ------------------- | ------------------- | ---------------------------------------------- |
| SpringBoot          | 容器+MVC框架        | https://spring.io/projects/spring-boot         |
| SpringSecurity      | 认证和授权框架      | https://spring.io/projects/spring-security     |
| MyBatis             | ORM框架             | http://www.mybatis.org/mybatis-3/zh/index.html |
| MyBatisGenerator    | 数据层代码生成      | http://www.mybatis.org/generator/index.html    |
| Redis               | 分布式缓存          | https://redis.io/                              |
| Druid               | 数据库连接池        | https://github.com/alibaba/druid               |
| JWT                 | JWT登录支持         | https://github.com/jwtk/jjwt                   |
| Lombok              | 简化对象封装工具    | https://github.com/rzwitserloot/lombok         |
| Hutool              | Java工具类库        | https://github.com/looly/hutool                |
| PageHelper          | MyBatis物理分页插件 | http://git.oschina.net/free/Mybatis_PageHelper |
| Swagger-UI          | 文档生成工具        | https://github.com/swagger-api/swagger-ui      |



### 开发工具

| 工具          | 说明                | 官网                                            |
| ------------- | ------------------- | ----------------------------------------------- |
| IDEA          | 开发IDE             | https://www.jetbrains.com/idea/download         |
| Navicat       | 数据库连接工具      | http://www.formysql.com/xiazai.html             |
| Postman       | API接口调试工具      | https://www.postman.com/                        |