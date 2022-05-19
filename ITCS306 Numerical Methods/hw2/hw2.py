
# 2. A program that can evaluate the value of natural log of 11025 using Taylor Series expansion method.
# Given that you can find the value of e by setting e = limit (1+(1/n))^n as n approaches infinity.   
# Stop the iteration of the program using stopping criterion.  Test your result with a calculator.  
# (Hint: you cannot apply natural log (1+x) formula directly 
# since the formula requires |x| < 1 - refer to the radius of convergence topic I mentioned in class.  
# Instead, you can split the number 11025 into two numbers a and b where a * b = 11025 and make sure a is exp(k) and |b| < 1.
# Then apply basic log property: log(a * b) = log(a) + log(b) and log(exp(k)) = k

import numpy as np

__sudo_inf__ = 1E10
my_e = np.power(1. + (1./__sudo_inf__), __sudo_inf__)
# print(my_e)
def e_x(x):
    # e^x = limit (1+(x/n))^n as n approaches infinity.
    return np.power(my_e, x)

def ln_one_plus_x_taylor_series(x, sc=5E-1, verbose=False):
    # ln(1+X), |x| < 1
    epsilon_a = float("inf")
    v = 0.00000000001
    n = 1
    if verbose:
        print("           a      epsilon_a")
    while epsilon_a >= sc:
        new_v = v + np.power(-1, n+1) * np.power(x, n) / n
        epsilon_a = np.abs((new_v - v) / new_v) * 100.0
        v = new_v
        if verbose:
            print("iter%d: %6.6f %6.6f" % (n, v, epsilon_a))
        n += 1
    return v

def ln(x, sc=5E-1, verbose=False):
    if 0. < x <= 2.:
        if verbose:
            print("x is in range (0, 2].")
        return ln_one_plus_x_taylor_series(x - 1., sc=sc, verbose=verbose)
    if verbose:
        print("x is not in range (0, 2).\nusing method finding s.\n\nwhere\ns, integer that satisfied ln(x) = s + ln(a) and a = x/e^s in range (0, 2]\n")

    for s in range(2, int(x) + 1):
        a = x / e_x(s)
        if 1. < a < 2.:
            if verbose:
                print("select a = %f" % a)
                print("select s = %d" % s)
            o = ln_one_plus_x_taylor_series(a - 1., sc=sc, verbose=verbose)
            return s + o
    return -1


def main(n, sc, verbose):
    try:
        n = float(n)
        sc = float(sc)
        y = ln(n, sc, verbose)
        if verbose:
            print("ln(%f) ~>" % (n), y)
        else:
            print(y)
    except Exception:
        print("==============INVALID INPUT==============")
        print("n: \"%s\" must be float" % str(n))
        print("sc: \"%s\" must be float" % str(sc))
    

if __name__ == "__main__":
    d_sc = 5E-3
    from argparse import ArgumentParser
    parser = ArgumentParser()
    parser.add_argument("n", help="number to be calculate ln(n)", type=str)
    parser.add_argument("-sc", help="stopping criteria, as its lower, precision goes higher. Default is %s" % (d_sc), type=str, default=d_sc)
    parser.add_argument("-v", "--verbose", help="set verbosity", action="store_true")
    args = parser.parse_args()
    main(args.n, args.sc, args.verbose)
