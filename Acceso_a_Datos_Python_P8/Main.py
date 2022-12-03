import xml.etree.ElementTree as ET
import tkinter as tk
from tkinter import *
from tkinter import messagebox
from tkinter import scrolledtext
from tkinter import Button
from tkinter.filedialog import askopenfilename

app = Tk()
app.title('Gestion de audiotecas')
app.geometry("800x800")
Tk().withdraw()
 
filename = askopenfilename(title='Seleccione la audioteca')
tree = ET.parse(filename)
root = tree.getroot()

def CrearAudioteca():
    
    segunda_ventana = Tk()
    segunda_ventana.title('Creación de nueva Audioteca')
    segunda_ventana.geometry("500x500")

    añadir_disco = Button(master=segunda_ventana, text="Añadir disco")
    añadir_disco.pack()
    añadir_canción = Button(master=segunda_ventana, text="Añadir cancion")
    añadir_canción.pack()

    Label
    entrada_disco = tk.Text(segunda_ventana, height=1, width=50)
    entrada_disco.pack()
    entrada_cancion = tk.Text(segunda_ventana, height=1, width=50)
    entrada_cancion.pack()
 
    discos = ET.Element("discos")
    disco = ET.SubElement(discos, 'disco', nombre = "disco 1")

    ET.SubElement(disco, "cancion", nombre="cancion 1").text = "Cancion 1"
    ET.SubElement(disco, "cancion", nombre="cancion 2").text = "Cancion 2"
    ET.SubElement(disco, "cancion", nombre="cancion 3").text = "Cancion 3"
    tree = ET.ElementTree(discos)
    tree.write('Discos3.xml')

def SeleccionarAudioteca():
    global filename, tree, root
    filename = askopenfilename(title='Seleccione la audioteca')
    tree = ET.parse(filename)
    root = tree.getroot()
    selec.config(text='Archivo seleccionado: \n' + filename)

def MostrarDiscos():
    string = ""
    chars='{},'

    #Recorremos el archivo xml guardando en string los elementos con sus atributos
    for child in root: 
        string += "\n\n" + str(child.tag) + str(child.attrib)
        for child in child:
            string += "\n" + str(child.tag) + str(child.attrib)

    # Borramos caracteres innecesarios de la cadena
    string = string.translate(str.maketrans('', '', chars))
    string = string.replace('\'', ' ')
    # Reemplazamos el contenido anterior por la nueva cadena
    st.replace('1.0', END, string)

def ModificarAudioteca():
    print()

crear_audioteca = Button(master=app, text="Crear nueva audioteca", command=CrearAudioteca)
crear_audioteca.pack()

seleccionar_audioteca = Button(master=app, text="Seleccionar otra audioteca", command=SeleccionarAudioteca)
seleccionar_audioteca.pack()

mostrardiscos = Button(master=app, text="Mostrar discos", command=MostrarDiscos)
mostrardiscos.pack()

modificardisco = Button(master=app, text="Modificar Audioteca" , command=ModificarAudioteca)
modificardisco.pack()

selec = Label(text='Archivo seleccionado: \n' + filename)
selec.pack()

st = scrolledtext.ScrolledText(app)
st.pack()

app.mainloop()