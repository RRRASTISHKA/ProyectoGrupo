import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class LocalizacionFuncionable implements Serializable {
    private static ArrayList<LocalizacionFuncionable> localizaciones = new ArrayList<>();
    private String direccion;
    private String cp;
    private String localidad;
    private String provincia;
    static transient Scanner sc = new Scanner(System.in);
    final static String RUTA = new File("").getAbsolutePath() + "\\src\\";

    public static void main(String[] args) {
        cargarDatos();
        System.out.println("Dame dirección");
        String direccion = sc.nextLine();
        System.out.println("Dame código postal");
        String cp = sc.nextLine();
        System.out.println("Dame localidad");
        String localidad = sc.nextLine();
        System.out.println("Dame provincia");
        String provincia = sc.nextLine();

        LocalizacionFuncionable loc = new LocalizacionFuncionable(direccion, cp, localidad, provincia);
        localizaciones.add(loc);

        escribirEnArchivo();
        guardarDatos();
    }

    public LocalizacionFuncionable(String direccion, String cp, String localidad, String provincia) {
        this.direccion = direccion;
        this.cp = cp;
        this.localidad = localidad;
        this.provincia = provincia;
    }

    static void escribirEnArchivo() {
        try {
            FileWriter writer = new FileWriter(RUTA + "localizaciones.txt");

            for (LocalizacionFuncionable loc : localizaciones) {
                writer.write("Dirección: " + loc.direccion + "  CP: " + loc.cp + "  Localidad: " + loc.localidad + "  Provincia: " + loc.provincia + "\n");
            }
            writer.close();
            System.out.println("Datos de localización escritos en el archivo correctamente.");

            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(RUTA + "localizaciones.dat"));
            outputStream.writeObject(localizaciones);
            outputStream.close();
            System.out.println("Objeto Localizaciones serializado correctamente.");

        } catch (Exception o) {
            System.out.println("Error al escribir en el archivo: " + o.getMessage());
        }
    }

    static void guardarDatos() {
        try (FileWriter writer = new FileWriter(RUTA + "Localizaciones(Datos).txt")) {
            for (LocalizacionFuncionable loc : localizaciones) {
                writer.write(loc.direccion + "," + loc.cp + "," + loc.localidad + "," + loc.provincia + "\n");
            }
        } catch (Exception e) {
            System.out.println("Error al guardar los datos: " + e.getMessage());
        }
    }

    static void cargarDatos() {
        try (BufferedReader reader = new BufferedReader(new FileReader(RUTA + "Localizaciones(Datos).txt"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                LocalizacionFuncionable loc = new LocalizacionFuncionable(partes[0], partes[1], partes[2], partes[3]);
                localizaciones.add(loc);
            }
        } catch (Exception e) {
            System.out.println("Error al cargar los datos: " + e.getMessage());
        }
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
}