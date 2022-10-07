# TP2 ITIL

## Compilación

```
mvn clean package
```

## Ejecución

```
java -jar target/itil-0.0.1-SNAPSHOT.jar --spring.profiles.active=[profile]

Ej. [profile]=dev-h2
```

## Documentación
La aplicación utiliza la siguiente extension para generar documentación en forma online y offline.

```
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-ui</artifactId>
</dependency>
```

De forma online, al levantar la aplicación, se puede acceder a `[url]:[puerto]]/swagger-ui/index.html`