#service name
server.port=8101
spring.application.name=${projectName}-service

#service discovery url
eureka.client.serviceUrl.defaultZone=http://${center.name:admin}:${center.password:Jinshui520}@${center.host:center.goldenwater.com.cn}:${center.port:8761}/eureka/

#启用shutdown
endpoints.shutdown.enabled=true
#禁用密码验证
endpoints.shutdown.sensitive=false


#出现错误时, 直接抛出异常
spring.mvc.throw-exception-if-no-handler-found=true
#不要为我们工程中的资源文件建立映射
spring.resources.add-mappings=false

# mybatis
mybatis.type-aliases-package=${packagePath}.model
mybatis.mapper-locations=classpath*:mybatis/mappings/*.xml
mybatis.config-location=classpath:mybatis/mybatis-config.xml

#pagehelper
pagehelper.helperDialect=mysql
pagehelper.reasonable=true
pagehelper.supportMethodsArguments=false

#feign use okhttp
feign.httpclient.enabled=true
#feign.okhttp.enabled=true

##开启请求压缩功能
feign.compression.request.enabled=true
#开启响应压缩功能
feign.compression.response.enabled=true
#指定压缩请求数据类型
feign.compression.request.mime-types=text/xml;application/xml;application/json
#如果传输超过该字节，就对其进行压缩
feign.compression.request.min-request-size=2048

logging.level.cn.cn.com.goldenwater.common.client.*=DEBUG

##ribbon
ribbon.IsSecure=false
ribbon.ConnectTimeout=60000
ribbon.ReadTimeout=60000

feign.hystrix.enabled=true
hystrix.command.default.execution.timeout.enabled=false
hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds=60000

