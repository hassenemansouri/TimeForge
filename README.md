# Time Forge Microservices

## 🛠 Introduction
Time Forge est une application basée sur une architecture **microservices** développée par **Deep Tech Team**. Elle permet de gérer efficacement les espaces de travail, les collaborations, les projets et leurs objectifs.

## 🏗 Architecture
L'application repose sur une architecture **microservices** avec les composants suivants :

- 🚪 **Gateway Service** : API Gateway pour gérer les requêtes entrantes.
- 🔍 **Discovery Service** : Service de découverte des microservices via Eureka.
- ⚙️ **Config Server** : Gestion centralisée des configurations.
- 👤 **User Service** : Gestion des utilisateurs.
- 🏢 **Workspace Service** : Gestion des espaces de travail.
- 🤝 **Collaborations Service** : Gestion des collaborations entre utilisateurs.
- 📂 **Projects Service** : Gestion des projets.
- 🎯 **Goals Service** : Gestion des objectifs de projets.
- 🔐 **Keycloak Integration** : Gestion de l'authentification et de l'autorisation via Keycloak.

## 🚀 Technologies Utilisées
- **Backend** : `Spring Boot`, `Spring Cloud (Gateway, Eureka, Config Server)`, `Feign Clients`
- **Sécurité** : `Keycloak`, `Spring Security`
- **Base de Données** : `PostgreSQL`, `MongoDB`
- **Communication Interservices** : `Feign Clients`, `OpenFeign`
- **Déploiement** : `Docker`, `Kubernetes (prévu)`

## ⚙️ Installation et Configuration
### 📌 Prérequis
- ✅ **Java 17+**
- ✅ **Spring Boot**
- ✅ **Docker & Docker Compose**
- ✅ **PostgreSQL & MongoDB**
- ✅ **Keycloak** configuré avec les bons realms et clients

### 🏗 Installation
1. **Cloner le dépôt**
   ```bash
   git clone https://github.com/deep-tech-team/time-forge-microservices.git
   cd time-forge-microservices
   ```

2. **Lancer les services avec Docker Compose**
   ```bash
   docker-compose up -d
   ```

3. **Démarrer les microservices manuellement** (si nécessaire)
   ```bash
   mvn spring-boot:run -pl config-server
   mvn spring-boot:run -pl discovery-service
   mvn spring-boot:run -pl gateway-service
   mvn spring-boot:run -pl user-service
   # Répéter pour les autres services
   ```

## 📖 API Documentation
L'API est documentée via **Swagger**.

- 🔗 URL Swagger : `http://localhost:8080/swagger-ui.html`

## 🤝 Contribuer
Les contributions sont les bienvenues !

1. 🍴 **Fork** du projet
2. 🌱 Création d'une branche (`feature/ma-fonctionnalité`)
3. 💾 Commit des modifications (`git commit -m 'Ajout de ma fonctionnalité'`)
4. 📤 Push sur la branche (`git push origin feature/ma-fonctionnalité`)
5. 🔄 Ouverture d'une **Pull Request**

## 📬 Contact
📢 **Deep Tech Team**  
📧 Email : support@deeptechteam.com  
🔗 GitHub : [https://github.com/deep-tech-team](https://github.com/deep-tech-team)

