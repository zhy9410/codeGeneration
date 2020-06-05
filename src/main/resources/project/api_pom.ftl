<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">

    <parent>
        <artifactId>${projectName}</artifactId>
        <groupId>${depencePackage}</groupId>
        <version>0.0.1-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>${projectName}-api</artifactId>
    <packaging>jar</packaging>

    <dependencies>
        <dependency>
            <groupId>${depencePackage}</groupId>
            <artifactId>${projectName}-model</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>

        <dependency>
            <groupId>com.github.pagehelper</groupId>
            <artifactId>pagehelper</artifactId>
            <version>${pagehelper.versioin}</version>
        </dependency>

    </dependencies>
</project>
