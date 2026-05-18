package logica; 
import modelo.Estudiante; 
import java.util.ArrayList; 

public class GestionEstudiantes { 
    private ArrayList<Estudiante> lista; 
    public GestionEstudiantes() { 
        lista = new ArrayList<>(); 
        precargarDatos(); 
        } 
        
    private void precargarDatos() { 
        lista.add(new Estudiante(101, "Ana", "Software", 9.5)); 
        lista.add(new Estudiante(102, "Luis", "TI", 8.7)); 
        lista.add(new Estudiante(103, "Maria", "Software", 9.8)); 
        lista.add(new Estudiante(104, "Carlos", "Redes", 7.9)); 
        lista.add(new Estudiante(105, "Sofia", "TI", 8.9)); 
        } 
        
    public ArrayList<Estudiante> getLista() { 
        return lista; 
    } 
    
    // VALIDAR CÓDIGO ÚNICO 
    
    public boolean codigoExiste(int codigo) { 
        for (Estudiante e : lista) { 
            if (e.getCodigo() == codigo) { 
                return true; 
            } 
        } 
                return false; 
    } 
    
    // AGREGAR 
     
    public boolean agregarEstudiante(Estudiante estudiante) { 
        if (codigoExiste(estudiante.getCodigo())) { 
            return false; 
        } 
        
        lista.add(estudiante); 
        return true; 
        } 
        
    // BÚSQUEDA SECUENCIAL 
     
    public Estudiante buscarPorNombreSecuencial(String nombre) { 
        for (Estudiante e : lista) { 
            if (e.getNombre().equalsIgnoreCase(nombre)) { 
                return e; 
                
                } 
                } 
                
                return null; 
                
                } 
    // ORDENAR POR PROMEDIO DESCENDENTE 
    // public void ordenarPorPromedioDescendente() { for (int i = 0; i < lista.size() - 1; i++) { for (int j = 0; j < lista.size() - 1 - i; j++) { if (lista.get(j).getPromedio() < lista.get(j + 1).getPromedio()) { Estudiante aux = lista.get(j); lista.set(j, lista.get(j + 1)); lista.set(j + 1, aux); } } } } // ORDENAR POR CÓDIGO ASCENDENTE public void ordenarPorCodigoAscendente() { for (int i = 0; i < lista.size() - 1; i++) { for (int j = 0; j < lista.size() - 1 - i; j++) { if (lista.get(j).getCodigo() > lista.get(j + 1).getCodigo()) { Estudiante aux = lista.get(j); lista.set(j, lista.get(j + 1)); lista.set(j + 1, aux); } } } } // BÚSQUEDA BINARIA public Estudiante buscarPorCodigoBinario(int codigo) { ordenarPorCodigoAscendente(); int inicio = 0; int fin = lista.size() - 1; while (inicio <= fin) { int medio = (inicio + fin) / 2; if (lista.get(medio).getCodigo() == codigo) { return lista.get(medio); } if (codigo < lista.get(medio).getCodigo()) { fin = medio - 1; } else { inicio = medio + 1; } } return null; } // RECURSIVIDAD public int contarRecursivo(int indice) { if (indice == lista.size()) { return 0; } return 1 + contarRecursivo(indice + 1); } public double sumaPromediosRecursiva(int indice) { if (indice == lista.size()) { return 0; } return lista.get(indice).getPromedio() + sumaPromediosRecursiva(indice + 1); } public double promedioGeneralRecursivo() { if (lista.isEmpty()) { return 0; } return sumaPromediosRecursiva(0) / contarRecursivo(0); } // MAYOR PROMEDIO RECURSIVO public double mayorPromedioRecursivo(int indice) { if (indice == lista.size() - 1) { return lista.get(indice).getPromedio(); } double mayorResto = mayorPromedioRecursivo(indice + 1); if (lista.get(indice).getPromedio() > mayorResto) { return lista.get(indice).getPromedio(); } return mayorResto; } // MÉTODO EXTRA public boolean listaVacia() { return lista.isEmpty(); } }