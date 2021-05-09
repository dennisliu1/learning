#!/bin/python

import math
import os
import random
import re
import sys

# Complete the repeatedString function below.
'''
n = number of characters to check for letter 'a'
s = string that will repeat infinitely

n / len(s) = number of full repetitions
n % len(s) = number of remaining characters to check
'''
def repeatedString(s, n):
    reps = n / len(s)
    count = reps * s.count('a')
    # print('reps count:', count)
    partial = n - (reps * len(s))
    count = count + s.count('a', 0, partial)
    # print('partial count:', count)
    return count

if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    s = raw_input()

    n = int(raw_input())

    result = repeatedString(s, n)

    fptr.write(str(result) + '\n')

    fptr.close()
