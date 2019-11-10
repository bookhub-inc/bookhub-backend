create database bkh_adm;

use bkh_adm;

CREATE USER 'bkh_adm'@'%'
identified by 'bkh_admn';

GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, RELOAD, PROCESS, REFERENCES, INDEX, ALTER, SHOW DATABASES, CREATE TEMPORARY TABLES, LOCK TABLES, EXECUTE, REPLICATION SLAVE, REPLICATION CLIENT, CREATE VIEW, SHOW VIEW, CREATE ROUTINE, ALTER ROUTINE, CREATE USER, EVENT, TRIGGER ON *.* TO 'bkh_adm'@'%' WITH GRANT OPTION;

Create table usuario(
id int primary key auto_increment not null,
nome varchar(30) not null,
sobrenome varchar(30) not null,
email varchar(40) not null unique,
id_avatar int not null,
telefone varchar(14) UNIQUE,
relacionamento varchar(30),
login varchar(25) not null unique,
senha varchar(40) not null,
dta_criacao datetime not null,
dta_ultacesso datetime not null);

create table livro(
id int primary key auto_increment,
capa varchar(2000),
nome varchar(80) not null,
autor varchar(40) not null,
editora varchar(60),
descricao text,
dta_lancamento date,
n_paginas int,
aprovado tinyint(1));

create table livxcat(
id_livro int not null,
id_cat int not null);

create table categoria(
id int primary key auto_increment,
nome_categoria varchar(40) not null unique);

create table comentario(
id int primary key auto_increment not null,
comentario text,
id_usuario int not null,
id_livro int not null,
nota int,
dta_criacao datetime);

create table topico(
id int primary key auto_increment,
titulo varchar (60) not null,
id_usuario int not null,
descricao text,
dta_criacao date,
spoiler tinyint(1));

create table topico_comentario(
id int primary key not null auto_increment,
comentario varchar(500) not null,
dta_comentario datetime not null,
id_usuario int not null,
id_topico int not null);

Create table avatar(
id int primary key auto_increment not null,
url varchar(2000) not null);

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
id int primary key auto_increment not null,
id_usuario int not null,
id_livro int not null,
lido tinyint(1),
comprado tinyint(1),
nota tinyint(1),
foreign key (id_Usuario) references usuario(id),
foreign key (id_Livro) references livro(id));

alter table usuario
add foreign key (id_avatar) references avatar(id);
<<<<<<< HEAD

drop table livro_rejeitado;

alter table livro add column motivo text;

alter table livro add column dta_atualizacao datetime;
=======
                                        
alter table comentario rename livro_comentario;
                                        
create table livro_rejeitado(
id int primary key auto_increment not null,
id_livro int not null,
motivo text,
foreign key (id_livro) references livro(id));
                                        
create table recomendador_livro_usuario(
id_usuario int not null,
id_livro int not null,
nota int not null,
foreing key (id_usuario) references usuario(id),
foreing key (id_livro) references livro(id),
foreing key (nota) references usuario_estante(nota));
>>>>>>> 5b89529415bc6a8e49c050d8f69d2eda44fc5820
