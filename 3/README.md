## Scenariusze testowe Selenium

Wymagania/środowisko testowe

1. Python 3.9.4
2. Biblioteki: unittest, selenium (zainstalować za pomocą pip3 install <nazwa_biblioteki>)
3. Przeglądarki Chrome w wersji 107 oraz Firefox w wersji 86.0
4. Zainstalowane sterowniki Selenium dla przeglądarek Firefox, Chrome w wersjach jak wyżej - dostępne [tutaj](https://chromedriver.chromium.org/downloads) oraz [tutaj](https://github.com/mozilla/geckodriver/releases) 


Pepper

    1. Wejść na stronę https://www.pepper.pl/
    2. W wyszukiwarce wpisać frazę "apple"
    3. Wybrać kategorię "Apple"
    4. Z listy promocji wybieramy zakładkę "gorące"
    5. Otwieramy pierwszą promocję z listy. 

DobreZiele

    1. Wejść na stronę https://dobreziele.pl
    2. Wpisać w wyszukiwarkę wrazę "aguamate"
    3. Kliknąć na znaleziony produkt - aguamate 500g
    4. Dodać produkt do koszyka
    5. Poczekać, aż zniknie wiadomość o dodaniu produktu do koszyka
    6. Kliknąć ikonkę koszyka
    7. Wprowadzić kod rabatowy "newsletter-447d13f5"
    8. Kliknąć przycisk "sprawdź rabat"
    9. Zweryfikować, czy cena produktu została obniżona o 5%