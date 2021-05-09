import sys

def tex_quotes(input):
    text = list(input)
    is_start = True
    idx = 0
    while idx >= 0:
        idx = text.index('"')
        if is_start and idx >= 0:
            text[idx] = "``"
            is_start = False
        elif is_start and idx >= 0:
            text[idx] = "''"
            is_start = True
        else:
            break
    return str(text)

result = ""
for line in sys.stdin:
    # print line
    result = result + tex_quotes(line)
print(result)