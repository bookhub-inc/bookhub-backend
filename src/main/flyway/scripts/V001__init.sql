CREATE USER 'bkh_adm'@'%'
identified by 'bkh_admn';

GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, RELOAD, PROCESS, REFERENCES, INDEX, ALTER, SHOW DATABASES, CREATE TEMPORARY TABLES, LOCK TABLES, EXECUTE, REPLICATION SLAVE, REPLICATION CLIENT, CREATE VIEW, SHOW VIEW, CREATE ROUTINE, ALTER ROUTINE, CREATE USER, EVENT, TRIGGER ON *.* TO 'bkh_adm'@'%' WITH GRANT OPTION;

create database bkh_adm;

use bkh_adm;

Create table Usuario(
id int primary key auto_increment,
nome varchar(30) not null,
sobrenome varchar(30) not null,
facebook varchar(40),
telefone varchar(14) UNIQUE,
relacionamento char,
usuario varchar(25) not null,
senha varchar(40) not null,
cpf varchar(11) unique,
dta_criacao datetime not null,
dta_ultacesso datetime not null
);

create table Livro(
Id int primary key,
Nome varchar(80) not null,
Autor varchar(40) not null,
Publicadora varchar(60),
Descricao text,
Dta_lancamento date,
N_paginas int
);

create table LivXcat(
id_livro int not null,
id_cat int not null
);

create table Categoria(
id int primary key auto_increment,
nome_categoria varchar(40)
);

create table Comentario(
id int primary key,
comentario varchar(500),
id_usuario int not null,
rating double,
dta_criacao datetime,
id_livro int
);

create table Topico(
id int primary key,
titulo varchar (60),
id_usuario int not null,
usuario varchar(25),
dta_criacao date
);

create table Topico_comentario(
id int primary key not null,
comentario varchar(500),
dta_comentario datetime,
id_usuario int not null,
id_topico int not null);


alter table livxcat
add foreign key (id_livro) references livro(id),
add foreign key (id_cat) references categoria(id);

alter table comentario
add foreign key (id_usuario) references Usuario(id),
add foreign key (id_livro) references Livro(id);

alter table topico_comentario
add foreign key (id_topico) references topico(id);

use bkh_adm;

alter table usuario modify column senha varbinary(255);

set SQL_SAFE_UPDATES = 0;

update Usuario set Senha = aes_encrypt(senha,64);

use bkh_adm;

alter table topico
add column dta_criacao datetime after titulo;

create table Usuario_Estante(
id int primary key,
id_usuario Int,
id_livro Int,
lido boolean,
comprado boolean,
gostou boolean,
foreign key (id_Usuario) references usuario(id),
foreign key (id_Livro) references livro(id));