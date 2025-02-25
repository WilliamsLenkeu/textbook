# Cahier Backend

Ce projet est une application avec un backend développée avec **Spring Boot**.

## 🚀 Lancer le backend

1. **Cloner le repository**
   ```sh
   git clone https://github.com/WilliamsLenkeu/textbook.git
   cd textbook
   ```

2. **Créer la base de données**
   Avant de démarrer l'application, assurez-vous d'avoir une base de données nommée `cahier_bd`. Vous pouvez la créer avec la commande SQL suivante :
   
   ```sql
   CREATE DATABASE cahier_bd;
   ```
3. **Ce placer dans le dossier du backend**
   Utilisez la commande suivante pour vous y placez :
   
   ```sh
   cd backend
   ```

4. **Lancer le backend**
   Utilisez la commande suivante pour démarrer l'application :
   
   ```sh
   ./mvnw spring-boot:run
   ```

## 🛠 Prérequis

- **Java 17+**
- **Maven** (inclus via `mvnw`)
- **MySQL ou PostgreSQL** (selon votre configuration)
