insert into silniki (typ, pojemnosc, moc, moment) values ('hybrydowy', 1400, 75, 450);
insert into silniki (typ, pojemnosc, moc, moment) values ('benzynowy', 1100, 50, 300);
insert into silniki (typ, pojemnosc, moc, moment) values ('wysokoprezny', 3000, 600, 1200);
insert into silniki (typ, pojemnosc, moc, moment) values ('benzynowy', 2200, 400, 600);

insert into placowki (nazwa, adres, telefon, mail) values('Salon Skoda', 'Zakopianska 94 Krakow', '123-456-789', 'adres@mail.com');
insert into placowki (nazwa, adres, telefon, mail) values('Volkswagen Krupowki', 'Kwiatowa 15 Zakopane', '976-451-756', 'strona@poczta.pl');
insert into placowki (nazwa, adres, telefon, mail) values('Traktory', 'Krakowskie Przedmiescie 102 Warszawa', '555-576-422', 'porzeczka@skrzynka.org');
insert into placowki (nazwa, adres, telefon, mail) values('Slupek Pietnasty', 'Molo 176. metr Sopot', '234-195-267', 'orzel7@paczka.lol');

insert into nadwozia (typ, liczba_drzwi) values('hatchback', 2);
insert into nadwozia (typ, liczba_drzwi) values('sedan', 4);
insert into nadwozia (typ, liczba_drzwi) values('fastback', 2);
insert into nadwozia (typ, liczba_drzwi) values('kombi', 3);
insert into nadwozia (typ, liczba_drzwi) values('kabriolet', 2);

insert into modele (producent, nazwa, produkowany_od, produkowany_do, gwarancja) values('Volkswagen', 'Garbus', '1938-01-01', '2003-12-31', 2);
insert into modele (producent, nazwa, produkowany_od, produkowany_do, gwarancja) values('Mazda', '6', '2002-03-02', '2007-11-17', 4);
insert into modele (producent, nazwa, produkowany_od, produkowany_do, gwarancja) values('Henschel', 'Tiger Ausf. E', '1942-04-07', '1945-01-03', 1);

insert into kolory_view values('perlowy', 'zolty');
insert into kolory_view values('perlowy', 'fioletowy');
insert into kolory_view values('perlowy', 'bezowy');
insert into kolory_view values('matowy', 'niebieski');
insert into kolory_view values('metaliczny', 'czerwony');
insert into kolory_view values('metaliczny', 'szary');

insert into wyposazenia (abs, airbag_pas, ksenony, el_szyby, cz_parkowania) values(0, 500, 500, 300, 200);
insert into wyposazenia (esp, komputer, centr_zamek, tempomat, alufelgi) values(0, 1000, 0, 0, 2000);
insert into wyposazenia (alarm, centr_zamek, el_szyby, el_lusterka, airbag_kier, airbag_bok) values(100, 500, 300, 333, 1000, 1000);
insert into wyposazenia (nawigacja, alufelgi, tempomat, alarm, centr_zamek) values(0, 0, 0, 0, 0);
insert into wyposazenia (abs, esp, klimatyzacja_man, klimatyzacja_aut, airbag_kier, airbag_pas, airbag_bok, komputer, nawigacja, centr_zamek, alarm, alufelgi, ksenony, tempomat, el_szyby, el_lusterka, cz_parkowania) values(100, 300, 50, 50, 1000, 250, 350, 700, 500, 600, 400, 500, 600, 45, 56, 175, 200);
insert into wyposazenia (id_wypos) values(666);

insert into wersje_view values('Volkswagen', 'Garbus', 'wersja1', 'benzynowy', 1100, 45, 300, 'fastback', 2, 'matowy', 1, 5000);
insert into wersje_view values('Henschel', 'Tiger Ausf. E', 'test3', 'benzynowy', 6000, 800, 3000, 'tygrysek', 1, 'metaliczny', 3, 300000);


insert into klienci (nazwa, telefon, mail, adres) values('Doge', '696-569-769', 'MuchWow@Mail.org', 'SuchStreet 6');
insert into klienci (nazwa, telefon) values('Jas Roztocze', '911-112-765');
insert into klienci (nazwa, telefon, adres) values('Zomfg Enterprises', '555-419-555', 'Soft Street 18');

insert into konfiguracje (id_koloru, klimatyzacja_man, airbag_kier, komputer, ksenony, alufelgi, alarm, el_szyby) values(4, true, true, false, false, true, true, true);
insert into konfiguracje (id_koloru, klimatyzacja_man, airbag_pas, tempomat, el_lusterka, cz_parkowania, centr_zamek, el_szyby) values(1, true, false, false, false, true, false, true);
insert into konfiguracje (id_koloru, abs, airbag_kier, alarm, alufelgi, el_szyby) values(6, true, true, true, true, true);
insert into konfiguracje (id_konfig, id_koloru) values(1337, 1);

insert into auta_na_sprzedaz values('k1r3o3w7a', '1943-10-10', 15000, 2, 1, 1337, 1);
insert into auta_na_sprzedaz values('12389yh9', '1944-07-11', 22000, 2, 3, 1337, 1);
insert into auta_na_sprzedaz values('y4585g45h9', '1976-01-25', 0, 1, 2, 1, 4);
insert into auta_na_sprzedaz values('y45814asah', '1978-05-14', 0, 1, 2, 1, 4);
insert into auta_na_sprzedaz values('y4585gsad9', '1975-07-07', 0, 1, 2, 1, 4);