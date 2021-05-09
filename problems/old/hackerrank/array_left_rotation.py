#!/bin/python

import math
import os
import random
import re
import sys

# Complete the rotLeft function below.
'''
find the cutting point, then cut & paste the two sides.

n d
arr_contents
n = length of array
d = number of left rotations

The cutting point is c = d % n
because if d > n, it loops back to the front again. So its only at the very end
after d - d/n does it matter, so d % n gets you the final cutting point.
'''
def rotLeft(a, d, n):
    c = d % n
    new_arr = a[c:] + a[0:c]
    return new_arr

if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    nd = raw_input().split()

    n = int(nd[0])

    d = int(nd[1])

    a = map(int, raw_input().rstrip().split())

    result = rotLeft(a, d, n)

    fptr.write(' '.join(map(str, result)))
    fptr.write('\n')

    fptr.close()
