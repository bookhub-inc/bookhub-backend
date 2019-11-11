ALTER TABLE bkh_adm.usuario  change relacionamento relacionamento varchar(30);

ALTER TABLE bkh_adm.usuario  add email varchar(40) not null;

ALTER TABLE bkh_adm.usuario  add id_avatar int;

Create table bkh_adm.avatar(
id int primary key,
url varchar(21844) not null
);

-- ja tem id do usuario
ALTER TABLE bkh_adm.topico  drop usuario;


ALTER TABLE bkh_adm.topico  add descricao TEXT;

ALTER TABLE  bkh_adm.usuario MODIFY id int NOT NULL AUTO_INCREMENT;


alter table  bkh_adm.topico add spoiler TINYINT(1) not null;


SET FOREIGN_KEY_CHECKS=0;

ALTER TABLE  bkh_adm.topico MODIFY id int NOT NULL AUTO_INCREMENT;

SET FOREIGN_KEY_CHECKS=1;