FROM eclipse-temurin:17-jdk-jammy AS builder
LABEL authors="jaewonLeeKOR"

#작업 디렉토리를 /app으로 설정
WORKDIR /app

COPY demo/gradlew demo/build.gradle demo/settings.gradle ./
COPY demo/gradle ./gradle
COPY demo/src ./src

RUN ./gradlew bootJar

FROM eclipse-temurin:17-jre-jammy
LABEL authors="jaewonLeeKOR"

WORKDIR /app

COPY --from=builder /app/build/libs/*.jar /app/app.jar

EXPOSE 8080

ENTRYPOINT java -jar /app/app.jar