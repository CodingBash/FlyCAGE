# -*- coding: utf-8 -*-
"""
Created on Thu Sep 21 07:46:43 2017

@author: bbece
"""

import csv

output_string = ""
with open("expression-stages-2.txt") as tsvin:
    tsvin = csv.reader(tsvin, delimiter='\t')
    
    for row in tsvin:
        output_string = output_string + 'expressionStages.add("' + row[0] + '");' + "\n"
    
print(output_string)
    