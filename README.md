# truefitgoogle
True fit google test automation suite

Requirements:
-------------
Main read me doc:
-----------------
https://docs.google.com/document/d/1lztO2KR-tocgfxmlhKJbZlGm60lTKDj1x73fOAETq8M/edit

Download the following items:
==============================
- selenium-server-standalone-3.14.0.jar
From -> https://selenium-release.storage.googleapis.com/3.14/selenium-server-standalone-3.14.0.jar, 
https://www.seleniumhq.org/download/

- chromedriver_mac64.zip 
From -> https://chromedriver.storage.googleapis.com/2.42/chromedriver_mac64.zip, 
https://chromedriver.storage.googleapis.com/index.html?path=2.42/

- geckodriver-v0.22.0-macos.tar.gz
From -> https://github.com/mozilla/geckodriver/releases

- selenium-java-3.14.0.zip
From -> https://selenium-release.storage.googleapis.com/3.14/selenium-java-3.14.0.zip, 
https://www.seleniumhq.org/download/


IDE used:
=========
* IntelliJ 2017
* Maven setup
* TestNG 6.8

POM file:
=========
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>TrueFitGoogle</groupId>
    <artifactId>truefitgoogle</artifactId>
    <version>1.0-SNAPSHOT</version>

    <dependencies>

        <dependency>
            <groupId>org.seleniumhq.selenium</groupId>
            <artifactId>selenium-java</artifactId>
            <version>3.9.1</version>
        </dependency>

        <dependency>
            <groupId>org.seleniumhq.selenium</groupId>
            <artifactId>selenium-firefox-driver</artifactId>
            <version>3.9.1</version>
        </dependency>

        <dependency>
            <groupId>org.seleniumhq.selenium</groupId>
            <artifactId>selenium-chrome-driver</artifactId>
            <version>3.9.1</version>
        </dependency>

        <dependency>
            <groupId>org.seleniumhq.selenium</groupId>
            <artifactId>selenium-firefox-driver</artifactId>
            <version>3.9.1</version>
        </dependency>

        <dependency>
            <groupId>org.seleniumhq.selenium</groupId>
            <artifactId>selenium-safari-driver</artifactId>
            <version>3.9.1</version>
        </dependency>

        <dependency>
            <groupId>org.apache.maven</groupId>
            <artifactId>maven-plugin-api</artifactId>
            <version>3.3.9</version>
        </dependency>

        <dependency>
            <groupId>org.testng</groupId>
            <artifactId>testng</artifactId>
            <version>6.8</version>
        </dependency>

        <dependency>
            <groupId>org.codehaus.groovy</groupId>
            <artifactId>groovy</artifactId>
            <version>1.8.3</version>
            <scope>test</scope>
        </dependency>
    </dependencies>
</project>

