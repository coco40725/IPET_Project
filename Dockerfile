FROM openjdk:17-jdk-alpine3.14
EXPOSE 8080
ADD docker_IPetProject.jar docker_IPetProject.jar
ENTRYPOINT ["java","-jar","docker_IPetProject.jar"]