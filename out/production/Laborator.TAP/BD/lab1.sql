CREATE TABLE IF NOT EXISTS clienti (
                                       id serial primary key ,
                                       nume VARCHAR(25) not null ,
    prenume VARCHAR(25) not null ,
    email VARCHAR(100) unique ,
    telefon VARCHAR(15) not null
    );



CREATE TABLE IF NOT EXISTS camere (
                                      id serial primary key ,
                                      tip VARCHAR(50) not null,
    pret DECIMAL(10,2) not null ,
    statut VARCHAR(20) not null
    );

CREATE TABLE IF NOT EXISTS rezervari (
                                         id serial primary key ,
                                         id_client int not null ,
                                         id_camere int not null ,
                                         date_start Date not null ,
                                         date_end Date not null ,
                                         FOREIGN KEY (id_client) references clienti(id),
    FOREIGN KEY (id_camere) references camere(id)
    );

CREATE TABLE IF NOT EXISTS facturi (
                                       id serial primary key,
                                       id_rezervare int not null,
                                       total DECIMAL(10,2) not null,
    data_emiterii DATE not null,
    status VARCHAR(20) not null,
    FOREIGN KEY (id_rezervare) REFERENCES rezervari(id)
    );


CREATE TABLE IF NOT EXISTS plati (
                                     id serial primary key,
                                     id_factura int unique not null,
                                     suma DECIMAL(10,2) not null,
    metoda_plata VARCHAR(50) not null,
    data_platii DATE not null,
    FOREIGN KEY (id_factura) REFERENCES facturi(id)
    );


INSERT INTO clienti (nume, prenume, email, telefon) VALUES
                                                        ('Popescu', 'Ion', 'ion.popescu@example.com', '0712345678'),
                                                        ('Ionescu', 'Maria', 'maria.ionescu@example.com', '0723456789'),
                                                        ('Georgescu', 'Andrei', 'andrei.georgescu@example.com', '0734567890');

INSERT INTO camere (tip, pret, statut) VALUES
                                           ('Single', 100.00, 'Disponibil'),
                                           ('Double', 150.00, 'Disponibil'),
                                           ('Suite', 250.00, 'Ocupat'),
                                           ('Deluxe', 300.00, 'Disponibil');

INSERT INTO rezervari (id_client, id_camere, date_start, date_end) VALUES
                                                                       (1, 1, '2024-10-10', '2024-10-15'),
                                                                       (2, 2, '2024-10-12', '2024-10-20'),
                                                                       (1, 3, '2024-10-05', '2024-10-10');


INSERT INTO facturi (id_rezervare, total, data_emiterii, status) VALUES
                                                                     (1, 500.00, '2024-10-05', 'Neachitat'),
                                                                     (2, 1200.00, '2024-10-12', 'Achitat'),
                                                                     (3, 1250.00, '2024-10-15', 'Achitat');

INSERT INTO plati (id_factura, suma, metoda_plata, data_platii) VALUES
                                                                    (1, 500.00, 'Card', '2024-10-10'),
                                                                    (2, 1200.00, 'Transfer Bancar', '2024-10-13'),
                                                                    (3, 1250.00, 'Card', '2024-10-16');

-- Teste
INSERT INTO rezervari (id_camere, date_start, date_end)
VALUES (1, '2024-10-10', '2024-10-15');

INSERT INTO rezervari (id_client, id_camere, date_start, date_end)
VALUES (1, 999, '2024-10-10', '2024-10-15');

DELETE FROM clienti WHERE id = 8;

INSERT INTO facturi (id_rezervare, total, data_emiterii, status)
VALUES (999, 500.00, '2024-10-05', 'Neachitat');

INSERT INTO plati (id_factura, suma, metoda_plata, data_platii)
VALUES (999, 500.00, 'Card', '2024-10-10');

INSERT INTO clienti (nume, prenume, email, telefon)
VALUES ('Popescu', 'Andrei', 'ion.popescu@example.com', '0745678901');
