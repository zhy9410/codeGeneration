<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">

    <parent>
        <artifactId>${parentProject}</artifactId>
        <groupId>${depencePackage}</groupId>
        <version>0.0.1-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>
    <packaging>pom</packaging>
    <artifactId>${projectName}</artifactId>
    <!--引入子模块结构-->
    <modules>
        <module>${projectName}-api</module>
        <module>${projectName}-dao</module>
        <module>${projectName}-model</module>
        <module>${projectName}-service</module>
        <module>${projectName}-client</module>
    </modules>
</project>