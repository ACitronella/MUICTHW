
d = "50.59" # "0 10000100 10010100101110000101001"

# 58.5625 -> 01000010011010100100000000000000

def uint_to_bin(int_part):
    l = []
    while int_part > 0:
        r = int_part % 2
        s = int_part // 2
        int_part = s
        l.append(str(r))
    return "".join(l[::-1])


def padding(bits, target_len, fill_mode):
    while fill_mode == "f" and len(bits) < target_len:
        bits = "0" + bits # 00011100, 8
    while fill_mode == "b" and len(bits) < target_len:
        bits = bits + "0"
    return bits[0:target_len]

def int_to_bin_then_padding(int_part, l, fill_mode):
    b = uint_to_bin(int_part)
    return padding(b, l, fill_mode)

def decimal_to_bin(deci_part, wanted_len):
    l = []
    first_one = False
    c = 0
    while c < wanted_len and deci_part != 0.0:
        d = deci_part * 2 # 0.5 * 2 => 1.0
        a = int(d) # 1
        deci_part = d - a  # 1.0 - 1 => 0 
        l.append(str(a))
        if a == 1:
            first_one = True
        if first_one:
            c += 1
    return "".join(l)

def find_first_index(s, t):
    for i, v in enumerate(s):
        if v == t:
            return i
    return -1

def find_exponent(int_part_bit, deci_part_bit):
    i = find_first_index(int_part_bit, "1") 
    if i == -1:
        i = find_first_index(deci_part_bit, "1") 
        return -i - 1 
    return len(int_part_bit) - i - 1

def bit_round(bit_string, l):
    if len(bit_string) < l+1:
        return bit_string
    if bit_string[l] == "1" and bit_string[l-1] == "0":
        d = list(bit_string[:l])
        d[-1] = "1"
        return "".join(d)
    return bit_string[:l]

def decimal_to_bitstring(ds, is_64):
    bias = 127
    mantissa_len = 23
    exp_l = 8
    if is_64:
        bias = 1023
        mantissa_len = 52
        exp_l = 11
    d = float(ds)
    if d != d: # check Nan
        return "1" * (1 + mantissa_len + exp_l), {"sign": "1", "exponent": "1" * exp_l, "mantissa": "1" * mantissa_len}
    if d == 0: # check zero
        return "0" * (1 + mantissa_len + exp_l), {"sign": "0", "exponent": "0" * exp_l, "mantissa": "0" * mantissa_len}
    s = 0
    if d < 0: 
        d = -d
        s = 1
    if d == float("inf") or d == float("-inf"): # check inf
        return str(s) + "1" * (exp_l) + "0" * (mantissa_len), {"sign": str(s), "exponent": "1" * exp_l, "mantissa": "0" * mantissa_len}

    # extracting int part and decimal part
    int_part = int(d) 
    deci_part = d - int_part 

    wanted_len = mantissa_len + 1
    int_part_bit = uint_to_bin(int_part)
    deci_part_bit = decimal_to_bin(deci_part, wanted_len)
    e = find_exponent(int_part_bit, deci_part_bit)
    offset = len(int_part_bit) - e # let first bit out

    ep = int_to_bin_then_padding(e + bias, exp_l, "f")
    

    m = int_part_bit + deci_part_bit
    m = padding(m[offset:], mantissa_len, fill_mode="b")

    return "%s%s%s" % (s, ep, m), {"sign": s, "exponent": ep, "mantissa": m}

def helper():
    print("=========================Invalid Input=========================")
    print("Convert from decimal to bit string in form of IEEE-754 32-bit")
    print("For example", d, "will be converted to", decimal_to_bitstring(d, False)[0])
    print("You can run this file by `python decimal2bitstring.py \"particular decimal\"` for 32-bit conversion")
    print("and `python decimal2bitstring.py \"particular decimal\" -b64` for 64-bit conversion")

def is_convert_to_C_able(x, C):
    # most easiest way to check
    try:
        C(x)
        return True
    except Exception:
        pass
    return False

def main(deci_str, is_64):
    if deci_str != "" and is_convert_to_C_able(deci_str, float):
        b, t = decimal_to_bitstring(deci_str, is_64)
        if is_64:
            print("%10s%18s%60s" % ("sign(1-bit)", "exponent(11-bit)", "mantissa(52-bit)"))
            print("%10s%18s%60s" % (t["sign"], t["exponent"], t["mantissa"]))
            print("%s (base 10) = %s (IEEE-754 64-bit)" % (deci_str, b))
            assert len(b) == 64
        else:
            print("%10s%18s%28s" % ("sign(1-bit)", "exponent(8-bit)", "mantissa(23-bit)"))
            print("%10s%18s%28s" % (t["sign"], t["exponent"], t["mantissa"]))
            print("%s (base 10) = %s (IEEE-754 32-bit)" % (deci_str, b))
            assert len(b) == 32
        
    else:
        helper()

if __name__ == "__main__":
    from argparse import ArgumentParser
    parser = ArgumentParser()
    parser.add_argument("decimal", type=str, help="bit string that you want to convert, can be 32-bit or 64-bit only, ex.%s" % d)
    parser.add_argument("-b64", "--bits64", action="store_true", help="use 64-bit conversion")
    args = parser.parse_args()
    main(args.decimal, args.bits64)
