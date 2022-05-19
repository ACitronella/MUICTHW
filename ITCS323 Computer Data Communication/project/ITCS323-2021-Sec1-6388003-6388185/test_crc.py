import subprocess
from functools import reduce

# https://docs.python.org/3/library/subprocess.html
def test():
    # 10 sample datatwords maximum length of 128 bits
    datawords = [
        '10100011011010000000000010011000100110100010000100101110111000101010011010001111110100010000101001111011110111011001000111000101', 
        '10010100101111111001110001010101010010011001000000001101111011011001011100001000000110001111101011010110010110000101111000101100', 
        '10001111111110101100001011100111101100111110101011101100100001000010110111101000110010101011111001000001110110000101010010011100', 
        '1110010101101110011011100010101100100010111111110001001010100011000111110000100001000110111011100001011011011000001101110111010', 
        '1101101100000110110100111111101010110101110000110001110001100011111000100001101100011000110001001101001111101100001000100100000', 
        '1001011011000001111110010110110101000101001111000001000000111110101101001001110000010111010101110100011101011011111001100111100', 
        '100001011000010100010001100010011110100000110100000110011010010011000110010001000111000000011011001100000011111010010110110111', 
        '1111001010100011010001110101111111001111101011011111111101011110001001000110110111111011110001001001100010001001100111110010000', 
        '10111101011100001010110110010001010011110100001111001001111100011011111100010000111011011110110111010110110000011111110110110011', 
        '11100000101010111111101011101001111001111011001101011001101010011100101111000000111100100010101010100000001000101001001101100111'
    ]

    codewords = list(map(lambda dataword : subprocess.run(["python", "crc.py", dataword, "CRC-4", "--word_size", "128"], capture_output=True).stdout.decode("utf-8").rstrip(), datawords))
    verfications = list(map(lambda codeword : subprocess.run(["python", "crc.py", codeword, "CRC-4", "--check"], capture_output=True).stdout.decode("utf-8").rstrip(), codewords))
    assert reduce(lambda x, y: x and y, map(lambda x: x == "1", verfications)) # should raise error if check failed
    
    codewords = list(map(lambda dataword : subprocess.run(["python", "crc.py", dataword, "CRC-8", "--word_size", "128"], capture_output=True).stdout.decode("utf-8").rstrip(), datawords))
    verfications = list(map(lambda codeword : subprocess.run(["python", "crc.py", codeword, "CRC-8", "--check"], capture_output=True).stdout.decode("utf-8").rstrip(), codewords))
    assert reduce(lambda x, y: x and y, map(lambda x: x == "1", verfications)) # should raise error if check failed
    
    codewords = list(map(lambda dataword : subprocess.run(["python", "crc.py", dataword, "Reversed CRC-16", "--word_size", "128"], capture_output=True).stdout.decode("utf-8").rstrip(), datawords))
    verfications = list(map(lambda codeword : subprocess.run(["python", "crc.py", codeword, "Reversed CRC-16", "--check"], capture_output=True).stdout.decode("utf-8").rstrip(), codewords))
    assert reduce(lambda x, y: x and y, map(lambda x: x == "1", verfications)) # should raise error if check failed
    
    codewords = list(map(lambda dataword : subprocess.run(["python", "crc.py", dataword, "CRC-16", "--word_size", "128"], capture_output=True).stdout.decode("utf-8").rstrip(), datawords))
    verfications = list(map(lambda codeword : subprocess.run(["python", "crc.py", codeword, "CRC-16", "--check"], capture_output=True).stdout.decode("utf-8").rstrip(), codewords))
    assert reduce(lambda x, y: x and y, map(lambda x: x == "1", verfications)) # should raise error if check failed
    
    codewords = list(map(lambda dataword : subprocess.run(["python", "crc.py", dataword, "CRC-24", "--word_size", "128"], capture_output=True).stdout.decode("utf-8").rstrip(), datawords))
    verfications = list(map(lambda codeword : subprocess.run(["python", "crc.py", codeword, "CRC-24", "--check"], capture_output=True).stdout.decode("utf-8").rstrip(), codewords))
    assert reduce(lambda x, y: x and y, map(lambda x: x == "1", verfications)) # should raise error if check failed
    
    codewords = list(map(lambda dataword : subprocess.run(["python", "crc.py", dataword, "CRC-32", "--word_size", "128"], capture_output=True).stdout.decode("utf-8").rstrip(), datawords))
    verfications = list(map(lambda codeword : subprocess.run(["python", "crc.py", codeword, "CRC-32", "--check"], capture_output=True).stdout.decode("utf-8").rstrip(), codewords))
    assert reduce(lambda x, y: x and y, map(lambda x: x == "1", verfications)) # should raise error if check failed
    
    print("No exception was raised")

if __name__ == "__main__":
    test()