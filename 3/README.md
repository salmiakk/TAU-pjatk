## Scenariusze testowe Selenium

Wymagania/środowisko testowe

1. Python 3.9.4
2. Biblioteki: unittest, selenium (zainstalować za pomocą pip3 install <nazwa_biblioteki>)
3. Przeglądarki Chrome w wersji 107 oraz Firefox w wersji 86.0
4. Zainstalowane sterowniki Selenium dla przeglądarek Firefox, Chrome w wersjach jak wyżej - dostępne [tutaj](https://chromedriver.chromium.org/downloads) oraz [tutaj](https://github.com/mozilla/geckodriver/releases) 


Trójmiasto.pl - lokalizacja ulicy Cieszyńskiego

    1. Wejść na stronę https://www.trojmiasto.pl/
    2. Zaakceptować informację o użyciu cookies
    3. Kliknąć w przycisk "Wiadomości" (na skos od głównego logo trojmiasto.pl) 
    4. Wpisać w wyszukiwarkę frazę "chełm"
    5. Wcisnąć przycisk ENTER na klawiaturze
    6. Kliknąć przycisk "Chełm - serwis dzielnicowy"
    7. Kliknąć w Mapę Dzielnicy Chełm
    8. Przejść do nowootwartej zakładki w przeglądarce
    9. Wpisać w wyszukiwarkę frazę "Cieszyńskiego"
    10. Wcisnąć przycisk ENTER na klawiaturze
    11. Wybrać pierwszą wyszukaną pozycję - "ul. Władysława Cieszyńskiego"
    12. Zrobić zrzut ekranu

DobreZiele - test koszyka

    1. Wejść na stronę https://dobreziele.pl
    2. Wpisać w wyszukiwarkę wrazę "aguamate"
    3. Kliknąć na znaleziony produkt - aguamate 500g
    4. Wcisnąć przycisk ENTER na klawiaturze
    5. Dodać produkt do koszyka
    6. Poczekać, aż zniknie wiadomość o dodaniu produktu do koszyka
    7. Kliknąć ikonkę koszyka
    8. Wprowadzić kod rabatowy "newsletter-447d13f5"
    9. Kliknąć przycisk "sprawdź rabat"
    10. Zweryfikować, czy cena produktu została obniżona o 5%