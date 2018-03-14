# Assignment #7 - 20150521

Esercizio su Attori - “Guess the Number (again)”

Implementare il gioco “Guess the Number”  utilizzando il modello degli attori e la tecnologia actor-based Akka.

Il gioco consiste in N attori Player (N parametro di ingresso dell’applicazione) che devono indovinare un numero estratto a caso da un altro attore, denominato Oracolo.

I player a turno devono tentare di indovinare il numero inviando all’Oracolo il proprio guess. Nel caso in cui il guess corrisponda al  numero cercato, allora l’Oracolo deve comunicare al player che ha inviato il guess che è il vincitore e a tutti gli altri che hanno perso. Il vincitore deve quindi scrivere in output “won!” e gli altri “sob!”. Nel caso in cui il guess non corrisponda al numero cercato, allora l’Oracolo deve comunicare al player che ha inviato il guess un suggerimento, relativo al fatto che il numero specificato sia più grande o più piccolo del numero cercato.

Ad ogni turno deve essere stampato in output il numero del turno. Il gioco termina quando si trova un vincitore.
