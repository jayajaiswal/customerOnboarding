mvn clean install -DskipTests=true
docker network create customer-onboarding-network
docker run --name mysqldb --network customer-onboarding-network -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=loyalbank -d mysql
docker build -t customer-onboarding-image .
sleep 30
docker run --network customer-onboarding-network --name customer-onboarding-app -p 8082:8080 customer-onboarding-image

cd src
docker cp main/resources/schema.sql mysqldb:/
docker exec mysqldb /bin/sh -c 'mysql -u root -proot </dbScript.sql'