#!/bin/python

import math
import os
import random
import re
import sys

# Complete the sockMerchant function below.
def sockMerchant(n, ar):
    if n <= 1:
        return 0
    ar.sort()
    curr_sock = None
    count = 0
    pairs = 0
    for sock in ar:
        if curr_sock == sock:
            count = count + 1
        else:
            curr_sock = sock
            pairs = pairs + count / 2
            count = 1
        # print(sock, count, pairs)
    pairs = pairs + count / 2
    # print(sock, count, pairs)
    return pairs

if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    n = int(raw_input())

    ar = map(int, raw_input().rstrip().split())

    result = sockMerchant(n, ar)

    fptr.write(str(result) + '\n')

    fptr.close()
