CREATE TABLE silniki (
	id_silnika int PRIMARY KEY,
	typ char(1) NOT NULL,
	pojemnosc int NOT NULL,
	moc int NOT NULL,
	moment int NOT NULL
);

CREATE TABLE nadwozia (
	typ varchar(20) PRIMARY KEY,
	liczba_drzwi int NOT NULL
);

CREATE TABLE modele (
	nazwa varchar(20) PRIMARY KEY,
	produkowany_od date NOT NULL,
	produkowany_do date,
	gwarancja int NOT NULL
);

CREATE TABLE lakiery (
	typ varchar(20) PRIMARY KEY
);

CREATE TABLE kolory (
	id_koloru int,
	lakier varchar(20) NOT NULL REFERENCES lakiery (typ),
	nazwa varchar(30) NOT NULL,
	PRIMARY KEY (id_koloru, lakier)
);

CREATE TABLE wersje (
	id_wersji numeric PRIMARY KEY,
	model varchar(20) NOT NULL REFERENCES modele (nazwa),
	silnik int NOT NULL REFERENCES silniki (id_silnika),
	nadwozie varchar(20) NOT NULL REFERENCES nadwozia (typ),
	lakier varchar(20) NOT NULL REFERENCES lakiery (typ),
	cena numeric NOT NULL,
	nazwa_wersji varchar(20),
	UNIQUE (model, silnik, nadwozie, lakier)
);

CREATE TABLE wyposazenia (
	id_konfig numeric,
	model varchar(20) NOT NULL REFERENCES modele (nazwa),
	abs numeric,
	esp numeric,
	klimatyzacja_man numeric,
	klimatyzacja_aut numeric,
	airbag_kier numeric,
	airbag_pas numeric,
	airbag_bok numeric,
	komputer numeric,
	nawigacja numeric,
	centr_zamek numeric,
	alarm numeric,
	alufelgi numeric,
	ksenony numeric,
	tempomat numeric,
	el_szyby numeric,
	el_lusterka numeric,
	cz_parkowania numeric,
	PRIMARY KEY (id_konfig, model)
);

CREATE TABLE konfiguracje (
	id_konfig numeric,
	abs boolean,
	esp boolean,
	klimatyzacja_man boolean,
	klimatyzacja_aut boolean,
	airbag_kier boolean,
	airbag_pas boolean,
	airbag_bok boolean,
	komputer boolean,
	nawigacja boolean,
	centr_zamek boolean,
	alarm boolean,
	alufelgi boolean,
	ksenony boolean,
	tempomat boolean,
	el_szyby boolean,
	el_lusterka boolean,
	cz_parkowania boolean,
	id_koloru int NOT NULL REFERENCES kolory,
	PRIMARY KEY (id_konfig, id_koloru)
);

CREATE TABLE auta_na_sprzedaz (
	vin char(17) PRIMARY KEY,
	rok_produkcji date NOT NULL,
	wersja numeric NOT NULL REFERENCES wersje (id_wersji),
	placowka int NOT NULL REFERENCES placowki (id_placowki)
	id_konfig numeric NOT NULL,
	id_koloru int NOT NULL,
	FOREIGN KEY (id_konfig, id_koloru) REFERENCES konfiguracje (id_konfig, id_koloru)
);

CREATE TABLE klienci (
	id_klienta int PRIMARY KEY,
	nazwa varchar(100) NOT NULL,
	telefon varchar(20) NOT NULL,
	mail varchar(50),
	adres varchar(100)
);

CREATE TABLE auta_klientow (
	vin char(17) PRIMARY KEY,
	nr_rej varchar(10) NOT NULL,
	rok_produkcji date NOT NULL,
	wersja numeric NOT NULL REFERENCES wersje (id_wersji),
	data_zakupu date NOT NULL,
	placowka int NOT NULL REFERENCES placowki (id_placowki),
	klient int NOT NULL REFERENCES klienci (id_klienta),
	id_konfig numeric NOT NULL,
	id_koloru int NOT NULL,
	FOREIGN KEY (id_konfig, id_koloru) REFERENCES konfiguracje (id_konfig, id_koloru)
);

CREATE TABLE naprawy (
	numer int PRIMARY KEY,
	vin char(17) NOT NULL REFERENCES auta_klientow,
	placowka int NOT NULL REFERENCES placowki (id_placowki),
	data date NOT NULL,
	koszt numeric NOT NULL,
	opis varchar(200) NOT NULL
);

CREATE TABLE placowki (
	id_placowki int PRIMARY KEY,
	nazwa varchar(30) NOT NULL,
	adres varchar(100) NOT NULL,
	dochod numeric NOT NULL,
	telefon varchar(20),
	mail varchar(50)
);