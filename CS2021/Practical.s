	AREA	AsmTemplate, CODE, READONLY
	IMPORT	main

; I reused the code from the sample program
; (c) Mike Brady, 2011 -- 2019.

	EXPORT	start
start

ASCIITOP	EQU	0x00000030		;the part of the ascii sequence we don't want.
NUMBER		EQU "0123456789"	;the number to be shown
	
IO1DIR	EQU	0xE0028018
IO1SET	EQU	0xE0028014
IO1CLR	EQU	0xE002801C

	ldr	r1,=IO1DIR
	ldr	r2,=0x000f0000	;select P1.19--P1.16
	str	r2,[r1]		;make them outputs
	ldr	r1,=IO1SET
	str	r2,[r1]		;set them to turn the LEDs off
	ldr	r2,=IO1CLR
; r1 points to the SET register
; r2 points to the CLEAR register

; Main loop

launch	mov r5, -1             
	mov r6, 0x00000000	   
	b	light0 			   
	
charloop	mov r6, NUMBER[r5]   

	cmp r6, 0           
	beq launch             

	sub r6, ASCIITOP       
	
	cmp r6, 0x00000000			;convert 0 to 1111 so 0 shows up
	addeq r6, 0x0000000f
	
	
light0	cmp r6, 0x00000008
		bgt	on0
		beq	on0
		blt	off0
	
light1	cmp r6, 0x00000004
		bgt	on1
		beq	on1
		blt	off1
		
light2	cmp r6, 0x00000002
		bgt	on2
		beq	on2
		blt	off2
	
light3	cmp r6, 0x00000001
		bgt	on3
		beq	on3
		blt	off3
		
lightd	add r5, 1

		ldr	r4,=8000000		;wait
dloop	subs	r4,r4,#1
		bne	dloop
		
		b charloop           ; loop

; Turn lights on or off

on0		ldr	r3,=0x00010000
		str	r3,[r2]
		sub r6,0x00000008
		b 	light1
	
on1 	ldr	r3,=0x00020000
		str	r3,[r2]
		sub r6,0x00000004
		b	light2

on2		ldr	r3,=0x00040000
		str	r3,[r2]
		sub r6,0x00000002
		b	light3

on3		ldr	r3,=0x00080000
		str	r3,[r2]
		b	lightd

off0	ldr	r3,=0x00010000
		str	r3,[r1]
		b	light1

off1	ldr	r3,=0x00020000
		str	r3,[r1]
		b	light2

off2	ldr	r3,=0x00040000
		str	r3,[r1]
		b	light3

off3	ldr	r3,=0x00080000
		str	r3,[r1]
		b	lightd
	END