import argparse
from math import ceil
from functools import reduce

def check_all_zeros(s:str) -> bool:
    """
    Check if every string is a "0" character.
    Using map and reduce from functional programming, 
    bc it ways more cooler
    """
    return reduce(lambda x, y: x and y, map(lambda x : x == "0", s))

def int_to_bin_padded(x:int, out_length:int, cut_left:bool=True) -> str:
    """
    convert $x to bit string and fill zeros to match output length $out_length
    """
    x_bin = bin(x)[2:]
    if len(x_bin) < out_length:
        return "0"*(out_length-len(x_bin)) + x_bin
    if cut_left: # preserve right part (least significant bit)
        return x_bin[-out_length:]
    return x_bin[:out_length] # preserve right part (most significant bit)

def int_complement(s:int, bit_expected:int) -> str:
    """
    Convert $s to bitstring then pad it with zeros to get length of $bit_expexcted then flip the bits
    """
    sstr = int_to_bin_padded(s, bit_expected)
    return "".join(map(lambda x: "0" if x == "1" else "1", list(sstr)))

def checksum_adding_then_complement(dataword:str, num_blocks:int, verbose:bool=False) -> str:
    dataword_int = int(dataword, base=2)
    csum = 0
    basebit_mask = 2**num_blocks - 1 # = int("1"*num_blocks, base=2)
    if verbose:
        print(" "*2*num_blocks + " Adding Main bit")
    for i in range(ceil(len(dataword)/num_blocks)):
        extracted = (dataword_int & (basebit_mask << (i*num_blocks))) >> (i*num_blocks)
        csum = csum + extracted
        if verbose:
            print(" "*num_blocks + int_to_bin_padded(extracted, num_blocks))
    if verbose:
        print("-"*2*num_blocks)
        print(int_to_bin_padded(csum, 2*num_blocks))

    # carry bit elimination
    if verbose:
        print(" "*2*num_blocks + " Adding Carry bit")
        print(" "*num_blocks + int_to_bin_padded(csum, num_blocks))
    carrybit_mask = basebit_mask << num_blocks # int("1"*num_blocks + "0"*num_blocks, base=2)
    iter_count = 0
    while (carry := ((csum & carrybit_mask) >> num_blocks)) > 0: # is Left(sum) is nonzero
        csum = (csum & basebit_mask) + carry
        iter_count += 1
        if verbose:
            print(" "*num_blocks + int_to_bin_padded(carry, num_blocks))
    if verbose and iter_count == 0:
        print(" "*num_blocks + int_to_bin_padded(0, num_blocks))


    if verbose:
        print(" "*num_blocks + "-"*num_blocks)
        print(" "*num_blocks + int_to_bin_padded(csum, num_blocks), "<-- Result from adding", end="\n\n")
    checksum = int_complement(csum, num_blocks)
    if verbose:
        print(" "*2*num_blocks + " Complement it")
        print(" "*num_blocks + checksum + " <-- Checksum/Syndrome")
    return checksum

def checksum_gen(dataword:str, word_size:int, num_blocks:int, verbose:bool=False) -> str:
    assert word_size >= 5 and len(dataword) <= word_size # the constrains stated in the assignment
    padded_data = "0"*(word_size - len(dataword)) + dataword
    checksum_value = checksum_adding_then_complement(dataword, num_blocks, verbose)
    return padded_data + checksum_value

def checksum_check(codeword:str, word_size:int, num_blocks:int, verbose:bool=False) -> int:
    assert word_size >= 5 and len(codeword) <= word_size + num_blocks # the constrains stated in the assignment
    syndrome = checksum_adding_then_complement(codeword, num_blocks, verbose)
    return 1 if check_all_zeros(syndrome) else 0

def main(data_or_codeword:str, num_block:int, check:bool, word_size:int=0, verbose:bool=False):
    if not word_size:
        word_size = len(data_or_codeword)
    if not check:
        print(checksum_gen(data_or_codeword, word_size, num_block, verbose))
    else:
        print(checksum_check(data_or_codeword, word_size, num_block, verbose))
    
if __name__ == "__main__":
    parser = argparse.ArgumentParser(description="Checksum generator or checker")
    parser.add_argument("data_or_codeword", help="Dataword or codeword to be generate codeword or to be check", type=str)
    parser.add_argument("num_block", help="Length of each block", type=int)
    parser.add_argument("--word_size", help="Maximum size of dataword. (default word_size = len(datacode))", type=int)
    parser.add_argument("-c", "--check", help="Use this flag if you want to check if the codeword is valid", action="store_true")
    parser.add_argument("-v", "--verbose", help="Verbosity", action="store_true")
    args = parser.parse_args()
    main(
        data_or_codeword=args.data_or_codeword, 
        num_block=args.num_block, 
        word_size=args.word_size, 
        verbose=args.verbose, 
        check=args.check
    )