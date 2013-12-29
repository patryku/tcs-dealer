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


insert into wersje_view values('Volkswagen', 'Garbus', 'wersja1', 'benzynowy', 1100, 45, 300, 'fastback', 2, 'matowy', 5000);
insert into wersje_view values('Henschel', 'Tiger Ausf. E', 'test3', 'benzynowy', 6000, 800, 3000, 'tygrysek', 1, 'metaliczny', 300000);