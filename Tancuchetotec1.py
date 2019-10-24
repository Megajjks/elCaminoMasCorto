# -*- coding: utf-8 -*-
#Author: Jayro Salazar
#IA | ISC | ITESCAM | 7A

# Módulos
import numpy as np; #importamos numpy con un alias
import networkx as nx
#import matplotlib.pyplot as plt
#import pip

# Clases
# ---------------------------------------------------------------------
class Nodo:
	def __init__(self, x, y, g):
		pos[self.x,self.y]
		self.g = g
		self.h = distancia(self.pos, pos_f)

		
class Mapa:
	def __init__(self, archivo="mapa.txt"):
		self.mapa = leerMapa(archivo)
		self.fil = len(self.mapa)
		self.col = len(self.mapa[0])
				
	def __str__(self):
		salida = ""
		for f in range(self.fil):
			for c in range(self.col):
				if self.mapa[f][c] == 0:
					salida += "  "
				if self.mapa[f][c] == 1:
					salida += "# "
				if self.mapa[f][c] == 2:
					salida += "T "
				if self.mapa[f][c] == 3:
					salida += "S "
				if self.mapa[f][c] == 4:
					salida += ". "
			salida += "\n"
		return salida
		
	def camino(self, lista):
		del lista[-1]
		for i in range(len(lista)):
			self.mapa[lista[i][0]][lista[i][1]] = 4
		
		

# funciones extras
# ----------------------------------------------------------------------
def distancia(a, b):
	return abs(a[0] - b[0]) + abs(a[1] - b[1]) #Valor absoluto.

# Devuelve la posición de "x" en una lista.
def buscarPos(x, mapa):
	for f in range(mapa.fil):
		for c in range(mapa.col):
			if mapa.mapa[f][c] == x:
				return [f, c]
	return 0

# main
# ----------------------------------------------------------------------
def main():
	
	print "hellos"
	G=nx.Graph()
	G.add_nodes_from(["Tankuche","Siho","Halacho","Becal","Tepakan","Calkini","Dzilbalche","Tec","StaCruzPueblo","Sacabchen","Concepcion","StaCruzExHacienda","Nunkini","Cuchholoch","Nicolas"])
	G.add_edge("Tankuche","Siho",weight=7)
	G.add_edge("Siho","Halacho",weight=7)
	G.add_edge("Halacho","Becal",weight=6)
	G.add_edge("Becal","Tepakan",weight=4)
	G.add_edge("Tepakan","Calkini",weight=2)
	G.add_edge("Calkini","Tec",weight=3)
	G.add_edge("Calkini","Dzilbalche",weight=3)
	G.add_edge("Tec","Dzilbalche",weight=3)
	G.add_edge("Dzilbalche","StaCruzPueblo",weight=5)
	G.add_edge("StaCruzPueblo","Sacabchen",weight=4)
	G.add_edge("Sacabchen","Concepcion",weight=7)
	G.add_edge("Concepcion","StaCruzExHacienda",weight=10)
	G.add_edge("StaCruzExHacienda","Nicolas",weight=4)
	G.add_edge("StaCruzExHacienda","Nunkini",weight=10)
	G.add_edge("Nunkini","Cuchholoch",weight=5)
	G.add_edge("Nunkini","Calkini",weight=9)
	G.add_edge("Cuchholoch","Halacho",weight=3)
	G.add_edge("Nicolas","Tankuche",weight=8)
	
	#nx.draw(G)
	#plt.show()
	print "Nodos: ", G.number_of_nodes(), G.nodes()
	print "Enlaces: ", G.number_of_edges(),G.edges()

	

if __name__ == '__main__':
	main()