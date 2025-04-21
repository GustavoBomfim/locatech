FROM openjdk:21-rc-oraclelinux8

RUN mkdir /app

WORKDIR /app

COPY target/*.jar /app/app.jar

CMD ["java", "-jar", "/app/app.jar"]