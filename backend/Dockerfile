FROM adoptopenjdk:11-jre-hotspot
EXPOSE 8086
ARG JAR_FILE=target/backend.jar
ARG USER=dali
RUN groupadd -r ${USER} && useradd --no-log-init -r -g ${USER} ${USER}
USER ${USER}
COPY ${JAR_FILE} dali/app.jar
ENTRYPOINT ["java","-jar","dali/app.jar"]
