# microservices-sample-project

### Prerequisities
  * JDK 1.8.X
  * Maven 3.3.X
  * MongoDB

### install common-lib
  * go inside to the common-lib project folder
  * then run ``` mvn clean install ```
  
### run discovery-server
  * go inside to the discovery-server project folder
  * then run ``` mvn clean package ```
  * then run ``` java -jar target\discovery-server-0.0.1-SNAPSHOT.jar ```
  
### run api-gateway
  * go inside to the api-gateway project folder
  * then run ``` mvn clean package ```
  * then run ``` java -jar target\api-gateway-0.0.1-SNAPSHOT.jar ```
  
### run auth-service
  * go inside to the auth-service project folder
  * then run ``` mvn clean package ```
  * then run ``` java -jar target\auth-service-0.0.1-SNAPSHOT.jar ```
  
### auth-service init
  * make request for the auth-service for setup roles and permissions.
  * ``` POST /api/auth-service/roles-permissions-setup/init ```
  
### run product-service
  * go inside to the product-service project folder
  * then run ``` mvn clean package ```
  * then run ``` java -jar target\product-service-0.0.1-SNAPSHOT.jar ```


  
 

  

