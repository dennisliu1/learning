import random
# https://en.wikipedia.org/wiki/Trie

class TrieNode:
  def __init__(self, value=None):
    self.value = value
    self.children = dict()
  
  def __str__(self):
    return str(self.value)

class Trie:
  def __init__(self):
    self.root = None

  def find(self, key):
    pass

  def insert(self, key, value):
    pass
  
  def delete(self, key):
    pass

  def deleteNode(self, node, key, i):
    pass

  def autocomplete(self, prefix):
    pass
  
  def getValues(self, node, result):
    pass


def printTree(root):
  def _printNode(node, depth):
    if node is None:
      return
    for char in node.children:
      print('{}{}'.format('-' * depth, char))
      _printNode(node.children[char], depth+1)
  print('root')
  _printNode(root, 1)

if __name__ == "__main__":
  trie = Trie()

  input = ['baby', 'bad', 'bank', 'box', 'blah', 'dark', 'dad', 'dance', 'bye']
  for i in range(len(input)):
    trie.insert(input[i], input[i])
  print('input: ', input)
  printTree(trie.root)
  print()

  print('find')
  search = list(input + ['a', '', 'be', 'by'])
  for i in range(len(search)):
    result = trie.find(search[i])
    print('{} = {}'.format(search[i], result))
  print()

  print('autocomplete:')
  prefixes = ['b', 'ba', 'd']
  for prefix in prefixes:
    results = trie.autocomplete(prefix)
    print('{} = {}'.format(prefix, results))

  
  deletion = list(input)
  random.shuffle(deletion)
  print('delete:', deletion)

  for word in deletion:
    trie.delete(word)
    print('delete', word)
    # printTree(trie.root)


# class Trie:
#   def __init__(self):
#     self.root = None

#   def find(self, key):
#     pass

#   def insert(self, key, value):
#     pass
  
#   def delete(self, key):
#     pass

#   def deleteNode(self, node, key, i):
#     pass

#   def autocomplete(self, prefix):
#     pass
  
#   def getValues(self, node, result):
#     pass