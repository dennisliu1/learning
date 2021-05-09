import random

class Heap:
  def __init__(self):
    self.arr = []
    # self.size = size

  def pop(self):
    if self.size() <= 0:
      return None
    
    # return smallest value
    x = self.arr[0]
    self.arr[0] = self.arr[-1]
    del self.arr[-1]

    self._bubbleDown(0) # bubble down
    return x

  def insert(self, x):
    # insert x into heap
    self.arr.append(x)
    self._bubbleUp(self.size()-1)

  def size(self):
    return len(self.arr)
  
  def heapify(self):
    pass

  def _getLeftChild(self, i):
    return 2*i + 1
  
  def _getRightChild(self, i):
    return 2*i + 2

  def _getParent(self, i):
    return (i-1) // 2

  # def _isLeaf(self, i):
  #   return ((self.size()) // 2) <= i and i <= self.size()
  
  def _swap(self, i, j):
    self.arr[i], self.arr[j] = self.arr[j], self.arr[i]

  def _bubbleDown(self, i): # heapify
    l = self._getLeftChild(i)
    r = self._getRightChild(i)
    
    # get the smallest of left and right child
    smallest = i
    if l < self.size() and self.arr[l] < self.arr[smallest]:
      smallest = l
    if r < self.size() and self.arr[r] < self.arr[smallest]:
      smallest = r
    
    if smallest != i:
      self._swap(i, smallest)
      self._bubbleDown(smallest)

  def _bubbleUp(self, i):
    iter = i
    while iter != 0 and self.arr[iter] < self.arr[self._getParent(iter)]:
      self._swap(iter, self._getParent(iter))
      iter = self._getParent(iter)
  
  def __repr__(self):
    return str(self.arr)

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