# InternshipTask

Program pobierze, posortuje i wskaże nazwę ostatnio modyfikowanego repozytorium na Githubie. 
Nie ma dla niego różnicy czy to będzie repozytorium Allegro (ustawione jako domyślne), czy np. Google, itp.

Program zapisuje też wszelkie istotne dane na temat projektu np. tytuł, datę modyfikacji, opis, język programowania, liczbę gwiazdek itd.
Dane, po uruchomieniu programu, są zapisywane  w pamięci komputera, w bazie H2.
Z poziomu przeglądarki możemy je przejrzeć pod adresem: http://localhost:8092/h2/ 
Wystawiłem też proste API, dane w formacie JSON na temat pobranych projektów można podejrzeć pod:
http://localhost:8092/project/all  - dla wszystkich projektów;
http://localhost:8092/project/{id} - dla pojedynczego projektu z podaniem jego id, dla ostatnio modyfikowanego będzie to nr 1;
http://localhost:8092/project/title={title}  - dla pojedynczego projektu z podaniem jego tytułu;
http://localhost:8092/project/language={programmingLanguage} - dla listy projektów napisanych w określonym języku.

Pogram można by dalej rozbudować, dodań warstwę widoku, jakąś wyszukiwarkę, zestawienia itp.
