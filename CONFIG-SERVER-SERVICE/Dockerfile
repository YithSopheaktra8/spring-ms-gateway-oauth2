FROM gradle:8.10.2-jdk21-alpine AS builder
ENV SPRING_PROFILES_ACTIVE=dev
WORKDIR /app
COPY / .
RUN gradle build --no-daemon

FROM openjdk:21-oracle
COPY --from=builder /app/build/libs/*-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8071