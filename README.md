# delivery-mail-service

Delivery mail service for Spring Boot

1. build

  + fix config info in `application-*.yml`
  
  + build jar
  
    ```shell
      mvnw jar -DskipTests=true
    ```

2. Deloyment
   
   + JDK11
   
   + Mariadb/Mysql/Postgresql(Jpa)...
   
   + Email
   
   + running jar
   
     ```shell
       nohup java -jar delivery-mail-service-1.1.0-RELEASE.jar &
     ```
