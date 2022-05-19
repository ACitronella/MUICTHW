# 1a. A program that can take a square root of a number using Taylor Series expansion method. 
# Stop the iteration of the program using stopping criterion. 
# Test with the square root of the number 58.25. 

# 1b. A program that can take a cube root of a number using Taylor Series expansion method.   
# Stop the iteration of the program using stopping criterion. 
# Test with the square root of the number 58.25. 

from hw3 import fac
from functools import lru_cache
import numpy as np


# @lru_cache(100)
def n_choose_k(n, k):
    a = np.prod(np.arange(n-k+1, n+1))
    return a / fac(k)

def binomial_taylor_series(x, alpha, sc=5E-1, verbose=False):
    # (1 + x)^alpha, |x| < 1
    epsilon_a = float("inf")
    v = 0
    n = 0
    if verbose:
        print("\t\ta\t\tepsilon_a")
    while epsilon_a >= sc:
        new_v = v + n_choose_k(alpha, n) * np.power(x, n)
        epsilon_a = np.abs((new_v - v) / new_v) * 100.0
        v = new_v
        if verbose:
            print("taylor%d:\t%6.6f\t%6.6f" % (n, v, epsilon_a))
        n += 1
    return v

def nth_rt_from_binom(x, n, sc=5E-1, verbose=False):
    if x == 0.:
        if verbose:
            print("x is 0.")
        return 0
    if 1. < x < 2.:
        if verbose:
            print("x is in range (1, 2].")
        return binomial_taylor_series(x - 1., 1./n, sc=sc)

    if verbose:
        print("x is not in range (0, 2].\nusing method finding s.\nwhere\ns, integer that satisfied x^(1/%d) = s * a^(%d) such that a in range (0, 2)" % (n, n))

    for s in range(2, int(x) + 1):
        a = x / np.power(s, n)
        if 1. < a < 2.:
            if verbose:
                print("select a = %f" % a)
                print("select s = %d" % s)
            o = binomial_taylor_series(a - 1., 1./n, sc=sc, verbose=verbose)
            return s * o
    # some error occur
    return -1


mode_available = ["sqrt", "crt"]

def main(mode, n, sc, verbose=False):
    try:
        mode = mode.lower()
        n = float(n)
        sc = float(sc)
        y = 0
        
        if mode == mode_available[0]:
            if verbose:
                print("finding %s of %f with sc = %f" % (mode, n, sc))    
            y = nth_rt_from_binom(n, 2, sc, verbose)
        elif mode == mode_available[1]:
            if verbose:
                print("finding %s of %f with sc = %f" % (mode, n, sc))
            y = nth_rt_from_binom(n, 3, sc, verbose)
        else:
            raise ValueError()
        if verbose:
            print("%s(%f) ~>" % (mode, n), y)
        else:
            print(y)

    except Exception:
        print("==============INVALID INPUT==============")
        print("mode: %s must be \"sqrt\" or \"crt\"" % str(mode))
        print("n: %s must be float" % str(n))
        print("sc: %s must be float" % str(sc))

if __name__ == "__main__":
    d_sc = "5E-3"

    from argparse import ArgumentParser
    parser = ArgumentParser()
    parser.add_argument("mode", help="%s only" % (str(mode_available)), type=str)
    parser.add_argument("n", help="number to be $mode ed", type=str)
    parser.add_argument("-sc", help="stopping criteria, as its lower, precision goes higher. Default is %s" % (d_sc), type=str, default=d_sc)
    parser.add_argument("-v", "--verbose", help="set verbosity", action="store_true")
    args = parser.parse_args()

    main(args.mode, args.n, args.sc, args.verbose)

