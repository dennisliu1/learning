#!/bin/python

import math
import os
import random
import re
import sys

# Complete the jumpingOnClouds function below.
def jumpingOnClouds(n, c):
    # jump = curr + 1 or curr + 2
    # you can do greedy, there's no drawback to jumping further
    steps = 0
    clouds = list(c)
    i = 0
    while True:
        cloud = clouds[i]
        if i == n-1:
            break
        elif i+2 < n and clouds[i+2] == 0:
            i = i + 2
            steps = steps + 1
        elif i+1 < n and clouds[i+1] == 0:
            i = i + 1
            steps = steps + 1
        else:
            print('no solution!')
    return steps

if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    n = int(raw_input())

    c = map(int, raw_input().rstrip().split())

    result = jumpingOnClouds(n, c)

    fptr.write(str(result) + '\n')

    fptr.close()
