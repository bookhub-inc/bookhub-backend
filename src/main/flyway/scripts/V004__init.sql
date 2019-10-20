use bkh_adm;

alter table topico
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
 
 drop table livro_favorito;