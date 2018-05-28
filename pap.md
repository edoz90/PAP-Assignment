# Definizione di paradigma di programmazione

Ci sono diverse definizioni in letteratura per "paradigma di programmazione": secondo Van Roy si tratta di un approccio alla programmazione di teorie matematiche o di un coerente insieme di principi; per wikipedia si tratta di uno stile fondamentale per la programmazione di computer, per altri serve come forma mentis per la programmazione.

Un paradigma di programmazione consiste nel descrivere come la computazione lavora e viene modellata; come un programma è strutturato e coincide con il rappresentare STRUTTURA, COMPORTAMENTE ed INTERAZIONE di un programma. 

Solitamente un paradigma definisce specifici concetti e meccanismi tramite esressioni primitive di un linguaggio e loro combinazioni rappresentando ad alto livello delle astrazioni (nomenclatura e manipolazione di unità).

Una proprietà chiave di un paradigma è la possibilità di esprimere un non-determinismo osservabile.

Un'altra proprietà chiave di un paradigma è il supporto agli stati, ovvero, l'abilità di salvare una sequenza di valori nel tempo. Questa proprietà è molto influenzata dal paradigma; infatti, nel paradigma imperativo è più forte rispetto a quello dichiarativo (funzionale e logico) e ancora in linguaggi che utilizzano message-passing e concorrenza con condivisione dello stato.

# Quali sono i principali paradigmi di programmazione e caratteristiche essenziali di ciascuno

1. **Imperativo**

    *Prima fai questo e dopo quest'altro*

    Descrizione della computazione tramite sequenze di statements che cambiano lo stato del programma. Ogni comando ha un suo misurabile effetto nello stato del programma che varia in base all'ordine dei comandi e nel tempo.

    Ha come modello di riferimento per la computazione la macchina di Turing e prende ispirazione dall'architettura di Von Neumann (condivisione di memoria tra programma ed istruzioni).

    Un'astrazione del paradigmi imperativo sono le 'procedure', programmazione procedulare e modulare (il passo successivo e OOP).

2. **Funzionale**

    *Valuta l'espressione ed usala per qualcos'altro*

    La computazione si base sulla valutazione di espressioni che sono rappresentate da funzioni senza side effects. Infatti non ci sono stati o dati mutabili. Il paradigma ha ispirazione dalla matematica e la teoria delle funzioni e prendere come riferimento il modello del lambda calculus (Alonzo Church 1936) e il linguaggio Lisp (1958).

    L'astrazione naturale è la funzione vista come una singola espressione da valutare. Le funzioni sono visti come cittadini di prima classe e sono tipate e passate come argomento ad altre funzioni (high-order).

    Le funzioni ricoprono il ruolo primario (funzioni come valori esprimibili, lambda), la computazione è una riduzione delle expressioni definite (funzioni).

    L'evaluation delle espressioni avviene tramite *call-by-value*, *call-by-name* o *lazy*.

3. **Logico**

    *Rispondi ad una domanda cercando una soluzione*

    I programmi consistono in un elenco di statements logici e l'esecuzione consiste nel cercare una prova (verifica, proof) degli statements; utili per l'estrazione di conoscenza partendo da casi base e relazioni (AI).

    Secondo Kowalski può anche essere usato come linguaggio general purpose.

    Ha origine dallo studio dell'intelligenza artificiale e prende come modello la logice al primo ordine di Socrate: esprimere enunciati e dedurre le loro conseguenze logiche in modo del tutto formale e meccanico).

    Si basa su assiomi, regole di inferenza e query. I programmi consistono solo nella logica mentre la parte di controllo viene realizzata macchina astratta che opera le ricerche in base a delle regole di inferenza.

4. **Orientato agli oggetti**

    Invia messaggi tra gli oggetti per simulare una evoluzione temporale di un insieme di fenomeni basati sul mondo reale.

    Lo scambio di messaggi avviene tra oggetti computazioni self-contained con un proprio stato ed identità (incapsulamento dello stato e del comportamento).

    Gli oggetti vengono realizzati in base al concetto che hanno nel mondo reale.

    Ha ispirazione dalla teoria dei concetti e modelli dell'interazione umana con i fenomeni del mondo. Partendo dal dominio, il mondo è modellato/astratto/rappresentato: non dalla matematica nemmeno dall'hardware disponibile.

    Il primo linguaggio ad adottare questo paradigma fu Simula (1960).

    I principali fattori chiave sono: Incapsulamento, Information Hiding, Message Passing, Classi, Ereditarietà.



È possibile anche raggruppare il paradigma logico e funzionale nel paradigma **dichiarativo** che esprime la logica della computazione senza esplicitare un control flow (unnamed, deterministic, sequential).

In quanto è possibile che un paradigma non risolva tutti i problemi reali in maniera facile ed adeguata nasce l'idea di utilizzare diversi paradigma di programmazione nello stesso linguaggio tramite estensioni del linguaggio stesso o framework.

# Non-determinismo osservabile

Quando un utente può vedere risultati diversi da esecuzioni che hanno come punto iniziale la stessa configuration interna (non è desiderabile salvo quando la sua potenza espressiva è richiesta (programmazione concorrente)); un tipico effetto è la `race condition`.

Un linguaggio che può specificare, ad un certo punto del programma ("choise points"), diverse alternative di flow (contrario al classico if-then-else). La scelta è arbitraria a run time tra alcune limimtate opzioni specificate dal programmatore. Alcuni linguagggi che rispecchiano questa specifica sono: Oz, Erlang, Java ma solo con costrutti di concorrenza (ND finite state machine). Spin e Promela per verifica correttezza algoritmi concorrenti.

# Creative Extension Principle

Partendo dalla tassonomia dei linguaggi classificati in base al loro core del linguaggio è possibile ordinarli in base al creative extension priciple secondo cui un nuovo concetto viene aggiunto quando non è possibile realizzarlo tramite trasformazioni locali. Due linguaggi che implementano lo stesso paradigma hanno dei flavors diversi in base a cosa è stato deciso per facilitare il programmatore.

L'utilizzo di paradigmi con concetti e meccanismi di concorrenza genera una programmazione multi-threaded (i.e. Java) mentre se si integra la concorrenza nel paradigma stesso si crea un nuovo paradigma: attori e oggetti concorrenti, Erlang: linguaggio funzionale ed attori.

Ad esempio partendo da un semplice paradigma di programmazione sequenziale e funzionale può nascere l'esigenza di modellare degli aggiornamenti in memoria, una soluzione potrebbe essere quella di realizzare le funzioni con due parametri: uno di input e uno di output. Questa soluzione risulta però essere estremamente poco maneggevole e modulare. In questo caso è opportuno introdurre il concetto di **stato**.

Una volta aggiunto lo stato è possibile che sia necessario implementare delle attività che rimangano indipendenti e, anzichè, utilizzare un nuovo stack con relativo scheduler e context switch, è possibile implementare un meccanismo di **concorrenza**. Una volta che un sistema diventa complesso è opportuno modellare delle attività di detection e correzione degli errori; come soluzione principali potrebbe essere utile aggiungere dei codice di errore per tutte le funzioni e delle routine per il relativo management, introdurre il concetto di **eccezione**.

Una volta evidenziata la necessità di nuovi costrutti nel linguaggio si torna ad avere il focus solo sulla risoluzione del problema.

# Cosa si intende per funzioni high-order e perchè sono utili

In quanto in un linguaggio funzionale le funzioni sono valori first-class queste possono essere passate come parametro ad altre funzioni, restituite come valore di ritorno di una funzione e salvate in strutture dati. Nella pratica si possono effettuare tutte le classice operazioni sui valori del paradigma imperativo.

Le funzioni high-order permetto al linguaggio di essere più modulare esponendo al programmatore il meccanismo per assemblare le varie componenti del programma.

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
cc
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

* `(λx.M)N` è chiamato **redex**

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

# Lambda calcolo: proprietà della confluenza, teoremi di Church-Rosser

La proprietà della confluenza descrvie che qualunque *redex* venga scelto di ridurre prima la forma normale finale sarà sempre la stessa. Un λ-calcolo è confluente con α- e β-riduzioni (α è solo il renaming).

Se un termine è ridotto nella sua forma normale indipendentemente dal percorso scelto e in base alla α-equivalenza.

Il **primo teorema di Church-Rosser** enuncia che:
> Se `M` riduce a `N1` in un qualunque numero di step e `M` riduce anche a `N2` in un qualunque numero di step; allora esiste un termine `P` tale che sia `N1` che `N2` riducono a `P` in un numero di passi.

Il **secondo teorema di Church-Rosser** enuncia che:
> Se un termine ha una forma normale allora la stretegia di valutazione *normal order* può ridurlo.

# Lambda calcolo: strategie di valutazione, normal-order vs. applicative order

Nel lambda calcolo esistono due strategia di valutazione più comuni:

* **Applicative Order** (*strict*): gli argomenti sono valutati da sinistra verso destra in a DFS (*deep-first search* post-order) prima di essere passati alla funzione. Gli argomenti sono sempre valutati completamente prima dell'applicazione della funzione

* **Normal Order** (*non strict*): gli argomenti sono prima sostituiti all'interno del body della funzione senza essere inizialmente valutati: se l'argomento non è utilizzato non viene valutato, se viene riutilizzato viene rivalutato ogni volta. L'applicazione viene valuta prima.

Nelle strategie **Call-by-value** e **Call-by-name** sono applicate la **Applicative Order** e la **Normal Order** ma con la restrizione che nessuna riduzione avviene all'interno delle abstraction.

# Lambda calcolo: encoding di booleani, interi, liste

Il lambda calcolo può anche essere utilizzato come un modello computazione: permette, infatti, di codificare dei tipi di dati come i booleani, gli interi e liste e ogni tipo di struttura dati.

Ad esempio per i booleani è possibile dichiarare che:

```
TRUE = (\x y. x)
FALSE = (\x y. y)
IF = (\b t e. b t e)
AND = \b1 b2. IF b1 b2 FALSE
OR = \b1 b2. IF b1 TRUE b2
NOT = \b1 b2. IF b1 FALSE TRUE
```
Per i numeri naturali invece Church propone una composizione di funzione ripetuta `n` volte con `n` il numero da rappresentare:

```
0 = \sz.z
1 = \sz.sz
2 = \sz.s(sz)
SUCC = \n.\sz.s(nsz)
ADD = \m.\n.\sz.ms(nsz)
IFZERO = \m.m (\x.FALSE) TRUE
```
Per esprimere le lista è necessario prima definire le coppie o tuple di due elementi:

```
PAIR = \xyf.fxy
FIRST = \p.p(\xy.x) -- funzione TRUE
SECOND = \p.p(\xy.y) -- funzione FALSE

FIRST (PAIR a b) -> a
= (\p.p (\x.\y.x)) ((\x.\y.\f.f x y) a b)
= (\p.p (\x.\y.x)) (\f.f a b)
= (\f.f a b) (\x.\y.x)
= (\x.\y.x) a b = a
```
In quanto una lista può essere vuota `NIL` o con valori `CONS` è possibile modellarle tramite le coppie (`head:tail`):

```
NIL = \x.TRUE -- lista vuota
CONS = \x.\y.\z.(z x y) -- come per le coppie
HEAD = \p.p TRUE -- come per le coppie
TAIL = \p.p FALSE -- come per le coppie
```

# Lambda calcolo: teorema del punto fisso

> Ogni espressione Lambda `e` ha un punto fisso `e'` tale che `(e e') =β e'`: l'applicazione di `e'` ad `e` beta riduce ad `e'`.

In pratica si tratta di un comportamento di auto replicazione `Yf = f(Yf) = f(f(Yf)) = ...` e permette di modellare funzioni ricorsive in termini di funzioni non ricorsive: data una funzione `f` non ricorsiva possiamo scrivere: `f = (\f. ... f ...) f` facendola diventare ricorsiva (`f` è legata ed è un punto fisso); come convenzione per i punti fissi si utilizza il combinatore `Y`: `e' = (Y f) = (Y (\f. ... f ...))` e quindi `f = Y (\f. ... f ...)`.

```
FACTORIAL = \n. if (n = 0) then 1 else (n * FACTORIAL(n-1))
FACTORIAL = Y (\FACTORIAL.\n. if (n = 0) then 1 else (n * FACTORIAL(n-1)))
```

# Lambda calcolo: combinatori

In aggiunta al combinatore `Y` esistono i combinatori:

* **Identità**: `I = \x.x`

* **Funzione costante**: `K = \x.\y.x`

* **Applicazione**: `S = \x.\y.\z.(x z (y z))`

* **Composizione**: `B = \g.\f.\x.g(f x)`

* **Omega**: `Ω = ωω = (\x. x x)(\x. x x)`

# Lambda calcolo: tesi di Church

La capacità di simulare la ricorsione nel lambda calculus è uno dei suoi punti forti come modello di computazione e Church espresse una sua tesi a riguardo appunto la modellazione di qualcosa effettivamente calcolabile tramite il Lambda calcolo.

> Effectively computable functions from positive integers to positive integers are just those definable in the lambda calculus.

# Definizione di programma concorrente

Un programma si definisce concorrente quando due o più attività computazionali si sovrappongono nel tempo e hanno una qualche forma di dipendenza e interazione; nella pratica, due o più processi che possono essere eseguiti concorrentemente come fossero paralleli (N.B.: un programma parallelo esegue su processori fisici diversi): *abstract parallelism*.

# Legge di Amdhal

La legge di Amdhal viene utilizzato per trovare il miglioramento atteso massimo in una architettura migliorando una sua parte: "velocizza il caso comune/atteso". Definisce una formula per il massimo migliormento parallelizzando un sistema: `S = 1 / (1-P + (P/N))` con `P` è la porzione del programma da parellelizzare, `(1-P)` e la parte non parallelizzabile e `N` il miglioramento apportato.

# Definizione di Speedup ed Efficienza

Lo *speedup* è una misura delle performance in termini di *throughput* e *risposta*: `S = T1 / Tn` con `N` il numero dei processori, `T1` il tempo di esecuzione del programma sequenziale e `Tn` il tempo di esecuzione dell'algoritmo parallelo su `N` processori. Un alto `S` implica uno *speedup* notevole nell'esecuzione parallela.

L'*efficienza* misura invece l'uso di ogni processore durante l'esecuzione dell'algoritmo: `E = S / P`

# Tipi di interazioni fra processi

Ogni programma concorrente si basa su diversi processi che hanno necessità di interagire tra di loro per raggiungere l'obiettivo e terminale la computazione. In generale esistono 3 tipi di interazioni:

* **Cooperazione**: parte semantica del programma concorrente (aspettata e voluta). La cooperazione avviene tramite comunicazione (messaggi) e sincronizzazione (dipendenze tra processi e azioni nel tempo)

* **Competizione/Contenzione**: aspettata e necessaria ma non voluta. Si generano i problemi **mutua esclusione** (accesso a risorse) e **sezione critica** (esecuzione di blocchi di azioni)

* **Interferenza**: non aspettata e non voluta. Genera problemi di **race condition**: quando due o più processi accedono o aggiornano risorse condivise contemporaneamente.

Errori ed interferenze nell'esecuzione di un programma concorrente posso portare a situazioni critiche per il sistema come:

* **Deadlock**: due o più processi sono in reciproca attesa di finire (e quindi nessuno esegue)

* **Starvation**: un processo è infinitamente in attesa di acesso ad una risorsa (*resource starvation*) e quindi il programma non potrà mai finire

* **Livelock**: simile al *deadlock* ma i processi per non causare un deadlock aspettano l'altro che fa la stessa cosa

# Il modello "interleaved" per la rappresentazione e analisi dell'esecuzione di programmi concorrenti

La modellazione e astrazione dei programmi concorrente permette di avere una descrizione rigorosa rappresentate la struttura del programma. Una volta realizzato il modello, indipendente dai dettagli a basso livello della loro implementazione, è facile fare analisi e verifiche sulla correttezza della soluzione.

L'esecuzione di un programma concorrente può essere rappresentato come una sequenza di azioni che si incrociano tra di loro (ogni istruzione è *atomica*) per ogni processo: si ha dunque un singolo processore che esegue le azioni **atomiche** con  un *control pointer* per la successiva operazione da eseguire. Le azioni si considerano atomiche in quanto porterebbe ad un esplosione degli stati.

Una **computazione** o **scenario** è una sequenza di esecuzione che può avvenire come risultato dell'interleaving delle istruzioni.

I diagrammi di stato per rappresentare la computazione concorrente tramite state e transizioni; il risultato è un grafo orientato con ogni stato definito da una tupla:
`<q1,p1,0,1,2>`

1. Un elemento per ogni statement (etichetta) di processo
2. I valori di ogni variabile globale o locale

Nel modello ogni istruzione atomica è rappresentata da una etichetta, ogni processo ha una memoria privata e una memoria globale con relative variabili locali o globali.

Le transizioni tra due stati rappresentano la possibile esecuzione di una istruzione con relativo aggiornamento delle etichette e delle variabili.

Il numero degli stati dipende quindi dal numero dei processi (`n`) e delle istruzioni da eseguire `m`: `ns = (∑ mi)! / (Π mi!)`. Se si hanno 3 processi con ognuno 3 istruzioni diventa: `9! / (3! * 3! * 3!) = 1680` stati.

# Sezioni critiche: definizione, proprietà, strategie implementative

Il problema della sezione critica è stato introdotto da Dijkstra nel 1965 ed definisce:
> `N` processi, ognuno che esegue in un loop infinito una sequenza di statement divisibili in due parti: una **sezione critica** (CS) e una sezione *non critica* (NCS).

La sezione critica tipicamente è un insieme di statements per l'accesso/scrittura ad una risorsa condivisa.

Il problema può essere risolto garantendo alcune proprietà che il sistema deve rispettare:

* **mutua esclusione**: statements di sezioni critiche diverse non possono essere *interleaved* (nel grafo degli stati non possono esserci stati con `<cs1,cs1,_>`)

* **assenza di deadlock**: se uno o più processi stanno cercando di entrare nella sezione critica uno dei due deve infine accedere (per uno stato bloccato l'altro esegue in CS)

* **assenza di starvation**: se qualche processo sta cercando di entrare nella sezione critica allora quel processo deve infine accedere; *bounded waiting property*: un processo deve aspettare un ammontare di tempo finito per accedere alla sezione critica (esiste un percorso nel grafo degli stati per cui un processo non entra mai in CS).

L'approccio alla risoluzione del sistema può essere sia algoritmica che tramite l'uso di maccanismi come *lock* e *semafori* forniti però dalla macchina.

# Algoritmo di Dekker: descrizione del problema e algoritmo

Il problema della sezione critica può essere risolto tramite l'algoritmo di Dekker che, basandosi sul quarto tentativo (dopo un controllo se l'altro processo sta accedendo alla CS allora aspetta, non previene starvation) introduce una variabile `turn` per garantire il diritto di entrare nella sezione critica.

```promela
bool wantp = false, wantq = false;
byte turn = 1;

proctype p() {
    do ::
        wantp = true;
        do :: wantq ->
            if :: (turn == 2) ->
                   wantp = false;
                   (turn == 1);
                   wantp = true;
               :: (turn != 2);
            fi
           :: else -> break;
        od;
        printf("Log: p in CS\n");
        turn = 2;
        wantp = false;
    od
}

proctype q() {
    do ::
        wantq = true;
        do :: wantp ->;
            if :: (turn == 1) ->
                   wantq = false;
                   (turn == 2);
                   wantq = true;
               :: (turn != 1);
            fi
           :: else -> break;
        od;
        printf("Log: q in CS\n");
        turn = 1;
        wantq = false;
    od
}

init {
    atomic {
        run p();
        run q();
    }
}
```

# Algoritmo di Peterson - descrizione del problema e algoritmo

L'algoritmo di Peterson (1981) si basa su quello di Dekker in quanto risulta essere più conciso collassando le due `await` in una sola tramite l'utilizzo dell'operatore `or`: `await !wantq || turn = 1` e rimuovendo quindi il ciclo `while`.

```promela
    do ::
        wantq = true;
        turn = 1;
        (!wantp || turn == 2)
        printf("Log: q in CS\n");
        wantq = false;
    od
```
Come prerequisito per l'algoritmo di Peterson è che la macchina supporti azioni atomiche con condizioni multiple per le `await`, exploitando l'atomicità delle operazioni il problema della sezione critica diventa meno complesso.

# La verifica di proprietà di correttezza in programmi concorrenti - concetti principali, tipi di proprietà, strumenti

Il modello proposto ignora il tempo come variabile (dipendente dalla velocità del processore e del sistema) e si focalizza solo un ordine parziale delle istruzioni e scegliere cosa rendere atomico e garantisce robustezza sui cambiamenti hardware e software e garantisce una analisi formale per provare la **correttezza** di programmi concorrenti.

La correttezza per programmi sequenziali si basa sul controllo dell'output in base all'input (determinismo) mentre per una programmazione concorrente (non-determinismo osservabile) è necessario definire diversi approcchi basati su modelli astratti per una analisi formale (**model checking**).

La corretteza di un programma concorrenza va definita in termini di alcune proprietà:

* **Safety**: la proprietà P deve essere sempre vera in ogni stato (invariante); non ci sono deadlock, livelock e corretta gestione delle sezioni critiche (*mutua esclusione*)

* **Liveness** o *progress*: la proprietà P deve essere vera se in ogni computazione esiste uno stato in cui P è verificata; no starvation; no dormancy (un processo in attesa è svegliato), comunicazione affidabile.

* **Fairness**: garantisce che la proprietà di *fairness* si verifichi un numero di volte infinito; ogni processo esegue il suo turno grazie a policy di scheduling

Le politiche di scheduling per garantire la *fairness* possono essere:

* **Unconditional**: se ogni azione atomica non condizionale è idonea ad eseguire (`n` sedie per `n` persone, nessuna risorsa condivisa come precondizione); ad esempio round-robin.

* **Weak**: se è *unconditional fair* e le azioni atomiche condizionali diventano valide (`true`) finchè non sono visibili dal processo che le deve eseguire (una sedia condivisa per 3 secondi a persona, una persona anziana potrebbe non fare in tempo a sedersi in 3s a meno che non sia disponibile per un tempo infinito per garantire che si possa sedere); non garantisce che tutti i processi in attesa eseguano.

* **Strong**: se è *unconditional fail* e le azioni atomiche condizionali diventano valide un numero infinito di volte selezionando anche processi in cui la condizione non è valida (una singola sedia diposibile per uno slot di tempo; per ogni round lo scheduler aumenterà lo slot di tempo per permettere a tutti di sedersi: in un numero infinito di round tutti possono sedersi).

# Cosa si intende per corsa critica

Per corsa critica si intende un fenomeno che avviene quando il risultato finale della computazione diepende dalla temporizzazione (interleaving) delle istruzioni di due o più processi concorrenti.

Per evitare il verificarsi di queste condizioni in cui sono coinvolti memoria, file, o risorse condivise, sono stati studiati diversi algoritmi che prevedono la mutua esclusione, ovvero, assicurarsi che, se la risorsa condivisa è occupata da un processo, durante quell'arco di tempo nessun altro processo potrà accedervi (solo per scritture). 

# Cosa si intende per deadlock e condizioni necessarie

Un deadlock si verifica quando due o più azioni competitive sono in attesa delle altre di finire senza però proseguire.

Le condizioni per cui si **verifica** un deadlock sono 4 e devono verificarsi tutte in un sistema affinchè sia presente un deadlock:

- *mutua escluzione*: una risorsa che non può essere usata da più di un processo alla volta

- *condizione hold e wait*: un processo che ha accesso ad una risorsa ne può richiedere un altra (in possesso di un altro processo)

- *condizione non-preemptive*: nessuna risorsa può essere rimossa dal processo che l'ha ottenuta senza il consenso del processo stesso

- *condizione di attesa circolare*: due o più processi in maniera circolare aspettano una risorsa che ha il successivo processo

Una soluzione, adottata solitamente nei database è quella di rilevare i cicli di dipendenza, selezionare una vittima ed annulare la transazione; meccanismo di recovery assente nella JVM.

# Utilizzo delle logiche temporali nella programmazione concorrente - LTL come esempio

L'utilizzo di formule per la verifica di alcune proprietà di un sistema sono utili alla verifica della correttezza: ad esepmio per la mutua esclusione la formula `¬(CSp ∧ CSq)` definisce che non deve esistere uno stato in cui quella condizione sia verificata.

In quanto, però, i sistemi si evolvono nel tempo è necessario formulare una logica che prenda in considerazione il tempo come operatore; le **logiche temporali** permettono, infatti, di creare predicati che prendono in considerazione il fattore tempo:

* Branching Temporal Logics: esprimono proprietà che devono essere vere in alcuni o tutti i possibili scenari partendo da uno stato (CTL: computational tree logic)

* **Linear Temporal Logics**: esprimono proprietà che devono essere vere in uno stato per ogni possibile scenario; modello discreto del tempo.

Le **LTL** si basano su due principali operatori: **always** ed **eventually**:

- *box* o **always** o *globally*: `◻` (o `G`)

- *diamond* o **eventually** o *finally*: `⋄` (o F)

Data una formula `A` l'operatore `◻` definische che `A` è vera in uno stato `Si` se e solo se la formula `A` è vera in tutti gli stati `Sj` con `j >= i` e viene usatao per garantire la proprietà di safety: `◻P` con `P = ¬Q` dove `Q` e uno stato che si vuole evitare (mutua esclusione nei problemi di CS).

Data una formula `A` l'operatore `⋄` definische che `A` è vera in uno stato `Si` se e solo se la formula `A` è vera in uno o più stati `Sj` con `j >= i` e viene utilizzato per garantire la proprietà di liveness (qualcosa che diventerà vero nel tempo): `⋄P` con `P` uno stato che si vuole avere (no starvation nei problemi di CS, `◻(p2 -> ⋄CSq)`).

Questi operatori rispondono alle proprietà di:

* *riflessione*: 

    - `◻A -> A`

    - `A -> ⋄A`

* *dualità*:

    - `¬◻A = ⋄¬A`: never A = finally not A

    - `¬⋄A = ◻¬A`: not eventually A = always not A

* *sequenze*:

    - `⋄◻A` (**stability**): ad un certo punto `A` resterà sempre vera

    - `◻⋄A` (**progress**, **liveness**): per ogni stato ci sarà uno stato in cui `A` è vera (ad esempio un semaforo sarà per infinite di volte verde, anche alternandosi)

Grazie ai propri assiomi e regole la LTL è un sistema formale di deduzione logica usato per formalizzare la semantica dei programmi concorrenti e provare rigorosamente la correttezza dei programmi.

Nelle LTL esistono anche operatori binary come:

- *until*: `A U B` è vera in uno stato `Si` se e solo se `B` è vera in qualche stato `Sj` (con `j >= i`) e `A` è vera in tutti gli stati `Sk` con `i<=k<j`: `B` diventerà vero e `A` rimarrà vera finchè ciò non avviene.

- *weak-until*: `A W B` è vera come per *until* ma non è necessario che `B` diventi vera; se non avviene allora `A` rimane vera indefinitamente.

Alcune volte è necessario esprimere anche la condizione massima entro cui una proprietà deve verificarsi, in questi casi è possibile utilizzare una sequenza di *weak-until* (`W`) per cui si può modellare il concetto di starvation: nel tempo in cui `p` richiede di entrare nella sua CS un altro processo può entrare nella propria CS al massimo `k` volte prima di `p`. Questa proprietà è chiamata **k-bounded-overtaking**.
```
try_p -> ¬CSq W (CSq W (¬CSq W CSp))
```
Ogni posizione che soddisfa `try_p` è seguita da un intervallo in cui `q` non è in CS, seguita da un intervallo in cui `q` è in CS, seguita da un intervallo in cui `q` non è in CS e che può essere terminata solo dauno stato in cui `p` è in CS.

# Model-checking - concetti principali, esempi

Il **model checking** è una tecnica per definire un metodo formale di verifica per un sistema (controlla che il sistema rispetti le specifiche e non che soddisfi il cliente: "*have we built the system right?*").

La verifica è fatta generando uno ad uno gli stati del sistema controllando le varie proprietà stato per stato; questo processo può essere automatizzato tramite alcuni tool.

È la tecnica più importante ed utilizzata (sia per HW che per SW) per automatizzare la verifica della corretteza delle proprietà di un sistema concorrente. La strategia si basa sul verificare le proprietà sull'intero spazio di ricerca (utile anche per trovare bug).

Un tool di model checking utilizzato in ambiente industriale ed accademico è [`Spin`](http://spinroot.com/spin/whatispin.html) con il relativo linguaggio [`PROMELA`](./dekker.pml).

Una alternativa al *model checking* è la prova induttiva delle invarianti: le proprietà invarianti del sistema sono verificate per induzione sugli stati del sistema; può essere automatizzata tramite dei tool di deduzione.

Una *invariante* è una formula che deve essere vera per ogni punto della computazione.

# Il costrutto semaforico - definizione, implementazione, esempi di utilizzo (problemi)

I **semafori** sono stati introdotti da Dijkstra nel 1968 e sono un costrutto molto semplice che permette di risolvere quasi tutti i problemi di mutua esclusione e sincronizzazione in cui è necessario fornire interazione in architetture a memoria condivisa.

I semafori funzionano proprio come quelli stradali bloccando o sbloccando processi nella loro esecuzione e sono un tipi di dato primitivo messo a disposizione dalla macchina.

Un semaforo è composto da due campi:

1. `S.V` un intero >= 0;

2. `S.L` un insieme di processi (id)

e fornisce due atomiche operazioni:

- **`wait(S)`** (o `P(S)`)

- **`signal(S)`** (o `V(S)`)

L'operazione di *wait* è atomica e viene utilizzata per controllare se un processo può procedere: se il valore in `S.V` è maggior di 0 allora il processo può accedere e `S.V` viene decrementato di 1 altrimenti il processo si inserisce `S.L` e rimane bloccato sul semaforo `S` in attesa di una *signal*.

L'operazione di *signal* è atomica e viene utilizzata per sbloccare un processo: se l'insieme dei processi `S.L` è vuota allora `S.V` viene incrementato di 1 altrimenti viene rimosso da `S.L` un processo (arbitrariamente) da sbloccare (nel diagramma degli stati laf signal fa eseguire direttamente il processo).

Esistono diverse *tipologie* di semafori tra cui i **mutex** in cui la componente intera `S.V` accetta come valori solo 0 e 1; **generali** o di *contatori* in cui `S.V` può assumere ogni valore maggiore uguale a 0 ed **eventi**, inizializzati a 0, utilizzati per scopi di sincronizzazione.

I semafori possono anche essere implementati in 3 pricipali tipologie:

* **strong**: `S.L` non è un insieme ma una coda (FIFO) e quindi garantisce l'assenza di starvation

* **weak**: `S.L` è un insieme (`set`) e quindi non è possibile scegliere quale processo prendere (non garantisce l'assenza di starvation)

* **busy-wait**: in cui non è presente la componente `S.L`, la signal incrementa il semaforo e la await lo decrementa dopo aver avuto il via (non garantisce l'assenza di starvation e consuma temp CPU)

I semafori sono utilizzati per risolvere problemi di mutua esclusione implementando dei `lock` e di sincronizzazione (eventi, barriere). Per i problemi di mutua esclusione è facile utilizzare un semaforo come un *lock*: `S` è inizializzato a 1 e ogni processo prima della sua CS fara un controllo tramite la `wait(S)`: se `S.V` è > 0 allora può procedere e gli altri processi saranno bloccati, quando il processo in CS esce tramite la signal andrà a incrementare `S.V` e a svegliare un processo in attesa. Questa soluzione garantisce: mutua esclusione, assenza di deadlock e di starvation (con l'aumentare del numero di processi scende la confidenza).

I semafori **evento** sono utilizzati per sincronizzare i processi nel caso sia necessaria un ordine di esecuzione: sono utilizzati per ricevere e inviare segnali temporali, inizializzati a `0`. Ad esempio per l'algoritmo parallelo del mergesort il processo `merge` deve rimanere in attesa che `sort1` e `sort2` finiscano (`wait(S1)` e `wait(S2)`) per procedere con il merge; i due processi di sort una volta finita la computazione eseguiranno la signal.

# Proprietà invarianti del semaforo

Dato un semaforo `S` e un valore `k` come valor iniziale di `S.V` allora `S` soddisfa:
> S.V >= 0
> S.V = k + #signal(S) - #wait(S)

# Utilizzo di semafori per la sincronizzazione di processi

I semafori **evento** sono utilizzati per sincronizzare i processi nel caso sia necessaria un ordine di esecuzione: sono utilizzati per ricevere e inviare segnali temporali, inizializzati a `0`. Ad esempio per l'algoritmo parallelo del mergesort il processo `merge` deve rimanere in attesa che `sort1` e `sort2` finiscano (`wait(S1)` e `wait(S2)`) per procedere con il merge; i due processi di sort una volta finita la computazione eseguiranno la signal.

# Utilizzo di semafori nella risoluzione del problema produttori-consumatori

Nel problema P/C con un buffer infinito è necessario solamente un controllo sul buffer vuoto: non appena il produttore produce un elemento tramite signal (sul semaforo di *risorsa* `nAvailItems`) sblocca il consumatore che può quindi proseguire. Se il buffer invece è finita è opportuno controllare anche se il buffer è pieno utilizzando un nuovo semaforo, in questo caso il semaforo è detto di *split*: il produttore tramite una await (sul semaforo `nAvailPlaces` inizializzato con la lunghezza del buffer) aspetta che il buffer abbia dei posti vuoti per inserire un nuovo elemento e sbloccare il consumatore (sul semaforo `nAvailItems`); viceversa il consumatore è in attesa che nel buffer ci siano elementi e una volta letti fara una signal al produttore (sul semafoto `nAvailPlaces`).

Nel caso in cui invece la struttura dati del buffer non sia atomica è necessario introdurre un *mutex* in lettura e scrittura.

# Utilizzo di semafori nella risoluzione del problema lettori-scrittori

Il problema è composto dalle invarianti:

```
nR >= 0
nW = 0 || nW = 1
(nR > 0 -> nW = 0) and (nW = 1 -> nR = 0)
```
Il problema si compone quindi come un problema simile alla mutua escluzione. Si possono utilizzare due semafori: uno per i lettori ed uno per i scrittori assieme ad un contatore per tracciare il numero dei lettori.

Gli scrittori sono in attesa sul semaforo `rw` (inizializzato ad 1) e dopo aver eseguito la scrittura sveglierà un altro scrittore (o se stesso) con una signal sullo stesso semaforo. I lettori invece sono più complessi in quanto è necessario inzialmente attendere il turno per leggere il numero di lettori, se uguale a 0 allora è necessario aspettare uno scrittore e poi aggiungersi al numero di lettori, rilasciare il semaforo, eseguire la lettura e riaggiornare (tramite il semaforo) il numero di lettori e se nessun lettore è in esecuzione allora svegliare uno scrittore:

```
// Reader
loop forever
p1: wait(mutexR)
p2: if (nr == 0)
p3:   wait(rw)
p4: nr <- nr + 1
p5: signal(mutexR)
p6: Item el <- read(dbase)
p7: wait(mutexR)
p8: nr <- nr - 1
p9: if (nr == 0)
p10:   signal(rw)
p11:signal(mutexR)
```

# Utilizzo di semafori nella risoluzione del problema dei filosofi

Per risolvere il problema dei filosofi tramite semafori è possibile introdurre un semaforo `turn`, oltre a quelli per le forchette di destra e sinitra, inizializzato a 4 (i filosofi, i posti e le forchette sono 5) che previene che ci siano situazioni di deadlock; il semaforo in questo caso è utilizzato come "biglietto per l'ingresso nella sala mensa": se tutti i posti sono occupati allora attendi.

Questa soluzione comporta una sequenza di 3 `wait` che può essere ottimizzata imponendo ai filosofi di prendere le forchette sempre prima a sinistra e poi a destra:

```
global semaphore array [0..4] fork <- [1,1,1,1] // Tutte le forche disponibili
----
int first = min(i, (i+1)%N)
int second = max(i, (i+1)%N)

loop forever
p1: think
p2: wait(fork[first])
p3: wait(fork[second])
p4: eat
p5: signal(fork[first])
p6: signal(fork[second])
```
Come regole generali per evitare deadlock con `N` processi che condividono molteplici lock si deve assegnare un ordine ai lock ed acquisirli sempre nello stesso ordine; in questo modo si evitano di avere condizioni di dipendenza circolare.

# Esempio di utilizzo dei semafori risorsa

I *semafori risorsa* sono utili per rappresentare ad esempio dei buffer di `N` elementi struturati e utili nei problemi di produttore/consumatore.

# Esempio di utilizzo dei semafori "split"

Un semaforo binario *split* è un insieme di `n` semafori binari in cui ogni semaforo rappresenta un diverso stato della risorsa condivisa.

# Monitor - definizione, implementazione, esempi di utilizzo (problemi)

In quanto i semafori non sono adatti a programmi molto complessi e sono costrutti a basso livello Hansen (1973) e Hoarre (1974) hanno formalizzato i **monitor**: un dato astratto concorrente che racchiude le policy di sincronizzazione e mutua esclusione nel suo accesso: **stati più operazioni più policy di concorrenza** (simile al concetto di HW mode nei kernel).

Per definizione i monitor espongono all'esterno solo i nomi delle operazioni e sono usati come interfacce e non possono accedere a risorse dichiarate al di fuori del monitor stesso.

I monitor forniscono, dunque, una intrinseca ed implicita mutua esclusione (il programmatore non deve far nulla) in quanto le procedure del monitor eseguono in mutua esclusione (solo una istanza di una procedura alla volta, i processi che vedono il monitor come *busy* sono sospesi): le operazioni sono quindi eseguite atomicamente. Il problema della starvation però potrebbe verificarsi in quanto non sono associate delle code ai monitor per i processi in attesa.

I monitor forniscono anche una esplicita interfaccia per la sincronizzazione tramite l'utilizzo di **variabili condizionali** utilizzate all'interno dei monitor (dal programmatore) per bloccare, sbloccare l'esecuzione dei processi in base allo stato del monitor.

Le **varibili condizionali** sono tipi di dati primitivi che possono essere utilizzare per sospendere o svegliare i processi all'interno del monitor tramite una coda FIFO (produttore consumatore con buffer finito). Le principali primivite per queste *variabili condizionali* sono:

- `waitC(cond)`: sospende l'esecuzione del processo e rilascia il lock del monitor.
    ```
    waitC(cond) =
    < append p to cond.queue
      p.state := blocked
      monitor.lock.realease() >
    ```
- `signalC(cond)`: sveglia un processo bloccato su una condizione (e nella coda).
    ```
    signalC(cond) =
    < if cond.queue != empty
        q := cond.queue.pop()
        q.state := ready  >
    ```
- `emptyC(cond)`: controlla se la coda è vuota

- `signalAllC(cond)`: tutti i processi in attesa sono svegliati

- `waitC(cond, rank)`: in attesa che `rank` possa essere aumentato

- `minrank(cond)`: ritorna il valore del rank del processo in cima alla lista dei processi in attesa

In quanto per ogni signal solo un processo può poi entrare di nuovo nel monitor esistono tre principali semantiche di signaling:

- **Signal e continua**: il processo che esegue la signal continua l'esecuzione e il processo risvegliato dovrà contendersi di nuovo l'accesso al monitor con gli altri processi (`E < W < S`); non-preemptive.

- **Signal e aspetta**: il processo risvegliato esegue subito e il segnalatore aspetta o si contende l'accesso con gli altri processi (`E = S < W`); preemptive.

- **Signal e aspetta con urgenza**: come per la *signal e aspetta* ma il segnalatore ha precedenza sugli altri processi in attesa del lock del monitor `(E < S < W)`

# Utilizzo di monitor nella risoluzione del problema produttori-consumatori

Si può implentare il buffer utilizzato dai produttori e dai consumatori come un monitor

```java
monitor BoundedBuffer {
    int[] buffer = new int [MAX_ELEMS]
    int first = 0; last = 0
    cond notFull, notEmpty

    procedure void put(int elem) {
        if ((last + 1) % MAX_ELEMS) = first // is full
            waitC(notFull)
        buffer[last] = elem
        last = (last + 1) % MAX_ELEMS
        signalC(notEmpty)
    }

    procedure int take() {
        if (first != last) // is empty
            waitC(notEmpty)
        int elem = buffer[first]
        first = (first + 1) % MAX_ELEMS
        signalC(notFull)
        return elem
    }
}
```

# Utilizzo di monitor nella risoluzione del problema lettori-scrittori

Si può utilizzare un monitor per implementare il lock per lettori e scrittori. Si può utilizzare anche una `signalAllC` per i lettori ma causa overhead (esegui e riblocca).

```java
monitor RWLock {
    int readers = 0
    int writers = 0
    cond okToRead, okToWrite

    procedure startRead() {
        if writers != 0
            waitC(okToRead)
        readers = readers + 1
        signalC(okToRead)
    }

    procedure stopRead() {
        readers = readers - 1
        if readers = 0
            signalC(okToWrite)
    }

    procedure startWrite() {
        if writers != 0 or readers != 0
            waitC(okToWrite)
        writers = writers + 1
    }

    procedure stopWrite() {
        writers = writers - 1    
        // se non ci sono lettori allori scrivi
        if emptyC(okToRead)
            then signalC(okToWrite)
            else signalC(okToRead)
    }
}
```

# Utilizzo di monitor come allocatori di risorse

I monitor sono utilizzati solitamente come allocatori di risorse in quanto permettono una gestione nell'allocazione di risorse tramite l'accesso al monitor. È una implementazione generica degli altri casi. Ad esempio per uno scheduler SJF:

```java
monitor SJFAllocator {
    bool free = true;
    cond turn;

    procedure request(int time) {
        if (free)
            free = false;
        else
            waitC(turn, time)
    }

    procecdure {
        if (emptyC(turn))
            free = true;
        else
            signalC(turn)
    }
}
```

# Semantiche di segnalazione nei monitor - descrizione, esempi

In quanto per ogni signal solo un processo può poi entrare di nuovo nel monitor esistono tre principali semantiche di signaling:

- **Signal e continua**: il processo che esegue la signal continua l'esecuzione e il processo risvegliato dovrà contendersi di nuovo l'accesso al monitor con gli altri processi (`E < W < S`); non-preemptive (implementato tramite un `while`).

- **Signal e aspetta**: il processo risvegliato esegue subito e il segnalatore aspetta o si contende l'accesso con gli altri processi (`E = S < W`); preemptive.

- **Signal e aspetta con urgenza**: come per la *signal e aspetta* ma il segnalatore ha precedenza sugli altri processi in attesa del lock del monitor `(E < S < W)`

# Classi concettuali per l'organizzazione di architetture concorrenti

Dato un problema da risolvere tramite un architettura concorrente è necessario un scegliere un pattern di decomposizione tra funzionale, dei dati o ricorsiva.

La decompomsizione funzionale implica la suddivisiona del problema in task (divide-et-impera) indipendenti e concorrenti su dati differenti generando pipeline.

La decomposizione sui dati viene applicata quando si ha a che fare con un data set molto grande; la strategia è quella di raggruppare i dati in input, output e intermedi. Tutti i processi eseguono la stessa istruzione ma su dati diversi (SIMD) come ad esempio per la moltilicazione tra matrici; molto scalabile con il numero dei processori.

Con la decomposizione ricorsiva invece il problema viene diviso in sottoproblemi che sono ricorsivamente divisi in altri sottoproblemi e che vengono poi riassembla per ricorstruire il risultato finale; molto scalabile in quanto i task più piccoli possono essere parallelizzati.

Alcune classi concettuali per il design delle archietture concorrenti sono:

- **result parallelism**: progettare il sistema attorno alle strutture dati che si vogliono in output computando tutti gli elementi in parallelo (decomposizione dei dati) utilizzando una opportuna struttura dati condivisa progettata per il risultato. Si producono i singoli pezzi di una casa e si assemblano non appena sono pronti (in parallelo con sincronizzazione).

- **specialist parallelism**: progettare il sistema con alcuni *specialisti* che costituiscono il programma stesso e che lavorano assieme ad in parallelo su uno specifico task (message box, blackboard, eventi); opposto alla classe *result*. Anzichè costruire il programma in base ai dati lo si progetta tenendo conto delle specializzazioni dei task; uno specialista per ogni lavoro da fare per costruire una casa.

- **agena parallelism**: progettare il sistema in base ad una agenda ed assegnare i worker ad ogni step; ogni agente lavora in parallelo per completare il task. Al posto di avere tanti agenti che lavorano su tante operazioni li si allocano tutti per costruire un pezzo di casa alla volta.

# Uso delle Reti di Petri per la rappresentazione della struttura e della dinamica di programmi concorrenti

Le Reti di Petri permettono un formalismo rigoroso nel descrivere modelli di sistemi di concorrenti tramite diagrammi; racchiuda dinamismo e rappresenta i vari task e dipendenze. In aggiunta esistono anche i diagrammi di stato e di attività.

Le Reti di Petri (1965) descrivono il controllo e flow dell'informazione nei sistemi e sono usate principalmente per sistemi ad eventi in cui è possibile che qualche evento occora concorrentemente ma con alcuni vincoli (precedenze, frequenza, sincronizzazione).

Una rete di Petri è formalizzabile come: `C = (P, T, I, O, u)`:

- P è l'insieme delle piazze

- T è l'insieme delle transizioni

- I una funzione delle transizioni in input

- O una funzione delle transizioni in output

- u una funzione P -> N che mappa la presenza di uno o più token per ogni piazza

Nelle reti di Petri non è possibile misurare il tempo ma solo definire un ordine partiale di occorrenza degli eventi.

Le reti di Petri sono dei grafi bi-partiti (due insiemi, un arco per ciascun vertice) con due tipi di nodi: piazze (cerchi) e transizioni (barre), un arco connette un nodo `i` con uno `j` dove `i` è un input di `j` e un output di `i`.

Per rappresentare il flow si utilizzano dei **token** (un punto nero all'interno delle piazze) che si possono mouvere nella rete (rete di Petri **marked**). Affinche un *token* possa passare da una piazza ad un altro deve seguire alcune regole di **firing** (a causa di una occorrenza di un evento):

- una transizione può avvenire solo se è abilitata, ovvero quando tutte le piazze in input ad un nodo hanno dei token

- la transizione esegue eliminando (consumando) i token abilitanti dall'input e generandone uno nuovo nel nodo in output.

Una transizione può anche consumare più token se viene espresso il numero nell'arco e più transazioni possono non avvenire sempre nello stesso ordine.

Una marcature (**marking**) di una rete di Petri rappresenta uno snapshot del grafo in quel momento e lo stato della rete. Partendo da un marking è dunque possibile stabilire il successivo stato.

# Rappresentazione di sezioni critiche con Reti di Petri

Non è assente da starvation

# Gli Statecharts come formalismo visuale: caratteristiche principali

Gli statechart sono utilizzati per modellare sistemi reattivi complessi e fanno parte dello standard UML. L'obiettivo è quello di descrivere il comportamento reattivo in maniera realistica e chiara e al tempo stesso formale e rigorosa (tale da essere simulata ed analizzata).

Sono una estensione dei classi digrammi di stato in quanto ne migliorano la capacità descrittiva.

È possibile esprimere gerarchie (clustering, refinement, zoom) e ortogonalità (indipendenza o concorrenza di sotto-stati).

Gli stati sono rappresentati da dei **box** mentre gli eventi da delle **frecce** (con eventuali condizoni e azioni). Tramite un tipo di freccia è possibile anche segnalare quale è lo stato inziale/ingresso.

# Comunicazione a scambio di messaggi

La comunicazione tra processi che hanno necessità di interagire avviene tramite uno scambio di messaggi con le primitive `send` e `receive`. Nato con l'idea di essere applicato nella programmazione distribuita ma sempre più applicati in ogni linguaggio di programmazione e framework o tecnologia. Il modello nasce nel 1970 da Hansen e nel 1971 Balzer introduce la porta di comunicazione instaurando le basi per la comunicazione asincrona tramite message passing.

Hoare nel 1978 modella il formalismo del CSP (Communicating Sequential Process) e nel 1973 nasce il concetto di *attore* con comunicazione asincrona.

# Modello asincrono vs sincrono

Un comunicazione sincrona è bloccante per la `send` fino a quando il messaggio non viene ricevuto sullo stesso canale così come per la `receive` (se non ci sono messaggi nel canale); è un modello primitivo. In pratica quando due processi sono uno su una istruzione `send` e l'altro su una `receive`.

Tramite il rendez-vous non solo il mittente deve aspettare che il destinatario possa ricevere ma deve anche aspettare una riposta.

Una comunicazione asincrona, invece, non è bloccante infatti si ha un buffer FIFO in cui vengono inseriti i messaggi da inviare (inserimento in coda) e ricevere (pop in testa). In alcuni modelli i canali con scambio di messaggi asincrono sono anche detti **porte**.

# Guardie

Le guardie sono state introdotte da Dijkstra nel 1974 e permettono di realizzare una `receive` selettiva tramite statement di selezione:

`B; C -> S`:

- `B` è una espressione booleana che se omessa è `True`

- `C` è uno statement di comunicazione (solitamente `receive`)

- `S` è una lista di statement

`B` e `C` compongono la **guardia**; la semantica del costrutto è quella di selezionare la guarda da eseguire selezionando quella che ha successo in maniera non deterministica:

- se `B` è vera e l'esecuzione di `C` non comporta dei ritardi la guarda ha successo

- se `B` è false la guarda fallisce

- se `C` non può non essere eseguita si blocca

Se una guardia viene seleziona per l'esecuzione allora viene eseguito il blocco di codice `S`.

```
if nReqs < max; receive computeSum(a, b, i) -> nReqs += 1; send result[i](a + b)
[] nReqs < max; receive computeMul(a, b, i) -> nReqs += 1; send result[i](a * b)
fi
```
Tramite le guardie è possibile anche implementare una versione del problema produttore/consumatore tramite un ciclo di selezione con controllo sul buffer.

# Modello di concorrenza degli attori

Gli attori nascono dall'idea di creare degli oggetti puramente autonomi con comunicazione a scambio di messaggi asincrona. Un attore ha un identificativo univoco ed una mailbox in cui i messaggi sono accodati. Ogni interazione tra attori avviene tramite questa mailbox.

Gli attori astraggono dalla loro implementazione incapsulando uno stato, un comportamento e una logica di controllo (or thread di controllo) disaccoppiando così una concorrenza fisica con quella logica. Un attore per eseguire non necessita di un thread fisico.

Gli aspetti principali di un attore sono:

- *comportamento puramente reattivo*: esegue solo quando riceve un messaggio

- *incapsulamento dello stato*: i cambi di stato avvengono solo tramite scambio di messaggi e non accesso diretto

- *sematica a macro-step*: per ogni messaggio l'handler deve eseguire completamente prima di processare un altro messaggio

- *firness nell'invio e processamento dei messaggi*: non è possibile definire una sequenza con cui i messaggi sono ricevuti ma è garantito che vengano processati

- *transparenza della posizione*: in quanto gli attori comunicano solo tramite message passing non è importante conoscerne la sua location ma solo il suo identificativo

# Modello a loop implicito e a loop esplicito

Un attore con loop implicito funziona come un event-loop in cui per ogni messaggio ricevuto viene eseguito il corrispondente handler: l'architettura embedda il ciclo al posto del codice del programmatore.

Un attore con loop esplicito (e anche receive) il programmatore deve gestire explicitamente il loop per la `receive` (anche tramite guardie); Erlang usa un modello explicito:

```
receive
    Pattern1 [when Guard1] -> Expression1;
    Pattern2 [when Guard2] -> Expression2;
    ...
end
```

# Cosa si intende per programmazione asincrona

La programmazione asincrona astrae dal concetto di thread e si propone di eseguire processi, richieste e computazioni in maniera asincrona e utilizza due principali metodologie per raggiungere il suo scopo:

- basandosi su task e future

- tramite continuation passing style (CPS: una funzione con callback) e architettura event-driven.

# Il meccanismo delle future

Durante una esecuzione asincrona (task) un oggetto **future**, che rappresenta l'oggetto risultato o lo stato della computazione stessa viene immediatamente create e ritornato.

L'oggetto permette di controllare lo stato del task, recuperare il risultato, cancellare il task e controllare eventuali errori od eccezioni del task.

Tutti i principali linguaggi di programmazione supportano le *future*: Java Task Executor, AsyncTask in Android, TAP .NET, Python (`concurrent.future`).

Solitamente le future sono utilizzate per controllare dei task che eseguono su thread diversi o in concorrenza; le *promise* invece sono viste come l'esito di una chiamata asincrona (callback).

# Programmazione asincrona basata su continuation (CPS) e callback

Rimodellamento della computazione e della programmazione per renderla completamente asincrona. Una volta lanciata una computazine asincrona deve essere specificato un **continuation** della computazione una volta che il task sarà completato o restituisca un errore. La funzione di *continuation* accetta un solo parametro: il risultato della computazione del task (o l'errore). Il *continuation* quindi esplicita il flusso di controllo per le azioni asincrone che, quindi, accettano come parametro anche il continuation.

Invocando una funzione CPS il chiamante deve fornire una procedure da invocare quando la subroutine completa l'esecuzione.


Nella programmazione asincrona le **callback** rappresentano un *continuation* che sono chiamate quando il risultato della computazione asincrona è completo.

```javascript
function loadUserPic(userId, ret) {
    findUserById(userId, (user) => {
        loadPic(user.picId, ret);
    });
}

loadUserPic("john", (pic) => {
    ui.show(pic);
})
```

Gli eventi scatenati dal task sono impliciti e si riferiscono al completamento con sucesso od errore della computazione. Le chiusure sono tipicamente definite per definire il contesto da essere usato per processare l'evento.

# Programmazione asincrona: modello di esecuzione basato su event-loop

La principale questione della programmazione asincrona basta su CPS è in che modo richiamare il *continuation*; esistono due principali modalità:

1. un thread di controllo separato che esegue concorrentemente al thread che richieste l'esecuzione asincrona; comporta una inversione di controllo (diependency injection) e problemi di corse.

2. lo stesso thread di controllo che invoca la richiesta tramite un modello ad event-loop implicito.

L'archietettura ed event-loop permette l'incapsulamento della computazione in un insieme di *event handler* interessati agli eventi percepiti.

In quanto è possibile che se verifichino più eventi esiste una coda di eventi che tiene traccia degli stessi (struttura molto simile al loop implicito degli attori).

L'esecuzione della singola computazione asincrona è considerata atomica: gli eventi che occorrono durante l'esecuzione di handler vengono accodati. Gli handler infatti devono eseguire senza potersi bloccare (nessun primitiva che lo permette).

La programmazione event-driven è anche detta *senza stack* in quando la gestione dell'evento non è una chiamata a procedura (nessun `return`).

Il fatto di avere un singolo thread che gestisce e coordina i task asicroni permette di avere un basso impatto di memoria rispetto a soluzioni con più thread, non ci sono corse o deadlock (nessuno stato condiviso, un thread alla volta, handler sono progettati per non bloccarsi).

# Problemi dell'approccio basato su callback e CPS 

Uno dei maggiori problemi di questo stile di programmazione è il *callback hell* che genera sia un codice molto frammentato a causa dei vari handler (*asynchronous spaghetti*) e difficile da mantenere e capire in aggiunta le funzione sono un aggregato di altre funzioni come callback.

La programmazione CPS porta ad avere molteplici livelli di funzione innestate per le callback o continuation; aumenta notevolmente la difficoltà di lettura del codice e di riusabilità.

Una soluzione è utilizzare le *Promise* (1976, Daniel Friedman e D. Wise).

# Il meccanismo delle promise

Il callback hell può essere parzialmente risolto tramite le **Promise**: oggetti proxy the rappresentano un risultato non ancora definito che deve essere computato (simile alle future). Le promise incapsulano azioni asincrone che possono essere risolte o rifiutate una ed una sola volta e sono immutabili.

In Javascript sono anche dette *thenable* in quando è possibile evitare la *pyramid of doom* risolvendo le promise tramite *then*. Al posto di avere della funzioni annidate si hanno delle catene di callback utilizzate nel caso la promise si risolva o rifiutata.

```javascript
findUserById("john").then(user => {
    return findPic(user.picId);
})
.then(pic => ui.show(pic));
```
Non è possibile però ottenere un computazione parziale in quando si ha a disposizione il solo risultato fine (o l'errore)  ed è difficile racchiudere il concetto di tempo.

# Composizione di promise

In molte librerie le promise sono astrazione ad alto livello che permetto non il loro concatenamento ma anche il passaggio come parametro e la composizione delle loro operazioni (al contrario delle callback).

Ad esempio in `Q` (libreria Javascript): è possibile definire ad alto livello un promise e dichiarare successivamente un methodo di spread per la risoluzione ricorsiva delle promise di ogni singola chiamata.

Altri framework supportano le promise come `AngularJS`, `Dart`.

# Reactive programming e reactive extension: concetti principali, tecniche di programmazione, esempi

La programmazione reattiva nasce dall'esigenza di gestire, in una applicazione, degli stream di dati/eventi e non solo un singolo risultato come nella programmazione asincrona (sia CSP che con le promise): l'obiettivo è ottenere una programmazione asincrona per stream di dati.

Il paradigma viene introdotto negli anni '90 ed è orietata al flusso di dati e alla propagazione del cambiamento, fortemente legata al pattern *Observer* e alla programmazione event-driven (molto interessante per ciò che riguarda Big Data e responsive web app). Alcuni linguaggi supportano la programmazione reattiva o hanno a disposizione delle estensioni: `RxJava`, Flapjax, AngularJS, DART.

Gli eventi che variano nel tempo costituiscono l'astrazione nella RP e che devono essere consumati nell'esecuzione del programma. Esistono due tipologie abstraction:

- *event stream*: valori discreti o continue che variano nel tempo, dati sequenziali da un evento ricorrente (eventi del mouse).

- *segnali (o behaviours)*: valori continui nel tempo derivati da una fonte che produce dati in maniera costante e non interrotta (un timer).

La programmazione reattiva permette in maniera dichirativa di lavorare con stream ed eventi.

In quanto si tratta di un flusso continuo di dati una espressione che dipende da questi dati deve essere essa stessa reattiva: l'aggiornamento dei dati dello stream influenzerà anche questa espressione. Questo processo è detto di **lifting**.

Un altro punto forse della programmazione reattiva è la possibilità di combinare i vari stream evitando il *callback hell* tramite funzione di `merge`, `zip`, etc.

Una estensione reattiva (**RX**) ha il compito di integrare gli aspetti della RP nei linguaggi:

- esecuzione asincrona basata su eventi

- composizione

- collezioni osservabili: astrarre la computazione asincrona come un sorgente di dati osservabili (movimento del mouse come un database di movimenti e click) e quindi fare una operazione di *subscribe*.

Esistono estensioni per ogni linguaggio di programmazione: `RxJS`, `RxPy`, `RxJava`, `RxScala`, ...

`RxJava` è una implementazione di una estesione reattiva per la JVM sviluppata originariamente da Netflix; estende il pattern observer per supportare una sequenza di dati od eventi e la possibilità di composizione tramite operatori senza dover lavorare con sincronizzazione, thread, strutture dati, I/O, ...

# Data parallelism con GPGPU e OpenCL - concetti principali

La computazione parallela di basa sulla macchina astratta SIMD e quindi necessita di un quantitativo di processori o co-processori notevole per rendere al meglio la computazione efficiente (FPGA, GPU).

La computazione su GPU anche detta **GPGPU** (*General Purpose computing on Graphic Processor Unit*) sfrutta l'architettura hardware delle GPU per eseguire calcoli molto velocemente ed in maniera parallela (x100 rispetto alla CPU, la Titan X ha 3072 CUDA Core e 336,6 GB/s di banda e produce 6,691 GFlops).

Le GPU hanno una organizzazione della memoria diversa a quella principale:

- globale: memoria principale con dimensioni più elevate (off-chip) e disponibile a tutte le unità di lavoro

- locale: riservata per ogni thread, riservata in quella globale (sostitutiva dei registri se pieni)

- condivisa: accessibile dai thread dello stesso blocco, velocità pare ad una cache L1 e con poca latenza

- costante: dove vengono caricati i dati dalla CPU in sola lettura con visibilità globale

- registri: minor latenza e minor dimensioni, visibili solo dal thread che li utilizza

- texture: memoria cached con visibilità globale e in sola lettura

E una gestione della computazione diversa:

- thread: codice concorrente che esegue sul device, unità atomica (molto leggeri rispetti ai thread CPU)

- blocco: gruppo di thread che eseguono insieme e formano l'unità di assegnamento delle risorse; possono comunicare sincronizzarsi, i blocchi sono assegnati agli *Stream Multiprocessor*

- warp: gruppo di 32 thread che vengono creati, schedulati ed eseguiti su un multiprocessore, condivisono lo stesso program counter (SIMD).

- griglia: collezione di blocchi che devono essere completati prima di passare alla fase successiva

- kernel: funzione chiamata dall'host che esegue su GPU; la chiamata ad un kernel lancia in esecuzione di una griglia di blocchi di thread.

Il codice di un programma per GPGPU deve essere prima compilato da un compilatore che divida il codice tra host e device.

# Data parallelism per BigData - concetti principali, Big-Data-Cube

Applicare la computazione parallela ad un enorme dimensioni di dati (cluster e cloud). L'obiettivo è quello di poter elaborare facilmente questi dati per fare della analisi.

La raccolta dei BigData è la prima fase assieme a delle attività di filtering, pulizia e integrazione; questi dati sono poi utilizzati come traning e test per un modello. Una volta validato il modello i dati vengono utilizzati per generare previsioni, raccomandazioni, analisi di mercato, etc.

La caratteristiche dei BigData sono racchiuse dalle *4 V*: varietà, velocità, volume e veracità (fiducia).

# MapReduce - modello e implementazione in Hadoop

**MapReduce** è una tecnica introdotta da Google per il processamento dei BigData ed implementata da **Hadoop**.

Dividere un algoritmo in due step:

* una `map` su una struttura dati

* una operazione di riduzione

La parallelizzazione sta nel distribuire la computazione in diversi cluster di computer. La divsione del lavoro avviene in maniera automatica e continua anche in caso di failure di una macchina.

Inizialmente i dati sono divisi in sezioni, ognuna processato da macchine diverse divise tra `mapper` e `recuers`: i primi prendono le sezioni dei dati e li mappano in strutture chiave/valore, i secondi convertono le coppie chiave/valore nel formato finale di output operando le opportune operazioni. Tipicamente tutte le coppie con la stessa chiave sono processare dallo stesso `reducer`.

La gestione delle failure è gestita da un master che periodicamente controlla lo stato degli slave; se non ci sono risposte allora il nodo viene segnato come `failed` e ogni task assegnato a lui viene resettato ed assegnato ad un altro nodo.

# Real-time data stream processing - Storm

Storm è introdotto da Nathan Marz per operare su BigData continui e quindi enormi stream di dati in realt-time. **Storm** ha l'obiettivo di essere estramamente performance ed affidabile: implementa un MOM (Message Oriented Middleware, `ZeroMQ`) per lo scambio di messaggi e permette lo scambio di messaggi direttamente tra i task (no accodamento di messaggi) tramite serializzazioni e deserializzazioni. La gestione delle failure permette a storm di garantire che ogni messaggio sia processato dalla topologia del sistema e se una tupla non viene processata allora è automaticamente reinviata così come per i messaggi ad un task down.

I principali elementi del modello Storm sono:

* **Spouts**: sorgente di stream

* **Bolts**: processa un qualunque numero di stream e produce un qualunque numero di strem di output e racchiudono la maggior parte della logica computazionale (funzioni, filtri, join, aggregazioni, ...)

* **Topologie**: una rete di spouts e bolts

# Paradigma ad agent

Un agente rappresenta una entita autonoma che può interagire con l'environment (sia fisico che logico) per eseguire dei task. Interagisce con l'ambiente tramite azioni e percezioni e comunica con altri agenti tramite message passing; favorisce una architettura distribuita.

Rispetto agli attori gli agenti sono orientati al task/obiettivo e hanno un comportamento reattivo e proattivo

