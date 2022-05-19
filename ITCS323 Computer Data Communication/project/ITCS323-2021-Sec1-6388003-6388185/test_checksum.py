import subprocess
from functools import reduce

# https://docs.python.org/3/library/subprocess.html
def test():
    # 10 sample datawords maximum length of 128 bits
    datawords = [ 
        '11110000111010101000000100010000000111100101100101010010001110011110010100101001101101011110011110111100010000111010001010010110',
        '1101110110110111110011010011000100101100000101101010011001000111100111010101110111101100001001000101011011000101010010100110000', 
        '1100111100000000110001000100011111000111001101011110001110011000101111100111101110101010111100111110010111111100100000101101111', 
        '11011011110110000010101110100110011111100110111000011101000000110111101011101110010010100110110000010000111011100101110100110110', 
        '1100001011010100101011111011110100101110011100000101010010101001100001100101000111010000100111110100011100101100100011001100101', 
        '10000010010101111101000001111010110011001011100111000100111101011100111111011000100110101001011010011010101110011001011001011000', 
        '1001010000111110101110100010000010010110000100011010011101000110010110011110110010111101010111010100000101101001110001110111110', 
        '11010100010010010011011011101111000101110110110010001001010000001000001101001100101101110110011111001011110001101001001010001000', 
        '11101011000001110110001110010101001011111010001110101111001100000001000011011011011110110100100101001000111111110001001111101110', 
        '10001010001110101000001011110111101110101100000011000011100101110001001111000001010011111101111001001000000111011010001101011001'
    ]

    codewords = list(map(lambda dataword : subprocess.run(["python", "checksum.py", dataword, "8", "--word_size", "128"], capture_output=True).stdout.decode("utf-8").rstrip(), datawords))
    verfications = list(map(lambda codeword : subprocess.run(["python", "checksum.py", codeword, "8", "--check", "--word_size", "128"], capture_output=True).stdout.decode("utf-8").rstrip(), codewords))
    assert reduce(lambda x, y: x and y, map(lambda x: x == "1", verfications)) # should raise error if check failed

    codewords = list(map(lambda dataword : subprocess.run(["python", "checksum.py", dataword, "16", "--word_size", "128"], capture_output=True).stdout.decode("utf-8").rstrip(), datawords))
    verfications = list(map(lambda codeword : subprocess.run(["python", "checksum.py", codeword, "16", "--check", "--word_size", "128"], capture_output=True).stdout.decode("utf-8").rstrip(), codewords))
    assert reduce(lambda x, y: x and y, map(lambda x: x == "1", verfications)) # should raise error if check failed

    codewords = list(map(lambda dataword : subprocess.run(["python", "checksum.py", dataword, "32", "--word_size", "128"], capture_output=True).stdout.decode("utf-8").rstrip(), datawords))
    verfications = list(map(lambda codeword : subprocess.run(["python", "checksum.py", codeword, "32", "--check", "--word_size", "128"], capture_output=True).stdout.decode("utf-8").rstrip(), codewords))
    assert reduce(lambda x, y: x and y, map(lambda x: x == "1", verfications)) # should raise error if check failed
    
    print("No exception was raised")

if __name__ == "__main__":
    test()