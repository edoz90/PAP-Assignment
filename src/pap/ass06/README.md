# Assignment #6 - 20150513

Esercizio su Task & Executor + GUI

Implementare una versione concorrente del gioco “The Game of Life” usando il framework Executor.

Il programma consiste nel calcolare e visualizzare l’evoluzione della matrice di celle che caratterizza il gioco, come sequenza di fotogrammi (ognuno dei quali rappresenta lo stato del mondo).  

Nella matrice, ogni cella può essere in uno di due stati possibili, live e dead.  Dato lo stato s(t) della matrice, lo stato s(t+1) si computa con le seguenti regole:

    una cella m[i,j] che nello stato s(t) è live e ha zero o al più una cella vicina live (e le altre dead), nello stato s(t+1) diventa dead (“muore di solitudine”)
    una cella m[i,j] che nello stato s(t) è live e ha quattro o più celle vicine live, nello stato s(t+1) diventa dead (“muore di sovrappopolamento”)
    una cella m[i,j] che nello stato s(t) è live e ha due o tre celle vicine live, nello stato s(t+1) rimane live (“sopravvive”)
    una cella m[i,j] che nello stato s(t) è dead e ha tre celle vicine live, nello stato s(t+1) diventa live 

Il gioco deve presentare una interfaccia grafica con pulsanti “start” e “stop” con cui si fa partire e si ferma il gioco. Ogni stato del gioco deve essere visualizzato, insieme al numero di celle nello stato “live”.
