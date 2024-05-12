FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY *.jar .
ENTRYPOINT exec java -Xms256M -Xmx256M -jar *.jar
