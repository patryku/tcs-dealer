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