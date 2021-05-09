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

We can treat each loop as one delete and one add operation.
It is slightly slower doing it separately but this way should mean no issues,
the operations are more atomic this way.

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

    # formula = (d + 1)/2. 
    median_d = int((d+1)/2)
    is_odd = (d % 2 == 1)
    median_pos, median_key_index, median_key, median, keys = start_rolling_median(trail, median_d, is_odd)
    
    if(debug):
        print("\n--- start ---\n")

    first_day = True
    for i in range(d, len(expenditure)):
        x = expenditure[i]

        if first_day:
            if(debug):
                print("--- {} first loop ---\n".format(i))
            first_day = False
        else:
            prev_x = expenditure[i-1]
            last_x = expenditure[i-d-1]
            if(debug):
                print("--- {} loop --- \n".format(i))
                print("-{} +{}\n".format(last_x, prev_x))

            trail, keys, median_pos, median_key_index, median_key = remove_and_update(trail, keys, last_x, median_pos, median_key_index, median_key, is_odd)
            if(debug):
                print("")
            trail, keys, median_pos, median_key_index, median_key = add_and_update(trail, keys, prev_x, median_pos, median_key_index, median_key, is_odd)
            if(debug):
                print("")
            median = get_median(trail, keys, median_pos, median_key_index, is_odd)
        
        if(debug):
            print("\nnotification: x({}) >= 2 * ({}) == {}".format(x, median, x >= 2 * median))
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
        insert_basic_expense(trail, x)
    return trail

def insert_basic_expense(trail, x):
    if x not in trail:
        trail[x] = 1
        return True
    else:
        trail[x] = trail[x] + 1
        return False

# def remove_basic_expense(trail, x):
#     if trail[x] == 1:
#         del trail[x]
#         return True
#     else:
#         trail[x] = trail[x] - 1
#         return False

def start_rolling_median(trail, median_d, is_odd):
    median_pos = -1
    median_key_index = -1
    median_key = -1
    median = -1
    
    keys = trail.keys()
    keys.sort()

    count = 0
    for i in range(len(keys)):
        key = keys[i]
        c = trail[key]
        count = count + c

        if(debug):
            print("{}: count({}) >= median_d({}), key({}), c({})".format(i, count, median_d, key, c))
        
        if count >= median_d:
            median_pos = count - median_d
            median_key = key
            median_key_index = i
            median = get_median(trail, keys, median_pos, median_key_index, is_odd)
            break
    if(debug):
        print("median={}, {}/{}, keys[{}]={}".format(median, median_pos, trail[keys[median_key_index]], median_key_index, keys[median_key_index]))
        print("keys={}".format(keys))
    return median_pos, median_key_index, median_key, median, keys

def remove_and_update(trail, keys, remove_element, median_pos, median_key_index, median_key, is_odd):
    x = remove_element
    if is_odd:
        if(debug):
            print("remove odd, ")
        if x < median_key:
            if(debug):
                print("x({}) < median_key({}), ".format(x, median_key))
            pass
        # x >= median_key:
        else:
            if(debug):
                print("not x({}) < median_key({}), ".format(x, median_key))
            # decrease_median_pos_index()
            if median_pos <= 0:
                if(debug):
                    print("-underflow: median_pos({}) <= 0, ".format(median_pos))
                median_key_index = median_key_index - 1
                median_key = keys[median_key_index]
                median_pos = trail[median_key]-1
            else:
                if(debug):
                    print("-: median_pos({}) < 0, ".format(median_pos))
                median_pos = median_pos - 1
        # delete from trail
        if trail[x] == 1:
            if(debug):
                print("delete trail[{}]={}, ".format(x, trail[x]))
            del trail[x]
            del keys[bisect.bisect_left(keys, x)]
            if x < median_key:
                if(debug):
                    print("decrease index {} from delete from x < median_key, ".format(median_key_index))
                median_key_index = median_key_index - 1
        else:
            if(debug):
                print("decrease trail[{}]={}, ".format(x, trail[x]))
            trail[x] = trail[x] - 1
    else: # even
        if(debug):
            print("remove even, ")
        if x > median_key:
            if(debug):
                print("right: x({}) > median_key({}), ".format(x, median_key))
            pass
        # x <= median_key:
        else:
            if(debug):
                print("left/mid: not x({}) > median_key({}), ".format(x, median_key))
            # increase_median_pos_index()
            if median_pos >= trail[keys[median_key_index]]-1:
                if(debug):
                    print("+overflow: median_pos({}) >= trail[keys[median_key_index]]({})-1, ".format(median_pos, trail[keys[median_key_index]]))
                median_key_index = median_key_index + 1
                median_key = keys[median_key_index]
                median_pos = 0
            else:
                if(debug):
                    print("+: median_pos({}) < trail[keys[median_key_index]]({})-1, ".format(median_pos, trail[keys[median_key_index]]))
                median_pos = median_pos + 1
        # delete from trail
        if trail[x] == 1:
            if(debug):
                print("delete trail[{}]={}, ".format(x, trail[x]))
            del trail[x]
            del keys[bisect.bisect_left(keys, x)]
            if x <= median_key:
                if(debug):
                    print("decrease index {} from delete from x <= median_key, ".format(median_key_index))
                median_key_index = median_key_index - 1
        else:
            if(debug):
                print("decrease trail[{}]={}, ".format(x, trail[x]))
            trail[x] = trail[x] - 1
        if(debug):
            print()
    if(debug):
        print("{}/{}, keys[{}]={}".format(median_pos, trail[keys[median_key_index]], median_key_index, keys[median_key_index]))
        print("trail={}".format(trail))
        print("keys={}".format(keys))
    return trail, keys, median_pos, median_key_index, median_key

def add_and_update(trail, keys, add_element, median_pos, median_key_index, median_key, is_odd):
    x = add_element

    if is_odd:
        if(debug):
            print("add odd, ")
        # insert
        inserted_x = False
        if x not in trail:
            trail[x] = 1
            bisect.insort_left(keys, x)
            inserted_x = True
            if(debug):
                print("insert new x({}), ".format(x))
        else:
            trail[x] = trail[x] + 1
            if(debug):
                print("insert old trail[{}]={}, ".format(x, trail[x]))
        # update keys and indexes
        if(debug):
            print("x({}) < median_key({}), ".format(x, median_key))
        if x < median_key:
            if inserted_x:
                median_key_index = median_key_index + 1
                median_key = keys[median_key_index]
            if(debug):
                print("left: increase pos keys[{}]={}, ".format(median_key_index, median_key))
        else:
            if(debug):
                print("mid/right: increase pos, ")
            if median_pos >= trail[keys[median_key_index]]-1:
                if(debug):
                    print("overflow: ")
                median_key_index = median_key_index + 1
                median_key = keys[median_key_index]
                median_pos = 0
            else:
                if(debug):
                    print("not overflow: ")
                median_pos = median_pos + 1
    else:
        if(debug):
            print("add even, ")
        # insert
        if x not in trail:
            if(debug):
                print("insert new x({}), ".format(x))
            trail[x] = 1
            bisect.insort_left(keys, x)
        else:
            trail[x] = trail[x] + 1
            if(debug):
                print("insert old trail[{}]={}, ".format(x, trail[x]))
        # udpate keys and indexes
        if x < median_key:
            if(debug):
                print("left: decrease pos, ")
            if median_pos <= 0:
                if(debug):
                    print("underflow, ")
                median_key_index = median_key_index - 1
                median_key = keys[median_key_index]
                median_pos = trail[keys[median_key_index]]-1
            else:
                if(debug):
                    print("no underflow, ")
                median_pos = median_pos - 1
        else:
            if(debug):
                print("mid,right: so no changes, ")
            pass # no change
    if(debug):
        print("\n{}/{}, keys[{}]={}".format(median_pos, trail[keys[median_key_index]], median_key_index, keys[median_key_index]))
        print("trail={}".format(trail))
        print("keys={}".format(keys))
    return trail, keys, median_pos, median_key_index, median_key

def get_median(trail, keys, median_pos, median_key_index, is_odd):
    i = median_key_index
    if is_odd:
        median = keys[i]
        if(debug):
            print("odd median: keys[{}]={} == {}".format(i, keys[i], median))
    else:
        # even d, so need average of two numbers.
        # if i is last of same element, second number is the next
        # largest number.
        if(debug):
            print("even median_pos({}) == trail[keys[i]]({})-1".format(median_pos, trail[keys[i]]))
        if median_pos == trail[keys[i]]-1:
            median = (keys[i] + keys[i+1])/2.0
            if(debug):
                print("(keys[{}]={} + keys[{}]={})/2.0 == {}".format(i, keys[i], i+1, keys[i+1], median))
        else:
            median = keys[i]
            if(debug):
                print("keys[{}]={} == {}".format(i, keys[i], median))
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
