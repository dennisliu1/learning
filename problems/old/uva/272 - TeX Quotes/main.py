import sys

def toTeXQuotes(line, danglingQuote):
    startPos = 0

    result = line

    # needs loop
    keepLooking = True
    while keepLooking:
        pos = result.find('\"', startPos)
        if pos >= 0:
            if not danglingQuote:
                replacement = '``'
            else:
                replacement = "''"
            result = result.replace('\"', replacement, 1)

            startPos = pos
            danglingQuote = not danglingQuote
        else:
            keepLooking = False
    
    return result, danglingQuote

if __name__ == '__main__':

    danglingQuote = False
    for line in sys.stdin:
        result, danglingQuote = toTeXQuotes(line, danglingQuote)
        print(result, end='')

    # print('')
    exit(0)