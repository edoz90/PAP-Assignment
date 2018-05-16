# Assignment #2 - 20150325 - Functional Programming in Haskell/2

Sono dati i tipi

`type P2d = (Int, Int)`

`type V2d = (Int, Int)`

che rappresentano punti e vettori all'interno di un piano dato da una viewport di una finestra grafica (che si può pensare avere come estremi (0,0) come angolo in alto a sinistra e  (w,h), w > 0, h > 0, come angolo in basso a destra).

Definire il tipo di dato algebrico Shape che rappresenta una figura geometrica nella viewport e che può essere:

    una linea, caratterizzata da due vertici
    un triangolo, caratterizzato da tre vertici
    un rettangolo, caratterizzato da due vertici (alto sx, basso dx)
    una circonferenza, caratterizzato da centro e raggio
    una composizione, come insieme di figure

Definire la classe CShape con funzioni

    `perim`: data una CShape calcola il perimetro

    `move`: data una CShape e un vettore, calcola la nuova shape spostata del vettore 

e quindi dichiarare Shape come istanza di CShape, implementando le funzioni corrispondenti.

Implementare poi le seguenti funzioni, usando funzioni high-order:

    `moveShapes`: data una lista di figure, computa la lista di figure traslate di un vettore dv

    `inBBox`: data una lista di figure e una coppia di vertici p0 p1, computa la lista delle figure contenute nel bounding box p0 p1, dove p0 è il vertice in alto, a sinistra e p1 è quello in basso a destra

    `maxArea`: data una lista di figure, determina la figura con area maggiore

    `makeShapeTree`: data una lista di figure, costruisce un albero binario di ricerca con le figure ordinate lungo l'asse x

Definire la classe Drawable che estende CShape con funzioni:

    `draw`: che data una shape, produce un'azione il cui effetto è di disegnare la figura

(Ri-)Definire Shape come istanza di Drawable, in cui siano disegnabili in modo text considerando come viewport quella dello schermo, usando la libreria Screen fornita nei sorgenti del corso.  Quindi implementare la funzione

    `drawAll`: data una lista di figure, produca un'azione il cui effetto è di disegnare sullo schermo tutte le figure della lista
