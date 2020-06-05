spring.cloud.config.enabled=true
spring.cloud.config.uri=http://${config.name:admin}:${config.password:Jinshui520}@${config.host:config.goldenwater.com.cn}:${config.port:8888}
spring.cloud.config.name=${projectName}-service
spring.cloud.config.profile=${config.profile:dev}
