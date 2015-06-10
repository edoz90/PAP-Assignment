import Data.List
data StarSeq = Star StarSeq | End deriving (Show)

testStar = [(Star (Star End)),
            (Star End),
            (Star (Star (Star End))),
            (Star (Star (Star (Star (Star (Star (Star (Star (Star (Star (Star End))))))))))),
            (Star (Star (Star (Star (Star (Star End)))))),
            (Star End)]


-- Data una lista di StarSeq computa lunghezza e posizione della sequenza più lunga
getMaxSeq :: [StarSeq] -> (Int, Int)
getMaxSeq l = maximum(mapStarSeq l 0)

mapStarSeq :: [StarSeq] -> Int -> [(Int, Int)]
mapStarSeq [] _ = []
mapStarSeq (h:t) y = (countStar h, y) : (mapStarSeq t (y+1))

countStar :: StarSeq -> Int
countStar ((Star x)) = 1 + countStar (x)
countStar (End) = 0

-- Data una lista di StarSeq, stampa in uscita le sequenze in ordine crescente di lunghezza,
-- rappresentandole come sequenze di *, una sequenza per ogni linea
printSeqs :: [StarSeq] -> IO ()
printSeqs l = putStr (printStar (printSort l))

printSort :: [StarSeq] -> [(Int, Int)]
printSort l = (reverse . sort) (mapStarSeq l 0)

printStar :: [(Int, Int)] -> String
printStar [] = "\n"
printStar ((1, _):t) = "*\n" ++ printStar t
printStar ((a, b):t) = '*' : printStar (((a-1), b):t)
