<#--
 ==========================LICENSE_START=================================
 PolyGenesis Platform
 ========================================================================
 Copyright (C) 2015 - 2019 OREGOR LTD
 ========================================================================
 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 
      http://www.apache.org/licenses/LICENSE-2.0
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 ===========================LICENSE_END==================================
-->
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xmlns="http://maven.apache.org/POM/4.0.0"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <parent>
    <groupId>${ projectDescription.groupId }</groupId>
    <artifactId>${ projectDescription.modulePrefix }-secondary-adapters</artifactId>
    <version>${ projectDescription.version }</version>
  </parent>
  <modelVersion>4.0.0</modelVersion>

  <groupId>${ projectDescription.groupId }.${ projectDescription.modulePrefix }-secondary-adapters</groupId>
  <artifactId>${ projectDescription.modulePrefix }-persistence-rdbms</artifactId>

  <dependencies>
    <!--SPRING BOOT JPA-->
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>

    <!--QUERY DSL-->
    <dependency>
      <groupId>com.querydsl</groupId>
      <artifactId>querydsl-apt</artifactId>
      <scope>provided</scope>
    </dependency>
    <dependency>
      <groupId>com.querydsl</groupId>
      <artifactId>querydsl-jpa</artifactId>
    </dependency>

    <!--DOMAIN MODEL-->
    <dependency>
      <groupId>${ projectDescription.groupId }</groupId>
      <artifactId>${ projectDescription.modulePrefix }-domain-model</artifactId>
    </dependency>

    <!--OREGOR-DDD4J-->
    <dependency>
      <groupId>com.oregor.ddd4j</groupId>
      <artifactId>ddd4j-core-spring-data-jpa</artifactId>
    </dependency>

    <!--TEST-->
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-test</artifactId>
      <scope>test</scope>
    </dependency>
    <dependency>
      <groupId>com.h2database</groupId>
      <artifactId>h2</artifactId>
      <scope>test</scope>
    </dependency>
  </dependencies>
</project>
