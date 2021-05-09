#!/bin/bash

diff <(cat inputs/in1 | python3 main.py) outputs/out1
diff <(cat inputs/in2 | python3 main.py) outputs/out2
diff <(cat inputs/in3 | python3 main.py) outputs/out3