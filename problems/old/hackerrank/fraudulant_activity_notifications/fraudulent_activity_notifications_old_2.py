#!/bin/python

import math
import os
import random
import re
import sys
import bisect

'''
try 2, it's easier to start from scratch.

The logic is:

create a trail storing the median.

Then go through the days and update the trail with the median
Since you know the starting median position, you can move it up/down
depending on what has been added.

Then you just do the normal check like usual.

much faster, but most likely missing edge cases still.

None of the test cases fail by timing out now.

most likely, there's a bug in how i integrated with the delete / insert element.

2 ways to move on from this:
1. identify the root cause of the error (need more thorough test cases)
2. write a program that compares expected vs program of the looping.
    - expected program can be slow.
    - need to connect it to my program so I can see the differences.


Best way to refactor for #1 is to combine the update of trail and the median together.

'''

debug = False

# Complete the activityNotifications function below.
# d = 1 based index!
def activityNotifications(expenditure, d):
    if len(expenditure) <= d:
        return 0
    
    notifications = 0

    trail = create_trail(expenditure)
    if(debug):
        print('trail:{}'.format(trail))

    median_d = int((d+1)/2)-1
    is_odd = (d % 2 == 1)
    median, median_index, median_pos, median_key, keys = start_rolling_median(trail, d, median_d, is_odd)
    if(debug):
        print("\n--- start ---\n")

    first_day = True
    for i in range(d, len(expenditure)):
        x = expenditure[i]

        if first_day:
            if(debug):
                print("first loop\n")
            first_day = False
        else:
            prev_x = expenditure[i-1]
            last_x = expenditure[i-d-1]
            if(debug):
                # print("{}-1, {}-{}-1".format(i,i,d))
                print("loop {}: -{} +{} on {}".format(i, last_x, prev_x, x))
            
            if prev_x == last_x:
                if(debug):
                    print("no change")
            else:
                created_trailed_count = insert_expense(trail, prev_x)
                deleted_trail_count = remove_expense(trail, last_x)
                if created_trailed_count:
                    bisect.insort(keys, prev_x)
                if deleted_trail_count:
                    del keys[bisect.bisect_left(keys, last_x)]
                if created_trailed_count or deleted_trail_count:
                    if(debug):
                        print("updated median_index: {} -> {}".format(median_index, bisect.bisect_left(keys, median_key)))
                    median_index = bisect.bisect_left(keys, median_key)
                if(debug):
                    print('trail:{} {}'.format(trail, keys))
                
                median, median_index, median_pos, median_key = update_rolling_median(trail, is_odd, median, median_index, median_pos, keys, prev_x, last_x)
        
        if x >= 2 * median:
            notifications = notifications + 1
        
        if(debug):
            print("")
    return notifications

def create_trail(expenditure):
    trail_arr = expenditure[0:d]
    # trail_arr.sort()

    trail = {}
    for x in trail_arr:
        insert_expense(trail, x)
    return trail

def insert_expense(trail, x):
    if x not in trail:
        trail[x] = 1
        return True
    else:
        trail[x] = trail[x] + 1
        return False

def remove_expense(trail, x):
    if trail[x] == 1:
        del trail[x]
        return True
    else:
        trail[x] = trail[x] - 1
        return False

def start_rolling_median(trail, d, median_d, is_odd):
    median = -1
    median_pos = -1
    median_index = -1
    median_key = -1

    keys = trail.keys()
    keys.sort()

    count = 0
    for i in range(len(keys)):
        x = keys[i]
        c = trail[x]
        if(debug):
            print('{}: {}+{} <= {}'.format(i, count, c, median_d) )

        if count + c <= median_d:
            count = count + c
        else:
            median_pos = median_d - count
            median_index = i
            median_key = keys[i]
            median = get_median(keys, i, is_odd, median_pos)
            break
    if(debug):
        print("median={}, {}/[0-{}], trail[{}], keys={}".format(median, median_pos, trail[keys[median_index]], median_index, keys))
    return median, median_index, median_pos, median_key, keys

def update_rolling_median(trail, is_odd, old_median, old_median_index, old_median_pos, keys, add_element, remove_element):
    median = -1
    median_pos = -1
    median_index = -1
    median_key = -1

    shift = 0
    if remove_element <= old_median and add_element >= old_median:
        if(debug): 
            print('median_index + 1')
        shift = shift + 1
    elif add_element > old_median and remove_element >= old_median:
        if(debug): 
            print('median_index - 1')
        shift = shift - 1
    else:
        if(debug):
            print('median_index no change')
        # add and remove left 
        # add and remove (same or right)

    if shift < 0:
        # shift left
        if old_median_pos is 0:
            if(debug):
                print("shift to lower x")
            median_index = old_median_index - 1
            median_key = keys[median_index]
            median = get_median(keys, median_index, is_odd, old_median_pos)
            median_pos = trail[median]
        else:
            if(debug):
                print("no shift")
            median_index = old_median_index
            median_key = keys[median_index]
            median = old_median
            median_pos = old_median_pos - 1
    elif shift > 0:
        # shift right
        if(debug):
            print("{} >= {} (trail[keys[{}]])".format(old_median_pos, trail[keys[old_median_index]]-1, old_median_index))
        if old_median_pos >= trail[keys[old_median_index]]-1:
            if(debug):
                print("shift to upper x")
            median_index = old_median_index + 1
            median_key = keys[median_index]
            median = get_median(keys, median_index, is_odd, old_median_pos)
            median_pos = 0
        else:
            if(debug):
                print("no shift")
            median_index = old_median_index
            median_key = keys[median_index]
            median = old_median
            median_pos = old_median_pos + 1
    else:
        # no change
        pass
    # median_pos = old_median_pos + shift

    if(debug):
        print("median={}, {}/[0-{}], trail[{}], keys={}".format(median, median_pos, trail[keys[median_index]], median_index, keys))
    return median, median_pos, median_index, median_key

def get_median(keys, i, is_odd, median_pos):
    if is_odd:
        median = keys[i]
    else:
        if median_pos is 0: # different value for previous; need to add keys[i] + keys[i-1]
            median = (keys[i] + keys[i+1])/2.0
        else:
            median = keys[i]
    return median

if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    nd = raw_input().split()

    n = int(nd[0])

    d = int(nd[1])

    expenditure = map(int, raw_input().rstrip().split())

    result = activityNotifications(expenditure, d)

    fptr.write(str(result) + '\n')

    fptr.close()
