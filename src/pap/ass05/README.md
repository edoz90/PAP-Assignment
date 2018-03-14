# Assignment #5 

Primi esercizi su interazione e coordinazione di processi

1. Min distance from centroid

Data una collezione di punti P in uno spazio bi-dimensionale, si vuole determinare e stampare il punto Pi della collezione che ha distanza euclidea minima dal centroide della collezione, utilizzando un programma multi-threaded che (1) massimizzi le performance, (2) utilizzi solo meccanismi Java nativi per mutua esclusione/sincronizzazione.

Per testare il programma (in termini di performance), definire una collezione di punti di dimensione opportuna (con punti definiti, ad esempio, in modo casuale) e quindi verificare che  il programma sfrutti nel miglior modo possibile tutti i processori presenti nel sistema HW in cui viene lanciato.

Confrontare la soluzione con una versione basata su stream Java (sequenziali).

2. Workflow

Un team di 6 worker thread (w1..w6) lavorano concorrentemente condividendo 4 contatori (c1..c4)  di classe UnsafeCounter classe (non thread-safe). In particolare:

    W1 ha il compito di incrementare c1 ripetutamente.
    W2 e W3 hanno il compito di incrementare rispettivamente  il valore di c2 e di c3 ogni volta che c1 è incrementato da W1
    W4 e W5 hanno il compito di stampare rispettivamente il valore di c2 e di c3 ogni volta che i contatori vengono aggiornati e quindi di incrementare c4, condiviso.
    Infine W6 ha il compito di stampare il valore di c4 solo dopo che è stato incrementato sia da W4 sia da W5.
    W1 può procedere ad un nuovo incremento solo dopo che W6 ha stampato il valore di c4.

Implementare il programma multi-threaded in Java usando semafori come unico meccanismo di coordinazione.
