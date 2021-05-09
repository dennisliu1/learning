#!/bin/python

import math
import os
import random
import re
import sys

# Complete the hourglassSum function below.
'''
given a 6x6 grid, we want to go through [1,n-1] for x & y possible centers
then return the max
'''
def hourglassSum(arr):
    print(arr)
    maxSum = -9*7 -1 # out of bounds min, so always replaced
    for i in range(1, 5): # 0 - 1,2,3,4 - 6
        for j in range(1,5):
            sum = doHourglassSum(i, j, arr)
            maxSum = max(maxSum, sum)
    return maxSum

def doHourglassSum(i, j, arr):
    sum = 0
    for x in range(-1, 2):
        # print( str(i-1)+','+str(j+x) )
        sum = sum + arr[i-1][j+x]
    # print( str(i)+','+str(j) )
    sum = sum + arr[i][j]
    for x in range(-1, 2):
        sum = sum + arr[i+1][j+x]
    return sum

if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    arr = []

    for _ in xrange(6):
        arr.append(map(int, raw_input().rstrip().split()))

    result = hourglassSum(arr)

    fptr.write(str(result) + '\n')

    fptr.close()
