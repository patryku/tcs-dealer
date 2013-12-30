create view kolory_view
as
select 
	l.typ as typ_lakieru, 
	k.nazwa as kolor 
from lakiery l 
	join kolory k on l.id_lakieru = k.id_lakieru
order by id_koloru;

create function kolory_view_trig() returns trigger as $$
declare 
tmp int;
begin
	select into tmp id_lakieru from lakiery where typ = new.typ_lakieru;
	if tmp is null then
		insert into lakiery (typ) values(new.typ_lakieru);
		select into tmp id_lakieru from lakiery where typ = new.typ_lakieru;
	end if;
	insert into kolory (id_lakieru, nazwa) values(tmp, new.kolor);
	return new;
end;
$$ language plpgsql;

create trigger kolory_view_trig instead of insert 
on kolory_view
for each row
execute procedure kolory_view_trig();

create view wersje_view
as
select 
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

create function wersje_view_trig() returns trigger as $$
declare 
tmp1 int;
tmp2 int;
tmp3 int;
tmp4 int;
begin
	select into tmp1 id_modelu from modele where producent = new.producent and nazwa = new.model;
	if tmp1 is null then
		raise notice 'Nie znaleziono takiego modelu samochodu';
		return new;
	end if;
	
	select into tmp2 id_silnika from silniki where typ = new.typ_silnika and pojemnosc = new.pojemnosc and moc = new.moc and moment = new.moment_obr;
	if tmp2 is null then
		insert into silniki (typ, pojemnosc, moc, moment) values(new.typ_silnika, new.pojemnosc, new.moc, new.moment_obr);
		select into tmp2 id_silnika from silniki where typ = new.typ_silnika and pojemnosc = new.pojemnosc and moc = new.moc and moment = new.moment_obr;
	end if;
	
	select into tmp3 id_nadwozia from nadwozia where typ = new.typ_nadwozia and liczba_drzwi = new.liczba_drzwi;
	if tmp3 is null then
		insert into nadwozia (typ, liczba_drzwi) values(new.typ_nadwozia, new.liczba_drzwi);
		select into tmp3 id_nadwozia from nadwozia where typ = new.typ_nadwozia and liczba_drzwi = new.liczba_drzwi;
	end if;
	
	select into tmp4 id_lakieru from lakiery where typ = new.typ_lakieru;
	if tmp4 is null then
		insert into lakiery (typ) values(new.typ_lakieru);
		select into tmp4 id_lakieru from lakiery where typ = new.typ_lakieru;
	end if;
	
	insert into wersje (id_modelu, id_silnika, id_nadwozia, id_lakieru, cena, nazwa_wersji) values(tmp1, tmp2, tmp3, tmp4, new.cena, new.wersja);
	return new;
end;
$$ language plpgsql;

create trigger wersje_view_trig instead of insert 
on wersje_view
for each row
execute procedure wersje_view_trig();

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