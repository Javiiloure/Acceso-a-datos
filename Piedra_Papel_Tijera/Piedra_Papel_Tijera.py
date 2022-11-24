
from tkinter import *
from tkinter import messagebox
from tkinter import Button
import random
my_list = ["piedra", "papel", "tijera"]
app = Tk()
app.geometry("50x50")
tk = messagebox
def Tijera():
    aux = random.choice(my_list)
    if(aux == Piedra): 
        tk.showinfo(message="Has perdido", title="Piedra")
    elif(aux == Papel): 
        tk.showinfo(message="Has ganado", title="Papel")
    elif(aux == Tijera):
        tk.showinfo(message="Empate", title="Tijera")

def Papel():
    aux = random.choice(my_list)
    if(aux == Piedra): 
        tk.showinfo(message="Has perdido", title="Piedra")
    elif(aux == Papel): 
        tk.showinfo(message="Has ganado", title="Papel")
    elif(aux == Tijera):
        tk.showinfo(message="Empate", title="Tijera")
    tk.showinfo(message="Has eledigo Papel", title="Papel")

def Piedra():
    aux = random.choice(my_list)
    if(aux == Piedra): 
        tk.showinfo(message="Has perdido", title="Piedra")
    elif(aux == Papel): 
        tk.showinfo(message="Has ganado", title="Papel")
    elif(aux == Tijera):
        tk.showinfo(message="Empate", title="Tijera")
    
Tijera = Button(master=app, text="Tijera", command=Tijera)
Tijera.pack()
Papel = Button(master=app, text="Papel", command=Papel)
Papel.pack()
Piedra = Button(master=app, text="Piedra", command=Piedra)
Piedra.pack()

app.mainloop()
#tk.showinfo(message=random.choice(my_list), title="Piedra Papel Tijera")
