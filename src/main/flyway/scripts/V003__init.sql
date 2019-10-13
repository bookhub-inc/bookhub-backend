use bkh_adm;

alter table Usuario modify column Senha varbinary(255);

Set SQL_SAFE_UPDATES = 0;

Update Usuario set Senha = aes_encrypt(Senha,64);