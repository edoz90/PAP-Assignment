# Definizione di paradigma di programmazione
Ci sono diverse definizioni in letteratura per "paradigma di programmazione": secondo Van Roy si tratta di un approccio alla programmazione di teorie matematiche o di un coerente insieme di principi; per wikipedia si tratta di uno stile fondamentale per la programmazione di computer, per altri serve come forma mentis per la programmazione.
Un paradigma di programmazione consiste nel descrivere come la computazione lavora e viene modellata; come un programma è strutturato e coincide con il rappresentare STRUTTURA, COMPORTAMENTE ed INTERAZIONE di un programma. 
Solitamente un paradigma definisce specifici concetti e meccanismi tramite esressioni primitive di un linguaggio e loro combinazioni rappresentando ad alto livello delle astrazioni (nomenclatura e manipolazione di unità).
Una proprietà chiave di un paradigma è la possibilità di esprimere un non-determinismo osservabile.
Un'altra proprietà chiave di un paradigma è il supporto agli stati, ovvero, l'abilità di salvare una sequenza di valori nel tempo. Questa proprietà è molto influenzata dal paradigma; infatti, nel paradigma imperativo è più forte rispetto a quello dichiarativo (funzionale e logico) e ancora in linguaggi che utilizzano message-passing e concorrenza con condivisione dello stato.

# Quali sono i principali paradigmi di programmazione e caratteristiche essenziali di ciascuno
1. **Imperativo**
    Prima fai questo e dopo quest'altro
    Descrizione della computazione tramite sequenze di statements che cambiano lo stato del programma. Ogni comando ha un suo misurabile effetto nello stato del programma che varia in base all'ordine dei comandi e nel tempo.
    Ha come modello di riferimento per la computazione la macchina di Turing e prende ispirazione dall'architettura di Von Neumann (condivisione di memoria tra programma ed istruzioni).
    Un'astrazione del paradigmi imperativo sono le 'procedure', programmazione procedulare e modulare (il passo successivo e OOP).
2. **Funzionale**
    Valuta l'espressione ed usala per qualcos'altro.
    La computazione si base sulla valutazione di espressioni che sono rappresentate da funzioni senza side effects. Infatti non ci sono stati o dati mutabili. Il paradigma ha ispirazione dalla matematica e la teoria delle funzioni e prendere come riferimento il modello del lambda calculus (Alonzo Church 1936) e il linguaggio Lisp (1958).
    L'astrazione naturale è la funzione vista come una singola espressione da valutare. Le funzioni sono visti come cittadini di prima classe e sono tipate e passate come argomento ad altre funzioni (high-order).
    Le funzioni ricoprono il ruolo primario (funzioni come valori esprimibili, lambda), la computazione è una riduzione delle expressioni definite (funzioni).
    L'evaluation delle espressioni avviene tramite *call-by-value*, *call-by-name* o *lazy*.
3. **Logico**
    Rispondi ad una domanda cercando una soluzione.
    I programmi consistono in un elenco di statements logici e l'esecuzione consiste nel cercare una prova (verifica, proof) degli statements; utili per l'estrazione di conoscenza partendo da casi base e relazioni (AI).
    Secondo Kowalski può anche essere usato come linguaggio general purpose.
    Ha origine dallo studio dell'intelligenza artificiale e prende come modello la logice al primo ordine di Socrate: esprimere enunciati e dedurre le loro conseguenze logiche in modo del tutto formale e meccanico).
    Si basa su assiomi, regole di inferenza e query. I programmi consistono solo nella logica mentre la parte di controllo viene realizzata macchina astratta che opera le ricerche in base a delle regole di inferenza.
4. **Orientato agli oggetti**
    invia messaggi tra gli oggetti per simulare una evoluzione temporale di un insieme di fenomeni basati sul mondo reale.
    Lo scambio di messaggi avviene tra oggetti computazioni self-contained con un proprio stato ed identità (incapsulamento dello stato e del comportamento).
    Gli oggetti vengono realizzati in base al concetto che hanno nel mondo reale.
    Ha ispirazione dalla teoria dei concetti e modelli dell'interazione umana con i fenomeni del mondo. Partendo dal dominio, il mondo è modellato/astratto/rappresentato: non dalla matematica nemmeno dall'hardware disponibile.
    Il primo linguaggio ad adottare questo paradigma fu Simula (1960).
    I principali fattori chiave sono: Incapsulamento, Information Hiding, Message Passing, Classi, Ereditarietà.

È possibile anche raggruppare il paradigma logico e funzionale nel paradigma **dichiarativo** che esprime la logica della computazione senza esplicitare un control flow (unnamed, deterministic, sequential).
In quanto è possibile che un paradigma non risolva tutti i problemi reali in maniera facile ed adeguata nasce l'idea di utilizzare diversi paradigma di programmazione nello stesso linguaggio tramite estensioni del linguaggio stesso o framework.

# Non-determinismo osservabile
Quando un utente può vedere risultati diversi da esecuzioni che hanno come punto iniziale la stessa configuration interna (non è desiderabile salvo quando la sua potenza espressiva è richiesta (programmazione concorrente)); un tipico effetto è la `race condition`.
Un linguaggio che può specificare, ad un certo punto del programma ("choise points"), diverse alternative di flow (contrario al classico if-then-else). La scelta è arbitraria a run time tra alcune limimtate opzioni specificate dal programmatore. Alcuni linguagggi che rispecchiano questa specifica sono: Oz, Erlang, Java ma solo con costrutti di concorrenza (ND finite state machine). Spin e Promela per varifica corretta algoritmi concorrenti.

# Creative Extension Principle
Partendo dalla tassonomia dei linguaggi classificati in base al loro core del linguaggio è possibile ordinarli in base al creative extension priciple secondo cui un nuovo concetto viene aggiunto quando non è possibile realizzarlo tramite trasformazioni locali. Due linguaggi che implementano lo stesso paradigma hanno dei flavors diversi in base a cosa è stato deciso per facilitare il programmatore.
L'utilizzo di paradigmi con concetti e meccanismi di concorrenza genera una programmazione multi-threaded (i.e. Java) mentre se si integra la concorrenza nel paradigma stesso si crea un nuovo paradigma: attori e oggetti concorrenti, Erlang: linguaggio funzionale ed attori.
Ad esempio partendo da un semplice paradigma di programmazione sequenziale e funzionale può nascere l'esigenza di modellare degli aggiornamenti in memoria, una soluzione potrebbe essere quella di realizzare le funzioni con due parametri: uno di input e uno di output. Questa soluzione risulta però essere estremamente poco maneggevole e modulare. In questo caso è opportuno introdurre il concetto di **stato**.
Una volta aggiunto lo stato è possibile che sia necessario implementare delle attività che rimangano indipendenti e, anzichè, utilizzare un nuovo stack con relativo scheduler e context switch, è possibile implementare un meccanismo di **concorrenza**. Una volta che un sistema diventa complesso è opportuno modellare delle attività di detection e correzione degli errori; come soluzione principali potrebbe essere utile aggiungere dei codice di errore per tutte le funzioni e delle routine per il relativo management, introdurre il concetto di **eccezione**.
Una volta evidenziata la necessità di nuovi costrutti nel linguaggio si torna ad avere il focus solo sulla risoluzione del problema.

# Cosa si intende per funzioni high-order e perchè sono utili
In quanto in un linguaggio funzionale le funzioni sono valori first-class queste possono essere passate come parametro ad altre funzioni, restituite come valore di ritorno di una funzione e salvate in strutture dati. Nella pratica si possono effettuare tutte le classice operazioni sui valori del paradigma imperativo.
Le funzioni high-ordere permetto al linguaggio di essere più modulare esponendo al programmatore il meccanismo per assemblare le varie componenti del programma.
Ad esempio `add2 = twice (\x -> x + 1)` dove `twice f x = f(f(x))`; il risultato di `add2 1` è `3` perchè viene applicata due volte la lambda `\x -> x + 1`.
Un altro esempio di funzioni high-order molto usate, in Haskell, sono `map`, `foldr` e `foldl` definite come:
```haskell
map :: (a->b) -> [a] -> [b]
map _ [] = []
map f (h:t) = f h : map f t

map f [x1, x2, ..., xn] == [f x1, f x2, ..., f xn]
```

```haskell
foldr :: (a->b->b) -> b -> [a] -> b
foldr f z [] = z
foldr f z (h:t) = f h (foldr f z t)

foldr f z [x1, x2, ..., xn] == x1 `f` (x2 `f` ... (xn `f` z)...)
f a1 (f a2 ( ... (f an z) ...))
```
dove prende in ingresso una funzione che opera sulla struttura dati `[a]` applicandogli l'operatore di accumulo `b` restituendo il risultato finale dell'accomulo (operator binario), il valore iniziale dell'accomulatore, la struttuda dati `[a]` e come risultato il valore della funzione applicata a tutti gli elementi di `[a]`. Right-associative: con la tail recusione e la lazy evaluation il risulato inizia ad essere computato partendo dall'ultimo elemento della lista **TIP**: lavora anche liste infinite.

```haskell
foldl :: (a->b->a) -> b -> [a] -> b
foldl f z [] = z
foldr f z (h:t) = foldl f (f z h) xs

foldl f z [x1, x2, ..., xn] == (...((z `f` x1) `f` x2) `f`...) `f` xn
f ( ... (f (f a1 x) a2) ... ) an
```
dove prende in ingresso una funzione che opera sulla struttura dati `[a]` applicandogli l'operatore di accumulo `b` per restituire il valore di `a` cambiato (operatore binario), il valore iniziale dell'accomulatore, la struttura dati `[a]` e ha come risultato il valore della funzione applicata a tutti gli elementi di `[a]`. Left-associative: la ricorsione inizia partendo, però, dal primo elmento della lista.

In *Scala* invece si ha:
```scala
def filesMatching(query: String, matcher: (String, String) => Boolean) =
    for (file <- filesHere; if (matcher(file.getName, query))
        yield field
    )
```

# In che cosa consiste il currying di funzioni
Una funzione curried è una funzione che accetta un solo parametro e il processo di trasformare una funzione `f` con N parametri in una catena di funzioni con un solo parametro si dice `currying`.
Le funzioni uncurried però non possono essere utilizzate come funzioni high-order.
Ad esempio: `\xy. x + 1` può essere espressa come due fuzioni su `x` e `y`: `\x . (\y. x + y)`.
In Haskell infatti ogni funzione è curried ma il linguaggio permette, tramite zucchero sintattico, di esprimere le funzioni con più argomenti.
```haskell
f x y z = <expr>
f x = \y -> (\z -> <expr>)
```
*Scala* supporta la definizione e l'invocazione di funzioni *curried* semplicemente separando la lista degli argomenti:
```scala
def curriedSum(x: Int)(y: Int) = x + y
curriedSum(1)(2)
res: Int = 3
```

# Che cosa sono le espressioni Lambda
Le Lambda expression prendono il nome dal **Lambda Calculus** sviluppato da Alonzo Church nel 1932 con l'obiettivo di catturare formalmente l'intuizione sul comportamento delle funzioni. Una espression lambda è una funzione anonima, ovvero, la cui definizione non è associata ad un indentificativo. Solitamente sono utilizzate per essere passate come parametro (high-order) ma nei linguaggi funzionali posso essere usate in ogni espressione.
Le Lambda expression permettono quindi di definire una funzione senza la necessità di associarle un identificativo e permette dei costrutti in-line (effettuare il `currying` implica una funzione high-order).
In Haskell è possibile definire una Lambda utilizzando `\`: `(\x -> x + 2) 3` ha come risultato 5; è possibile anche utilizzare più argomenti o Lambda annidate: `(\x -> (\y -> y + x)) 4 2` (res: 6).
In Scala (OOP + Funzionale): `val increase = (x: Int) => x + 1`.

# Che cosa sono le chiusure
Una chiusura è una tecnica per implementare un binding tra le variabili libere ed una funzione Lambda rispettando le opportune regole di scoping. In pratica si tratta di una Lambda con variabili libere definite nell'ambiente lessicale dove l'espressione viene definita.

# In che cosa consiste la lazy evaluation e perchè è importante
**Lazy Evaluation** o **Call-by-need** è una stragia di valutazione ed è una variante della **Normal Order** che evita di ridurre una *redex* molteplici volte: la prima volta che una *redex* viene valutate questo risultato viene propagato nel resto dell'espressione (nessuna sostituzione tra stringhe ma nel grafo). Utilizzato nei linguaggi funzinali moderni come Haskell e Miranda.
La valutazione **Normal Order** valuta per prima la *redex* a sinistra più esterna: questa viene valuta prima dei suoi argomenti che se non utilizzati all'interno della funzione velocizzano il processo di valutazione/esecuzione.
La *Lazy Evaluation* risulta essere molto importante in quanto permette di evitare di valutare più volte una stessa espressione o espressioni che non sono utilizzate e di manipolare strutture dati infinite come i stream.
```haskell
ones = 1 : ones
numsFrom n = n : numsfrom (n + 1)
(\ (x: _) -> x) ones
```
viene valutata come 1 in quanto viene correttamente presa solo la testa e tralasciata la valutazione della coda della lista di `ones`.

# Tipi di dati algebrici nei linguaggi funzionali
I dati algebrici sono **tipi composti** (combinazioni di altri tipi) con due principali classi: `sum` (enumerazioni) e `product`.
I tipi `sum` sono raggruppati in classi chiamate **varianti**; in Haskell `data Tree = Empty | Leaf Int | Node Tree Tree` dove `data` è un *costruttore* (ricorsivo), `Tree` il nome del nuovo tipo e gli elementi dopo l'uguale sono le *varianti*.
Un costruttore di tipo non può essere ridotto in quanto è gia in forma normale e risulta essere una entità quasi-funzionale. In questo caso `Leaf` è una funzione che trasforma un `Int` in `Tree`. È possibile anche utilizzare tipi di dati polimorfici (con attenzione alle classi di tipi) in quanto non ci sono dipendenze tra il costruttore ed il tipo usato in `Leaf`: `data Tree = Empty | Leaf a | Node (Tree a) (Tree a)`.
Un altro tipo di dato algebrico built-in in Haskell è quello per rappresentare le liste con due varianti: `data List a = Nil | Cons a (List a)` infatti le liste in Haskell possono essere richiamate tramite la loro testa e la coda.
I tipi `product` tipicamente contengono diversi valodi chiamati campi (`fields`); in Haskell `data MyRecord = MyRec Int Int` tutti i valori di quel tipo avranno sempre la stessa compibazione di campi. Un tipo `product` built-in in Haskell è quello delle tuple: sequenza delimitata da virgole tra parentesi tonde e con elementi anche di tipo diverso ma stesso numero di elementi.

# Tipi di dati astratti
I tipi di dati algebrici possono anche essere astratti (ADT): tipi con associati operazioni la cui rappresentazione è nascosta all'interno di un modulo. Un tipo algebrico se viene esportato da un modulo senza il suo costruttore diventa astratto.
Un esempio di tipo di dato astratto sono gli `Integer` e i `Float` in Haskell.

# Come esprimere l’iterazione in linguaggi puramente funzionali
Nei linguaggi funzionali l'iterazione avviene tramite **ricorsione**: la computazione è stateless quindi non è possibile effettuare dei loop o iterazioni espresse come nei linguaggi imperativi in quanto non so presenti costrutti per l'assegnamento.
Il meccanismo della ricorsione support naturalmente un approcchio top-down alla modellazione dei problemi (divide-et-impera) decomposti tramite induzione (caso base e caso induttivo).
Eccezione viene fatta (nei linguaggi non puri) per operativi di I/O o sequenze di azioni, tracciamento del tempo ed eccezioni (monadi).
Utilizzando la ricorsione alcuni linguaggi implementano il meccanismo di **tail recursion** che permette di avere una esecuzione molto efficiente in termini di consumo di memoria e di tempi: al posto di allocare sullo stack la stessa funzione viene utilizzato un meccanismo di `jump` (dopo aver aggiornato i parametri).

# Cosa si intende per *referential transparency*
Si parla di referential transparency quando una espressione può essere sostituita con il suo corrispondente valore senza cambiare il comportamento del programma. Nei linguaggi dichiarativi questo è immediatamente evidente. Queste funzioni sono dette **pure**.
Data una espressione `x + x; where x = f a`; l'applicazione `f a` può essere sostituita ad ogni occorrenza libera di `x`

# Il processo di riduzione nei linguaggi funzionali
Nei linguaggi funzionali l'**evaluation** delle espressioni consiste nel trasformare espressioni complesse nei loro valori; questa operazione si basa sulla riduzione delle espressioni che consiste nel riscrivere una espressione sostituendone delle sub-espressione in altre più semplici: sostituzione di una sotto-espressione nel body delle funzione in cui i parametri formali vengono catturati.
Nella fase di riduzione si identificano le **redex**: una applicazione (f_exp a_exp) in cui vengono forniti tutti gli argomenti e le **reductum** sono le espressioni ottenute dalla sostituzione dei parametri formali nel body della funzione (per le variabile catturate); la fase di riduzione è gestita dalla **beta-reduction** (regola di riscrittura) che processa il calcolo del risultato dall'applicazione di una funzione ad una espressione e quindi la sostituzione di una `<exp1>`, in `<exp1>`, ottenuta dalla sostituzione della **redex** con la sua **reductum**.
Durante questa fase è opportune fare attenzione alla sostituzione delle variabili libere e catturate con opportune attività di rinomina. Il processo di riduzione termina una volta che le espressioni sono diventate `valori` (tipi primitivi o valori funzionali: `\x.<exp>` non viene ridotto finchè non sono applicati gli argomenti).

# Strategie di valutazione nei linguaggi funzionali
In quanto una espressione può avere diversi **redex** (applicazione `<f_exp> <a_exp>`) è opportuno definire una strategia per procedere alla riduzione.
* **Applicative Order** o **Call-by-value**: la *redex* interna e più a sinistra viene valuata per prima se e solo se una *redex* ha gli argomenti che sono gia in forma ridotta (valori).
    Partendo da sinistra si seleziona la prima applicazione più interna: `<f_exp> <a_exp>`; ricorsivamente si riduce `f_exp` finchè non è nella forma `\x.<body>`; poi si valutano gli argomenti `<a_exp>` dell'applicazione e infine si riduce l'espressione `\x.<body> <val>`. Questa strategia può portare ad una divergenza; non è pura e viene usata solitamente nei linguaggi imperativi o nel List, Scheme, OCaml.

* **Normal Order** o **Call-by-name**: la *redex* a sinistra più esterna viene valuta per prima; una *redex* viene valuta prima dei suoi argomenti.
    Partendo da sinistra si seleziona la prima applicazione `<f_exp> <a_exp>`; si riduce `f_exp` finchè non è nella forma `\x.<body>`; poi si riduce la *redex* `((\x.<exp>) <a_exp>)` utilizzando la beta-riduzione.
    Questa soluzione è preferibile in quanto se un argomento non viene utilizzato non verrà valutato; utilizzata nei linguaggi .NET e Java tramite le lambda expression.

* **Lazy Evaluation** o **Call-by-need**: questa strategia è una variante della **Normal Order** ma evita di ridurre una *redex* molteplici volte: la prima volta che una *redex* viene valutate questo risultato viene propagato nel resto dell'espressione (nessuna sostituzione tra stringhe ma nel grafo). Utilizzato nei linguaggi funzinali moderni come Haskell e Miranda.

# Teorema fondamentale relativo alla valutazione di espressioni in linguaggi funzionali puri (senza side-effects)
Il teorema enuncia per i linguaggi pure:

> data una espressione chiusa (tutte le variabili sono legate) `<exp>`,
> se questa riduce in valore primitivo (no valori funzionali) `<val>` utilizzando una delle 3 strategie allora `<exp>` riduce in `<val>` anche con la strategia **Normal Order** o **Call-by-name**;
> se, invece, `<exp>` diverge utilizzando la stregia **Call-by-name** (o **Normal Order**) allora divergerà anche nelle altre.

# In che cosa consiste la nozione di monade e a che cosa serve
Le funzioni non possono essere utilizzate per modellare ogni singolo aspetto dei problemi infatti, casistiche come I/O e side effects, tempo, eccezioni e concorrenza possono compromettere la purezza di un linguaggio.
Le **monadi** sono una tecnica per denotare azioni in termini funzionali (denotare != eseguire) e incorporare side effects nei linguaggi funzionali puri, una monade è una struttura dati con uno stato associato (Eugenio Moggi, 1991).
Ad esempio un valore di tipo `IO a` è una azione che quando verrà eseguita produrrà un input/output prima di ritornare il tipo `a` o `()` se non ritorna nulla.
Le monadi sono una famiglia di tipi `m a` basati su un costruttore polimorfico `m` con funzioni `return`, `fail`, `>>=` e `>>`.

```haskell
class Monad m where
    (>>=) :: m a -> (a -> m b) -> m b
    (>>) :: m a -> m b -> m b
    return :: a -> m a
    fail :: String -> m a -- error funciont is based on fail
```

# Come specificare la combinazione di azioni con le monadi
Le azioni, che possono ritornare un valore, non prendono nessun parametro in ingresso (`putStrLn` è una funzione con un argomento che ritorna una azione di tipo `IO ()`).
Haskell esegue una sola azione di `IO` che è il `main` ma è possibile combinare diverse azioni tramite l'operatore *bind* `(>>=) :: IO a -> (a -> IO b) -> IO b`.

```haskell
getChar >>= putChar
c
c
```

`act >>= f` applica `act` al mondo corrente applicando il risultato alla funzione `f` come seconda azione per il risultato finale.
Ma se una azione non ritorna nessun valore (`IO ()`) si può utilizzare l'operatore `>>` *then* `(>>) :: IO a -> IO b -> IO b` (ad esempio `echo >> echo`).
La notazione con il blocco `do` permette la combinazione di due o più azioni eseguite in cascata con visibilità dei cambiamenti alle azioni successive a quella eseguita (solo azioni); tramite il `do` è possibile anche utilizzare il risultato di una azione in un'altra tramite l'utilizzo dell'operatore freccia `<-`:

```haskell
main :: IO ()
main = do
    line <- getLine
    putStrLn ("you said: " ++ line)
```

L'operatore `arrow` permette di legare il risultato di una azione tramite un nome (*single assignment strategy*).
Per utilizzare il blocco `do` anche con espressioni è possibile utilizzare è utilizzare la funzione `return :: a -> IO a` che da un qualsiasi tipo ritorna una azione con quel valore.

# Quale modello di integrazione fra OOP e prog funzionale è supportato nei principali linguaggi attuali - Java 8, Scala, JavaScript, C#
Linguaggio multi paradigma progettato da zero (Oz); da un linguaggio funzionale estenderlo con costrutti OOP o viceversa; programmazioe poliglotta: JVM + Clojure.
* Java 8 - **Project Lambda**: interfacce per callback, parametrizzazione del comportamento (passare del codice ai metodi per flessibilità e riuso) e funzioni high-order; introduzione dell'interfaccia `Stream<T>` e del metodo `stream()` per le `Collections`.
    ```java
    Collections.sort(list, (s1, s2) -> s1.length() - s2.length());
    ```
    È possibile anche utilizzare metodi (`<class>::<method>`) e costruttori (`<class>::new`) come espressioni lambda e con chiusure ma solo su tipi che non cambiano i propri valori (utilizzare Optional).
* C# - **Delegates**: le espressioni Lambda sono espresse tramite *delegates* che sono oggetti che rappresentano un metodo. I *delegates* possono essere passati a metodi, referenziati e salvati in strutture dati. Sono utilizzati per implementare callback, eventi, etc; sono identificati dalla sintassi `delegate` (`delegate int del(int i); del myDelegate = x => x * x;`). Anche in C# è possibile utilzzare le chiusure. L'alternative .NET ai stream è la composizione di *delegate* (operatori `-` e `+`) utili, ad esempio, con l'utilizzo di *LINQ*.
* Javascript: questo linguaggio con tipizzazione debole si basa su oggetti (no classi) e le funzioni sono oggetti che hanno i propri metodi e proprietà; funzioni possono essere assegnate a variabili e passate come argomenti con relative chiusere.
    ```javascript
    materials.map((material) => {
        return material.length;    
    });
    // o meglio
    materials.map(material => material.length);
    ```
* Scala: progettato per integrare OOP con FP basandosi sulla JVM; linguaggio conciso e fortemente tipato rilasciato nel 2004, supporta il pattern matching e l'overloading degli operatori. Le funzioni sono cittadini di prima classe e sono mappate in oggetti (interfacce funzionali e lambda), supporta l'inferenza di tipo (come i precedenti linguaggi) e come modello di concorrenza ha gli attori.
    ```scala
    // type inference on x
    increase = (x) => {
        println("CIAO")
        x + 1
    }
    someNumber.filter(_ > 0)
    ```
    In scala le chiusure catturano le variabili stesse non il loro valore (come in Java): cambiando una valore cambia anche ogni sui riferimento nella chiusura e viceversa.

# Lambda calcolo: sintassi e regole di riduzione
La grammatica del Lambda Calculus è:
`M :: = X | M M | λX.M | ( M )`
dove:
* `M` è un termine λ
* `X` è un non-terminale che rappresenta una generica variabile nell'insieme `{x, y, z, w, ...}` 
* `(` e `)` sono simboli terminali
* `λX.M` è chiama **astrazione** e rappresenta la definizione di una funzione; `M` è il body di una funzione.
* `(M M)` è una **applicazione** di una funzione ad uno o più argomenti
Tutte le funzioni nel lambda calcolo sono anonime e rappresentate tramite `λ` (per brevità `\`) che accettano una sola variabile in input e i simbolo sono associativi a sinistra. Le variabili libere sono variabili non legate ad un operatore `λ`.
* `(λ.x.M)N` è chiamato **redex**
* `M[N/x]` è chiamato **reductum**
La computazione avviene per riscrittura dell'espressione tramite la regola della `β-riduzione`: `(λx.M)N -> M[N/x]`: tutte le occorrenze di `x`, libere, sono sostituite con `N` nell'espressione `M` in modo non deterministico.
Si dice che `M` β-riduce `N` (scritto come `(M -> N)`) quando `N` è il risultato di un passo di beta-riduzione per `M`.
```
((λx.λy. * x y) 7 ) 6
(λy. * 7 y) 6
(* 7 6)
42
```

# Lambda calcolo: cosa si intende per forma normale
Una forma normale nel lambda calcolo è un termine λ che non contiene *redex* e che quindi non può essere applicata nessuna beta-riduzione: `λx.x` è in forma normale.
