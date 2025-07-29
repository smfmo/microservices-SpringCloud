ARG MAVEN_VERSION='3.8.8-amazoncorretto-21-al2023'
ARG JDK_VERSION='21.0.5'

FROM maven:${MAVEN_VERSION} as build

WORKDIR /build

COPY . .

RUN mvn clean package -DskipTests

FROM amazoncorretto:${JDK_VERSION}

WORKDIR /app

COPY --from=build ./build/target/*jar ./app.jar

EXPOSE 8080

ENV URL_KEYCLOACK='http://keycloak:8080/realms/msCourseRealm'
ENV URL_EUREKA='http://admin:admin4321@eureka:8761/eureka'
ENV SPRING_PROFILES_ACTIVE='default'

ENTRYPOINT exec java -jar app.jar
