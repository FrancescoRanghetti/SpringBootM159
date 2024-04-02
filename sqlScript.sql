create database progettoM159;
use progettoM159;

create table proposte (
    id int not null auto_increment primary key,
    nome varchar(500),
    descrizione varchar(500),
    autore varchar(100)
);

create table votazioni (
    id int not null auto_increment primary key,
    nome varchar(500),
    descrizione varchar(500),
    autore varchar(100)
);

create table risultati (
    id int not null auto_increment primary key,
    idVotazione int not null,
    nome varchar(100),
    si int,
    no int,
    foreign key (idVotazione) references votazioni(id)
);

insert into votazioni value (default, 'nuove macchinette', 'altro cibo', 'qualcuno');
insert into votazioni value (default, 'nuovi bagni', 'altre cagate', 'sempre qualcuno');
insert into votazioni value (default, 'nuove lavagne', 'boh altre lavagne', 'pippo');
