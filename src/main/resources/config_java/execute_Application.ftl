package ${packagePath};

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableFeignClients(basePackages={"cn.com.goldenwater.common.client.**","cn.com.goldenwater.cache.util.**"})
@RefreshScope
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages={"${packagePath}", "cn.com.goldenwater.cache", "cn.com.goldenwater.common"})
@MapperScan({"${packagePath}.dao", "cn.com.goldenwater.common.dao"})
public class ${excuteProject}ServiceApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(${excuteProject}ServiceApplication.class);
        //springApplication.addListeners(new BizRcBasicServiceApplicationListener());
        springApplication.setBannerMode(Banner.Mode.OFF);
        springApplication.run(args);
    }
}
