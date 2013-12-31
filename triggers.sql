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
	konf konfiguracje%ROWTYPE;
	id_w int;
begin
	select w.id_wypos into id_w
	from wersje w join wyposazenia wyp on w.id_wypos = wyp.id_wypos
	where new.wersja = w.id_wersji;

	select * into wypos from wyposazenia
	where id_wypos = id_w;

	select * into konf from konfiguracje
	where id_konfig = new.id_konfig and id_koloru = new.id_koloru;

	if  (konf.abs is not null and wypos.abs is null) or
		(konf.esp is not null and wypos.esp is null) or
		(konf.klimatyzacja_man is not null and wypos.klimatyzacja_man is null) or
		(konf.klimatyzacja_aut is not null and wypos.klimatyzacja_aut is null) or
		(konf.airbag_kier is not null and wypos.airbag_kier is null) or
		(konf.airbag_pas is not null and wypos.airbag_pas is null) or
		(konf.airbag_bok is not null and wypos.airbag_bok is null) or
		(konf.komputer is not null and wypos.komputer is null) or
		(konf.nawigacja is not null and wypos.nawigacja is null) or
		(konf.centr_zamek is not null and wypos.centr_zamek is null) or
		(konf.alarm is not null and wypos.alarm is null) or
		(konf.alufelgi is not null and wypos.alufelgi is null) or
		(konf.ksenony is not null and wypos.ksenony is null) or
		(konf.tempomat is not null and wypos.tempomat is null) or
		(konf.el_szyby is not null and wypos.el_szyby is null) or
		(konf.el_lusterka is not null and wypos.el_lusterka is null) or
		(konf.cz_parkowania is not null and wypos.cz_parkowania is null) then
		raise exception 'Konfiguracja niezgodna z wersja wyposazenia';
	end if;

	if  (konf.abs is null and wypos.abs is not null and wypos.abs = 0) or
		(konf.esp is null and wypos.esp is not null and wypos.esp = 0) or
		(konf.klimatyzacja_man is null and wypos.klimatyzacja_man is not null and wypos.klimatyzacja_man = 0) or
		(konf.klimatyzacja_aut is null and wypos.klimatyzacja_aut is not null and wypos.klimatyzacja_aut = 0) or
		(konf.airbag_kier is null and wypos.airbag_kier is not null and wypos.airbag_kier = 0) or
		(konf.airbag_pas is null and wypos.airbag_pas is not null and wypos.airbag_pas = 0) or
		(konf.airbag_bok is null and wypos.airbag_bok is not null and wypos.airbag_bok = 0) or
		(konf.komputer is null and wypos.komputer is not null and wypos.komputer = 0) or
		(konf.nawigacja is null and wypos.nawigacja is not null and wypos.nawigacja = 0) or
		(konf.centr_zamek is null and wypos.centr_zamek is not null and wypos.centr_zamek = 0) or
		(konf.alarm is null and wypos.alarm is not null and wypos.alarm = 0) or
		(konf.alufelgi is null and wypos.alufelgi is not null and wypos.alufelgi = 0) or
		(konf.ksenony is null and wypos.ksenony is not null and wypos.ksenony = 0) or
		(konf.tempomat is null and wypos.tempomat is not null and wypos.tempomat = 0) or
		(konf.el_szyby is null and wypos.el_szyby is not null and wypos.el_szyby = 0) or
		(konf.el_lusterka is null and wypos.el_lusterka is not null and wypos.el_lusterka = 0) or
		(konf.cz_parkowania is null and wypos.cz_parkowania is not null and wypos.cz_parkowania = 0) then
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