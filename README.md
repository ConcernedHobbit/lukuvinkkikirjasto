# lukuvinkkikirjasto

![GitHub Actions](https://github.com/ConcernedHobbit/lukuvinkkikirjasto/actions/workflows/gradle.yml/badge.svg)
[![codecov](https://codecov.io/gh/ConcernedHobbit/lukuvinkkikirjasto/branch/main/graph/badge.svg?token=9SrEY3LhzC)](https://codecov.io/gh/ConcernedHobbit/lukuvinkkikirjasto)

## Kehittäminen ja suorittaminen
1. Kloonaa repositorio  
`git clone https://github.com/ConcernedHobbit/lukuvinkkikirjasto.git`

2. Mene kansioon  
`cd lukuvinkkikirjasto`

3. Rakenna projekti Gradlella  
Linux: `./gradlew build`   
Windows: `gradlew.bat build` (tai PowerShellissä `.\gradlew build`)

*Seuraavissa komennoissa (gradle) on riippuvainen käyttöjärjestelmästä, kts. kohta 3*  

**Suorittaminen**: `(gradle) run` 

**Testaaminen**: `(gradle) test`  
Testiraportti muodostuu polkuun `build/reports/test`

**Testikattavuus**: `(gradle) test jacocoTestReport`  
Testikattavuusraportti muodostuu polkuun `build/reports/jacoco/test`
