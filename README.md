Bu proje, mikroservis mimarisinde çalışan stock-service uygulamasını Eureka Server'a register etme örneğidir.

https://medium.com/@haticezeren0/feign-client-ile-servisler-aras%C4%B1-i%CC%87leti%C5%9Fim-nas%C4%B1l-sa%C4%9Flan%C4%B1r-d874ae44418b  


# Gereksinimler:
- Docker
- Docker Compose
- IntelliJ

# Projenin Çalıştırılması

## Docker Image'larının Oluşturulması

  Proje mysql database'ini kullandığından docker hub'dan mysql image'in son sürümünü indirebiliriz.
  Projeyi lokalimize çekelim maven ile clean-install yapalım ve intelliJ'e ait terminalde aşağıdaki komutu yazarak stock-service için image oluşturalım.

`    docker build -t stock-service-eureka-register:0.0.1 .
`

##  Docker Compose File dosyası ile deploy etme

-  mysql.yml dosyası aşağıdaki gibidir.

```
version: '3.3'

services:
  mysql:
    container_name: mysql
    hostname: mysql
    image: mysql:latest
    volumes:
      - /Users/haticezeren/data/mysql:/var/lib/mysql
    networks:
      - springboot-mysql-network
    expose:
      - 3306
    ports:
      - 3306:3306
    deploy:
      replicas: 1
      restart_policy:
        condition: on-failure
        delay: 20s
        max_attempts: 3
        window: 120s
      resources:
        reservations:
          cpus: "0.50"
          memory: 512M
        limits:
          cpus: "1.0"
          memory: 1G
    environment:
      MYSQL_ROOT_PASSWORD: root
      MYSQL_DATABASE: product_example
      MYSQL_USER: root
      MYSQL_PASSWORD: root
networks:
  springboot-mysql-network:
    name: springboot-mysql-network
```

Docker deploy komutu aşağıdaki gibidir:

> docker-compose -p eureka-example  -f mysql.yml up -d


-  stock-service.yml dosyası ise aşağıdaki gibidir.

```
version: '3.3'

services:
  stock-service:
    container_name: stock-service
    hostname: stock-service
    image: stock-service-eureka-register:0.0.1
    networks:
      - springboot-mysql-network
    expose:
      - 8091
    ports:
      - 8091:8091
    deploy:
      replicas: 1
      restart_policy:
        condition: on-failure
        delay: 5s
        max_attempts: 3
        window: 120s
      resources:
        reservations:
          cpus: "0.50"
          memory: 512M
        limits:
          cpus: "1.0"
          memory: 1G
    environment:
      - "SPRING_PROFILES_ACTIVE=stage"
    entrypoint: [ "java","-jar","app.jar" ]
networks:
  springboot-mysql-network:
    name: springboot-mysql-network
```

Docker deploy komutu aşağıdaki gibidir:

> docker-compose -p eureka-example  -f stock-service.yml up -d


## Mysql Database Bilgileri:

`    CREATE SCHEMA IF NOT EXISTS inventory;

-- Stok tablosunu oluşturma
CREATE TABLE IF NOT EXISTS inventory.stock (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR(255) NOT NULL,
    product_id BIGINT NOT NULL,
    quantity INT NOT NULL,
    price DOUBLE NOT NULL
);
`

