# lukuvinkkikirjasto

![GitHub Actions](https://github.com/ConcernedHobbit/lukuvinkkikirjasto/actions/workflows/gradle.yml/badge.svg)
[![codecov](https://codecov.io/gh/ConcernedHobbit/lukuvinkkikirjasto/branch/main/graph/badge.svg?token=9SrEY3LhzC)](https://codecov.io/gh/ConcernedHobbit/lukuvinkkikirjasto)

## Kehittäminen ja suorittaminen

### Vaatimuksina Java 11+ ja PostgreSQL

1. Kloonaa repositorio  
   `git clone https://github.com/ConcernedHobbit/lukuvinkkikirjasto.git`

2. Mene kansioon  
   `cd lukuvinkkikirjasto`

3. Luodaan tiedosto `.env` ja lisätään sinne ympäristömuuttujat  

```
URL=tietokannan osoite (esim: URL=jdbc:postgresql://localhost:5432/lukuvinkkikirjasto)
USER=psql käyttäjä
PW=käyttäjän salasana
```

4. Alustetaan tietokantaan tarvittavat taulut  
   `psql < schema.sql`

5. Rakenna projekti Gradlella  
   Linux: `./gradlew build`   
   Windows: `gradlew.bat build` (tai PowerShellissä `.\gradlew build`)

*Seuraavissa komennoissa (gradle) on riippuvainen käyttöjärjestelmästä, kts. kohta 3*

**Suorittaminen**: `(gradle) run`

**Testaaminen**: `(gradle) test`  
Testiraportti muodostuu polkuun `build/reports/test`

**Testikattavuus**: `(gradle) test jacocoTestReport`  
Testikattavuusraportti muodostuu polkuun `build/reports/jacoco/test`

**Rakentaminen**: `(gradle) fatJar`  
Rakennettu .jar muodostuu polkuun `build/libs/lukuvinkkikirjasto.jar`

## Käyttöohje tekstikäyttöliittymään

[Käyttöohje](https://github.com/ConcernedHobbit/lukuvinkkikirjasto/blob/main/doc/Usermanual.md)

## Hallinnolliset asiat

- [Definition of Done](/doc/DefOfDone.md)
- [Product backlog](https://github.com/ConcernedHobbit/lukuvinkkikirjasto/projects/1)
- [Sprint1 backlog](https://github.com/ConcernedHobbit/lukuvinkkikirjasto/projects/2)
- [Sprint2 backlog](https://github.com/ConcernedHobbit/lukuvinkkikirjasto/projects/3)
- [Burndown](https://github.com/ConcernedHobbit/github-projects-burndown-chart/blob/main/src/github_projects_burndown_chart/charts/lukuvinkkikirjasto-latest.png)
