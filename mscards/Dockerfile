FROM maven:3.8.8-amazoncorretto-21-al2023 as build

WORKDIR /build

COPY . .

RUN mvn clean package -DskipTests

FROM amazoncorretto:21.0.5

WORKDIR /app

COPY --from=build ./build/target/*jar ./app.jar

ENV RABBIT_HOST='rabbitmq'
ENV RABBIT_USERNAME='guest'
ENV RABBIT_PASSWORD='guest'
ENV URL_EUREKA='http://admin:admin4321@eureka:8761/eureka'

ENTRYPOINT exec java -jar app.jar