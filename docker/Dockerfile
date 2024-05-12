FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY *.jar .
ENTRYPOINT exec java -Xms128M -Xmx128M -jar *.jar
