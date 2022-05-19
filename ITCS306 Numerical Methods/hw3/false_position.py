# 3.  Solve function f(x) = x^3 -6x^2 + 4x + 12 = 0 (find all x's that makes f(x) = 0) 
# between region [-2, 6] using the False Position method implemented as 
# a simple while loop and a recursive function.   
# Since the region [-2, 6] has multiple roots, 
# you should use the interval search method to locate small intervals 
# that contains single roots first and then apply the False Position method.  
# Check your answer using wolframalpha.com.

from interval_search import roots_searching, f, E_S

def false_position_iter(f, x, xn, sc=E_S, verbose=False, **kwargs):
    epa = float("inf")
    current_root = 0
    old_root = float("inf")
    n = 0
    if verbose:
        print("\tstart\t\tend\t\tcurrent root\tepsilon a")
        
    while sc < epa:
        fx = f(x)
        current_root = x - fx * (x - xn) / (fx - f(xn))
        epa = abs((current_root - old_root)/current_root) * 100.
        if verbose:
            print("iter%d:\t%f\t%f\t%f\t%f" % (n, x, xn, current_root, epa))    
        old_root = current_root
        if abs(current_root - x) < abs(current_root - xn):
            x = current_root
        else:
            xn = current_root
        n += 1

    return current_root

def false_position_recur(f, x, xn, sc=E_S, verbose=False, **kwargs):
    if "old_root" not in kwargs:
        old_root = float("inf")
        n = 0
        if verbose:
            print("\tstart\t\tend\t\tcurrent root\tepsilon a")
    else:
        old_root = kwargs["old_root"]
        n = kwargs["n"] + 1

    fx = f(x)
    current_root = x - fx * (x - xn) / (fx - f(xn))
    epa = abs((current_root - old_root)/current_root) * 100.
    if verbose:
        print("stac%d:\t%f\t%f\t%f\t%f" % (n, x, xn, current_root, epa))    
    if sc > epa:
        return current_root
    old_root = current_root
    if abs(current_root - x) < abs(current_root - xn):
        x = current_root
    else:
        xn = current_root
    return false_position_recur(f, x, xn, sc, verbose, n=n, old_root=old_root)



def main(mode, verbose):
    if mode.lower() == "iter":
        m = false_position_iter
    elif mode.lower() == "recur":
        m = false_position_recur
    else:
        print("invalid mode value")
        return
    roots = roots_searching(m, f, -2, 6, verbose=verbose)
    for r in roots:
        print("root found at x ~", r)

import math

if __name__ ==  "__main__":
    from argparse import ArgumentParser
    parser = ArgumentParser()
    parser.add_argument("mode", type=str, help="select mode of bisection [iter, recur]")
    parser.add_argument("-v", "--verbose", help="set verbosity", action="store_true")
    args = parser.parse_args()
    main(args.mode, args.verbose)
    # print(false_position_iter(lambda x: math.exp(x) - 100, 3, 8, verbose=True))
    
