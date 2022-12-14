import xml.etree.ElementTree as ET
import tkinter as tk
from tkinter import *
from tkinter import scrolledtext
from tkinter import Button
from tkinter.filedialog import askopenfilename    
from tkinter.simpledialog import askstring

filename = askopenfilename(title='Seleccione la audioteca')
tree = ET.parse(filename)
root = tree.getroot()

def CrearAudioteca():
    
    audioteca = askstring('Nombre audioteca', 'Introduzca el nombre de la nueva audioteca: ')
    discos = ET.Element("discos")
    arbol = ET.ElementTree(discos)
    arbol.write(audioteca + ".xml")
    
    crear_audioteca = tk.Toplevel()
    crear_audioteca.title('Creación de nueva Audioteca')
    crear_audioteca.geometry("800x800")
    
    disco = Label(master=crear_audioteca, text="Nombre del disco:")
    disco.pack()
    nombre_disco = tk.Entry(crear_audioteca , width=50)
    nombre_disco.pack()

    artista = Label(master=crear_audioteca, text="Nombre del artista:")
    artista.pack()
    nombre_artista = tk.Entry(crear_audioteca, width=50)
    nombre_artista.pack()

    genero = Label(master=crear_audioteca, text="Genero del disco:")
    genero.pack()
    genero_disco = tk.Entry(crear_audioteca, width=50)
    genero_disco.pack()

    año = Label(master=crear_audioteca, text="Año del publicacion: ")
    año.pack()
    año_disco= tk.Entry(crear_audioteca, width=50)
    año_disco.pack()

    nombre = Label(master=crear_audioteca, text="Nombre de cancion: ")
    nombre.pack()
    nombre_cancion = tk.Entry(crear_audioteca, width=50)
    nombre_cancion.pack()
    
    duracion = Label(master=crear_audioteca, text="Duracion de cancion: ")
    duracion.pack()
    duracion_cancion = tk.Entry(crear_audioteca, width=50)
    duracion_cancion.pack()

    canciones = scrolledtext.ScrolledText(crear_audioteca)
    canciones.config(state=DISABLED)
    canciones.pack()

    def AñadirCancion():
        canciones.config(state=NORMAL)
        canciones.insert(END, '\n\'' + nombre_cancion.get() + '\'')
        canciones.insert(END, '\''+ duracion_cancion.get() + '\'')
        canciones.config(state=DISABLED)

    def BorrarCancion():
        aux = float(canciones.index('end')) - 1
        canciones.config(state=NORMAL)
        canciones.delete(aux, END)
        canciones.config(state=DISABLED)

    def GuardarDisco():
        disco = ET.SubElement(discos, 'disco', nombre=nombre_disco.get(), artista=nombre_artista.get(), genero=genero_disco.get(), anho=año_disco.get())
        aux = int(canciones.index('end').split('.')[0]) - 2
        
        cont_linea = 0
        for line in range(1, aux + 1):
            cont_linea = cont_linea + 1 
            cont_coma = 0
            index_cuarta_coma = 0
            index_segunda_coma = 0
            str_aux = canciones.get(cont_linea + .0, END)
            
            i = 0
            while i < len(str_aux):
                if(str_aux[i].__eq__ ('\'')):
                    cont_coma = cont_coma + 1
                if(cont_coma == 2):
                    index_segunda_coma = i + 1
                if(cont_coma == 4):
                    index_cuarta_coma = i + 1
                    break
                i+=1
             
            nombre_aux = str_aux[1:index_segunda_coma - 1]
            duracion_aux = str_aux[index_segunda_coma + 1 :index_cuarta_coma - 1]
            print(nombre_aux)
            print(duracion_aux)
            cancion = ET.SubElement(disco, 'cancion', nombre=nombre_aux, duracion=duracion_aux)

        arbol.write(audioteca + ".xml")

    añadir_canción = Button(master=crear_audioteca, text="Añadir cancion", command=AñadirCancion)
    añadir_canción.pack()
    borrar_cancion = Button(master=crear_audioteca, text="Borrar ultima cancion", command=BorrarCancion)
    borrar_cancion.pack()
    guardar_disco = Button(master=crear_audioteca, text="Guardar disco", command=GuardarDisco)
    guardar_disco.pack()

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
    st.config(state=NORMAL)
    st.replace('1.0', END, string)
    st.config(state=DISABLED)

def ModificarAudioteca():
    # Preguntamos por el titulo del disco
    titulo_disco = askstring('Modificar Disco', 'Introduzca el titulo del disco: ')

    # Buscamos en el archivo seleccionado el disco por el nombre
    for child in root:
        if child.attrib['nombre'] == titulo_disco:
            modificar_disco = tk.Toplevel()
            modificar_disco.title('Modificar Disco')
            modificar_disco.geometry("800x800")
    
            disco = Label(master=modificar_disco, text="Nombre del disco:")
            disco.pack()
            nombre_disco = tk.Entry(modificar_disco , width=50)
            nombre_disco.insert(0, child.attrib['nombre'])
            nombre_disco.pack()

            artista = Label(master=modificar_disco, text="Nombre del artista:")
            artista.pack()
            nombre_artista = tk.Entry(modificar_disco, width=50)
            nombre_artista.insert(0, child.attrib['artista'])
            nombre_artista.pack()

            genero = Label(master=modificar_disco, text="Genero del disco:")
            genero.pack()
            genero_disco = tk.Entry(modificar_disco, width=50)
            genero_disco.insert(0, child.attrib['genero'])
            genero_disco.pack()

            año = Label(master=modificar_disco, text="Año del publicacion: ")
            año.pack()
            año_disco= tk.Entry(modificar_disco, width=50)
            año_disco.insert(0, child.attrib['año'])
            año_disco.pack()

            nombre = Label(master=modificar_disco, text="Nombre de cancion: ")
            nombre.pack()
            nombre_cancion = tk.Entry(modificar_disco, width=50)
            nombre_cancion.pack()

            duracion = Label(master=modificar_disco, text="Duracion de cancion: ")
            duracion.pack()
            duracion_cancion = tk.Entry(modificar_disco, width=50)
            duracion_cancion.pack()

            canciones = scrolledtext.ScrolledText(modificar_disco)
            canciones.pack()
            
            for child2 in child:
                canciones.insert(END, '\n\'' + child2.attrib['nombre'] + '\'')
                canciones.insert(END, '\''+ child2.attrib['duracion'] + '\'')
            
            def AñadirCancion():
                canciones.config(state=NORMAL)
                canciones.insert(END, '\n\'' + nombre_cancion.get() + '\'')
                canciones.insert(END, '\''+ duracion_cancion.get() + '\'')
                canciones.config(state=DISABLED)

            def BorrarCancion():
                aux = float(canciones.index('end')) - 1
                canciones.config(state=NORMAL)
                canciones.delete(aux, END)
                canciones.config(state=DISABLED)

            def GuardarDisco():
                print()

            añadir_canción = Button(master=modificar_disco, text="Añadir cancion", command=AñadirCancion)
            añadir_canción.pack()
            borrar_cancion = Button(master=modificar_disco, text="Borrar ultima cancion", command=BorrarCancion)
            borrar_cancion.pack()
            guardar_disco = Button(master=modificar_disco, text="Guardar disco", command=GuardarDisco)
            guardar_disco.pack()
            break

app = Tk()
app.title('Gestion de audiotecas')
app.geometry("800x800")
Tk().withdraw()

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