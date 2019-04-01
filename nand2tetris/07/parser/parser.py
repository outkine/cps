import re, argparse, os

parser = argparse.ArgumentParser(description='Translate VM code to assembly.')
parser.add_argument('infile', type=argparse.FileType('r'))
parser.add_argument('outfile', nargs='?', type=str)
args = parser.parse_args()
lines = [line.strip() for line in args.infile.readlines()]

ops = {'add': 'D+M', 'sub': 'M-D', 'neg': '-M', 'and': 'D&M', 'or': 'D|M', 'not': '!M'}
segments = {'local': 'LCL', 'argument': 'ARG', 'this': 'THIS', 'that': 'THAT', 'pointer': 3, 'temp': 5}

def joinre(mappings):
    return '|'.join(map(re.escape, mappings.keys()))

comment = r'\s*\/\/.*'
syntax = {k: rf'^{v}({comment})?$' for k, v in {
    'operation': rf'(?P<op>{joinre(ops)}|lt|gt|eq)',
    'memory': rf'(?P<command>push|pop) (?P<segment>{joinre(segments)}|constant|static) (?P<index>\d+)',
    'newline': '',
}.items()}

file_name = os.path.basename(args.infile.name).split('.')[0]
def get_address(segment, index):
    if segment == 'static':
        return [f'@{file_name}.{index}']
    if segment in ('pointer', 'temp'):
        first = [f'@{segments[segment]}']
    else:
        first = [f'@{segments[segment]}', 'A=M']
    return first + ['A=A+1' for _ in range(int(index))]


i = 0
def translate_op(op):
    global i
    if op in ('lt', 'gt', 'eq'):
        i += 1
        return [f'@RET_{i}', 'D=A', '@' + op.upper(), '0;JMP', f'(RET_{i})']
    elif op in ('not', 'neg'):
        return ['@SP', 'A=M-1', 'M=' + ops[op]]
    else:
        return ['@SP', 'AM=M-1', 'D=M', 'A=A-1', 'M=' + ops[op]]

def translate_push(segment, index):
    if segment == 'constant':
        first = ['@' + index, 'D=A']
    else:
        first = get_address(segment, index) + ['D=M']
    return first + ['@SP', 'AM=M+1', 'A=A-1', 'M=D']

def translate_pop(segment, index):
    return ['@SP', 'AM=M-1', 'D=M'] + get_address(segment, index) + ['M=D']

def error(exception):
    print('Error compiling: ' + exception)
    exit(1)

def generate_func(name, jump):
    return [
        f'({name})', '@R13', 'M=D', '@SP', 'AM=M-1', 'D=M', 'A=A-1', 'D=M-D', 'M=-1',
        f'@{name}_RET', f'D;{jump}', '@SP', 'A=M-1', 'M=0',
        f'({name}_RET)', '@R13', 'A=M', '0;JMP',
    ]

output = ['@START', '0;JMP']
for name, jump in [('EQ', 'JEQ'), ('LT', 'JLT'), ('GT', 'JGT')]:
    output += generate_func(name, jump)
output += ['(START)']

for line in lines:
    res = re.match('|'.join(syntax.values()), line)
    if not res:
        error(f'invalid syntax: "{line}"')

    elif res.group('op'):
        output += translate_op(res.group('op'))

    elif res.group('command'):
        if res.group('command') == 'push':
            output += translate_push(res.group('segment'), res.group('index'))
        else:
            output += translate_pop(res.group('segment'), res.group('index'))

outfile = args.outfile or args.infile.name.split('.')[0] + '.asm'
with open(outfile, 'w') as write_file:
    write_file.writelines('\n'.join(output) + '\n')

