FROM openjdk:8
EXPOSE 9002
ADD target/mmtspl-address-service-1.0.0-SNAPSHOT.jar mmtspl-address-service-1.0.0-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar","/mmtspl-address-service-1.0.0-SNAPSHOT.jar"]