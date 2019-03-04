import re

predefined = {'SP': 0, 'LCL': 1, 'ARG': 2, 'THIS': 3, 'THAT': 4, 'SCREEN': 0x4000, 'KBD': 0x6000}
cs = {'0': '101010', '1': '111111', '-1': '111010', 'D': '001100', 'A': '110000', '!D': '110000', '!A': '001101', '-D': '110001', '-A': '001111', 'D+1': '110011', 'A+1': '011111', 'D-1': '110111', 'A-1': '001110', 'D+A': '110010', 'A+D': '000010', 'D-A': '000010', 'A-D': '010011', 'D&A': '000111', 'A&D': '000000', 'D|A': '000000', 'A|D': '010101'}
js = {'JGT': '001', 'JEQ': '010', 'JGE': '011', 'JLT': '100', 'JNE': '101', 'JLE': '110', 'JMP': '111'}
ds = {'M': '001', 'D': '010', 'MD': '011', 'A': '100', 'AM': '101', 'AD': '110', 'AMD': '111'}

with open('./out.jack', 'w') as write_file:
    for line in open('./program.asm'):
        if line[0] == '@':
            pointer = predefined.get(line[1:], format(int(line[1:]), '015b'))
            write_file.write('0' + pointer + '\n')

        else:
            res = re.match(r"((?P<d>.+)=)?(?P<c>(.+(?=;)|.+))(;(?P<j>.+))?", line)
            a = '1' if 'M' in res.group('c') else '0'
            write_file.write('111' + a + cs[res.group('c').replace('M', 'A')] + ds[res.group('d')] + js[res.group('j')])
