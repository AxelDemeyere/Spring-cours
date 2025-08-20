# Exercice — API REST Spring Boot « Creatures »


Vous devez créer une petite API REST avec Spring Boot pour manipuler des **créatures**.
L’objectif est de mettre en place un **CRUD complet** et de tester la **pagination**  et la **validation** sur les 
résultats.

---

## Entité à implémenter

**Creature**

* `id : Long`
* `name : String`
* `age : int`
* `weight : double`
* `dangerous : boolean`
* `type : CreatureType` (enum : `DRAGON, ELF, DWARF, ORC, HUMAN`)

---

## Endpoints attendus

* **POST** `/api/creatures` → créer une créature
* **GET** `/api/creatures` → récupérer toutes les créatures
* **GET** `/api/creatures/{id}` → récupérer une créature par son id
* **PUT** `/api/creatures/{id}` → mettre à jour une créature
* **DELETE** `/api/creatures/{id}` → supprimer une créature
* **GET** `/api/creatures/paged?page=0&size=5` → récupérer les créatures avec pagination

## Bonus

* **GET** `/api/creatures/paged?page=0&size=5&sortField=age&direction=asc` → récupérer les créatures avec pagination et tri


 