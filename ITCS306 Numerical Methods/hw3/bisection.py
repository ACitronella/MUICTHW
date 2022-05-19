# 2. Solve function f(x) = x^3 -6x^2 + 4x + 12 = 0 (find all x's that makes f(x) = 0) 
# between region [-2, 6] using the Bisection method implemented as a recursive function.  
# Since the region [-2, 6] has multiple roots, you should use the interval search method to locate small intervals 
# that contains single roots first and then apply bisection method. 
# Check your answer using wolframalpha.com.

from interval_search import roots_searching, f, E_S

def bisection_recur(f, x, xn, sc=E_S, old_root=float("inf"), verbose=False, **kwargs):
    if "n" in kwargs:
        n = kwargs["n"] + 1
    else:
        n = 0
        if verbose:
            print("\tstart\t\tend\t\tcurrent root\tepsilon a")

    current_root = (x + xn) / 2.
    epa = abs((current_root - old_root) / current_root) * 100
    if verbose:
        print("stac%d:\t%f\t%f\t%f\t%f" % (n, x, xn, current_root, epa))
    if sc > epa:
        return current_root
    if f(x) * f(current_root) < 0.:
        xn = current_root
    else:
        x = current_root
    return bisection_recur(f, x, xn, sc=sc, old_root=current_root, verbose=verbose, n=n)


def bisection_iter(f, x, xn, sc=E_S, verbose=False, **kwargs):
    epa = float("inf")
    current_root = 0.
    old_root=float("inf")
    n = 0
    if verbose:
        print("\tstart\t\tend\t\tcurrent root\tepsilon a")
    while sc < epa:
        current_root = (x + xn) / 2.
        epa = abs((current_root - old_root) / current_root) * 100
        if verbose:
            print("iter%d:\t%f\t%f\t%f\t%f" % (n, x, xn, current_root, epa))
        if f(x) * f(current_root) < 0.:
            xn = current_root
        else:
            x = current_root
        old_root = current_root
        n += 1
    return current_root
    

def main(mode, verbose):
    if mode.lower() == "iter":
        m = bisection_iter
    elif mode.lower() == "recur":
        m = bisection_recur
    else:
        print("invalid mode value")
        return
    roots = roots_searching(m, f, -2, 6, verbose=verbose) 
    for r in roots:
        print("root found at x ~", r)

if __name__ ==  "__main__":
    from argparse import ArgumentParser
    parser = ArgumentParser()
    parser.add_argument("mode", type=str, help="select mode of bisection [iter, recur]")
    parser.add_argument("-v", "--verbose", help="set verbosity", action="store_true")
    args = parser.parse_args()
    main(args.mode, args.verbose)
    
