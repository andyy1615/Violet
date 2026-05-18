import logica.GestionEstudiantes; 
import modelo.Estudiante; 
import org.junit.jupiter.api.Test; 
import static org.junit.jupiter.api.Assertions.*; 

public class GestionEstudiantesTest { 
    @Test public void testAgregarEstudiante() { 
        GestionEstudiantes gestion = new GestionEstudiantes(); 
        boolean agregado = gestion.agregarEstudiante( 
            new Estudiante( 200, "Pedro", "Software", 9.0)); 
            assertTrue(agregado); 
            
        } 
        
    @Test public void testCodigoDuplicado() { 
        GestionEstudiantes gestion = new GestionEstudiantes(); 
        boolean agregado = gestion.agregarEstudiante( 
            new Estudiante( 101, "Juan", "TI", 8.0)); 
            assertFalse(agregado); 
        } 
        
    @Test public void testBusquedaSecuencial() { 
        GestionEstudiantes gestion = new GestionEstudiantes(); 
        Estudiante estudiante = gestion.buscarPorNombreSecuencial( "Ana"); 
        assertNotNull(estudiante); 
        
    } 
    
    @Test public void testBusquedaBinaria() { 
        GestionEstudiantes gestion = new GestionEstudiantes(); 
        Estudiante estudiante = gestion.buscarPorCodigoBinario(103); 
        assertNotNull(estudiante); 
        
    } 
    
    @Test public void testContarRecursivo() { 
        GestionEstudiantes gestion = new GestionEstudiantes(); 
        assertEquals( 5, gestion.contarRecursivo(0)); 
        } 
    }