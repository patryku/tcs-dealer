begin;

drop table if exists modele cascade;
drop table if exists silniki cascade;
drop table if exists nadwozia cascade;
drop table if exists lakiery cascade;
drop table if exists placowki cascade;
drop table if exists klienci cascade;
drop table if exists kolory cascade;
drop table if exists wersje cascade;
drop table if exists auta_na_sprzedaz cascade;
drop table if exists auta_klientow cascade;
drop table if exists naprawy cascade;
drop table if exists konfiguracje cascade;
drop table if exists wyposazenia cascade;

drop function if exists kolory_view_trig();
drop function if exists wersje_view_trig();
drop function if exists rok_produkcji_trig();
drop function if exists konfig_trig1();
drop function if exists konfig_trig2();
drop function if exists cena_fabryczna(char(17));

commit;
