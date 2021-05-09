#!/bin/python

import math
import os
import random
import re
import sys

# Complete the activityNotifications function below.
'''
arr[i] >= 2 x median(arr[i-3:i])

since we know the trailing number of days is fixed, we can create an array to track it.
and since we only need the median, we can use a frequency array to track it.
we can get the median by doing a counting sort to find the median.
But since we can pop the last day of the trail and insert the previous day,
we can keep a rolling index so we're always on the median.

we're pretty close, but if d ~ large then the keys is the bottleneck.
Since we only want the median, which is d/2 largest (or smallest number)
then we can treat this as a kth largest number problem and use a heap.

We add and remove from the heap whenever a key is added/removed.
The operations are O(logn) instead of O(nlogn) for add and O(n) for remove.

which makes the entire algorithm O((n-d)logd) ~ O(n)

I could heapify the key lookup, which is the original intent here...
but the median searching still takes O(n) from counting the frequencies.

or I could heapify the trail, which would store d elements...
and do the lookup in log(n) time

I guess its about the same.

That means not using a frequency array and using a heap instead.

we can try changing the keys to a heap first, then the trail.

ok.... heapifying the keys didn't help.

I think the way to think of this problem is to store the median and
shift the median position around based on what been deleted / added.

'''
def activityNotifications(expenditure, d):
    print(expenditure, d)
    if len(expenditure) <= d:
        return 0

    notifications = 0

    trail = {}
    for x in expenditure[0:d]:
        add_to_trail(trail, x)
    # print("trail = {}".format(trail))
    
    # get median from trail
    median_index = int(round(d/2.0))
    odd_trail = (d % 2 == 1)

    median,keys = get_median(trail, median_index, odd_trail)

    first_day = True
    for i in range(d, len(expenditure)):
        # print("{}: {} {} {}".format(i, expenditure[i-d], expenditure[i-1], expenditure[i]))
        if first_day:
            first_day = False
        else:
            # delete trail last day
            trail[expenditure[i-d]] = trail[expenditure[i-d]] - 1
            if trail[expenditure[i-d]] <= 0:
                del trail[expenditure[i-d]]
                keys.remove(expenditure[i-d])
                # keys.sort()
            # add previous day
            new_key = add_to_trail(trail, expenditure[i-1])
            if new_key:
                keys.append(expenditure[i-1])
                keys.sort()
            
        # print("  trail = {}".format(trail))
        median,keys = get_median(trail, median_index, odd_trail)
        notify = (expenditure[i] >= 2 * median)
        if notify:
            notifications = notifications + 1
        # print("  {} >= 2*{} == {}".format(expenditure[i], median, notify))
    return notifications

def add_to_trail(trail, x):
    if x not in trail:
        trail[x] = 1
        return True
    else:
        trail[x] = trail[x] + 1
        return False

def get_median(trail, median_index, odd_trail, keys=None):
    # print("get_median:", trail, median_index, odd_trail, keys)
    if keys is None:
        keys = trail.keys()
        keys.sort()
    count = 0
    median = None
    for i in range(len(keys)):
        x = keys[i]
        c = trail[x]
        count = count + c
        print(i, x, c, count)
        if count >= median_index:
            if odd_trail:
                median = x
            else:
                if count == median_index:
                    median = (keys[i] + keys[i+1])/2.0
                else:
                    median = (keys[i] + keys[i])/2.0
            break
    print("median = {}".format(median))
    return median, keys


if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    nd = raw_input().split()

    n = int(nd[0])

    d = int(nd[1])

    expenditure = map(int, raw_input().rstrip().split())

    result = activityNotifications(expenditure, d)

    fptr.write(str(result) + '\n')

    fptr.close()
