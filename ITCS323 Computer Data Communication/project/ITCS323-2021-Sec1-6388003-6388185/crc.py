import argparse
from functools import reduce

CRC_METHOD_DIVISOR_TABLE = {
    "CRC-32": bin(2**32 + 2**26 + 2**23 + 2**22 + 2**16 + 2**12 + 2**11 + 2**10 + 2**8 + 2**7+ 2**5 + 2**4 + 2**2 + 2 + 1)[2:],
    "CRC-24": bin(2**24 + 2**23 + 2**14 + 2**12 + 2**8 + 1)[2:],
    "CRC-16": bin(2**16 + 2**15 + 2**2 + 1)[2:],
    "Reversed CRC-16": bin(2**16 + 2**14 + 2 + 1)[2:],
    "CRC-8": bin(2**8 + 2**7 + 2**6 + 2**4 + 2**2 + 1)[2:],
    "CRC-4": bin(2**4 + 2**3 + 2**2 + 2 + 1)[2:],

    "test": bin(2**3 + 2**2 + 1)[2:] # to match in the slide
}

def check_all_zeros(s:str) -> bool:
    """
    Check if every string is a "0" character.
    Using map and reduce from functional programming, 
    bc it ways more cooler
    """
    return reduce(lambda x, y: x and y, map(lambda x : x == "0", s))

def xor_bitwise_string(a:str, b:str, padding=False) -> str:
    len_a = len(a)
    assert len_a == len(b)
    c = bin(int(a, base=2) ^ int(b, base=2))[2:] # exclude 0b
    return "0"*padding*(len_a-len(c)) + c

def modulo2_division(dividend:str, divisor:str, verbose:bool=False) -> str:
    remainder_len = len(divisor) - 1
    remainder = dividend[0:remainder_len]
    if verbose:
        print("modulo 2 division")
        print(" "*len(divisor) + " " + "_"*len(dividend))
        print(divisor + "|" + dividend)
    i = 0
    for i, v in enumerate(dividend[remainder_len:]):
        remainder = remainder + v
        if remainder[0] == "1":
            if verbose and i > 0:
                print(" "*len(divisor) + " "*(i+1) + remainder)
            remainder = xor_bitwise_string(remainder, divisor, padding=True)[-remainder_len:]
            if verbose:
                print(" "*len(divisor) + " "*(i+1) + divisor + " XOR")
                print(" "*len(divisor) + " "*(i+1) + "-"*len(divisor))
        else: 
            remainder = remainder[-remainder_len:] 
    if verbose:
        print(" "*len(divisor) + " "*(i+2) + remainder + " <-- CRC or Syndrome", end="\n\n")

    return remainder

def crc_gen(dataword:str, word_size:int, crc_type:str, verbose:bool=False) -> str:
    assert word_size >= 5 and len(dataword) <= word_size # the constrains stated in the assignment
    divisor = CRC_METHOD_DIVISOR_TABLE[crc_type]
    dataword = "0"*(word_size-len(dataword)) + dataword # pad zero to fill word_size (unnessary)
    dividend = dataword + "0"*(len(divisor)-1) # pad zero for crc
    crc = modulo2_division(dividend, divisor, verbose)
    codeword = dataword + crc
    return codeword

def crc_checker(codeword:str, crc_type:str, verbose:bool=False) -> int:
    divisor = CRC_METHOD_DIVISOR_TABLE[crc_type]
    syndrome = modulo2_division(codeword, divisor, verbose)
    return 1 if check_all_zeros(syndrome) else 0

def main(data_or_codeword:str, crc_type:str, check:bool, word_size:int, verbose:bool=False):
    if not check:
        if not word_size:
            word_size = len(data_or_codeword) # default value
        if verbose:
            print("Generate " + "0"*(word_size-len(data_or_codeword)) + data_or_codeword + "'s " + crc_type)
            print("Codeword: ", end="")
        print(crc_gen(data_or_codeword, word_size, crc_type, verbose))
    else:
        if verbose:
            print("Checking", data_or_codeword, "with", crc_type)
            print("is codeword valid: ", end="")
        print(crc_checker(data_or_codeword, crc_type, verbose)) 

if __name__ == "__main__":
    parser = argparse.ArgumentParser(description="CRC generator or checker")
    parser.add_argument("dataorcodeword", help="dataword or codeword to be generate codeword or to be check", type=str)
    parser.add_argument("CRC_Type", help="Type of CRC. Valid value are " + str(list(CRC_METHOD_DIVISOR_TABLE.keys())), type=str)
    parser.add_argument("--word_size", help="Maximum size of dataword. (default word_size = len(datacode)) (will be ignore if --check is on)", type=int)
    parser.add_argument("--check", help="Use this flag if you want to check if the codeword is valid", action="store_true")
    parser.add_argument("-v", "--verbose", help="Verbosity", action="store_true")
    args = parser.parse_args()
    main(
        data_or_codeword=args.dataorcodeword, 
        crc_type=args.CRC_Type, 
        verbose=args.verbose, 
        check=args.check, 
        word_size=args.word_size
    )
