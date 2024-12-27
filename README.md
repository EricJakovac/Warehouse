Tema: Sustav za upravljanje skladištem "ERIC JAKOVAC i MONIKA LAKIĆ"

1. Faza: Dodavanje i praćenje robe

Opis funkcionalnosti:
Korisnici mogu unositi nove proizvode u jedno ili više skladišta, ažurirati količinu proizvoda (ulaz ili izlaz robe) te pregledavati stanje zaliha i popis robe za odabrano skladište.

Sustavi:

Web poslužitelj: Spring Boot za backend aplikaciju s REST API-jem, Vue.js za frontend aplikaciju (odabir skladišta (prije unosa ili pregleda), unos podataka, pregled i filtriranje).

Relacijska baza podataka: PostgreSQL za pohranu podataka o proizvodima, strukturirana na način da svaki proizvod ima atribut ID skladišta kao vanjski ključ (FK) povezan s tablicom škladišta.


2. Faza: Automatizacija narudžbi robe

Opis funkcionalnosti:
Sustav automatski generira narudžbe kada količina robe u određenom skladištu padne ispod minimalne zalihe. Korisnici mogu pregledavati status narudžbi i upravljati njima (potvrda, otkazivanje, praćenje). Implementirati će se jedna od mogućnosti za detekciju niske zalihe (indeksiranje u PostgreSQL umjesto Redisa), uz opis prednosti i nedostataka alternativnih rješenja u izvještaju.

Sustavi:

Web poslužitelj: Dodatni API endpointovi za generiranje narudžbi kada količina robe padne ispod minimalne, pregled i upravljanje narudžbama (statusi: generirano, potvrđeno, otkazano). Dodavanje funkcionalnosti za pregled i upravljanje narudžbama na frontendu. Pregled otvorenih narudžbi za odabrano skladište, mogućnost potvrde ili otkazivanja narudžbi, pregled povijesti narudžbi po skladištu ili proizvodu.

Relacijska baza podataka: Postojeće tablice za proizvod proširena s indeksom za brzo dohvaćanje robe s niskom zalihom. Nova tablica narudžbe s informacijama o narudžbama i njihovim statusima.

3. Faza: Analiza i izvještavanje

Opis funkcionalnosti:
Sustav generira dnevne izvještaje o prometu robe (ukupni ulaz, izlaz, najčešće naručivani proizvodi) i pruža prognozu kada će određeni proizvod dostići minimalnu zalihu na temelju povijesnih podataka. Fokus će biti na pouzdanosti (praćenje koji su procesi završeni, mogućnost ponovnog pokretanja izračuna) i efikasnosti/skalabilnosti generiranja prognoza i izvještaja.

Sustavi:

Web poslužitelj: Novi endpointovi za generiranje izvještaja te prognoza, za dohvaćanje podataka i povijesnih zapisa o prometu robe.

Relacijska baza podataka: PostgreSQL tablica za praćenje povijesnih podataka o ulazu i izlazu robe. Indeksi za brzo dohvaćanje povijesnih podataka, omogućujući efikasne upite za prognoze i izvještaje.

Sustav za automatizaciju zadataka: Apache Airflow za automatizaciju generiranja dnevnih izvještaja. Provjerava status obrade (jesu li procesi završeni te omogućuje ponovni pokušaj generiranja u slučaju pogreške.

Vizualizacija podataka: Frontend implementiran s Chart.js za prikaz statistika (grafovi i tablice) - promet robe, najčešće naručivani proizvodi, predviđanje minimalne zalihe za određene proizvode.

4. Faza: Predikcija i AI/ML funkcionalnosti (za APVO)

Opis funkcionalnosti:
Dodavanje funkcionalnosti predviđanja budućih potreba za zalihama koristeći povijesne podatke. Implementacija modela strojnog učenja za prognozu zaliha i detekciju anomalija (npr. neočekivano velike narudžbe ili greške u inventaru).

Sustavi:

NoSQL baza podataka: MonogDB za fleksibilnu pohranu podataka potrebnih za treniranje i predikciju modela.

Strojno učenje: Python biblioteke poput Scikit-learn ili TensorFlow za treniranje i primjenu prediktivnih modela.

Integracija sa backendom: Proširenje API-ja za dohvat prediktivnih podataka.
