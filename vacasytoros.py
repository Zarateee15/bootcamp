####### Desarrollado por Jorge Zárate
import os, random

def limpiarPantalla():
    os.system("cls" if os.name == "nt" else "clear")

def mostrarMenu():
    limpiarPantalla()
    print(f"----------------------------------------------------------------------------------------------------")
    print(f"                                         VACAS y TOROS")
    print(f"----------------------------------------------------------------------------------------------------")
    print(f"Ingrese una una cadena de 4 digitos e intenta adivinar el numero que está pensando la máquina! ")
    print(f"----------------------------------------------------------------------------------------------------")


def pedirNumero():
    while True:
        try:
            numero = int(input("Ingrese un numero: "))
            if numero in range(1000, 10000): 
                numero = str(numero)
                n1 = numero[0]
                n2 = numero[1]
                n3 = numero[2]
                n4 = numero[3]
    
                if n1 != n2 and n1!=n3 and n1!=n4 and n2!=n3 and n2!=n4 and n3!=n4 :
                    break
                else:
                    print(f"Numero Invalido.")
                    
            else:
                print(f"Numero Invalido.")
                
        except ValueError:
            print(f"Numero Invalido.")
            
    return numero


def contNumeros(numero, numero_maquina):
    limpiarPantalla()
    toros = 0
    vacas = 0
    numero = str(numero)
    numero_maquina = str(numero_maquina)
    
    for i in range(0, 4):
        if numero_maquina[i]==numero[i]:
            toros+=1
            
        for j in range (0,4):
            if numero[i] == numero_maquina[j]:
                vacas+=1        
    
    print(f"Numero del usuario: {numero} ")
    print(f"Cantidad de Vacas: {vacas}")
    print(f"Cantidad de Toros: {toros}")
    print(f"Por favor, intente de nuevo.")
    
    return toros
    
    
if __name__ == "__main__":
    while True:
        numero_maquina = str(random.randint(1000, 9999))
        if numero_maquina[0] != "0":
            break
    '''numero_maquina = 1234'''

    mostrarMenu()
    
    while True:    
        num = str(pedirNumero())
        toros = contNumeros(num, numero_maquina)
        if toros == 4:
            limpiarPantalla()
            print(f"Felicidades! Ganaste el juego!")
            print(f"El numero secreto era {numero_maquina}")
            break
