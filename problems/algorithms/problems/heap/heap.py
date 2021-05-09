import random

# https://www.geeksforgeeks.org/binary-heap/
# read the examples, its an actual working solution.

class Heap:
  def __init__(self):
    self.heap = []
    self.size = 0

  def pop(self):
    pass

  def insert(self, x):
    pass
  
  def heapify(self, i):
    pass
  
  def __repr__(self):
    return str(self.heap)

if __name__ == "__main__":
  heap = Heap()
  input = list(range(10))
  random.shuffle(input)
  for i in range(len(input)):
    val = input[i]
    heap.insert(val)
  print('input: ', input)
  print('heap:  ', heap)

  output = []
  for i in range(heap.size):
    val = heap.pop()
    output.append(val)
    print('heap:', val, heap)
  print('output:', output)






















# class Heap:
#   def __init__(self):
#     self.heap = []
#     self.size = 0

#   def pop(self):
#     pass

#   def insert(self, x):
#     pass
  
#   def heapify(self):
#     pass
  
#   def __repr__(self):
#     return str(self.heap)