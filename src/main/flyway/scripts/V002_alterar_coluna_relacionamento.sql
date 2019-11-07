ALTER TABLE bkh_adm.usuario  change relacionamento relacionamento varchar(30);

ALTER TABLE bkh_adm.usuario  add email varchar(40) not null;

ALTER TABLE bkh_adm.usuario  add id_avatar int;

Create table bkh_adm.avatar(
id int primary key,
url varchar(21844) not null
);