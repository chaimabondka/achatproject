FROM openjdk:8-slim
EXPOSE 8089
ADD http://169.254.230.2:8081/repository/maven-releases/tn/esprit/rh/achat/1.0/achat-1.0.jar achat-1.0.jar
ENTRYPOINT ["java", "-jar", "achat-1.0.jar"]
