create database progettoM159;
use progettoM159;

create table proposte
(
    id          int not null auto_increment primary key,
    nome        varchar(500),
    descrizione varchar(500),
    autore      varchar(100)
);

create table votazioni
(
    id          int not null auto_increment primary key,
    nome        varchar(500),
    descrizione varchar(500),
    autore      varchar(100)
);

create table risultati
(
    id          int not null auto_increment primary key,
    idVotazione int not null,
    nome        varchar(100),
    si          int,
    no          int,
    foreign key (idVotazione) references votazioni (id)
);

create table risultatiVotazioniDeleted
(
    id          int not null auto_increment primary key,
    nome        varchar(500),
    descrizione varchar(500),
    autore      varchar(100),
    si          int,
    no          int,
    dataDeleted date
);