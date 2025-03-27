FROM tomcat:10-jdk17
RUN rm -rf /usr/local/tomcat/webapps/*
COPY target/ticket-service-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/ticket-service-0.0.1-SNAPSHOT.war
EXPOSE 8083
CMD ["catalina.sh", "run"]
