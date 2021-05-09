#!/bin/python

import math
import os
import random
import re
import sys

# Complete the minimumBribes function below.
'''
sorting problem.
bribe = swap with person directly in front of them. Max 2 times
so i can calculate the following:
- difference in positions from start to current
    - if (start - current) of all elements != 0, its wrong
    - if not -2 <= (start - current) <= 2 then its wrong
- then add up positives to count min number of bribes
'''
def minimumBribes(Q):
    #
    # initialize the number of moves
    moves = 0 
    #
    # decrease Q by 1 to make index-matching more intuitive
    # so that our values go from 0 to N-1, just like our
    # indices.  (Not necessary but makes it easier to
    # understand.)
    # 1,2,3,4,5,6,7,8
    # 0,1,2,3,4,5,6,7
    Q = [P-1 for P in Q]
    #
    # Loop through each person (P) in the queue (Q)
    # 1..n
    for i in range(len(Q)):
        P = Q[i] # P = person's original spot

        # if person moved more than 2 spots ahead, its too chaotic
        if P - i > 2:
            print("Too chaotic")
            return
        
        # we want to count the number of bribes a person received
        # so we want to get [curr_position, original position]
        # P - 1 because they can be at most 1 in front of P's original position
        for j in range(max(P-1,0),i):
            if Q[j] > P:
                moves += 1
    print(moves)


if __name__ == '__main__':
    t = int(raw_input())

    for t_itr in xrange(t):
        n = int(raw_input())

        q = map(int, raw_input().rstrip().split())

        minimumBribes(q)

        # print(str(result))