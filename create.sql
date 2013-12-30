BEGIN;

CREATE TABLE silniki (
	id_silnika serial PRIMARY KEY,
	typ varchar(20) NOT NULL,
	pojemnosc int NOT NULL CHECK (pojemnosc > 0),
	moc int NOT NULL CHECK (moc > 0),
	moment int NOT NULL CHECK (moment > 0),
	unique (typ, pojemnosc, moc, moment)
);

CREATE TABLE placowki (
	id_placowki serial PRIMARY KEY,
	nazwa varchar(30) NOT NULL,
	adres varchar(100) NOT NULL,
	telefon varchar(20),
	mail varchar(50)
);

CREATE TABLE nadwozia (
	id_nadwozia serial primary key,
	typ varchar(20) not null,
	liczba_drzwi int NOT NULL CHECK (liczba_drzwi > 0),
	unique (typ, liczba_drzwi)
);

CREATE TABLE modele (
	id_modelu serial primary key,
	producent varchar(20) not null,
	nazwa varchar(20) not null,
	produkowany_od date NOT NULL,
	produkowany_do date CHECK (produkowany_od < produkowany_do),
	gwarancja int NOT NULL CHECK (gwarancja >= 0),
	unique (producent, nazwa)
);

CREATE TABLE lakiery (
	id_lakieru serial primary key,
	typ varchar(20) unique
);

CREATE TABLE kolory (
	id_koloru serial primary key,
	id_lakieru int NOT NULL REFERENCES lakiery (id_lakieru),
	nazwa varchar(30) NOT NULL,
	unique (id_lakieru, nazwa)
);

CREATE TABLE wersje (
	id_wersji serial PRIMARY KEY,
	id_modelu int NOT NULL references modele (id_modelu),
	id_silnika int NOT NULL REFERENCES silniki (id_silnika),
	id_nadwozia int NOT NULL REFERENCES nadwozia (id_nadwozia),
	id_lakieru int NOT NULL REFERENCES lakiery (id_lakieru),
	cena numeric NOT NULL CHECK (cena >= 0),
	nazwa_wersji varchar(20),
	UNIQUE (id_modelu, id_silnika, id_nadwozia, id_lakieru)
);

CREATE TABLE wyposazenia (
	id_wypos serial,
	id_modelu int NOT NULL references modele (id_modelu),
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
	PRIMARY KEY (id_wypos, id_modelu),
	UNIQUE (id_modelu, abs, esp, klimatyzacja_man, klimatyzacja_aut, airbag_kier, airbag_pas, airbag_bok, 
	komputer, nawigacja, centr_zamek, alarm, alufelgi, ksenony, tempomat, el_szyby, el_lusterka, cz_parkowania)
);

CREATE TABLE konfiguracje (
	id_konfig serial,
	id_koloru int NOT NULL REFERENCES kolory (id_koloru),
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
	PRIMARY KEY (id_konfig, id_koloru),
	UNIQUE (id_koloru, abs, esp, klimatyzacja_man, klimatyzacja_aut, airbag_kier, airbag_pas, airbag_bok, 
	komputer, nawigacja, centr_zamek, alarm, alufelgi, ksenony, tempomat, el_szyby, el_lusterka, cz_parkowania)
);

CREATE TABLE auta_na_sprzedaz (
	vin char(17) PRIMARY KEY,
	rok_produkcji date NOT NULL,
	przebieg int NOT NULL CHECK (przebieg >= 0),
	wersja int NOT NULL REFERENCES wersje (id_wersji),
	placowka int NOT NULL REFERENCES placowki (id_placowki),
	id_konfig int NOT NULL,
	id_koloru int NOT NULL,
	FOREIGN KEY (id_konfig, id_koloru) REFERENCES konfiguracje (id_konfig, id_koloru)
);

CREATE TABLE klienci (
	id_klienta serial PRIMARY KEY,
	nazwa varchar(100) NOT NULL,
	telefon varchar(20) NOT NULL,
	mail varchar(50),
	adres varchar(100)
);

CREATE TABLE auta_klientow (
	vin char(17) PRIMARY KEY,
	nr_rej varchar(10) NOT NULL,
	rok_produkcji date NOT NULL,
	przebieg int NOT NULL CHECK (przebieg >= 0),
	wersja int NOT NULL REFERENCES wersje (id_wersji),
	data_zakupu date NOT NULL,
	cena_zakupu numeric NOT NULL CHECK (cena_zakupu >= 0),
	placowka int NOT NULL REFERENCES placowki (id_placowki),
	klient int NOT NULL REFERENCES klienci (id_klienta),
	id_konfig int NOT NULL,
	id_koloru int NOT NULL,
	FOREIGN KEY (id_konfig, id_koloru) REFERENCES konfiguracje (id_konfig, id_koloru)
);

CREATE TABLE naprawy (
	numer serial PRIMARY KEY,
	vin char(17) NOT NULL REFERENCES auta_klientow,
	placowka int NOT NULL REFERENCES placowki (id_placowki),
	data date NOT NULL,
	koszt numeric NOT NULL CHECK (koszt >= 0),
	opis varchar(200) NOT NULL
);

COMMIT;