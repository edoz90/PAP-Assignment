data BSTree a = Nil | Node Int (BSTree Int) (BSTree Int)

testTree = (Node 6
                (Node 1
                    (Node 4
                        (Nil)
                        (Node 2 Nil Nil)
                    )
                    (Nil)
                )
                (Node 3
                    (Node 9
                        (Node 3 Nil Nil)
                        (Nil)
                    )
                    (Node 7 Nil Nil)
                )
            )

-- dato un albero binario t di interi e un valore intero d, stampa gli  elementi
-- dell'albero la cui distanza dal nodo radice è pari a d
print_nodes_at_dist :: BSTree Int -> Int -> String
print_nodes_at_dist (Nil) _ = ""
print_nodes_at_dist (Node x _ _) 0 = (show x) ++ " "
print_nodes_at_dist (Node x sx dx) d = print_nodes_at_dist sx (d-1) ++ print_nodes_at_dist dx (d-1)
