# Project Food
## Проект по предметите Веб програмирање/Веб базирани системи при Факултет за информатички науки и компјутерско инженерство
Член: Бојан Димоски, 161060

Член: Елена Митревска, 161091

## Краток опис

Целта на проектот е да се креира веб сајт каде ќе имаме пристап до податоци за храна, состојки, хранливи материи, како и текови на отпад 
произведени од нив. Локалните податоци се поврзани со податоци од различни надворешни извори/бази, каде можеме да продолжиме со 
истражување. Сајтот има интерфејси за мапирање на податоците со надворешни линкови, каде можеме да ги верифицираме мапирањата 
или пак да додадеме нови мапирања за одредени ставки. Исто така можеме да поставуваме и SPARQL прашалници врз нашата база на податоци.

## Упатство за стартување

За да функционираат сите функционалности на апликацијата, потребна е интернет конекција.

### База на податоци (jdbc:postgresql://localhost:54320/pfood)

За подигање на базата на податоци потребно е да имате Docker. PostgreSQL container-от го стартуваме користејќи ги 
конфигурациите од docker-compose.yml со командата: 
```bash
docker-compose up -d
```
Потоа ја пополнуваме базата со потребните податоци, користејќи ја скриптата restore.sh:
```bash
./restore.sh
```

### Back-end (http://localhost:8080/)

Back-end апликацијата е Spring Boot апликација која можеме да ја стартуваме откако ќе ги импортираме потребните зависности за проектот.
Бидејќи користиме Database-first приод, пожелно е и да поставиме Data Source за проектот каде се поврзуваме кон PostgreSQL базата
користејќи ги конфигурациите зададени во docker-compose.yml (user, password, db, port).

### Front-end (http://localhost:3000/)

Front-end апликацијата е ReactJs апликација која можеме да ја стартуваме користејќи ја командата:
```bash
npm start
```
, откако ќе ги превземеме 
потребните зависности со командата:
```bash
npm install
```

### D2R Server (http://localhost:2020/)

За да го стартуваме D2R серверот ја внесуваме следната команда:
```bash
d2r-server mapping.ttl
```
Ја користиме mapping.ttl датотеката која ја мапира шемата на нашата база на податоци во RDF.

Доколку имаме промени на шемата тогаш можеме да изгенерираме нова мапинг датотека користејќи ја командата:
```bash
generate-mapping -o mapping.ttl -d org.postgresql.Driver -u postgres -p secret123 jdbc:postgresql://localhost:54320/pfood
```

SPARQL endpoint е достапен на http://localhost:2020/sparql.

