# How to run:

## Database
Create user with name 'soa' , password: 'soa'
Create schema 'soa'
    

    sudo -u postgres psql
    create user soa with encrypted password 'soa';
    create database soaDB;
    \c soadb
    create schema soa;
    
## Server

1. Start wildfly server with standalone file included in folder: <strong>standalone</strong>

2. Type 
 <code> mvn clean install </code> in main folder
 
3. When project build correctly go to ear folder ( <code> cd ear </code>),
then run wildfly plugin to deploy project on server: 
<code> mvn wildfly:deploy </code>

# Swagger documentation

Swagger documentation is available under : 

http://127.0.0.1:8080/web/docs/
