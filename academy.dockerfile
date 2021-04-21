FROM adoptopenjdk/openjdk15:ubi

COPY ./target/demo-*.jar app.jar

CMD ["/usr/bin/java", "-jar", "app.jar"]