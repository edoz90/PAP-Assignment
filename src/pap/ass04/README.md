# Assignment #4 - primi esercizi sul multi-threading

NOTE:

    Il materiale Java a cui si fa riferimento nei seguenti esercizi è presente nel package pap.ass04, disponibile fra i sorgenti del corso.
    In entrambi gli esercizi, utilizzare il supporto alla programmazione funzionale in Java e le relative librerie, ogni volta lo si ritiene opportuno

1. BruteForce

E' data la classe SecureSystem nel package pap.ass04 che rappresenta un sistema protetto da password. La password è data da una stringa composta da un certo numero di caratteri stampabili. Nel codice ASCII, i caratteri stampabili vanno dal codice 32 al codice 127 - vedere https://en.wikipedia.org/wiki/ASCII).

Un oggetto di tipo SecureSystem è creato specificando il numero di caratteri di cui deve essere composta la password. La classe fornisce il metodo:

boolean login(String passwd)

con cui si simula il login nel sistema. Il metodo restituisce true se il login ha avuto successo, ovvero la password specificata coincide con quella richiesta.

Scrivere un programma multi-threaded BruteForce che, dato un numero N  fornito come parametro del programma, crea un oggetto SecureSystem protetto da una password di N caratteri e quindi esegue il login nel minor tempo possibile, stampando in uscita la password trovata.

1. Bouncing Words

È data la classe di utilità TextLibFactory in pap.ass04 che fornisce un'istanza di un oggetto di tipo TextLib. Questo oggetto fornisce funzionalità per scrivere sulla schermo della console (vedere il test TextLibTest).

Scrivere un programma multi-threaded BouncingWords che, fornito come argomento del programma una lista di parole w1 w2 ... wN, realizza un'animazione su console nella quale le parole specificate si muovono autonomamente sullo schermo (il tipo di movimento è a discrezione dello studente) ognuna pilotata da un thread opportuno.

NOTE

    per funzionare correttamente, il programma deve essere lanciato da una console.

    (Sistemi Windows) Il terminale di base di Windows (CMD.exe) potrebbe non supportare le sequenze ANSI di ESCAPE utilizzate nella libreria. E' possibile aggirare il problema non usando CMD ma un qualsiasi altro terminale che supporti le sequenze. Un esempio è ANSICON: http://adoxa.altervista.org/ansicon/

Lanciando Java da questo terminale, la libreria funziona, anche dai computer/VM del laboratorio. Quindi per usare la libreria è necessario: (1) scaricare il programma; (2) mandare in esecuzione ansicon.exe che si trova nella directory x64. Questo ha come effetto aprire un terminale; (3) posizionarsi nell'opportuna directory e lanciare il programma Java dal terminale.
