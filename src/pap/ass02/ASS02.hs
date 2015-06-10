import GHC.Float
import Screen
import Data.List

-- Type Point and Vector
type P2d = (Int, Int)
type V2d = (Int, Int)

-- Data Shape (i valori dei punti saranno convertiti in positivi per evitare errori)
data Shape = Line P2d P2d | Triangle P2d P2d P2d | Rectangle P2d P2d | Circle P2d Int | Comp [Shape] | Void
	deriving (Show)
data BSTree a = Nil | Node a (BSTree a) (BSTree a)
	deriving (Show)

testComp = Comp [(Triangle (1,1) (5,5) (3,3)), (Rectangle (2,2) (5,5)), (Circle (6,6) 3)]
testList = [(Triangle (1,1) (5,5) (3,3)), (Rectangle (2,2) (5,5)), (Circle (6,6) 3)]

-- UTILS
distance :: P2d -> P2d -> Double
distance (ax, ay) (bx, by) = sqrt(a + b)
							 where
							 a = int2Double(abs(ax - bx)^2)
							 b = int2Double(abs(ay - by)^2)

movePoint :: P2d -> P2d -> P2d
movePoint (ax, ay) (bx, by) = (abs(ax) + abs(bx), abs(ay) + abs(by))

checkPointTop :: P2d -> P2d -> Bool
checkPointTop (ax, ay) (px, py) = (abs(ax) >= abs(px)) && (abs(ay) >= abs(py))

checkPointBottom :: P2d -> P2d -> Bool
checkPointBottom (ax, ay) (px, py) = (abs(ax) <= abs(px)) && (abs(ay) <= abs(py))

-- p0 < Shape < p1
inBox :: Shape -> P2d -> P2d -> Bool
inBox (Line a b) p0 p1 = checkPointTop a p0 && checkPointTop b p0 && checkPointBottom a p1 && checkPointBottom b p1
inBox (Triangle a b c) p0 p1 = checkPointTop a p0 && checkPointTop b p0 && checkPointTop c p0 && checkPointBottom a p1 && checkPointBottom b p1 && checkPointBottom c p1
inBox (Rectangle a b) p0 p1 = checkPointTop a p0 && checkPointBottom b p1
inBox (Circle (cx, cy) r) p0 p1 = checkPointTop (cx-r, cy-r) p0 && checkPointBottom (cx+r, cy+r) p1
inBox (Comp l) p0 p1 = foldr (\x f -> and [inBox x p0 p1]) True l

insertBSTree :: BSTree Shape -> Shape -> BSTree Shape
insertBSTree Nil a = Node a Nil Nil
insertBSTree (Node s sx dx) a | minX a < minX s = (Node s (insertBSTree sx a) dx)
							  | otherwise	= (Node s sx (insertBSTree sx a))

-- Bresenham
-- https://wiki.haskell.org/Bresenham%27s_line_drawing_algorithm
drawLine :: P2d -> P2d -> [P2d]
drawLine pa@(xa,ya) pb@(xb,yb) = map maySwitch . unfoldr go $ (x1,y1,0)
								 where
    							 steep = abs (yb - ya) > abs (xb - xa)
    							 maySwitch = if steep then (\(x,y) -> (y,x)) else id
    							 [(x1,y1),(x2,y2)] = sort [maySwitch pa, maySwitch pb]
    							 deltax = abs(x2 - x1)
    							 deltay = abs(y2 - y1)
    							 ystep = if y1 < y2 then 1 else -1
    							 go (xTemp, yTemp, error)
        							| xTemp > x2 = Nothing
        							| otherwise  = Just ((xTemp, yTemp), (xTemp + 1, newY, newError))
        							where
          							tempError = error + deltay
          							(newY, newError) = if (2*tempError) >= deltax
                            					   	   then (yTemp+ystep,tempError-deltax)
                            					       else (yTemp,tempError)

-- http://rosettacode.org/wiki/Bitmap/Midpoint_circle_algorithm#Haskell
drawCircle :: P2d -> Int -> [P2d]
drawCircle (x0, y0) radius
  -- Four initial points, plus the generated points
  = (x0, y0 + radius) : (x0, y0 - radius) : (x0 + radius, y0) : (x0 - radius, y0) : points
    where
      -- Creates the (x, y) octet offsets, then maps them to absolute points in all octets.
      points = concatMap generatePoints $ unfoldr step initialValues
      generatePoints (x, y)
        = [(xop x0 x', yop y0 y') | (x', y') <- [(x, y), (y, x)], xop <- [(+), (-)], yop <- [(+), (-)]]

      -- The initial values for the loop
      initialValues = (1 - radius, 1, (-2) * radius, 0, radius)

      -- One step of the loop. The loop itself stops at Nothing.
      step (f, ddf_x, ddf_y, x, y) | x >= y = Nothing
                                   | otherwise = Just ((x', y'), (f', ddf_x', ddf_y', x', y'))
                                     where
                                     (f', ddf_y', y') | f >= 0 = (f + ddf_y' + ddf_x', ddf_y + 2, y - 1)
                                                      | otherwise = (f + ddf_x, ddf_y, y)
                                     ddf_x' = ddf_x + 2
                                     x' = x + 1
-- END UTILS

-- Class CShape
class CShape a where
	perim :: a -> Double 		-- Calculates the perimeter
	move :: a -> V2d -> Shape	-- Move a Shape
	area :: a -> Double
	minX :: a -> Int

-- Instance of CShape for Shape
instance CShape Shape where
	perim (Line _ _) = error "Not Defined"
	perim (Triangle a b c) = distance a b + distance b c + distance c a
	perim (Rectangle (ax, ay) (bx, by)) = 2 * distance (ax, ay) (ax, by) + 2 * distance (ax, ay) (bx, ay)
	perim (Circle (cx, cy) r) | (cx - r >= 0 && cy - r >= 0) = 2 * int2Double(abs(r)) * pi
							  | otherwise = error "Bad Shape"
 	perim (Comp l) = foldr (\x sum -> perim x + sum) 0 l

	move (Line a b) v = Line (movePoint a v) (movePoint b v)
	move (Triangle a b c) v = Triangle (movePoint a v) (movePoint b v) (movePoint c v)
	move (Rectangle a b) v = Rectangle (movePoint a v) (movePoint b v)
	move (Circle a r) v = Circle (movePoint a v) r
	move (Comp l) v = Comp (map (\x -> move x v) l)

	area (Line _ _) = error "Not Defined"
	area (Triangle a b c) = sqrt (semiPerim * (semiPerim - distance a b) * (semiPerim - distance b c) * (semiPerim - distance a c))
							where semiPerim = perim (Triangle a b c) / 2
	area (Rectangle (ax, ay) (bx, by)) = distance (ax, ay) (ax, by) * distance (ax, ay) (bx, ay)
	area (Circle (cx, cy) r) | (cx - r >= 0 && cy - r >= 0) = pi * int2Double(abs(r))^2
							 | otherwise = error "Bad Shape"
	area (Comp l) = foldr (\x sum -> area x + sum) 0 l
	area Void = 0

	minX (Line (ax, _) (bx, _)) = min ax bx
	minX (Triangle (ax, _) (bx, _) (cx, _)) = minimum [ax, bx, cx]
	minX (Rectangle (ax, _) (bx, _)) = min ax bx
	minX (Circle (ax, _) r) = ax - r
	minX (Comp (h:t)) = foldl (\x l -> min (x) (minX l)) (minX h) t

-- moveShapes: data una lista di figure, computa la lista di figure traslate di un vettore dv
moveShapes :: [Shape] -> V2d -> [Shape]
moveShapes l v = map (\x -> move x v) l

-- inBBox: data una lista di figure e una coppia di vertici p0 p1,
-- computa la lista delle figure contenute nel bounding box p0 p0,
-- dove p0 è il vertice in alto, a sinistra e p1 è quello in basso a destra
inBBox :: [Shape] -> P2d -> P2d -> [Shape]
inBBox l p0 p1 = filter (\x -> inBox x p0 p1) l

-- maxArea: data una lista di figure, determina la figura con area maggiore
maxArea :: [Shape] -> Shape
maxArea [] = Void
maxArea (h:t) | area h >= area (maxArea t) = h
			  | otherwise = maxArea t

-- makeShapeTree: data una lista di figure, costruisce un albero binario di ricerca con le figure ordinate lungo l'asse x
makeShapeTree :: [Shape] -> BSTree Shape
makeShapeTree [] = Nil
makeShapeTree (x:xs) = insertBSTree (makeShapeTree xs) x

-- Class Drawable
class (CShape a) => Drawable a where
	draw :: a -> IO()

-- Instance of Drawable for Shape
instance Drawable Shape where
	draw (Line a b) = cls >> foldr (>>) (goto (0, 50)) (map (\x -> writeAt x "|") (drawLine a b))
	draw (Triangle a b c) = cls >> foldr (>>) (goto (0, 50)) (map (\x -> writeAt x "|") (drawLine a b)) >>
								   foldr (>>) (goto (0, 50)) (map (\x -> writeAt x "|") (drawLine b c)) >>
								   foldr (>>) (goto (0, 50)) (map (\x -> writeAt x "|") (drawLine c a)) >> beep
	draw (Rectangle (ax, ay) (bx, by)) = cls >> foldr (>>) (goto (0, 50)) (map (\x -> writeAt x "¯") (drawLine (ax, ay) (bx, ay))) >>
								  				foldr (>>) (goto (0, 50)) (map (\x -> writeAt x "|") (drawLine (bx, ay) (bx, by))) >>
								  				foldr (>>) (goto (0, 50)) (map (\x -> writeAt x "_") (drawLine (bx-1, by) (ax, by))) >>
								  				foldr (>>) (goto (0, 50)) (map (\x -> writeAt x "|") (drawLine (ax, by) (ax, ay))) >> beep
	draw (Circle c r) = cls >> foldr (>>) (goto (0, 50)) (map (\x -> writeAt x "*") (drawCircle c r)) >> beep
	draw (Comp l) = foldr (>>) (goto (0, 50)) (map (\x -> draw x) l)

drawAll :: [Shape] -> IO()
drawAll l = foldr (>>) (goto (0, 50)) (map (\x -> draw x) l)