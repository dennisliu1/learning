#!/bin/python

import math
import os
import random
import re
import sys

# Complete the arrayManipulation function below.
'''
a b k
start, end, key
1. change a & b to zero based indexing
2. init array
3. for each operation, do for loop start to end

optimization
because each operation is a range, you could simply check which array index has what query done to it.
so instead of storing each array value, we should store unique ranges.


so, how do we store ranges?

queries = [[a,b,k],...]

1.
query 1 2 100 | ['1,5']
entering key 1,2
entering key 2,5
dict {'2,5': 100, '1,2': 100}

query 2 5 100 | ['2,5', '1,2']
entering key 1,2
dont delete
entering key 2,5
dont delete
dict {'2,5': 100, '1,2': 100}

query 3 4 100 | ['2,5', '1,2']
entering key 1,2
dont delete
entering key 2,3
entering key 3,4
entering key 4,5
dict {'4,5': 100, '3,4': 100, '1,2': 100, '2,3': 100}

2.
query 1 5 3 | ['1,10']
entering key 1,5
entering key 5,10
dict {'1,5': 3, '5,10': 3}

query 4 8 7 | ['1,5', '5,10']
entering key 1,4
entering key 4,5
entering key 5,8
entering key 8,10
dict {'5,8': 7, '8,10': 7, '4,5': 7, '1,4': 7}

query 6 9 1 | ['5,8', '8,10', '4,5', '1,4']
entering key 1,4
dont delete
entering key 4,5
dont delete
entering key 5,6
entering key 6,8
entering key 8,9
entering key 9,10
dict {'4,5': 1, '1,4': 1, '5,6': 1, '6,8': 1, '8,9': 1, '9,10': 1}

3.


after an hour...
- problem is half done
- missing off-by-one edge cases
- sum
we did finish:
- problem analysis
- recursive solution
- deleting previous ranges issue

'''
def arrayManipulation(n, queries):
    s = {1: None, n: None}
    d = {}
    d["{},{}".format(1,n)] = 0
    max = -1
    
    for query in queries:
        a = query[0]
        b = query[1]
        k = query[2]
        prevKeys = d.keys()
        dontDelete = {}
        print("query {} {} {} | {}".format(a,b,k, prevKeys) )
        
        s[a] = None
        s[b] = None
        indexes = list(s.keys())
        indexes.sort()
        for i in range(len(indexes)-1):
            key = "{},{}".format(indexes[i], indexes[i+1])
            print('entering key {}'.format(key))
            if key in d:
                print('dont delete')
                d[key] = k
                dontDelete[key] = True
            else:
                d[key] = k

        for prevKey in prevKeys:
            if not prevKey in dontDelete:
                del d[prevKey]
        del dontDelete
        print("dict {}\n".format(d))
        # key = "{},{}".format(1,n)
    return max

if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    nm = raw_input().split()

    n = int(nm[0])

    m = int(nm[1])

    queries = []

    for _ in xrange(m):
        queries.append(map(int, raw_input().rstrip().split()))

    result = arrayManipulation(n, queries)

    fptr.write(str(result) + '\n')

    fptr.close()
