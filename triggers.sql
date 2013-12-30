CREATE OR REPLACE FUNCTION naprawy_insert() RETURNS trigger AS $$
BEGIN
	UPDATE placowki SET dochod = dochod + NEW.koszt WHERE id_placowki = NEW.placowka;
	RETURN NEW;
END
$$ LANGUAGE plpgsql;

CREATE TRIGGER naprawy_insert AFTER INSERT ON naprawy
FOR EACH ROW EXECUTE PROCEDURE naprawy_insert();


CREATE OR REPLACE FUNCTION naprawy_update() RETURNS trigger AS $$
BEGIN
	UPDATE placowki SET dochod = dochod - OLD.koszt WHERE id_placowki = OLD.placowka;
	UPDATE placowki SET dochod = dochod + NEW.koszt WHERE id_placowki = NEW.placowka;
	RETURN NEW;
END
$$ LANGUAGE plpgsql;

CREATE TRIGGER naprawy_update AFTER UPDATE OF koszt, placowka ON naprawy
FOR EACH ROW EXECUTE PROCEDURE naprawy_update();


CREATE OR REPLACE FUNCTION auta_klientow_insert() RETURNS trigger AS $$
BEGIN
	UPDATE placowki SET dochod = dochod + NEW.cena_zakupu WHERE id_placowki = NEW.placowka;
	RETURN NEW;
END
$$ LANGUAGE plpgsql;

CREATE TRIGGER auta_klientow_insert AFTER INSERT ON auta_klientow
FOR EACH ROW EXECUTE PROCEDURE auta_klientow_insert();


CREATE OR REPLACE FUNCTION auta_klientow_update() RETURNS trigger AS $$
BEGIN
	UPDATE placowki SET dochod = dochod - OLD.cena_zakupu WHERE id_placowki = OLD.placowka;
	UPDATE placowki SET dochod = dochod + NEW.cena_zakupu WHERE id_placowki = NEW.placowka;
	RETURN NEW;
END
$$ LANGUAGE plpgsql;

CREATE TRIGGER auta_klientow_update AFTER UPDATE OF cena_zakupu, placowka ON auta_klientow
FOR EACH ROW EXECUTE PROCEDURE auta_klientow_update();


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