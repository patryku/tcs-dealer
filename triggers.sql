CREATE OR REPLACE FUNCTION rok_produkcji_trig() RETURNS trigger AS $$
DECLARE
	prod_od date;
	prod_do date;
BEGIN
	SELECT m.produkowany_od INTO prod_od
	FROM wersje w JOIN modele m ON w.id_modelu = m.id_modelu
	WHERE NEW.wersja = w.id_wersji;

	SELECT m.produkowany_do INTO prod_do
	FROM wersje w JOIN modele m ON w.id_modelu = m.id_modelu
	WHERE NEW.wersja = w.id_wersji;

	IF NEW.rok_produkcji < prod_od THEN
		RAISE EXCEPTION 'Nieprawidlowy rok produkcji';
	ELSEIF prod_do IS NOT NULL AND prod_do < NEW.rok_produkcji THEN
		RAISE EXCEPTION 'Nieprawidlowy rok produkcji';
	END IF;
	RETURN NEW;
END
$$ LANGUAGE plpgsql;

CREATE TRIGGER auta_na_sprzedaz_trig BEFORE INSERT OR UPDATE OF rok_produkcji ON auta_na_sprzedaz
FOR EACH ROW EXECUTE PROCEDURE rok_produkcji_trig();

CREATE TRIGGER auta_klientow_trig BEFORE INSERT OR UPDATE OF rok_produkcji ON auta_klientow
FOR EACH ROW EXECUTE PROCEDURE rok_produkcji_trig();

--------------------------------------------------------------------

create function wersje_view_trig() returns trigger as $$
declare 
tmp1 int;
tmp2 int;
tmp3 int;
tmp4 int;
tmp5 int;
begin
	select into tmp1 id_modelu from modele where producent = new.producent and nazwa = new.model;
	if tmp1 is null then
		raise notice 'Nie znaleziono takiego modelu samochodu';
		return new;
	end if;
	
	select into tmp5 id_wypos from wyposazenia where id_wypos = new.id_wypos;
	if tmp5 is null then
		raise notice 'Nie znaleziono takiego id wyposazenia';
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
	
	insert into wersje (id_modelu, id_silnika, id_nadwozia, id_lakieru, id_wypos, cena, nazwa_wersji) values(tmp1, tmp2, tmp3, tmp4, tmp5, new.cena, new.wersja);
	return new;
end;
$$ language plpgsql;

create trigger wersje_view_trig instead of insert 
on wersje_view
for each row
execute procedure wersje_view_trig();

---------------------------------------------------------------

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

----------------------------------------------------------------

create function konfig_wypos_trig() returns trigger as $$
declare
	wypos wyposazenia%ROWTYPE;
	id_w int;
begin
	select w.id_wypos into id_w
	from wersje w join wyposazenia wyp on w.id_wypos = wyp.id_wypos
	where new.wersja = w.id_wersji;

	select * into wypos from wyposazenia
	where id_wypos = id_w;

	IF  NEW.abs is not null and wypos.abs is null or
		NEW.esp is not null and wypos.esp is null or
		NEW.klimatyzacja_man is not null and wypos.klimatyzacja_man is null or
		NEW.klimatyzacja_aut is not null and wypos.klimatyzacja_aut is null or
		NEW.airbag_kier is not null and wypos.airbag_kier is null or
		NEW.airbag_pas is not null and wypos.airbag_pas is null or
		NEW.airbag_bok is not null and wypos.airbag_bok is null or
		NEW.komputer is not null and wypos.komputer is null or
		NEW.nawigacja is not null and wypos.nawigacja is null or
		NEW.centr_zamek is not null and wypos.centr_zamek is null or
		NEW.alarm is not null and wypos.alarm is null or
		NEW.alufelgi is not null and wypos.alufelgi is null or
		NEW.ksenony is not null and wypos.ksenony is null or
		NEW.tempomat is not null and wypos.tempomat is null or
		NEW.el_szyby is not null and wypos.el_szyby is null or
		NEW.el_lusterka is not null and wypos.el_lusterka is null or
		NEW.cz_parkowania is not null and wypos.cz_parkowania is null then
		raise exception 'Konfiguracja niezgodna z wersja wyposazenia';
	end if;

	return new;
end
$$ language plpgsql;

create trigger konfig_wypos_trig1 before insert or update on auta_na_sprzedaz
for each row execute procedure konfig_wypos_trig();

create trigger konfig_wypos_trig2 before insert or update on auta_klientow
for each row execute procedure konfig_wypos_trig();
----------------------------------------------------------------