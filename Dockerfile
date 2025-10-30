FROM bellsoft/liberica-openjdk-debian:latest AS builder

WORKDIR /app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
RUN ./mvnw dependency:go-offline -B

COPY src src
RUN ./mvnw package -DskipTests

RUN find target -name "*.jar" -exec mv {} application.jar \;

FROM bellsoft/liberica-openjre-debian:latest

ARG PORT=8080
ENV PORT=${PORT}
EXPOSE ${PORT}

COPY --from=builder /app/application.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
