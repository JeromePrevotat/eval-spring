# eval-spring

## Architecture

-   Java Springboot Mysql Docker

## How to use

### Start the application

-   docker compose up --build -d

### Access the Database

-   docker exec -it mysql bash
-   mysql -p (password = root)

## Fonctionnalités attendues

## Utilisateur

-   `POST /users` → Créer un utilisateur → DONE
-   `GET /users/{id}` → Afficher un utilisateur → DONE
-   `GET /users/{id}/projects` → Projets créés par l’utilisateur
    → DONE by using a minimalist DTO
-   `GET /users/{id}/tasks` → Tâches assignées à l’utilisateur
    → DONE by using the TaskDTO

## Projet

-   `POST /projects` → Créer un projet (avec ID du créateur) → DONE
-   `GET /projects/{id}` → Détails d’un projet avec ses tâches
    → DONE by using a specific DTO containing more attributes

## Tâche

-   `POST /tasks` → Créer une tâche (avec ID du projet et de l’utilisateur assigné) → DONE
-   `PATCH /tasks/{id}` → Modifier le statut (Enum : `TODO`, `IN_PROGRESS`, `DONE`) → DONE
-   `GET /projects/{id}/tasks` → Lister les tâches d’un projet
    → DONE by using TaskDTO
