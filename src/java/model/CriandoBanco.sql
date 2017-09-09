/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Igor
 * Created: 09/09/2017
 */

create table compromissos(
    id int not null AUTO_INCREMENT,
    usuario varchar(50) not null,
    compromisso varchar(500) not null,
    data date not null,
    hora time not null,
    PRIMARY KEY (id));

create table usuarios(
    id int not null AUTO_INCREMENT,
    usuario varchar(50) not null,
    senha varchar(50) nol null,
    PRIMARY KEY(id);

select * from compromissos

select * from compromissos order by data, hora

insert into compromissos (usuario, compromisso, data, hora)
values ("lucas", "jogar pubg", '2017-09-08', '16:00')

