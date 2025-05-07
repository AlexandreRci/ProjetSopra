# ---- Etape 1 : Build Maven ----
    FROM maven:3.9.9-amazoncorretto-21-debian-bookworm AS build

    WORKDIR /app
    
    # Copier le fichier pom.xml
    COPY pom.xml .
    
    # Télécharger les dépendances
    RUN mvn dependency:go-offline -B
    
    # Copier le code source
    COPY ./src ./src
    
    # Compiler le projet (crée target/*.jar)
    RUN mvn clean package -DskipTests
    
    # ---- Etape 2 : Image finale légère avec OpenJDK ----
    FROM openjdk:21-jdk-bullseye
    
    # Définir les variables d'environnement pour DB (optionnel, utile si tu veux overrider par défaut)
    ENV ENV_DB_URL=jdbc:mysql://4.233.149.159:3312/space
    ENV ENV_DB_USER=root
    ENV ENV_DB_PASSWORD=Not24Get!
    
    WORKDIR /app
    
    # Copier le JAR compilé
    COPY --from=build /app/target/*.jar ./space-boot.jar
    
    # Commande de démarrage
    ENTRYPOINT ["java", "-jar", "space-boot.jar"]
    
    # Exposer le port (Spring Boot par défaut 8080)
    EXPOSE 8080
    