# Assignment #3 - 20150401

## Esercizi su Lambda Expression e Stream

Sono date le classi `P2d` e `V2d` nel package `pap.ass03` che rappresentano rispettivamente un punto e un vettore all'interno di un piano dato da una viewport di una  finestra grafica (che si può pensare avere come estremi (0,0) come angolo in alto a sinistra e (w,h) con w > 0, h > 0, come angolo in basso a destra), e la classe `BBox` che rappresenta un bounding box rettangolare (definito da due vertici, quello in alto a sinistra e quello in basso a destra).

Definire nello stesso package l'interfaccia Shape, caratterizzata dai metodi:

* `void move(V2d dv)`: trasla la figura di un certo vettore, passato come parametro
* `double getPerim()`: computa il perimetro della figura
* `bool isInside(BBox bbox)`: verifica se la figura ricade all'interno del bounding box specificato
* `BBox getBBox()`: restituisce il bounding box più piccolo che contiene la figura
* `bool contains(P2d p0)`: verifica se il punto appartiene o meno alla figura

Definire come classi concrete che implementano l'interfaccia `Shape`: `Line` (che rappresenta una linea), `Rect` (rettangolo), `Circle` (circonferenza), `Combo` (composizione di figure)

Definire la classe `Utils` con i seguenti metodi statici, utilizzando opportunamente espressioni Lambda e Stream nella loro implementazione

* `moveShapes`: data una lista di figure e un vettore dv, trasla ogni figura del vettore specificato
* `inBBox`: data una lista di figure e un bounding box, computa la lista delle figure contenute nel bounding box `(p0,p1)`
* `maxPerim`: data una lista di figure, determina il perimetro maggiore
* `shapeWithMaxPerim`: data una lista di figure, determina la figura con perimetro maggiore
* `sortShapesByX`: data una lista di figure, le ordina lungo l'asse `x`
* `contains`: data una lista di figure e un punto, verifica se esiste una figura che contiene il punto
* `getContaining`: data una lista di figure e un punto `p`, computa la lista delle figure che contengono il punto
* `logAll`: data una lista di figure, le stampa in uscita

Implementare un programma `TestShapes` che permetta di testare le funzionalità della classe `Utils`.

Definire la classe `Viewer` che permette di visualizzare una lista di `Shape` in una viewport grafica (basata su un qualsiasi GUI toolkit, es: Swing).
La classe deve implementare l'interfaccia `ShapeViewer` contenuta nel package `pap.ass03`, caratterizzata dai seguenti metodi

* `update(List<Shape> shapes)`: aggiorna l'insieme delle figure da visualizzare

La visualizzazione consiste nel disegno delle figure nella viewport della finestra.

La classe `Viewer` deve inoltre implementare una semplice funzionalità di selezione delle figure con il mouse, ovvero: clickando in un punto della viewport contenuto in una o più shape, le shape in questione devono essere disegnate di colore diverso (o spessore diverso).

> Suggerimento: fare riferimento all'evento MouseEvent dei componenti Swing

Implementare un programma `TestShapeViewer` che permetta di testare le funzionalità della classe `Utils`.

## Esercizi di programmazione funzionale in Haskell

Data la struttura dati

```
data StarSeq = Star StarSeq | End
               deriving (Show)
```

che rappresenta sequenze di `Star`, scrivere le funzioni

* `getMaxSeq :: [StarSeq] -> (Int,Int)`: data una lista di `StarSeq` computa lunghezza e posizione della sequenza più lunga
* `printSeqs :: [StarSeq] -> IO ()`: data una lista di `StarSeq`, stampa in uscita le sequenze in ordine crescente di lunghezza, rappresentandole come sequenze di `*`, una sequenza per ogni linea

Per ognuna delle funzioni specificate,  definire opportuni test per verificarne il corretto funzionamento.
