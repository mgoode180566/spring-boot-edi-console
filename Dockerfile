FROM openjdk:8
ADD target/spring-boot-edi-console.jar spring-boot-edi-console
EXPOSE 8085
ENTRYPOINT ["java","-jar","spring-boot-edi-console"]
