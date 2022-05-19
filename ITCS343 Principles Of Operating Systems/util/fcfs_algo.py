
def main():
    s = input()
    l = input_interpret(s)
    print(fcfs_distance(l))

def input_interpret(s:str) -> "list[int]":
    l = []
    if "," in s:
        l = list(map(lambda x : int(x.strip()), s.split(",")))
    else:
        l = list(map(lambda x : int(x.strip()), s.split()))
    return l

def fcfs_distance(positions:"list[int]") -> int:
    return sum(map(lambda x, y: abs(x - y), positions, positions[1:]))


if __name__ == "__main__":
    main()