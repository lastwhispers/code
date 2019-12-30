FROM hub.c.163.com/library/java:8-alpine

MAINTAINER 15037584397@163.com

ADD target/*.jar app.jar

EXPOSE 8761

ENTRYPOINT ["java","-jar","/app.jar"]

