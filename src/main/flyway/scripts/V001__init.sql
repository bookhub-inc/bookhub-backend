CREATE USER 'bkh_adm'@'%'
identified by 'bkh_admn';

GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, RELOAD, PROCESS, REFERENCES, INDEX, ALTER, SHOW DATABASES, CREATE TEMPORARY TABLES, LOCK TABLES, EXECUTE, REPLICATION SLAVE, REPLICATION CLIENT, CREATE VIEW, SHOW VIEW, CREATE ROUTINE, ALTER ROUTINE, CREATE USER, EVENT, TRIGGER ON *.* TO 'bkh_adm'@'%' WITH GRANT OPTION;

create database bkh_adm;

use bkh_adm;

Create table Usuario(
Id int primary key,
Nome varchar(30) not null,
Sobrenome varchar(30) not null,
Facebook varchar(40),
Telefone varchar(14) UNIQUE,
Relacionamento char,
Usuario varchar(25) not null,
Senha varchar(40) not null,
Cpf varchar(11) unique,
Dta_criacao datetime not null,
Dta_ultacesso datetime not null
);

create table Livro_favorito(
Id_usuario int,
Usuario varchar(25),
Id_livro int,
Nome_livro varchar(80));

create table Livro(
Id int primary key,
Nome varchar(80) not null,
Autor varchar(40) not null,
Publicadora varchar(60),
Descricacao text,
Dta_lancamento date,
N_paginas int
);

create table LivXcat(
Id_livro int,
Id_cat int
);

create table Categoria(
Id int primary key,
Categoria varchar(40)
);

create table Comentario(
Id int primary key,
Comentario varchar(500),
Id_usuario int,
Usuario varchar(25),
Rating double,
Dta_comentario date,
Id_livro int
);

create table Topico(
Id int primary key,
Titulo varchar (60),
Id_usuario int,
Usuario varchar(25)
);

create table Topico_comentario(
Id int primary key,
Comentario varchar(500),
Dta_comentario datetime,
Id_usuario int,
Usuario varchar(25),
Id_topico int);


alter table Livro_favorito
add foreign key (Id_usuario) references Usuario(Id),
add foreign key (Id_livro) references Livro(Id);

alter table LivXcat
add foreign key (Id_livro) references Livro(Id),
add foreign key (Id_cat) references Categoria(Id);

alter table Comentario
add foreign key (Id_usuario) references Usuario(Id),
add foreign key (Id_livro) references Livro(Id);

alter table Topico_comentario
add foreign key (Id_topico) references Topico(Id);

use bkh_adm;

alter table Usuario modify column Senha varbinary(255);

Set SQL_SAFE_UPDATES = 0;

Update Usuario set Senha = aes_encrypt(Senha,64);


use bkh_adm;

alter table Topico
add column Dta_Criacao datetime after Titulo;

Create table Usuario_Livro(
Id int primary key,
Id_Usuario int,
Usuario varchar (20),
Id_Livro int,
Nome varchar(60),
 foreign key (Id_usuario) references Usuario(Id),
 foreign key (Id_Livro) references Livro(Id));
 
 Create table Usuario_Estante(
 Id int primary key,
 Id_Usuario Int,
 Id_Livro Int,
 Nome varchar(60),
 Lido binary,
 Comprado binary,
 foreign key (Id_Usuario) references Usuario(Id),
 foreign key (Id_Livro) references Livro(Id));
 
 drop table Livro_favorito;