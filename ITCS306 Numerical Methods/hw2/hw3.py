
# 3. Find the n-term approximation of a function (T_n(x)) of sin(x) where n = 2, 4, 6, 8, 10.  
# Plot T_n(x) against sin(x) in the way similar to the figure in slide 34.

import numpy as np
import matplotlib.pyplot as plt
# from sympy import Symbol 

# for cache value of factorial
__fac_list__ = [1., 1., 2.]
def fac(x):
    global __fac_list__
    if len(__fac_list__) > x:
        return __fac_list__[x]
    else:
        __fac_list__.append(x * fac(x - 1)) # before it can append to the list, it must solve call stack first.
    return __fac_list__[x]


# generate family of taylor series of sin(x), where x = 0
def sine_taylor_series_generator_n(n): # AKA T_n(x)
    def sin_n(x):
        v = 0
        z = 0
        for i in range(1, n, 2):
            # skip even power, since sin(0) = 0, sin''(0) = -sin(0) = 0, ...
            if i % 2 != 0:
                v = v + np.power(-1, z) / fac(i) * np.power(x, i)
                z = 1 - z # value oscillate between 0 and 1
        return v
    return sin_n


def main():
    x = np.linspace(-2*np.pi, 2*np.pi, 100)
    plt.figure(figsize=(15, 15))
    
    for n in range(2, 11, 2):
        sin_n = sine_taylor_series_generator_n(n)
        plt.plot(x, sin_n(x), label="T_%d(x)" % n)
    plt.title("Taylor's series of sine function")
    plt.legend()
    plt.grid()
    plt.ylim(-2, 2)
    plt.show()
    plt.clf()


if __name__ == "__main__":
    main()
