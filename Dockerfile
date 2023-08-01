FROM openjdk:17
EXPOSE 8888
#COPY ././parivesh_backend /opt/parivesh_backend
ARG JAR_FILE=target/Parivesh-1.0.0.jar
ADD ${JAR_FILE} parivesh2_dev.jar
ENTRYPOINT ["java","-jar","/parivesh2_dev.jar"]
