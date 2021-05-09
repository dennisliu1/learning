import random
# https://en.wikipedia.org/wiki/Trie

class TrieNode:
  def __init__(self, value=None):
    self.value = value
    self.children = dict()

class Trie:
  def __init__(self):
    self.root = None

  def find(self, key):
    return self.search(self.root, key)

  # find if the key is in the trie
  def search(self, node, key):
    for char in key:
      if char in node.children:
        node = node.children[char]
      else:
        return None
    return node.value

  def insert(self, key, value):
    return self.insertNodes(self.root, key, value)

  def insertNodes(self, root, key, value):
    if root is None:
      root = TrieNode()
    
    # print('inserting {}'.format(key))
    node = root
    for char in key:
      # print('char {}'.format(char))
      if char not in node.children:
        node.children[char] = TrieNode()
      node = node.children[char]
    node.value = value
    return root
  
  def delete(self, key):
    return self.deleteNodes(self.root, key)

  def deleteNodes(self, root, key):
    # eagerly delete the key at root, return whether trie rooted at root is now empty
    def _delete(node, key, i):
      # clear node corresponding to key[d], delete child key[d+1] if that subtrie is completely empty
      # return whether ndoe has been cleared
      if i == len(key):
        node.value = None
      else:
        c = key[i]
        if c in node.children and _delete(node.children[c], key, i+1):
          del node.children[c]
      # return if the sub-trie rooted at node is now completely empty
      return node.value is None and len(node.children) == 0
    
    return _delete(root, key, 0)

  def autocomplete(self, prefix):
    return self.keys_with_prefix(self.root, prefix)

  def keys_with_prefix(self, node, prefix):
    def _collect(x, prefix, results):
      if x is None:
        return
      if x.value is not None:
        prefix_str = ''.join(prefix)
        results.append(prefix_str)
      for c in x.children:
        prefix.append(c)
        _collect(x.children[c], prefix, results)
        del prefix[-1]
    
    def _get_node(node, key):
      for char in key:
        if char in node.children:
          node = node.children[char]
        else:
          return None
      return node

    results = []
    x = _get_node(self.root, prefix)
    _collect(x, list(prefix), results)
    return results

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
    trie.root = trie.insert(input[i], input[i])
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

  print('delete')
  deletion = list(input)
  random.shuffle(deletion)

  for word in deletion:
    trie.delete(word)
    print('delete', word)
    printTree(trie.root)