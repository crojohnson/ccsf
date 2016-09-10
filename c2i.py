"""
This program formats code for CCSF Insight's "moodle autoformat option" and copies it to clipboard.
Requires Pyperclip
"""

from sys import argv
from pyperclip import copy

java_keywords = ["abstract", "continue", "for", "new", "switch",
                "assert", "default", "goto", "package", "synchronized",
                "boolean", "do", "if", "private", "this", "break",
                "double", "implements", "protected", "throw", "byte",
                "else", "import", "public", "throws", "case", "enum",
                "instanceof", "return", "transient", "catch", "extends",
                "int", "short", "try", "char", "final", "interface", "static", 
                "void", "class", "finally", "long", "strictfp", "volatile",
                "const", "float", "native", "super", "while"]

if len(argv) in [2, 3]:
    with open(argv[1]) as file:
        result = "<font style=\"font-family:'Courier New';font-size:10pt;\">"
    
        for line in file:
            result += line.replace(" ", "&nbsp;").replace("<", "&lt;").replace(">", "&gt;")

        # todo: implement syntax highlighting
        #for keyword in java_keywords:
        #    result = result.replace(keyword + '&nbsp;', "<font style='color:blue;'>" + keyword + "</font>&nbsp;")


    # copy result to clipboard
    copy(result)

    # if filename was provided, write result to file
    if len(argv) == 3:
        f = open(argv[2], 'w')
        f.write(result)
        f.close()
    
else:
    print("\nUsage: python c2i.py InputFilename [OutputFilename]\n\nWarning: running " +
    "this program will overwrite your clipboard contents.\nRequires pyperclip.  " +
    "To install it, run: pip install Pyperclip\n")
    