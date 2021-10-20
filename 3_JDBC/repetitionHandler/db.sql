create table corso
(
    titolo      varchar(255) not null
        primary key,
    descrizione varchar(255) null
);

INSERT INTO test.corso (titolo, descrizione) VALUES ('Algoritmi e strutture dati', 'corso da 9CFU');
INSERT INTO test.corso (titolo, descrizione) VALUES ('Database', 'ciaoooo');
INSERT INTO test.corso (titolo, descrizione) VALUES ('Diritto informatico', 'corso da 3CFU');
INSERT INTO test.corso (titolo, descrizione) VALUES ('Programmazione 3', 'corso da 9CFU');

create table docente
(
    matricola int          not null
        primary key,
    cognome   varchar(255) null,
    nome      varchar(255) null
);

INSERT INTO test.docente (matricola, cognome, nome) VALUES (1234, 'Ardissono', 'Lilliana');
INSERT INTO test.docente (matricola, cognome, nome) VALUES (2468, 'De'' Liguoro', 'Ugo');
INSERT INTO test.docente (matricola, cognome, nome) VALUES (5678, 'Sacchetto', 'Camillo');
INSERT INTO test.docente (matricola, cognome, nome) VALUES (34565, 'vsfgvsdfgvf', 'sgdrgrdsg');
INSERT INTO test.docente (matricola, cognome, nome) VALUES (34567, 'Pensa', 'Gaetano');

create table affiliazione
(
    matricola_docente int          not null,
    titolo_corso      varchar(255) not null,
    primary key (matricola_docente, titolo_corso),
    constraint ID_docente_aff___fk
        foreign key (matricola_docente) references docente (matricola),
    constraint titolo_corso_aff___fk
        foreign key (titolo_corso) references corso (titolo)
);

INSERT INTO test.affiliazione (matricola_docente, titolo_corso) VALUES (1234, 'Algoritmi e strutture dati');
INSERT INTO test.affiliazione (matricola_docente, titolo_corso) VALUES (1234, 'Database');
INSERT INTO test.affiliazione (matricola_docente, titolo_corso) VALUES (2468, 'Algoritmi e strutture dati');
INSERT INTO test.affiliazione (matricola_docente, titolo_corso) VALUES (5678, 'Diritto informatico');
INSERT INTO test.affiliazione (matricola_docente, titolo_corso) VALUES (34567, 'Database');

create table utente
(
    matricola int          not null
        primary key,
    account   varchar(255) null,
    password  varchar(255) null,
    ruolo     varchar(255) null
);

INSERT INTO test.utente (matricola, account, password, ruolo) VALUES (1234, 'helo.andrea', 'heheeee', 'amministratore');
INSERT INTO test.utente (matricola, account, password, ruolo) VALUES (4545, 'ciao.luca', 'yooo', 'utente');

create table ripetizione
(
    docente    int          not null,
    utente     int          not null,
    ora_inizio varchar(255) not null,
    ora_fine   varchar(255) null,
    corso      varchar(255) not null,
    primary key (docente, utente, ora_inizio),
    constraint id_docente_rip___fk
        foreign key (docente) references docente (matricola),
    constraint id_utente_rip___fk
        foreign key (utente) references utente (matricola),
    constraint titolo_corso_rip___fk
        foreign key (corso) references corso (titolo)
);

INSERT INTO test.ripetizione (docente, utente, ora_inizio, ora_fine, corso) VALUES (1234, 4545, '12:45', '13:45', 'Algoritmi e strutture dati');