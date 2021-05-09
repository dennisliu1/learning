import random

class Node:
  def __init__(self, val, height=1, parent=None, left=None, right=None):
    self.val = val
    self.height = height
    self.parent = parent
    self.left = left
    self.right = right

  def __repr__(self):
    return "(val={} height={})".format(self.val, self.height)

class AVLTree:
  def __init__(self):
    self.root = None

  def insert(self, x):
    pass
  
  def insertNode(self, node, x):
    pass

  def delete(self, x):
    pass

  def deleteNode(self, node, x):
    pass

  def getMinValueNode(self, node):
    pass

  def search(self, node, x):
    pass

  def getHeight(self, node):
    pass

  def getBalance(self, node):
    pass

  def rebalanceTree(self, node):
    pass

  def leftRotate(self, z):
    pass

  def rightRotate(self, z):
    pass

  def updateHeight(self, node):
    pass

  def preOrder(self, root, fn):
    if not root:
      return

    fn(root)
    
    self.preOrder(root.left, fn)
    self.preOrder(root.right, fn)

  def checkTreeBalance(self, node):
    if node is None:
      return True

    return -1 <= self.getBalance(node) <= 1 and self.checkTreeBalance(node.left) and self.checkTreeBalance(node.right)

def printNode(node):
  print("{} ".format(node.val), end="")

if __name__ == "__main__":
  tree = AVLTree()

  # 1. insert
  print('1. insert')

  input = list(range(10))
  random.shuffle(input)
  print('input', input)

  root = None
  for i in range(len(input)):
    val = input[i]
    root = tree.insertNode(root, val)

  print("Preorder Traversal before deletion, balance:{} = ".format(tree.checkTreeBalance(root)), end="")
  tree.preOrder(root, printNode)
  print()

  # 2. search
  print('2. search')
  searchKeys = list(input)
  random.shuffle(searchKeys)
  for i in range(len(searchKeys)):
    key = searchKeys[i]
    result = tree.search(root, key)
    print("search:{}, balance:{} = {}".format(key, tree.checkTreeBalance(root), result), end="")
    print()

  print('3. delete')
  deleteKeys = list(input)
  random.shuffle(deleteKeys)

  for i in range(len(deleteKeys)):
    key = deleteKeys[i]
    root = tree.deleteNode(root, key)
    print("delete:{}, balance:{} = ".format(key, tree.checkTreeBalance(root)), end="")
    tree.preOrder(root, printNode)
    print()










# class Node:
#   def __init__(self, val, height=1, parent=None, left=None, right=None):
#     self.val = val
#     self.height = height
#     self.parent = parent
#     self.left = left
#     self.right = right

#   def __repr__(self):
#     return "(val={} height={})".format(self.val, self.height)

# class AVLTree:
#   def __init__(self):
#     self.root = None

#   def insert(self, x):
#     pass
  
#   def insertNode(self, node, x):
#     pass

#   def delete(self, x):
#     pass

#   def deleteNode(self, node, x):
#     pass

#   def getMinValueNode(self, node):
#     pass

#   def search(self, node, x):
#     pass

#   def getHeight(self, node):
#     pass

#   def getBalance(self, node):
#     pass

#   def rebalanceTree(self, node, x):
#     pass

#   def leftRotate(self, z):
#     pass

#   def rightRotate(self, z):
#     pass

#   def updateHeight(self, node):
#     pass

#   def preOrder(self, root, fn):
#     if not root:
#       return

#     fn(root)
    
#     self.preOrder(root.left, fn)
#     self.preOrder(root.right, fn)

#   def checkTreeBalance(self, node):
#     if node is None:
#       return True

#     return -1 <= self.getBalance(node) <= 1 and self.checkTreeBalance(node.left) and self.checkTreeBalance(node.right)
