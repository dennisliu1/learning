#!/bin/python

import math
import os
import random
import re
import sys

# Complete the countingValleys function below.
def countingValleys(n, s):
    steps = list(s)
    altitude = 0
    valleys = 0
    start_valley = False
    for step in steps:
        if step == 'U':
            if altitude == -1 and start_valley:
                start_valley = False
                valleys = valleys + 1
            altitude = altitude + 1
        elif step == 'D':
            if altitude == 0:
                start_valley = True
            altitude = altitude - 1
        else:
            print('something went wrong!')
    return valleys

if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    n = int(raw_input())

    s = raw_input()

    result = countingValleys(n, s)

    fptr.write(str(result) + '\n')

    fptr.close()
