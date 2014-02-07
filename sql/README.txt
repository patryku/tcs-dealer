Projekt samochodowej sieci dealerskiej. Celem jest zapewnienie następujących funkcjonalności:
- konfiguracja wybranego modelu przez klienta
- wyszukiwanie wśród samochodów modeli o konkretnych własnościach
- utrzymywanie informacji o klientach, sprzedanych samochodach i wykonanych naprawach
- administracja po stronie sieci dealerskiej: dodawanie nowych placówek, modeli, silnikow, przenoszenie samochodu do innej placówki, zmiana cen, itp.


W pliku tcs-dealer.png znajduje się diagram ER bazy danych.
Uwagi do diagramu:

1. # oznacza klucz podstawowy encji, * to wartość obowiązkowa, o - opcjonalna. Związki oznaczone poprzeczną kreską wchodzą w skład identyfikatora encji.
2. Podencje w praktyce oznaczają osobne tabele, na diagramie są oznaczone w ten sposób, aby nie zaciemniać obrazu.


Charakterystyka poszczególnych tabel:

1. Silniki - tabela ze wszystkimi dostępnymi silnikami i ich parametrami.
2. Nadwozia - zawiera rekordy określające rodzaj nadwozia (np. sedan) i liczbę drzwi.
3. Modele - wszystkie modele dostępne w ofercie, jeśli atrybut produkowany_do jest nullem, to model ciągle jest produkowany.
4. Lakiery - zawiera wszystkie dostępne typy lakierów np. metaliczny, matowy itp.
5. Kolory - wszystkie dostępne kombinacje (lakier, kolor) np. metaliczny, czerwony.
6. Wersje - konkretna charakterystyka danego pojazdu tj. kombinacja następujących informacji: model, nadwozie, silnik, lakier i wyposażenie. Każda wersja posiada cenę bazową i opcjonalną nazwę. Konieczność podziału lakierów i kolorów na dwie tabele wynika właśnie stąd, że cena wersji zależy jedynie od rodzaju lakieru, a nie od konkretnego koloru.
7. Wyposażenia - każda krotka odpowiada jednej, konkretnej wersji i opisuje jeden element wyposażenia (nazwa, cena). Cena równa 0 oznacza wyposażenie standardowe, wartość większa od 0 oznacza wyposażenie dodatkowe i jednocześnie jego koszt.
8. Konfiguracje - służy do zapisywania konkretnych konfiguracji danego egzemplarza. Dla każdego pojazdu identyfikowanego przez vin, zapisujemy elementy wyposażenia dodatkowego, które są w nim zainstalowane (jedna krotka - jeden element).
9. Auta_na_sprzedaz - kazdy rekord oznacza konkretny, wyprodukowany egzemplarz samochodu. Charakteryzuje go konkretna wersja i konfiguracja oraz placówka, w której jest sprzedawany. Każdy samochód posiada unikalny numer VIN oraz informacje o roku produkcji i aktualnym przebiegu.
10. Klienci - tabela zawierająca informacje o klientach.
11. Auta_klientow - tabela sprzedanych samochodów. Zawiera takie same atrybuty jak tabela auta_na_sprzedaz (tutaj placowka oznacza punkt sprzedaży, w którym sprzedano pojazd) oraz dodatkowo numer rejestracyjny samochodu, datę i cenę zakupu, jak również klienta, który zakupił pojazd.
12. Placowki - tabela zawierająca dane o wszystkich punktach sprzedaży i naprawy samochodów.
13. Naprawy - historia przeprowadzonych napraw. Każda naprawa związana jest z konkretnym pojazdem klienta oraz placówką, w której była wykonywana.


Instrukcja instalacji i uruchomienia aplikacji w systemie Linux:

1. Aby aplikacja działała prawidłowo, należy w PostgreSQL stworzyć użytkownika dude z hasłem 12345, a następnie stworzyć bazę o nazwie dealer:
	psql
	CREATE USER dude WITH PASSWORD '12345';
	CREATE DATABASE dealer;
	GRANT ALL PRIVILEGES ON DATABASE dealer to dude;
	\q
2. Utworzyć strukturę bazy danych za pomocą polecenia: psql -U dude -d dealer < create.sql
2a. Jeśli dalej jest problem z dostępem, należy w pliku /etc/postgresql/9.1/main/pg_hba.conf dodać linię:
	local   all             dude                                    md5
w następującym miejscu:
	# "local" is for Unix domain socket connections only
	local   all             dude                                    md5
	local   all             all                                     peer
3. Najprostszym sposobem kompilacji i uruchomienia aplikacji z kodu jest stworzenie w Eclipse nowego projektu Javy wskazując katalog dealer jako jego lokalizację:
	File->New->Java Project i w location wskazujemy ścieżkę do folderu dealer z tej paczki
4. Jeśli 2 pliki jar znajdujące się w folderze dealer/libs/ nie zostały automatycznie dołączone do projektu, należy je dodać do Java Build Path we właściwościach projektu.
5. Projekt podzielony jest na 2 moduły: kliencki i administracyjny. Aby uruchomić moduł kliencki należy odpalić metodę main z klasy ClientView znajdującej się w pakiecie view. Aby uruchumoić moduł administracyjny należy odpalić metodę main z klasy AdminTool znajdującej się również w pakiecie view.
