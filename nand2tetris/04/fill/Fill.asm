// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/04/Fill.asm

// Runs an infinite loop that listens to the keyboard input.
// When a key is pressed (any key), the program blackens the screen,
// i.e. writes "black" in every pixel;
// the screen should remain fully black as long as the key is pressed.
// When no key is pressed, the program clears the screen, i.e. writes
// "white" in every pixel;
// the screen should remain fully clear as long as no key is pressed.

// Put your code here.

(WAITPRESS)
@KBD
D=M
@STARTTOGGLE
D;JGT
@WAITPRESS
0;JMP

(WAITRELEASE)
@KBD
D=M
@STARTTOGGLE
D;JEQ
@WAITRELEASE
0;JMP

(STARTTOGGLE)
@i
M=0
@TOGGLE
0;JMP

(TOGGLE)
@i
M=M+1

@i
D=M
@SCREEN
A=D+A
M=!M

@i
D=M
@8192
D=D-A
@DECIDEWAIT
D;JEQ
@TOGGLE
0;JMP

(DECIDEWAIT)
@KBD
D=M
@WAITRELEASE
D;JGT
@WAITPRESS
0;JMP

