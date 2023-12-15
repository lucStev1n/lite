FROM openjdk:11

ENV active prod

EXPOSE 80

ADD target/pkg/lib /lib
ADD target/pkg/config /config

ENTRYPOINT ["java", "-jar", "app.jar"]