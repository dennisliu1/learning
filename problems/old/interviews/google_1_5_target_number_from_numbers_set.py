#!/bin/python

import math
import os
import random
import re
import sys


'''

'''
def getResult(s, x):
    return str(s) +" = "+ str(x)

if __name__ == '__main__':
    # fptr = open(os.environ['OUTPUT_PATH'], 'w')

    # s = input()
    s = [1, 2, 3, 4]

    x = 10

    result = getResult(s, x)

    print(result)

    # fptr.write(str(result) + '\n')

    # fptr.close()
