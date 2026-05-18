package modelo; 
public class Estudiante { 
    private int codigo; private String nombre; 
    private String carrera; private double promedio; 
    public Estudiante(int codigo, String nombre, String carrera, double promedio) { 
            this.codigo = codigo; 
            this.nombre = nombre; 
            this.carrera = carrera; 
            this.promedio = promedio; 
            } 
            public int getCodigo() { 
                return codigo; 
                } 
                public void setCodigo(int codigo) { 
                    this.codigo = codigo; 
                    
                } 
                
                public String getNombre() { 
                    return nombre; 
                    } public void setNombre(String nombre) { 
                        this.nombre = nombre; 
                        } 
                        
                        public String getCarrera() { 
                            return carrera; 
                            } 
                            
                            public void setCarrera(String carrera) { 
                                this.carrera = carrera; 
                                } 
                                
                                public double getPromedio() { 
                                    return promedio; 
                                    } 
                                    
                                    public void setPromedio(double promedio) {
                                         this.promedio = promedio; 
                                         } 
                                         
                                         @Override public String toString() { 
                                            return "Código: " + codigo + " | Nombre: " + nombre + " | Carrera: " + carrera + " | Promedio: " + String.format("%.2f", promedio); 
                                            } 
                                            }
