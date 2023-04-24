FROM openjdk:20
WORKDIR /app
COPY target/customer-onboarding-api.jar /app/customer-onboarding-api.jar
ENTRYPOINT ["java", "-jar", "customer-onboarding-api.jar"]