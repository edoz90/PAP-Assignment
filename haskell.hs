data Node = Node String [String] deriving Show
data Graph = Graph [Node]

-- represent a simple graph, characterized by a set of nodes, each one characterized by an
-- identifier and a list of the outgoing arcs, represented by the identifiers of the nodes
-- that can be directly reached from that node.

-- Implement a function:
-- is_reachable :: String -> String -> Graph -> Bool
-- that, given the identifier of source node S, of a target node T, and a graph G, it
-- checks if T is reachable from S in G.
-- T is reachable from S if either T is among the nodes that are immediately reachable
-- from S or it is reachable from a node  which is immediately reachable from S.

--Example
-- Let
test_graph = Graph [ (Node "a" ["b","c"]),
                     (Node "b" ["a"]),
                     (Node "c" ["d","e"]),
                     (Node "d" ["c","f"]),
                     (Node "e" ["f"]),
                     (Node "f" ["e"])
                    ]
-- then
-- is_reachable "a" "k" test_graph is False
-- is_reachable "a" "b" test_graph is True
-- is_reachable "a" "f" test_graph is True
-- is_reachable "f" "a" test_graph is False

is_reachable :: String -> String -> Graph -> Bool
is_reachable s t g = path s t g []

-- checks the reachability keeping track
-- of the list of the nodes already visited v, to avoid loops
path :: String -> String -> Graph -> [String] -> Bool
path s t g v
    | elem t links = True  -- the node is immediately reachable
    | otherwise = check links t g (s:v) -- check if it is reachable from
    where            
            links = get_links (nodes g) s
            nodes = get_node_list
            
-- this function checks if a node t is reachable from any of the
-- nodes specified in the list passed as first argument,
-- given a graph g and given a list of nodes v that have been already visited
check :: [String] -> String -> Graph -> [String] -> Bool
check [] _ _ _ = False
check (s:xs) t g v
    | elem s v = check xs t g v  -- node already visited, to be skipped
    | path s t g v = True        -- ok, there is path
    | otherwise = check xs t g v -- no path from this node, check nexts


get_node_list :: Graph -> [Node]
get_node_list (Graph l) = l

get_links :: [Node] -> String -> [String]
get_links [] _ = []
get_links ((Node n l):nt) s
    | n == s = l
    | otherwise = get_links nt s


data Sequence = Dot Sequence | End
data BTree = Nil | NodeT (Int,Int) BTree BTree
-- representing sequences of dots, write a function
-- comp_len :: [Sequence] -> [(Int,Int)]
-- that, given a list of sequences L, it computes a  list of elements (len, i)
-- where len is the length of the sequence of index i inside L, ordered by len.
-- The length of a sequence is given by the number of dots Dot inside.
-- > comp_len [ Dot End, Dot (Dot (Dot (Dot End))), Dot (Dot End) ]
--   [(4,1),(2,2),(1,0)]

test_seq = (Dot (Dot (Dot End)))

comp_len :: [Sequence] -> [(Int, Int)]
comp_len l = visit_tree (build_tree l 0)
    where
        visit_tree Nil = []
        visit_tree (NodeT e l r) = (visit_tree r)++[e]++(visit_tree l)

build_tree :: [Sequence] -> Int -> BTree
build_tree [] _ = Nil
build_tree (sh:st) n = insert_tree ((seq_len sh), n) (build_tree st (n+1))

insert_tree :: (Int, Int) -> BTree -> BTree
insert_tree el Nil = NodeT el Nil Nil
insert_tree (i, x) (NodeT (i1, x1) l r)
    | i <= i1 = NodeT (i1, x1) (insert_tree (i,x) l) r
    | otherwise = NodeT (i1, x1) l (insert_tree (i,x) r)

seq_len :: Sequence -> Int
seq_len End = 0
seq_len (Dot s) = 1 + seq_len s
