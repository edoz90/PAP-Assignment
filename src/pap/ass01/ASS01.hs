-- Data una lista di elementi Elem così definiti: --
data Elem = Dot | Star deriving (Show)

-- Elem array for tests --
test1 = [Dot,Star,Dot,Star,Dot,Star,Star,Star]
test2 = [Dot, Dot, Dot, Dot, Star, Star, Star, Dot, Dot, Star, Dot, Star, Dot, Star, Star, Star, Dot]
test3 = [Dot, Star, Star, Star, Dot, Star, Dot, Star, Dot, Dot, Dot, Dot, Dot, Star, Star, Star, Dot, Dot, Dot]

-- .*.*.***
-- ....***..*.*.***.
-- .***.*.*.....***...

data BSTree a = Nil | Node a (BSTree a) (BSTree a)
-- BSTree for tests
testTree1 = (Node Star
				(Node Star
					(Node Star
						(Node Dot Nil Nil)
						(Nil)
					)
					(Node Star Nil Nil)
				)
				(Node Star
					(Node Star
						(Node Star Nil Nil)
						(Nil)
					)
					(Nil)
				)
			)

testTree2 = (Node Dot
				(Node Star
					(Node Star
						(Nil)
						(Node Dot Nil Nil)
					)
					(Node Dot
						(Node Star
							(Node Star Nil Nil)
							(Node Dot
								(Node Star Nil Nil)
								(Nil)
							)
						)
						(Node Dot Nil Nil)
					)
				)
				(Node Dot Nil Nil)
			)


-- conta il numero di elementi Star presenti nella lista passata come parametro --
countStar :: [Elem] -> Int
countStar [] = 0
countStar (Star:t) = 1 + countStar t
countStar (_:t) = countStar t

-- restituisce una rappresentazione testuale della sequenze, ove Dot è rappresentato dal carattere ‘.’ e Star dal carattere ‘*’.
-- Questa funzione è utile ogni volta si voglia stampare una lista di Elem.
printableSeq :: [Elem] -> [Char]
printableSeq [] = ""
printableSeq (Star:t) = '*' : printableSeq t
printableSeq (Dot:t) = '.' : printableSeq t

-- restituisce una sequenza pari a quella passata l in cui ogni valore Dot è sostituito con uno Star e viceversa. --
swapSeq :: [Elem] -> [Elem]
swapSeq [] = []
swapSeq (Star:t) = Dot : swapSeq t
swapSeq (Dot:t) = Star : swapSeq t

-- data una lista di Elem restituisce una lista in cui le sequenze di 1 o più Dot sono sostituite da un solo Dot. --
zipSeq :: [Elem] -> [Elem]
zipSeq [] = []
zipSeq (h:[]) = [h]
zipSeq (Star:t) = Star : zipSeq t
zipSeq (Dot:(Dot:t)) = zipSeq (Dot:t)
zipSeq (Dot:(Star:t)) = Dot : (Star : zipSeq t)

-- computa la lunghezza della sequenza di Star più lunga --
countStarSeq :: [Elem] -> Int
countStarSeq [] = 0
countStarSeq (Star:Dot:t) = 1
countStarSeq (Star:t) = 1 + countStarSeq t
countStarSeq (Dot:t) = countStarSeq t

maxStarSeq :: [Elem] -> Int
maxStarSeq [] = 0
maxStarSeq (h:t) = max (countStarSeq (h:t)) (maxStarSeq t)

-- date due liste di Elem restituisce vero se le liste contengono le medesime sequenze di Star in ordine,
-- a prescindere dal numero di Dot in mezzo
matchSeq :: [Elem] -> [Elem] -> Bool
matchSeq [] [] = True
matchSeq (Dot:t) x = matchSeq t x
matchSeq x (Dot:t) = matchSeq x t
matchSeq _ [] = False
matchSeq [] _ = False
matchSeq (Star:t) (Star:t2) = matchSeq t t2

-- determina una lista delle tuple a 2 elementi, in cui il primo indica la lunghezza di una sequenza di Star presente
-- nella lista e il secondo la lista delle posizioni in cui tale sequenza compare nella lista (la prima posizione è 1).
getStarPos :: Int -> Int -> [Elem] -> [Int]
getStarPos _ _ [] = []
getStarPos occ pos (Dot:Star:t)
	-- se il numero di occorrenze è uguale al numero di Star restituisco la posizione della Star
	| occ == countStarSeq (Star:t) = (pos + 1) : getStarPos occ (pos + 2) t
	| otherwise = getStarPos occ (pos + 2) t
getStarPos occ pos (_:t) = getStarPos occ (pos + 1) t

removeStar :: Int -> Int -> [Elem] -> [Elem]
removeStar _ _ [] = []
-- se c'è un Dot lascio così
removeStar occ _ (Dot:t) = Dot:removeStar occ 0 t
removeStar occ index (Star:t)
	-- se c'è una Star la sostituisco con un Dot, come per le Star successive
	| (index + countStarSeq (Star:t)) == occ = Dot:(removeStar occ (index + 1) t)
	| otherwise = Star:(removeStar occ (index + 1) t)

occ :: [Elem] -> [(Int, [Int])]
occ x = if countStar x > 0
			-- se ci sono Star creo la mappa NumStar, [Positions], poi devo rimuovere le star appena prese in considerazione
			then (numStar, getStarPos (numStar) 0 (Dot:x)) : occ (removeStar numStar 0 x)
        	else []
        	where
        		numStar = countStarSeq x
-- Esempio: occ [Dot,Star,Dot,Star,Dot,Star,Star,Star] è pari a [(1,[2,4]), (3,[6])] --

-- conta il numero di elementi Star presenti nell'albero t --
countStarInTree :: BSTree Elem -> Int
countStarInTree Nil = 0
countStarInTree (Node Star sx dx) = 1 + countStarInTree sx + countStarInTree dx
countStarInTree (Node Dot sx dx) = countStarInTree sx + countStarInTree dx

-- determina la lunghezza del ramo più profondo composto da soli elementi Star --
pathTree :: BSTree Elem -> Int
pathTree Nil = 0
pathTree (Node Star t1 t2) = 1 + (max (pathTree t1) (pathTree t2))
pathTree (Node Dot t1 t2) = 0