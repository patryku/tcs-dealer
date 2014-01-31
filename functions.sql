begin;

create or replace function cena_fabryczna(id char(17)) returns numeric as $$
declare
	cena_baz numeric;
	cena_opcji numeric := 0;
	sam record;
	wypos wyposazenia%ROWTYPE;
begin
	select * into sam from auta_na_sprzedaz where id = vin;
	if not found then
		select * into sam from auta_klientow where id = vin;
		if not found then raise exception 'Nieprawidlowy vin'; end if;
	end if;

	select cena into cena_baz from wersje where id_wersji = sam.wersja;

	for wypos in select * from wyposazenia
		where id_wersji = sam.wersja and cena > 0
		and nazwa = any(select k.nazwa from konfiguracje k where k.vin = sam.vin)
		loop
			cena_opcji = cena_opcji + wypos.cena;
		end loop;

	return cena_baz + cena_opcji;
end
$$ language plpgsql;

commit;
