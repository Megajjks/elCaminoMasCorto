/*
-->Practica de Heristica<--
->Jayro Salazar | 5871 | ITESCAM<-
*/

import java.util.*;

public class Heuristica 
{
    char[]  nodos;
    int[][] grafo;
    String  rutaMasCorta;
    int     longitudMasCorta = Integer.MAX_VALUE;
    List<Nodo>  listos=null;
 
    Heuristica(String serieNodos) 
    {
        nodos = serieNodos.toCharArray();
        grafo = new int[nodos.length][nodos.length];
    }
 
    public void agregarPueblo(char origen, char destino, int distancia) {
        int n1 = posicionNodo(origen);
        int n2 = posicionNodo(destino);
        grafo[n1][n2]=distancia;
        grafo[n2][n1]=distancia;
    }
 
    private int posicionNodo(char nodo) {
        for(int i=0; i<nodos.length; i++) {
            if(nodos[i]==nodo) return i;
        }
        return -1;
    }

    public String encontrarRutaA(char inicio, char fin)
    {

        encontrarRutaA(inicio);
        Nodo tmp = new Nodo(fin);
      
        tmp = listos.get(listos.indexOf(tmp));
        int distancia = tmp.distancia;  
        Stack<Nodo> pila = new Stack<Nodo>();
        while(tmp != null) {
            pila.add(tmp);
            tmp = tmp.anterior;
        }
        String ruta = "";
        while(!pila.isEmpty()) ruta+=(pila.pop().id + " -> ");
        return "Distancia Minima: "+ distancia +" --  Nodos que se recorrieron: "+ ruta;
    }
 
    public void encontrarRutaA(char inicio) {
        Queue<Nodo>   cola = new PriorityQueue<Nodo>();
        Nodo            ni = new Nodo(inicio);
        listos = new LinkedList<Nodo>();
        cola.add(ni);
        while(!cola.isEmpty()) 
        {
            Nodo tmp = cola.poll();
            listos.add(tmp);
            int p = posicionNodo(tmp.id);   
            for(int j=0; j<grafo[p].length; j++)
            {
                if(grafo[p][j]==0) continue;
                if(estaTerminado(j)) continue;
                Nodo nod = new Nodo(nodos[j],tmp.distancia+grafo[p][j],tmp);
                if(!cola.contains(nod)) {
                    cola.add(nod);
                    continue;
                }
                for(Nodo x: cola) {
                    if(x.id==nod.id && x.distancia > nod.distancia) {
                        cola.remove(x);
                        cola.add(nod);
                        break;
                    }
                }
            }
        }
    }

    public boolean estaTerminado(int j)
    {
        Nodo tmp = new Nodo(nodos[j]);
        return listos.contains(tmp);
    }

    public void encontrarRutaMinimaFuerzaBruta(char inicio, char fin)
    {
        int p1 = posicionNodo(inicio);
        int p2 = posicionNodo(fin);
        Stack<Integer> resultado = new Stack<Integer>();
        resultado.push(p1);
        recorrerRutas(p1, p2, resultado);
    }

    private void recorrerRutas(int nodoI, int nodoF, Stack<Integer> resultado) 
    {
        if(nodoI==nodoF) {
            int respuesta = evaluar(resultado);
            if(respuesta < longitudMasCorta) {
                longitudMasCorta = respuesta;
                rutaMasCorta     = "";
                for(int x: resultado) 
			rutaMasCorta+=(nodos[x]+"  ");
            }
            return;
        }

        List<Integer> lista = new Vector<Integer>();
        for(int i=0; i<grafo.length;i++) {
            if(grafo[nodoI][i]!=0 && !resultado.contains(i))lista.add(i);
        }

        for(int nodo: lista) {
            resultado.push(nodo);
            recorrerRutas(nodo, nodoF, resultado);
            resultado.pop();
        }
    }

    public int evaluar(Stack<Integer> resultado) {
        int  resp = 0;
        int[] r = new int[resultado.size()];
        int i = 0;
        for(int x: resultado) r[i++]=x;
        for(i=1; i<r.length; i++) resp+=grafo[r[i]][r[i-1]];
        return resp;
    }
 
    public static void main(String[] args) {
        Heuristica g = new Heuristica("abcdefghijklmno");
        System.out.println("1 = a = Tankuche");
	System.out.println("2 = b = Siho");
	System.out.println("3 = c = Halacho");
	System.out.println("4 = d = Becal");
	System.out.println("5 = e = Tepakan");
	System.out.println("6 = f = Calkini");
	
	System.out.println("7 = g = Dzilbalche");
	System.out.println("8 = h = Tec");
	System.out.println("9 = i = StaCruzPueblo");
	System.out.println("10 = j = Sacabchen");
	System.out.println("11= k = Concepcion");
	System.out.println("12 = l = StaCruzExHacienda");
	    
	System.out.println("13 = m = Nunkini");
	System.out.println("14= n = Cuchholoch");
	System.out.println("15 = o = Nicolas");
           
        g.agregarPueblo('a','b', 7);
        g.agregarPueblo('b','c', 7);   
        g.agregarPueblo('c','d', 6);
        g.agregarPueblo('d','e', 4);
        g.agregarPueblo('e','f', 2);
        g.agregarPueblo('f','g', 3);
        g.agregarPueblo('f','h', 3);
        g.agregarPueblo('h','g', 3);
        g.agregarPueblo('g','i', 5);
        g.agregarPueblo('i','j', 4);
        g.agregarPueblo('j','k', 7);
        g.agregarPueblo('k','l', 10);
        g.agregarPueblo('l','o', 4);
        g.agregarPueblo('l','m', 10);
        g.agregarPueblo('m','n', 5);
        g.agregarPueblo('m','f', 9);
        g.agregarPueblo('n','c', 3);
        g.agregarPueblo('o','a', 8);
        

        char inicio = 'a';
        char fin    = 'h';
        String respuesta = g.encontrarRutaA(inicio, fin);
        System.out.println(respuesta);
    }
}


//Nodo
class Nodo implements Comparable<Nodo> {
    char id;
    int  distancia   = Integer.MAX_VALUE;
    Nodo anterior = null;
    Nodo(char x, int d, Nodo p) 
	{
	    id=x; distancia=d; 
	    anterior=p; 
	}
    Nodo(char x) 
	{ 
		this(x, 0, null); 
	}
    public int compareTo(Nodo tmp) 
	{ 
		return this.distancia-tmp.distancia; 
	}
    public boolean equals(Object o) 
	{
	    
        Nodo tmp = (Nodo) o;
        if(tmp.id==this.id) return true;
        return false;
    }
}
