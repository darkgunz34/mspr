# Mspr Spring

## Connexion BDD

Pour pouvoir utiliser la base de données en local a des fins de tests, il faut avoir la configuration suivante :

```text
url dev : Localhost
port : 3306
user : root
password : root
databaseName : gostyle
```

## JDD

Consulter le fichier initializeDb.java pour voir l'ensemble des jeux de données utilisable dans l'application par défaut.

Attention, a chaque redémarrage de l'application, la base de données est re-créer.

## Log

Mise en place d'un logger pour l'application au niveau debug actuellement.
Le résultat dedans se trouve dans le dossier `log/application`.