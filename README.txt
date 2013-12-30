Projekt samochodowej sieci dealerskiej. Celem jest zapewnienie następujących funkcjonalności:
- konfiguracja wybranego modelu przez klienta
- wyszukiwanie wśród gotowych egzemplarzy samochodu o konkretnych własnościach
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
6. Wersje - konkretna charakterystyka danego pojazdu tj. kombinacja następujących informacji: model, nadwozie, silnik, lakier. Każda wersja posiada cenę bazową i opcjonalną nazwę. Konieczność podziału lakierów i kolorów na dwie tabele wynika właśnie stąd, że cena wersji zależy jedynie od rodzaju lakieru, a nie od konkretnego koloru.
7. Wyposażenia - każda wersja może mieć kilka poziomów wyposażenia. Każdy atrybut jest typem numeric - 0 oznacza wyposażenie standardowe, wartość większa od 0 oznacza wyposażenie dodatkowe i jednocześnie jego koszt, null oznacza brak możliwości dokupienia danego elementu.
8. Konfiguracje - służy do zapisywania konkretnych konfiguracji danego egzemplarza. Identyczne kolumny jak w tabeli wyposażenia, lecz wszystkie atrybuty są typu boolean, gdyż dla konkretnego egzemplarza potrzebujemy wiedzieć jakie elementy wyposażenia dodatkowego zostały w nim zainstalowane. True - oznacza zainstalowane wyposażenie dodatkowe, false - standardowe, null - brak w wyposażeniu. Każda konfiguracja zapisuje również kolor lakieru, gdyż odnosi się do konkretnego pojazdu.
9. Auta_na_sprzedaz - kazdy rekord oznacza konkretny, wyprodukowany egzemplarz samochodu. Charakteryzuje go konkretna wersja i konfiguracja oraz placówka, w której jest sprzedawany. Każdy samochód posiada unikalny numer VIN oraz informacje o roku produkcji i aktualnym przebiegu.
10. Klienci - tabela zawierająca informacje o klientach.
11. Auta_klientow - tabela sprzedanych samochodów. Zawiera takie same atrybuty jak tabela auta_na_sprzedaz (tutaj placowka oznacza punkt sprzedaży, w którym sprzedano pojazd) oraz dodatkowo numer rejestracyjny samochodu, datę i cenę zakupu, jak również klienta, który zakupił pojazd.
12. Placowki - tabela zawierająca dane o wszystkich punktach sprzedaży i naprawy samochodów.
13. Naprawy - historia przeprowadzonych napraw. Każda naprawa związana jest z konkretnym pojazdem klienta oraz placówką, w której była wykonywana.