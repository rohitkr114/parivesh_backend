FROM openjdk:17
EXPOSE 8888
#COPY ././parivesh_backend /opt/parivesh_backend
ARG JAR_FILE=target/config-server-1.0.0.jar
ADD ${JAR_FILE} config-server.jar
ENTRYPOINT ["java","-jar","/config-server.jar"]
