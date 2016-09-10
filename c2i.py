"""
This program formats code for CCSF Insight's 
"moodle autoformat option" and copies it to clipboard.
Requires Pyperclip
"""

import argparse
from pyperclip import copy

parser = argparse.ArgumentParser(description="\nThis program formats code " +
        "for CCSF Insight's \"moodle autoformat option\" and copies it to " +
        "clipboard.\nRequires pyperclip.  To install it, run: pip " +
        "install Pyperclip\n")
        
parser.add_argument("-i", help = "Input file", required = True)
parser.add_argument("-o", help = "Output file", required = False)

args = vars(parser.parse_args())

with open(args.get("i")) as file:
    result = "<font style=\"font-family:'Courier New';font-size:10pt;\">"

    for line in file:
        result +=  line.replace("<", "&lt;").replace(">", "&gt;")\
                   .replace("\t", "    ").replace(" ", "&nbsp;")
                  
    result += "</font>" 
    
# Copy result to clipboard
copy(result)

# If filename was provided, write result to file
if args.get("o"):
    f = open(args.get("o"), 'w')
    f.write(result)
    f.close()
