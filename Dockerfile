FROM java:17
EXPOSE 8080
ADD target/IpetProject_demo1.jar IpetProject_demo1.jar
ENTRYPOINT ["java","-jar","IpetProject_demo1.jar"]