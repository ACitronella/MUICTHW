# 4. Find at least 2 mathematical functions that Bisection method performs better than 
# False Position method (less number of search step until epa < eps) and vice versa 
# (Find at least 2 mathematical functions that False Position method performs better than the Bisection method).  
# Confirm your claims with a program.

from false_position import false_position_iter
from bisection import bisection_iter
from interval_search import roots_searching
import math

def g(x):
    return x**(0.8*x) - 10.5**x

def k(x):
    return (x/10. - 1) ** 50 - x ** (1/60)

def a(x):
    return math.exp(x) - 5

def b(x):
    return math.log(x) - 2

def main():
    print("test g(x) = x^(0.8*x) - 10.5^x, on interval [0, 20]")
    print("bisection: ")
    roots_searching(bisection_iter, g, 15, 25, verbose=True)
    print("false position : ")
    roots_searching(false_position_iter, g, 15, 25, verbose=True)


    print("test k(x) = (x/10 - 1)^50 - x^(1/60), on interval [15, 25]")
    print("bisection: ")
    roots_searching(bisection_iter, k, 15, 25, verbose=True)
    print("false position: ")
    roots_searching(false_position_iter, k, 15, 25, verbose=True)


    print("test a(x) = e^x - 5, on interval [-5, 2]")
    print("bisection: ")
    roots_searching(bisection_iter, a, -5, 2, verbose=True)
    print("false position: ")
    roots_searching(false_position_iter, a, -5, 2, verbose=True)


    print("test b(x) = ln(x) + 5, on interval [1, 10]")
    print("bisection: ")
    roots_searching(bisection_iter, b, 1, 10, verbose=True)
    print("false position: ")
    roots_searching(false_position_iter, b, 1, 10, verbose=True)




if __name__ == "__main__":
    main()