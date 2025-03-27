FROM tomcat:10-jdk17
RUN rm -rf /usr/local/tomcat/webapps/*
COPY target/order-service-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/order-service-0.0.1-SNAPSHOT.war
EXPOSE 8081
CMD ["catalina.sh", "run"]
