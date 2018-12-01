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
@i
M=0

// toggle if screen is off and key is pressed
@SCREEN
D=!M
@KBD
D=D&M
@TOGGLE
D;JNE
// or screen is on and key is not presssed
@SCREEN
D=!M
@KBD
D=D|M
@TOGGLE
D;JEQ

@WAITPRESS
0;JMP

(TOGGLE)
@i
D=M
@SCREEN
A=D+A
M=!M

@i
M=M+1

@i
D=M
@8192
D=D-A
@WAITPRESS
D;JEQ
@TOGGLE
0;JMP

