# Output pattern : date [thread] priority category - message   FATAL 0  ERROR 3  WARN 4  INFO 6  DEBUG 7 
log4j.rootLogger=WARN, Console, RollingFile

#Console
log4j.appender.Console=org.apache.log4j.ConsoleAppender
log4j.appender.Console.layout=org.apache.log4j.PatternLayout
log4j.appender.Console.layout.ConversionPattern=%d %-5p [%c{5}] - %m%n

#RollingFile
log4j.appender.RollingFile=org.apache.log4j.DailyRollingFileAppender
log4j.appender.RollingFile.DatePattern='.'yyyy-MM-dd'.log'
log4j.appender.RollingFile.File=../logs/${projectName}-service.log
log4j.appender.RollingFile.layout=org.apache.log4j.PatternLayout
log4j.appender.RollingFile.layout.ConversionPattern=%d [%t] %-5p [%c] - %m%n

#log4j.logger.java.sql=DEBUG

#Springframework level
#log4j.logger.org.springframework=ERROR

#Hibernate level
#log4j.logger.org.hibernate=ERROR
#log4j.logger.org.hibernate.cache.ehcache.AbstractEhcacheRegionFactory=ERROR
#log4j.logger.org.hibernate.search.impl.ConfigContext=ERROR
#log4j.logger.net.sf.ehcache.config.CacheConfiguration=ERROR

#Project defalult level
#log4j.logger.org.activiti.engine.impl.persistence=DEBUG
#log4j.logger.org.apache.shiro=DEBUG
log4j.logger.cn.com.goldenwater=DEBUG
log4j.logger.cn.com.goldenwater.common.security.shiro=WARN
log4j.logger.cn.com.goldenwater.common.utils.JedisUtils=WARN
log4j.logger.cn.com.goldenwater.sys.controller.LoginController=WARN
log4j.logger.cn.com.goldenwater.oa.dao.OaNotifyDao.findCount=WARN
