## Automatyczne testy dla repozytorium

W katalogu .github/workflows znajduje się kod workflow dla Github Actions, zintegrowany z tym repozytorium.
Po każdym commicie w danym projekcie, automatycznie zostaną uruchomione testy, zależne od projektu w którym wykonano poprzedni commit.

UWAGA - automatyczne testy nie działają dla tego projektu 3, z uwagi na napotkany problem z konfiguracją drivera Chrome dla Selenium. 
Zakładając, że testy w tym projekcie nie obejmowałyby użycia Selenium, prawdopodobnie wykonałyby się poprawnie.