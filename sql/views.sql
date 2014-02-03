begin;

create view kolory_view
as
select 
	l.typ as typ_lakieru, 
	k.nazwa as kolor 
from lakiery l 
	join kolory k on l.id_lakieru = k.id_lakieru
order by id_koloru;

create view wersje_view
as
select 
	w.id_wersji as id,
	m.producent as producent, 
	m.nazwa as model, 
	w.nazwa_wersji as wersja,
	s.typ as typ_silnika, 
	s.pojemnosc as pojemnosc,
	s.moc as moc, 
	s.moment as moment_obr, 
	n.typ as typ_nadwozia, 
	n.liczba_drzwi as liczba_drzwi, 
	l.typ as typ_lakieru,
	w.cena
from wersje w 
	join modele m on w.id_modelu = m.id_modelu 
	join silniki s on w.id_silnika = s.id_silnika
	join nadwozia n on w.id_nadwozia = n.id_nadwozia
	join lakiery l on w.id_lakieru = l.id_lakieru
order by id_wersji;

CREATE VIEW naprawy_view as
SELECT
	n.numer as numer,
	n.data as data,
	n.koszt as koszt,
	n.opis as opis,
	p.nazwa as placowka,
	p.adres as miejsce_naprawy,
	a.vin as vin,
	a.nr_rej as nr_rejestracyjny,
	k.nazwa as klient,
	k.telefon as telefon_klienta
FROM naprawy n
	JOIN placowki p ON n.placowka = p.id_placowki
	JOIN auta_klientow a ON n.vin = a.vin
	JOIN klienci k ON a.klient = k.id_klienta
ORDER BY n.data;

commit;
