# Assignment #8 - 20150528

L'assignment consiste in 2 esercizi
    
    1. un esercizio su Reactive Programming in RxJava - “Temperature Monitoring”
    1. un esercizio su attori - “Game of (Actor) Life”

## Esercizio su Reactive Programming in RxJava - “Temperature Monitoring”

Nel package pap.ass08 è data una classe TempSensor che rappresenta un sensore  di temperatura.

A partire dal sensore, definire un observable TempStream  che rappresenti uno stream asincrono di valori di temperatura, generando i valori (letture) ad intervalli regolari, con una certa frequenza (specificabile via costruttore).

Si suppone di voler costruire un'applicazione di monitoraggio della temperatura di un certo ambiente  in cui si utilizzano tre sensori.

Dati tre TempStream corrispondenti ai tre sensori, l'applicazione deve creare un ulteriore observable AverageTempStream che contiene il valore medio dei valori generati dai singoli stream, opportunamente filtrati in modo da evitare di considerare degli spike, ovvero letture errate. Una lettura è ritenuta errata se si discosta rispetto alla precedente per più di un valore MaxVariation.

In una finestra grafica devono essere visualizzati man mano i valori prodotti dallo stream AverageTempStream, nonché il valore massimo e minimo prodotti fino a quel momento.

La finestra deve avere anche due pulsanti, start e stop, con cui far partire e fermare il monitoraggio.

Qualora il valore di temperatura medio si maggiore di una certa soglia - specificabile e modificabile  dalla GUI - per più di un certo tempo in millisecondi - specificabile e modificabile via GUI - allora deve essere visualizzato (sempre nella finestra) un messaggio di allarme.

NOTE

    In pap.ass08 è presente un semplice test (TestSensor) che crea un sensore e ne legge i valori.

    per capire come combinare insieme gli stream ai fini del problema, è utile consultare l'elenco degli operatori descritto in:

 https://github.com/ReactiveX/RxJava/wiki/Combining-Observables

## Esercizio su Attori - “Game of (Actor) Life”

Implementare il gioco “Game of Life”  discusso in un compito precedente utilizzando il modello degli attori e la tecnologia actor-based Akka.
