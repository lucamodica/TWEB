
create table test.corso
(
  titolo varchar(255) not null
    primary key,
  descrizione varchar(255) null
);

create table test.docente
(
  matricola int not null
    primary key,
  nome varchar(255) null,
  cognome varchar(255) null
);

create table test.affiliazione
(
  matricola_docente int not null,
  titolo_corso varchar(255) not null,
  primary key (matricola_docente, titolo_corso),
  constraint matricola_docente___fk
    foreign key (matricola_docente) references docente (matricola),
  constraint titolo_corso___fk
    foreign key (titolo_corso) references corso (titolo)
);

create table test.ripetizione
(
  docente int not null,
  corso varchar(255) not null,
  giorno date not null,
  ora time not null,
  luogo varchar(255) null,
  primary key (docente, corso, giorno, ora)
);

create table test.utente
(
  martricola int not null
    primary key,
  account varchar(255) null,
  password varchar(255) null,
  ruolo varchar(255) null
);

INSERT INTO test.corso (titolo, descrizione) VALUES ('Algoritmi e strutture dati', 'corso da 9CFU');
INSERT INTO test.corso (titolo, descrizione) VALUES ('Diritto informatico', 'corso da 3CFU');
INSERT INTO test.corso (titolo, descrizione) VALUES ('Programmazione 3', 'corso da 9CFU');

INSERT INTO test.docente (matricola, nome, cognome) VALUES (1234, 'Lilliana', 'Ardissono');
INSERT INTO test.docente (matricola, nome, cognome) VALUES (2468, 'Ugo', 'De'' Liguoro');
INSERT INTO test.docente (matricola, nome, cognome) VALUES (5678, 'Camillo', 'Sacchetto');

INSERT INTO test.affiliazione (matricola_docente, titolo_corso) VALUES (1234, 'Algoritmi e strutture dati');
INSERT INTO test.affiliazione (matricola_docente, titolo_corso) VALUES (2468, 'Algoritmi e strutture dati');
INSERT INTO test.affiliazione (matricola_docente, titolo_corso) VALUES (5678, 'Diritto informatico');