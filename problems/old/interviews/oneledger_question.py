# given an array, return the sum of the two largest numbers

def sum_two_largest(arr):
    arr.sort() # O(nlogn)
    return arr[:-1] + arr[:-2]

def sum_two_largest(arr):
    max = None
    max2 = None
    for i in arr: # O(n)
        if max is None:
            max = i
        elif i > max:
            max2 = max
            max = i
        elif i > max2:
            max2 = i
    return max + max2

# try 2

import heapq

def sum_max_arr(arr):
    max = None
    max2 = None
    for i in arr:
        if max is None:
            max = i
        elif i > max:
            max2 = max
            max = i
        elif i > max2:
            max2 = i
    return max + max2

def sum_max_arr(arr):
    arr.sort()
    return arr[:-1] + arr[:-2]

# O(nlogn + m); O(nlogn + n) if m=n
def sum_max_arr(arr, m):
    arr.sort() # nlogn
    sum = 0
    for i in range(-1, -1*m-1): # m
        sum = sum + arr[:-1*i]
    return sum

# O(n*m); O(n^2) if m=n
# O(n*logm + m); if m=n then O(nlogn + n)
def sum_max_arr(arr, m):
    maxHeap = heapq()
    for i in arr:
        if len(maxArr) < m:
            heapq.heappush(h, i) # O(logm)
        elif i > maxHeap[0]:
            heapq.heappop(h) # removes smallest O(logm)
            heapq.heappush(h, i) # O(logm)
    return sum(maxHeap)