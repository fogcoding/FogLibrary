## Retrofit

#### 使用示例







---

#### 注解的列表

Retrofit使用注解+java接口来定义后台服务API接口

注解主要分为 方法注解 和 参数注解

| 注解            | 类型     | 作用                                                         |
| --------------- | -------- | ------------------------------------------------------------ |
| @GET            | 方法注解 | 表明HTTP请求方法为GET,(可选)注解的value属性用来设置相对/绝对url |
| @POST           | 方法注解 | 表明HTTP请求方法为POST,(可选)注解的value属性用来设置相对/绝对url |
| @PUT            | 方法注解 | 表明HTTP请求方法为PUT,(可选)注解的value属性用来设置相对/绝对url |
| @DELETE         | 方法注解 | 表明http请求方法为DELETE,(可选)注解的value属性用来设置相对/绝对url |
| @PATCH          | 方法注解 | 表明HTTP请求方法为PATCH,(可选)注解的value属性用来设置相对/绝对url |
| @HEAD           | 方法注解 | 表明HTTP请求方法为HEAD,(可选)注解的value属性用来设置相对/绝对url |
| @OPTIONS        | 方法注解 | 表明HTTP请求方法为OPTIONS,(可选)注解的value属性用来设置相对/绝对url |
| @HTTP           | 方法注解 | 通过@HTTP注解指定http协议的请求方法,是否允许body,(可选)注解的value属性用来设置相对/绝对url |
| @FormUrlEncoded | 方法注解 | 表明发起HTTP请求的RequestBody是form表单方式                  |
| @Multipart      | 方法注解 | 表明发起HTTP请求的RequestBody是Multipar方式                  |
| @Headers        | 方法注解 | 使用注解的value值数组作为HTTP请求的头，用于一些固定的Header参数 |
| @Streaming      | 方法注解 | 用于需要直接返回流的函数                                     |
| @Url            | 参数注解 | HTTP请求的url路径(相对/绝对),可以包含{path_holder},如:[http://xxx.com/](https://link.jianshu.com/?t=http://xxx.com/){user_holder}/detail |
| @Path           | 参数注解 | 用于动态替换URL路径中的path_holder                           |
| @Body           | 参数注解 | 表明此参数用作HTTP请求的body                                 |
| @Field          | 参数注解 | 表明此参数用作HTTP请求的form表单参数，key为注解的value值     |
| @FieldMap       | 参数注解 | 以map形式传入的form表单参数                                  |
| @Header         | 参数注解 | 表明此参数用作HTTP请求的header，key为注解的value值           |
| @HeaderMap      | 参数注解 | 以map形式传入的多个header键值对                              |
| @Part           | 参数注解 | 表明参数为Http的multipart参数之一                            |
| @PartMap        | 参数注解 | 以map形式传入的multipart参数表                               |
| @Query          | 参数注解 | GET方法的query参数，用于拼接完整请求路径                     |
| @QueryMap       | 参数注解 | 以map传入的GET方法的query参数，用于拼接完整请求路径          |



#### 相关问题

##### 动态代理是如何在Retrofit里面运作的？



##### Retrofit定义的接口并没有实现，又为什么可以直接使用，哪里实现了具体的运行逻辑？



##### Retrofit定义接口的注解是哪里进行加载的，又是如何进行装配的？



##### 要从头至尾，分解一遍Retrofit的执行过程，并且分析一下设计架构和思想



