from math import ceil

# 19 -> 4.3588989

def heron_sqrt(n, stopping_criteria):
    x = float("0.0001")
    for i in range(ceil(n/2)+1, 0, -1):
        if i * i < n:
            x = i
            break
    i = 0
    epsilon_a = float("inf")
    while epsilon_a > stopping_criteria:
        print("x%d = %f" % (i, x))
        xn = float("0.5") * (x + n / x)
        i += 1
        epsilon_a = abs((xn - x)/xn) * 100.0
        x = xn
    print("x%d = %f" % (i, x))
    return x

def main(n, sc):
    x = heron_sqrt(float(n), float(sc))
    print(x)

if __name__ == "__main__":
    from argparse import ArgumentParser
    parser = ArgumentParser()
    parser.add_argument("n", help="number to be square rooted", type=str)
    parser.add_argument("-sc", help="stopping criteria, as its lower, precision goes higher. Default is 5E-1", type=str, default="5E-1")
    args = parser.parse_args()
    main(args.n, args.sc)
