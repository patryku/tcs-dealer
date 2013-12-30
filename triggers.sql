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