import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Base64;

class EquipoVR {
    private String id;

    public EquipoVR(String id) {
        this.id = id;
        System.out.println("Nuevo equipo VR disponible: " + id);
    }

    public void asignarUsuario(String usuario) {
        System.out.println("Equipo VR " + id + " asignado a " + usuario);
    }
}

class PoolEquiposVR {
    private Queue<EquipoVR> pool;
    private int limite;

    public PoolEquiposVR(int limite) {
        this.limite = limite;
        this.pool = new LinkedList<>();
        for (int i = 0; i < limite; i++) {
            pool.add(new EquipoVR("VR-" + (i + 1)));
        }
    }

    public EquipoVR obtenerEquipo() {
        if (!pool.isEmpty()) {
            return pool.poll();
        } else {
            System.out.println("No hay equipos VR disponibles, espere...");
            return null;
        }
    }

    public void liberarEquipo(EquipoVR equipo) {
        pool.offer(equipo);
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
        Encabezado.mostrar(); // Muestra el encabezado con el nombre codificado

        PoolEquiposVR pool = new PoolEquiposVR(3);

        EquipoVR e1 = pool.obtenerEquipo();
        EquipoVR e2 = pool.obtenerEquipo();

        e1.asignarUsuario("Carlos");
        pool.liberarEquipo(e1);

        EquipoVR e3 = pool.obtenerEquipo();
        e3.asignarUsuario("Ana");
    }
}