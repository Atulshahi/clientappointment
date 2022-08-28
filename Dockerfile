FROM openjdk:18
EXPOSE 8083
ENTRYPOINT ["java","jar","/spring-boot-docker.jar"]
