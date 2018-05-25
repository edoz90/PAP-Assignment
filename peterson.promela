/* spin -run peterson.promela */
bool wantp = false, wantq = false;
byte turn = 1;

proctype p() {
    do ::
        wantp = true;
        turn = 2;
        (!wantq || turn == 1)
        printf("Log: p in CS\n");
        wantp = false;
    od
}

proctype q() {
    do ::
        wantq = true;
        turn = 1;
        (!wantp || turn == 2)
        printf("Log: q in CS\n");
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

State-vector 28 byte, depth reached 19, errors: 0
       25 states, stored
       15 states, matched
       40 transitions (= stored+matched)
        1 atomic steps
hash conflicts:         0 (resolved)

Stats on memory usage (in Megabytes):
    0.001	equivalent memory usage for states (stored*(State-vector + overhead))
    0.291	actual memory usage for states
  128.000	memory used for hash table (-w24)
    0.534	memory used for DFS stack (-m10000)
  128.730	total actual memory usage

*/
