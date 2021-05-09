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
    # bst insert
    if node is None:
      return Node(x)
    if x < node.val:
      node.left = self.insertNode(node.left, x)
    else: # x >= node.val
      node.right = self.insertNode(node.right, x)
    
    # update node height
    node.height = self.updateHeight(node)

    # rebalance tree
    return self.rebalanceTree(node)

  def delete(self, x):
    pass

  def deleteNode(self, node, x):
    # bst delete
    if node is None:
      return node

    if x < node.val:
      node.left = self.deleteNode(node.left, x)
    elif x > node.val:
      node.right = self.deleteNode(node.right, x)
    else:
      # easy cases first
      if node.left is None:
        temp = node.right
        node = temp
      elif node.right is None:
        temp = node.left
        node = temp
      else:
        # both left and right; find the next node (smallest right subtree value) to replace current node
        smallest = self.getMinValueNode(node.right)
        node.val = smallest.val
        node.right = self.deleteNode(node.right, smallest.val)
    
    if node is None:
      return node

    # update node height
    node.height = self.updateHeight(node)

    # rebalance tree
    return self.rebalanceTree(node)

  # find the smallest value in this tree
  # this will be the leftmost leaf in the tree.
  def getMinValueNode(self, node):
    if node is None or node.left is None:
      return node
    return self.getMinValueNode(node.left)

  def search(self, node, x):
    if node is None:
      return node
    elif x < node.val:
      return self.search(node.left, x)
    elif x > node.val:
      return self.search(node.right, x)
    else:
      return node

  def getHeight(self, node):
    if node is None:
      return 0
    return node.height

  def getBalance(self, node):
    if node is None:
      return 0
    return self.getHeight(node.left) - self.getHeight(node.right)

  def rebalanceTree(self, node, x=0):
    balance = self.getBalance(node)

    # left left
    if balance > 1 and self.getBalance(node.left) >= 0:
      return self.leftRotate(node)
    elif balance > 1 and self.getBalance(node.left) < 0:
      node.left = self.rightRotate(node.left)
      return self.leftRotate(node)
    elif balance < -1 and self.getBalance(node.right) >= 0:
      return self.rightRotate(node)
    elif balance < -1 and self.getBalance(node.right) < 0:
      node.right = self.leftRotate(node.right)
      return self.rightRotate(node)
    return node

  def leftRotate(self, z):
    if z.left is None:
      return z
    y = z.left
    t3 = y.right

    y.right = z
    z.left = t3

    z.height = self.updateHeight(z)
    y.height = self.updateHeight(y)
    return y

  def rightRotate(self, z):
    if z.right is None:
      return z
    y = z.right
    t2 = y.left

    y.left = z
    z.right = t2

    z.height = self.updateHeight(z)
    y.height = self.updateHeight(y)
    return y

  def updateHeight(self, node):
    if node is None:
      return 0
    return 1 + max(self.getHeight(node.left), self.getHeight(node.right))

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
