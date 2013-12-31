create or replace function cena_fabryczna(id char(17)) returns numeric as $$
declare
	cena_baz numeric;
	cena_opcji numeric := 0;
	id_wyp int;
	sam auta_na_sprzedaz%ROWTYPE;
	wypos wyposazenia%ROWTYPE;
	konf konfiguracje%ROWTYPE;
begin
	select * into sam from auta_na_sprzedaz where id = vin;
	if not found then
		raise exception 'Nieprawidlowy vin';
	end if;

	select cena into cena_baz from wersje where id_wersji = sam.wersja;

	select w.id_wypos into id_wyp
	from wersje w join wyposazenia wyp on w.id_wypos = wyp.id_wypos
	where sam.wersja = w.id_wersji;

	select * into wypos from wyposazenia
	where id_wypos = id_wyp;

	select * into konf from konfiguracje
	where id_konfig = sam.id_konfig and id_koloru = sam.id_koloru;

	if konf.abs is not null then
		cena_opcji = cena_opcji + wypos.abs;
	end if;
	if konf.esp is not null then
		cena_opcji = cena_opcji + wypos.esp;
	end if;
	if konf.klimatyzacja_man is not null then
		cena_opcji = cena_opcji + wypos.klimatyzacja_man;
	end if;
	if konf.klimatyzacja_aut is not null then
		cena_opcji = cena_opcji + wypos.klimatyzacja_aut;
	end if;
	if konf.airbag_kier is not null then
		cena_opcji = cena_opcji + wypos.airbag_kier;
	end if;
	if konf.airbag_pas is not null then
		cena_opcji = cena_opcji + wypos.airbag_pas;
	end if;
	if konf.airbag_bok is not null then
		cena_opcji = cena_opcji + wypos.airbag_bok;
	end if;
	if konf.komputer is not null then
		cena_opcji = cena_opcji + wypos.komputer;
	end if;
	if konf.nawigacja is not null then
		cena_opcji = cena_opcji + wypos.nawigacja;
	end if;
	if konf.centr_zamek is not null then
		cena_opcji = cena_opcji + wypos.centr_zamek;
	end if;
	if konf.alarm is not null then
		cena_opcji = cena_opcji + wypos.alarm;
	end if;
	if konf.alufelgi is not null then
		cena_opcji = cena_opcji + wypos.alufelgi;
	end if;
	if konf.ksenony is not null then
		cena_opcji = cena_opcji + wypos.ksenony;
	end if;
	if konf.tempomat is not null then
		cena_opcji = cena_opcji + wypos.tempomat;
	end if;
	if konf.el_szyby is not null then
		cena_opcji = cena_opcji + wypos.el_szyby;
	end if;
	if konf.el_lusterka is not null then
		cena_opcji = cena_opcji + wypos.el_lusterka;
	end if;
	if konf.cz_parkowania is not null then
		cena_opcji = cena_opcji + wypos.cz_parkowania;
	end if;

	return cena_baz + cena_opcji;
end
$$ language plpgsql;