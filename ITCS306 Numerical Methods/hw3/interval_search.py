# 1.  Solve function f(x) = x^3 -6x^2 + 4x + 12 = 0 (find all x's that makes f(x) = 0) 
# using the divide and conquer method between region [-2, 6]  
# together with interval search 
# (search for the intervals that should contain the root of the function).  
# Check your answer using wolframalpha.com.

import numpy as np
p = 4
E_S = 0.5 * 10 ** (2 - p)

def f(x):
    return x**3 - 6*x**2 + 4*x + 12

def __interval_search__(f, start:float, end:float, sc:float=0.005, interval_num:int=10, old_root:float=float("inf"), verbose:bool=False):
    s = np.linspace(start, end, interval_num, endpoint=True)
    for x, xn in zip(s, s[1:]):
        if f(x) * f(xn) < 0.:
            current_root = (x + xn)/2.
            epa = abs((current_root - old_root)/current_root) * 100.
            if verbose:
                print("ensure root in interval [%f, %f]" % (x, xn))
            if epa < sc:
                return current_root
            return __interval_search__(f, x, xn, sc, interval_num, old_root=current_root, verbose=verbose)
    
    raise ValueError()

def interval_search(f, start:float, end:float, sc:float=0.005, interval_num:int=10, verbose:bool=False):
    return __interval_search__(f, start, end, sc, interval_num, verbose=verbose, old_root=float("inf"))

def roots_searching(algo, f, start:float, end:float, sc:float=E_S, interval_num:int=10, verbose=False) -> list:
    s = np.linspace(start, end, interval_num, endpoint=True)
    if verbose:
        print("searching in range [%f, %f] with %d sub interval and sc = %f" % (start, end, interval_num, sc))
    roots = []
    
    for x, xn in zip(s, s[1:]):
        if f(x) * f(xn) < 0:
            if verbose:
                print("ensure root in interval [%f, %f]" % (x, xn))
            root = algo(f, x, xn, sc, verbose=verbose, interval_num=interval_num)
            if verbose:
                print("approximate root as", root, "\n")
            roots.append(root)
    return roots

def main(verbose):
    roots = roots_searching(interval_search, f, -2, 6, verbose=verbose)
    for r in roots:
        print("root found at x ~", r)

if __name__ ==  "__main__":
    from argparse import ArgumentParser
    parser = ArgumentParser()
    parser.add_argument("-v", "--verbose", help="set verbosity", action="store_true")
    args = parser.parse_args()
    main(args.verbose)
    