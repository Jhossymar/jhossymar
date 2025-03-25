import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Queue;
import java.util.Base64;
import java.util.LinkedList;

class Camilla {
    private String id;

    public Camilla(String id) {
        this.id = id;
        System.out.println("Nueva camilla disponible: " + id);
    }

    public void asignarPaciente(String paciente) {
        System.out.println("Camilla " + id + " asignada a paciente " + paciente);
    }
}

class PoolCamillas {
    private Queue<Camilla> pool;
    private int limite;

    public PoolCamillas(int limite) {
        this.limite = limite;
        this.pool = new LinkedList<>();
        for (int i = 0; i < limite; i++) {
            pool.add(new Camilla("Camilla-" + (i + 1)));
        }
    }

    public Camilla obtenerCamilla() {
        if (!pool.isEmpty()) {
            return pool.poll();
        } else {
            System.out.println("No hay camillas disponibles, espere...");
            return null;
        }
    }

    public void liberarCamilla(Camilla camilla) {
        pool.offer(camilla);
    }
}

// Clase para codificar nombres en Base64
class NombreCodificador {
    public static String codificarNombre(String nombreCompleto) {
        return Base64.getEncoder().encodeToString(nombreCompleto.getBytes());
    }
}

// Clase para mostrar encabezado del programa
class Encabezado {
    public static void mostrar() {
        String nombre = "Jhossymar Garces";
        String nombreCodificado = NombreCodificador.codificarNombre(nombre);
        String universidad = "Universidad Cooperativa De Colombia";
        String profesor = "Harold Bolaños";
        String asignatura = "Patrones de Diseño";
        String fecha = new SimpleDateFormat("dd/MM/yyyy").format(new Date());

        System.out.println("========================================");
        System.out.println("                ENCABEZADO              ");
        System.out.println("========================================");
        System.out.println("Fecha       : " + fecha);
        System.out.println("Nombre      : " + nombre);
        System.out.println("Codificado  : " + nombreCodificado);
        System.out.println("Universidad : " + universidad);
        System.out.println("Profesor    : " + profesor);
        System.out.println("Asignatura  : " + asignatura);
        System.out.println("========================================");
    }
}

public class Main {
    public static void main(String[] args) {
        Encabezado.mostrar(); // Muestra el encabezado con el nombre encriptado

        PoolCamillas pool = new PoolCamillas(3);

        Camilla c1 = pool.obtenerCamilla();
        Camilla c2 = pool.obtenerCamilla();

        c1.asignarPaciente("María");
        pool.liberarCamilla(c1);

        Camilla c3 = pool.obtenerCamilla();
        c3.asignarPaciente("Pedro");
    }
}