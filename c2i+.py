"""
This program formats code for CCSF Insight's 
"moodle autoformat option" and copies it to clipboard.
Requires Pyperclip

This is the "fancy" version with minor syntax highlighting.  

todos:
-improve/fix replacement characters used to designate HTML tags
-add String highlighting
-add method highlighting
-fix issues with keywords next to parenthesis, e.g. "for(int i ...)"
"""

import argparse
from pyperclip import copy

java = ["abstract", "continue", "for", "new", "switch",
        "assert", "default", "goto", "package", "synchronized",
        "boolean", "do", "if", "private", "this", "break",
        "double", "implements", "protected", "throw", "byte",
        "else", "import", "public", "throws", "case", "enum",
        "instanceof", "return", "transient", "catch", "extends",
        "int", "short", "try", "char", "final", "interface", "static", 
        "void", "class", "finally", "long", "strictfp", "volatile",
        "const", "float", "native", "super", "while", "true", "false"]

ruby = ["BEGIN", "END", "__ENCODING__", "__END__", "__FILE__", "__LINE__",
        "alias", "and", "begin", "break", "case", "class", "def", "defined?",
        "do", "else", "elsif", "end", "ensure", "false", "for", "if", "in",
        "module", "next", "nil", "not", "or", "redo", "rescue", "retry",
        "return", "self", "super", "then", "true", "undef", "unless", 
        "until", "when", "while", "yield"]

python = ["and", "del", "from", "not", "while", "as", "elif", "global",
        "or", "with", "assert", "else", "if", "pass", "yield", "break",
        "except", "import", "print", "class", "exec", "in", "raise",
        "continue", "finally", "is", "return", "def", "for", "lambda", "try",
        "True", "False"]
        
parser = argparse.ArgumentParser(description="\nThis program formats code " +
        "for CCSF Insight's \"moodle autoformat option\" and copies it to " +
        "clipboard.\nRequires pyperclip.  To install it, run: pip " +
        "install Pyperclip\n")

parser.add_argument("-i", help = "Input file", required = True)
parser.add_argument("-o", help = "Output file", required = False)

args = vars(parser.parse_args())

# Select language based on filename
language = None
extension = ''.join(args.get("i").split(".")[-1:])
if extension in ["java", "python", "ruby"]: language = extension

with open(args.get("i")) as file:
    result = "<div style=\"background:#000; border-left:5px solid #777;" \
      + "padding:10px; color:#fff; font-family:'Courier New';"\
      + "font-size:10pt;\">"
    
    opened_comment = False
    process_line = True

    for line in file:
        
        if language:
            opened_quote = False
            opened_apostrophe = False

            temp_line = line.split(" ")
            
            for i in range(len(temp_line)):
                
                # check for beginning comment sections
                if not opened_comment and ((language == "java" and "/*" in temp_line[i]) or \
                    (language in ["ruby", "python"] and "\"\"\"" in temp_line[i]) or \
                    (language in ["ruby", "python"] and "'''" in temp_line[i])):
                    opened_comment = True
                    temp_line[i] = "~span`style='color:#999'%" + temp_line[i]
            
                # check for ending comment section
                if opened_comment and ((language == "java" and "*/" in temp_line[i]) or \
                    (language in ["ruby", "python"] and "\"\"\"" in temp_line[i]) or \
                    (language in ["ruby", "python"] and "'''" in temp_line[i])):
                    opened_comment = False
                    if "\n" in temp_line[i]: 
                        temp_line[i] += "~/span%  "
                    else: 
                        temp_line[i] += "~/span%"
            
                # check for a single line comment
                if not opened_comment and not opened_apostrophe and not opened_quote and \
                    ((not opened_comment and temp_line[i].startswith("#") \
                    and language in ["python", "ruby"]) or \
                    (temp_line[i].startswith("//") and language == "java")):
                    line = " ".join(temp_line[:temp_line.index(temp_line[i])]) \
                    + "~span`style='color:#999'%" + \
                    " ".join(temp_line[temp_line.index(temp_line[i]):]) + ("~/span%")
                    process_line = False
                    break
            
                # check for string open/close
                if not opened_comment:
                    if not opened_apostrophe and "'" in temp_line[i] and \
                        temp_line[i].count("'") % 2 == 0:
                        opened_apostrophe = True
                    elif not opened_quote and "\"" in temp_line[i] and \
                        temp_line[i].count("\"") % 2 == 0:
                        opened_quote = True
                        
                    # add color to keywords not in strings or comments
                    if not opened_apostrophe and not opened_quote:
                        for keyword in eval(language):
                            if temp_line[i] == keyword:
                                temp_line[i] = "~font`style=\"color:#44f;\"%" \
                                  + temp_line[i] + "~/font%"
                                  
                if not opened_comment:
                    if opened_apostrophe and "'" in temp_line[i] and \
                        temp_line[i].count("'") % 2 == 0:
                        opened_apostrophe = False
                    elif opened_quote and "\"" in temp_line[i] and \
                        temp_line[i].count("\"") % 2 == 0:
                        opened_quote = False

            
            if process_line:
                line = " ".join(temp_line)
            else: process_line = True

            # end if language
        
        result += line.replace("<", "&lt;").replace(">", "&gt;")\
                      .replace(" ", "&nbsp;").replace("(n)", "&#40;n&#41;")\
                      .replace("`", " ").replace("~", "<").replace("%", ">")
                  
    result += "</div>" 
    
# Copy result to clipboard
copy(result)

# If filename was provided, write result to file
if args.get("o"):
    f = open(args.get("o"), 'w')
    f.write(result)
    f.close()