# Assignment #1 - 20150318 - Functional Programming in Haskell.

Data una lista di elementi Elem così definiti:

`data Elem = Dot | Star`

implementare in Haskell le seguenti funzioni:

## `countStar :: [Elem] -> Int`

conta il numero di elementi Star presenti nella lista passata come parametro

### Esempi:

`countStar [] → 0`

`countStar [Dot, Dot] → 0`

`countStar [Dot, Star, Star, Dot, Star ] → 3`

## `printableSeq :: [Elem] -> String`

restituisce una rappresentazione testuale della sequenza, ove Dot è rappresentato dal carattere ‘.’ e Star dal carattere ‘*’. Questa funzione è utile ogni volta si voglia stampare una lista di Elem.

### Esempi:

`printableSeq [] → “”`

`printableSeq [Dot, Dot] → “..”`

`printableSeq [Dot, Star, Star, Dot, Star ] → “.**.*”`

## `swapSeq :: [Elem] -> [Elem]`

restituisce una sequenza pari a quella passata l in cui ogni valore Dot è sostituito con uno Star e viceversa.

### Esempi:

`swapSeq [] → []`

`swapSeq [Dot, Dot] → [Star, Star]`

`swapSeq [Dot, Star, Star, Dot, Star ] → [Star, Dot, Dot, Star, Dot ]`

## `zipSeq :: [Elem] -> [Elem]`

data una lista di Elem restituisce una lista in cui le sequenze di 1 o più Dot sono sostituite da un solo Dot.

### Esempi:

`zipSeq [] → []`

`zipSeq [Dot, Dot] → [Dot]`

`zipSeq [Dot, Star, Star, Dot, Star ] → [Dot, Star, Star, Dot, Star ]`

`zipSeq [Dot, Dot, Dot, Dot] → [Dot]`

`zipSeq [Dot, Dot, Star, Star, Dot, Dot, Dot, Star, Dot ] → [Dot, Star, Star, Dot, Star, Dot ]`

## `maxStarSeq :: [Elem] -> Int`

computa la lunghezza della sequenza di Star più lunga

Esempi:

`maxStarSeq [] → 0`

`maxStarSeq [Dot, Dot] → 0`

`maxStarSeq [Dot, Star, Star, Dot, Star ] → 2`

## `matchSeq :: [Elem] -> [Elem] -> Bool`

date due liste di Elem restituisce vero se le liste contengono le medesime sequenze di Star in ordine, a prescindere dal numero di Dot in mezzo

Esempi:

`matchSeq [] [Dot, Star] → False`

`matchSeq [Star, Dot, Dot, Star, Star, Dot] [Star, Dot, Star, Star, Dot, Dot]  → True`

`matchSeq [Dot, Dot, Star, Star, Dot, Star, Dot] [Star, Star, Dot, Star]  → True`

`matchSeq [Star, Star, Dot, Star] [Star, Star, Dot, Star, Star]  → False`

## `occ :: [Elem] -> [(Int, [Int])]`

determina una lista delle tuple a 2 elementi, in cui il primo indica la lunghezza di una sequenza di Star presente nella lista e il secondo la lista delle posizioni in cui tale sequenza compare nella lista (la prima posizione è la numero 1).

Esempi:

`occ [] → []`

`occ [Dot, Dot] → []`

`occ [Dot,Star,Dot,Star,Dot,Star,Star,Star] →  [(1,[2,4]), (3,[6])]`

`occ [Star, Star, Star, Star,Dot,Star,Star,Star,Star, Dot, Star] →  [(4,[1,6]), (1,[11])]`

## `countStarInTree :: BSTree Elem -> Int`

conta il numero di elementi Star presenti nell’albero passato come parametro

Esempi:

`countStarInTree Nil → 0`

`countStarInTree (Node Dot Nil Nil )  → 0`

`countStarInTree (Node Star (Node Dot Nil Nil) (Node Star Nil Nil))   → 2`

## `pathTree :: BSTree Elem -> Int`

determina la lunghezza del percorso più profondo - a partire dalla radice, fino ad un nodo Dot o ad una foglia - composto da soli elementi Star.

Esempi:

`pathTree Nil → 0`

`pathTree (Node Dot Nil Nil )  → 0`

`pathTree (Node Star (Node Dot Nil Nil) (Node Star Nil Nil))   → 2`

`pathTree (Node Star (Node Dot Nil Nil) (Node Star (Node Dot Nil Nil) (Node Dot Nil Nil)))   → 2`

`pathTree (Node Dot (Node Dot Nil Nil) (Node Star (Node Dot Nil Nil) (Node Dot Nil Nil)))   → 0`
