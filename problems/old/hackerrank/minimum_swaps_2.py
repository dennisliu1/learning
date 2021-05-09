#!/bin/python

import math
import os
import random
import re
import sys

# Complete the minimumSwaps function below.
'''
swap to sort.

Greedy algorithm will work basically.

left to right, find wrong positioned number and swap to right position.
repeat until all numbers are done.

There's no real way to optimize it.
Since swaps are O(1) regardless of position, you need to always do arr[i] = i.

What can be optimized is how to find the next wrong positioned number.

---

missed the optimization.

while True:
    for i in range(start, len):
        ...

It is very slow because you still have to keep restarting the range.
Manual increment is faster.


'''
def minimumSwaps(arr):
    swapped = False
    swaps = 0
    i = 0
    while i < len(arr):
        if not arr[i]-1 == i:
            swap(arr, i, arr[i]-1)
            swaps = swaps + 1
        else:
            i = i + 1
    return swaps

def swap(arr, i, j):
    temp = arr[i]
    arr[i] = arr[j]
    arr[j] = temp

if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    n = int(raw_input())

    arr = map(int, raw_input().rstrip().split())

    res = minimumSwaps(arr)

    fptr.write(str(res) + '\n')

    fptr.close()
