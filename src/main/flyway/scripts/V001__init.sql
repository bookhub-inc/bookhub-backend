create database bkh_adm;

use bkh_adm;

GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, RELOAD, PROCESS, REFERENCES, INDEX, ALTER, SHOW DATABASES, CREATE TEMPORARY TABLES, LOCK TABLES, EXECUTE, REPLICATION SLAVE, REPLICATION CLIENT, CREATE VIEW, SHOW VIEW, CREATE ROUTINE, ALTER ROUTINE, CREATE USER, EVENT, TRIGGER ON *.* TO 'bkh_adm'@'%' WITH GRANT OPTION;

Create table usuario(
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

create table livro(
id int primary key,
nome varchar(80) not null,
autor varchar(40) not null,
publicadora varchar(60),
descricao text,
dta_lancamento date,
n_paginas int
);

create table livxcat(
id_livro int not null,
id_cat int not null
);

create table categoria(
id int primary key auto_increment,
nome_categoria varchar(40)
);

create table comentario(
id int primary key,
comentario varchar(500),
id_usuario int not null,
rating double,
dta_criacao datetime,
id_livro int
);

create table topico(
id int primary key,
titulo varchar (60),
id_usuario int not null,
usuario varchar(25),
dta_criacao date
);

create table topico_comentario(
id int primary key not null,
comentario varchar(500),
dta_comentario datetime,
id_usuario int not null,
id_topico int not null);


alter table livxcat
add foreign key (id_livro) references livro(id),
add foreign key (id_cat) references categoria(id);

alter table comentario
add foreign key (id_usuario) references usuario(id),
add foreign key (id_livro) references livro(id);

alter table topico_comentario
add foreign key (id_topico) references topico(id);

alter table usuario modify column senha varbinary(255);

set sql_safe_updates = 0;

update usuario set senha = aes_encrypt(senha,64);

create table usuario_estante(
id int primary key,
id_usuario int,
id_livro int,
lido boolean,
comprado boolean,
gostou boolean,
foreign key (id_Usuario) references usuario(id),
foreign key (id_Livro) references livro(id));