; 6388185 Bunradar Chatchaiyadech
; Ascending sort three integers that is given by a user.

%include "asm_io.inc"

segment _DATA public align=4 class=DATA use32
Message         db      "Enter 3 numbers: ", 0
Message1        db      "Ascending sorted: ", 0
Message2        db      " ", 0
segment _BSS public align=4 class=BSS use32
n1              resd    1               ; the first number
n2              resd    1               ; the second number
n3              resd    1               ; the third number
group DGROUP _BSS _DATA

segment _TEXT public align=1 class=CODE use32
        global  _asm_main
_asm_main:
        enter   0,0               ; setup routine
        pusha

        mov     eax,  Message
        call    print_string         ; print "Enter 3 numbers: "
        
        call    read_int             ; input the first number
        mov     [n1], eax

        call    read_int             ; input the second number
        mov     [n2], eax

        call    read_int             ; input the third number
        mov     [n3], eax

if_1:
        mov eax, [n1]
        cmp eax, [n2]                ; compare n1 and n2
        jnle _Swap                   ; jump if n1 greater than n2

        mov eax, [n1]
        call print_int
            call print_nl
        mov eax, [n2]
        call print_int
            call print_nl

        jmp if_2                     ; jump to if_2    

_Swap:
        mov eax, [n1]
        mov ebx, [n2]
        push eax
        push ebx
        pop eax
        pop ebx           
        mov [n1], eax
        mov [n2], ebx


        mov eax, [n1]
        call print_int
        mov eax, [n2]
        call print_int
        
if_2:
        mov eax, [n1]
        cmp eax, [n3]                ; compare n1 and n3
        jnle _Swap1                   ; jump if n2 greater than n3
        jmp if_3                     ; jump to end if

_Swap1:
        mov [n1], eax
        mov [n3], ebx
        push eax
        push ebx
        pop eax
        pop ebx

if_3:
        mov eax, [n2]
        cmp eax, [n3]                ; compare n1 and n2
        jnle _Swap2                   ; jump if n2 greater than n3
        jmp _end                     ; jump to _end

_Swap2:
        mov [n2], eax
        mov [n3], ebx
        push eax
        push ebx
        pop eax
        pop ebx

_end:
        mov eax, Message1
        call print_string            ; print "Ascending sorted: "
        mov eax, [n1]
        call print_int
        mov eax, Message2
        call print_string
        mov eax, [n2]
        call print_int
        mov eax, Message2
        call print_string
        mov eax, [n3]
        call print_int
        popa
        mov eax, 0                   ; return
        leave
        ret