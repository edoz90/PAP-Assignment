/* spin -run dekker.promela */
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

/*

State-vector 28 byte, depth reached 39, errors: 0
       97 states, stored
       75 states, matched
      172 transitions (= stored+matched)
        1 atomic steps
hash conflicts:         0 (resolved)

Stats on memory usage (in Megabytes):
    0.005	equivalent memory usage for states (stored*(State-vector + overhead))
    0.288	actual memory usage for states
  128.000	memory used for hash table (-w24)
    0.534	memory used for DFS stack (-m10000)
  128.730	total actual memory usage

*/
