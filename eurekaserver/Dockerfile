#Build
ARG MAVEN_VERSION='3.8.8-amazoncorretto-21-al2023'
ARG JDK_VERSION='21.0.5'
FROM maven:${MAVEN_VERSION} as build

WORKDIR /build

COPY . .

RUN mvn clean package -DskipTests

#Run
FROM amazoncorretto:${JDK_VERSION}

WORKDIR /app

COPY --from=build ./build/target/*jar ./app.jar

EXPOSE 8761

ENV USERNAME_SECURITY='admin'
ENV PASSWORD_SECURITY='admin4321'

ENTRYPOINT exec java -jar app.jar