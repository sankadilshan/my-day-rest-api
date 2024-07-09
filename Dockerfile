# syntax=docker/dockerfile:1
FROM openjdk:17
EXPOSE 8080
EXPOSE 5005
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENV JAVA_TOOL_OPTIONS -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005
ENTRYPOINT ["java","-jar","/app.jar"]
