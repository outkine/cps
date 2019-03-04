import re

predefined = {'SP': 0, 'LCL': 1, 'ARG': 2, 'THIS': 3, 'THAT': 4, 'SCREEN': 0x4000, 'KBD': 0x6000}
cs = {'0': '101010', '1': '111111', '-1': '111010', 'D': '001100', 'A': '110000', '!D': '001101', '!A': '110001', '-D': '001111', '-A': '110011', 'D+1': '011111', '1+D': '011111', 'A+1': '110111', '1+A': '110111', 'D-1': '001110', 'A-1': '110010', 'D+A': '000010', 'A+D': '000010', 'D-A': '010011', 'A-D': '000111', 'D&A': '000000', 'A&D': '000000', 'D|A': '010101', 'A|D': '010101'}
js = {'JGT': '001', 'JEQ': '010', 'JGE': '011', 'JLT': '100', 'JNE': '101', 'JLE': '110', 'JMP': '111'}
ds = {'M': '001', 'D': '010', 'MD': '011', 'A': '100', 'AM': '101', 'AD': '110', 'AMD': '111'}

symbol = r'(\.|\$|:|[a-zA-Z])(\.|\$|:|\w)*'
syntax = {
    'label': rf'\((?P<label>{symbol})\)',
    'comment': r'\/\/.*',
    'a_instruction': rf'@((?P<name>{symbol})|(?P<value>\d+))$',
    'c_instruction': r'((?P<d>.+)=)?(?P<c>(.+(?=;)|.+))(;(?P<j>.+))?',
    'newline': '\n'
}

symbols = {}
hack = []
variable_address = 0x400

def toBinary(num):
    return format(num, '015b')


for line in open('./program.asm'):
    res = re.match('|'.join(syntax.values()), line)
    if not res:
        raise Exception('Invalid syntax')

    if res.group('label'):
        if res.group('label') in symbols:
            raise Exception('Label already defined')
        symbols[res.group('label')] = len(hack)

    elif res.group('name'):
        if res.group('name') not in predefined:
            if res.group('name') not in symbols:
                symbols[res.group('name')] = variable_address
                variable_address += 1
            name = symbols[res.group('name')]
        else:
            name = predefined[res.group('name')]
        hack.append('0' + toBinary(name))

    elif res.group('value'):
        hack.append('0' + toBinary(int(res.group('value'))))


    elif res.group('c'):
        a = '1' if 'M' in res.group('c') else '0'

        hack.append(
            '111' + a + cs[res.group('c').replace('M', 'A')]
            + ds.get(res.group('d'), '000') + js.get(res.group('j'), '000')
        )

with open('./out.hack', 'w') as write_file:
    write_file.writelines('\n'.join(hack))

