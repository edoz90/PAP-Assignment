# Assignment #5 

Primi esercizi su interazione e coordinazione di processi

## Min distance from centroid

Data una collezione di punti `P` in uno spazio bi-dimensionale, si vuole determinare e stampare il punto `Pi` della collezione che ha distanza euclidea minima dal centroide della collezione, utilizzando un programma multi-threaded che (1) massimizzi le performance, (2) utilizzi solo meccanismi Java nativi per mutua esclusione/sincronizzazione.

Per testare il programma (in termini di performance), definire una collezione di punti di dimensione opportuna (con punti definiti, ad esempio, in modo casuale) e quindi verificare che  il programma sfrutti nel miglior modo possibile tutti i processori presenti nel sistema HW in cui viene lanciato.

Confrontare la soluzione con una versione basata su stream Java (sequenziali).

## Cooperative Team (Semafori)

Un team di 5 worker (w1..w5) lavorano concorrentemente condividendo 3 contatori (c1..c3)  di classe `UnsafeCounter` classe (non thread-safe). In particolare:

* W1 e W2 hanno il compito di incrementare rispettivamente c1 e c2, concorrentemente e ripetutamente.
* W3 ha il compito di stampare il valore di c1 ogni volta che viene aggiornato da W1 e quindi di incrementare c3.
* Analogamente W4 ha il compito di stampare il valore di c2 ogni volta che viene aggiornato da W2 e quindi di incrementare c3.
* Infine W5 ha il compito di stampare il valore di c3 solo dopo che è stato incrementato sia da W3 che W4.
* W1 e W2 possono procedere ad un nuovo incremento solo dopo che W5 ha stampato il valore di c3.

Implementare il programma multi-threaded in Java usando semafori come unico meccanismo di coordinazione.

## Capture the Flag (Monitor)

Si vuole implementare un gioco caratterizzato dai seguenti elementi:

* N player, 1 Arbiter - componenti attivi
* 1 monitor Flag, 1 monitor Sync

N è un parametro del programma.

Il monitor `Flag` che rappresenta una bandierina, che può essere in 2 stati: alzata o abbassata.
Ha come interfaccia tre metodi:

- `void setHigh()`: cambia lo stato in alzata
- `void setLow()`: cambia lo stato in abbassata
- `boolean capture()`: restituisce true se la bandiera è alzata, false altrimenti

Il thread `Arbiter` periodicamente (con periodo casuale) interagisce con il monitor Flag cambiandone lo stato, tenendola alzata per un certo numero di millisecondi (casuale).

I componenti attivi `Player` a turno cercano di catturare la flag chiamando il metodo capture. La coordinazione fra i player - tale per cui si passano il turno - deve essere realizzata implementando un opportuno monitor `Sync`, usato dai `Player`.

Il monitor `Sync` deve avere la seguente interfaccia:

- `void waitForTurn(int turn)`: chiamata dal player i-esimo, sospende il player fin quando non è il suo turno
- `void next()`: cede il turno al player successivo

Se un `Player` in un turno riesce a catturare la Flag, stampa: `WON!` e il suo nome, altrimenti passa il turno. In tal caso, gli altri player devono stampare `SOB` e il gioco termina. 

## Prog funzionale in Haskell

Implementare una funzione Haskell print_nodes_at_dist che, dato un albero binario t di interi e un valore intero d, stampa gli  elementi dell'albero la cui distanza dal nodo radice è pari a d.
