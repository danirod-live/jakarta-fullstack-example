FROM maven:latest AS builder
WORKDIR /data
ADD pom.xml pom.xml
RUN mvn dependency:go-offline
ADD . .
ADD docker/persistence.xml src/main/resources/META-INF/persistence.xml
RUN mvn package
RUN ls

FROM tomcat:10
COPY --from=builder /data/target/fullstack-1.0.war /usr/local/tomcat/webapps/
ADD docker/tomcat-users.xml /usr/local/tomcat/conf/tomcat-users.xml
EXPOSE 8080
CMD ["catalina.sh", "run"]
