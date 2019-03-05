import re
import argparse

parser = argparse.ArgumentParser(description='Translate assembly code to hack.')
parser.add_argument('infile', type=argparse.FileType('r'))
parser.add_argument('outfile', nargs='?', type=str)
args = parser.parse_args()
lines = [line.strip() for line in args.infile.readlines()]

predefined = {
    'SP': 0, 'LCL': 1, 'ARG': 2, 'THIS': 3, 'THAT': 4, 'SCREEN': 0x4000, 'KBD': 0x6000,
    **{f'R{i}': i for i in range(16)}
}

ds = {'AMD': '111', 'AD': '110', 'AM': '101', 'A': '100', 'MD': '011', 'D': '010', 'M': '001'}
cs = {'A|D': '010101', 'D|A': '010101', 'A&D': '000000', 'D&A': '000000', 'A-D': '000111', 'D-A': '010011', 'A+D': '000010', 'D+A': '000010', 'A-1': '110010', 'D-1': '001110', '1+A': '110111', 'A+1': '110111', '1+D': '011111', 'D+1': '011111', '-A': '110011', '-D': '001111', '!A': '110001', '!D': '001101', 'A': '110000', 'D': '001100', '-1': '111010', '1': '111111', '0': '101010'}
cs = {**{key.replace('A', 'M'): val for key, val in cs.items() if 'A' in key}, **cs}
js = {'JGT': '001', 'JEQ': '010', 'JGE': '011', 'JLT': '100', 'JNE': '101', 'JLE': '110', 'JMP': '111'}

symbol = r'(\.|\$|:|[a-zA-Z])(\.|\$|:|\w)*'
comment = r'\s*\/\/.*'

def joinre(mappings):
    return '|'.join(map(re.escape, mappings.keys()))

syntax = {k: rf'^{v}({comment})?$' for k, v in {
    'label': rf'\((?P<label>{symbol})\)',
    'a_instruction': rf'@((?P<name>{symbol})|(?P<value>\d+))',
    'c_instruction': rf'((?P<d>{joinre(ds)})=)?(?P<c>({joinre(cs)}(?=;)|{joinre(cs)}))(;(?P<j>{joinre(js)}))?',
    'newline': ''
}.items()}

symbols = {}
hack = []
variable_address = 0x10

def numToInstruction(num):
    return '0' + format(num, '015b')

def error(exception):
    print('Error compiling: ' + exception)
    exit(1)

line_number = 0
for line in lines:
    res = re.match(syntax['label'], line)
    if res:
        if res.group('label') in symbols:
            error(f'label "{res.group("label")}" already defined')
        symbols[res.group('label')] = line_number

    elif re.match(syntax['a_instruction'] + '|' + syntax['c_instruction'], line):
        line_number += 1

for line in lines:
    res = re.match('|'.join(syntax.values()), line)
    if not res:
        error(f'invalid syntax: "{line}"')

    elif res.group('name'):
        if res.group('name') not in predefined:
            if res.group('name') not in symbols:
                symbols[res.group('name')] = variable_address
                variable_address += 1
            name = symbols[res.group('name')]
        else:
            name = predefined[res.group('name')]
        hack.append(numToInstruction(name))

    elif res.group('value'):
        hack.append(numToInstruction(int(res.group('value'))))

    elif res.group('c'):
        a = '1' if 'M' in res.group('c') else '0'
        hack.append('111' + a + cs[res.group('c')] + ds.get(res.group('d'), '000') + js.get(res.group('j'), '000'))

outfile = args.outfile or args.infile.name.split('.')[0] + '.hack'
with open(outfile, 'w') as write_file:
    write_file.writelines('\n'.join(hack) + '\n')

