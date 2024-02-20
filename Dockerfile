FROM eclipse-temurin:17-jre-alpine
VOLUME /tmp
COPY target/demo-*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]