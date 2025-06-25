# eval-spring

## Fonctionnalités attendues

## Utilisateur

-   `POST /users` → Créer un utilisateur → DONE
-   `GET /users/{id}` → Afficher un utilisateur → DONE
-   `GET /users/{id}/projects` → Projets créés par l’utilisateur → TO DO
-   `GET /users/{id}/tasks` → Tâches assignées à l’utilisateur → TO DO

## Projet

-   `POST /projects` → Créer un projet (avec ID du créateur) → DONE
-   `GET /projects/{id}` → Détails d’un projet avec ses tâches → TO DO

## Tâche

-   `POST /tasks` → Créer une tâche (avec ID du projet et de l’utilisateur assigné) → TO DO
-   `PATCH /tasks/{id}` → Modifier le statut (Enum : `TODO`, `IN_PROGRESS`, `DONE`) → TO DO
-   `GET /projects/{id}/tasks` → Lister les tâches d’un projet → TO DO
