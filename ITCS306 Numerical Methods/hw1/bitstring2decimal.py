
b = "11000000010100010001000001000000"

# 01000010011010100100000000000000 -> 58.5625

def bitstr_to_uint(b_str):
    v = 0
    b_str = b_str[::-1]  # 10100010 + 0 * 2^2 + 0 * 2^1 + 1 * 2^0
    for i, s in enumerate(b_str):
        v += 2 ** i * int(s)
    return v

def mantissa_to_float(b_str):
    v = float(1.) # 2^0 
    for i, s in enumerate(b_str, start=1): # .1 * 2^-1 + 1 * 2^-2 + 0 * 2^-3 + ...101001 
        v += (1 / (float(2) ** i)) * int(s) 
        
    return v

def bitstr_to_float(b_str):
    l = len(b_str)
    out = 0
    exp_count = 0
    bias = 0
    mantissa_count = 0
    if ("0" not in b_str and "1" not in b_str) or (l != 32 and l != 64):
        raise ValueError("bit string input is invalid \"%s\"" % b_str)
    if l == 32:
        bias = 127
        exp_count = 8
        mantissa_count = 23
    elif l == 64:
        bias = 1023
        exp_count = 11
        mantissa_count = 52

    # extract exp_bit and mantissa_bit
    exp_bit = b_str[1:1+exp_count]
    mantissa_bit = b_str[1+exp_count:]

    sign = (-1) ** int(b_str[0])

    # check for special case, (0, nan, -inf, inf)
    if "1" not in exp_bit and mantissa_bit.count("0") == mantissa_count:
        return float(0.0), {"sign": sign, "exponent" : 0, "mantissa" : 0, "bias" : bias}
    if exp_bit.count("1") == exp_count and "1" in mantissa_bit:
        return float("nan"), {"sign" : sign, "exponent": 2 ** exp_count - bias - 1, "mantissa": mantissa_to_float(mantissa_bit), "bias" : bias}
    if exp_bit.count("1") == exp_count:
        return sign * float("inf"), {"sign" : sign, "exponent": 2 ** exp_count - bias - 1, "mantissa": 0, "bias" : bias}

    # general case
    exponent_value = bitstr_to_uint(exp_bit) - bias
    mantissa_value = mantissa_to_float(mantissa_bit)
    out = sign * mantissa_value * (float(2) ** exponent_value)
    return out, {"sign" : sign, "exponent" : exponent_value, "mantissa" : mantissa_value, "bias" : bias}


def helper():
    print("=========================Invalid Input=========================")
    print("This program will convert bit string 32-bit or 64-bit")
    print("For example", b, "will convert to", bitstr_to_float(b)[0])
    print("You can run this file by `python app.py \"Particular bit string\"`")

def main(b_str):
    try:
        o, m = bitstr_to_float(b_str) 
        print("%s = %d * %f * 2 ^ (%d - %d)" % (str(o), m["sign"], m["mantissa"], m["exponent"] + m["bias"], m["bias"]))
        
    except Exception as e:
        print(e)
        helper()

if __name__ == "__main__":
    from argparse import ArgumentParser
    parser = ArgumentParser()
    parser.add_argument("bitstring", type=str, help="bit string that you want to convert, can be 32-bit or 64-bit only, ex.%s" % b)
    args = parser.parse_args()
    main(args.bitstring)
