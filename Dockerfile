FROM openjdk:17

WORKDIR /build

ARG SECRET_KEY=secret

ADD /target/TaskService.jar ./TaskService.jar

EXPOSE 8081

CMD java -jar UserService.jar