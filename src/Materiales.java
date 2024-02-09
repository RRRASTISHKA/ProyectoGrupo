import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Materiales implements Serializable {
    private static ArrayList<String> desc = new ArrayList<>();
    private static ArrayList<Integer> ctdad = new ArrayList<>();
    private static ArrayList<Date> fecha = new ArrayList<>(); 
    static transient Scanner sc = new Scanner(System.in); 
    final static String RUTA = new File("").getAbsolutePath() + "\\src\\";

    public static void main(String[] args) {
        cargarDatos();
        System.out.println("Dame nombre de material");
        String mat = sc.nextLine();
        if (desc.contains(mat)) {
            System.out.println("El material ya existe en el almacén. ¿Cuántas unidades deseas agregar?");
            int unidades = sc.nextInt();
            int index = desc.indexOf(mat);
            ctdad.set(index, ctdad.get(index) + unidades);
        } else {
            desc.add(mat);
            System.out.println("Cuantas unidades quieres meter?");
            int unidades = sc.nextInt();
            ctdad.add(unidades);
            fecha.add(new Date());         }

        escribirEnArchivo();
        guardarDatos();
    }

    static void escribirEnArchivo() {
        try {
            FileWriter writer = new FileWriter(RUTA + "materiales.txt");

            for (int i = 0; i < desc.size(); i++) {
                writer.write("Unidades: " + ctdad.get(i) + "  Material: " + desc.get(i) + "  Fecha: " + fecha.get(i) + "\n");
            }
            writer.close();
            System.out.println("Datos escritos en el archivo correctamente.");
            
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(RUTA + "materiales.dat"));
            outputStream.writeObject(new Materiales());
            outputStream.close();
            System.out.println("Objeto Materiales serializado correctamente.");

        } catch (Exception o) {
            System.out.println("Error al escribir en el archivo: " + o.getMessage());
        }
    }
    
    static void guardarDatos() {
        try (FileWriter writer = new FileWriter(RUTA + "Materiales(Datos).txt")) {
            for (int i = 0; i < desc.size(); i++) {
                writer.write(desc.get(i) + "," + desc.get(i) + "," + fecha.get(i) + "\n");
            }
        } catch (Exception e) {
            System.out.println("Error al guardar los datos: " + e.getMessage());
        }
    }
    
    static void cargarDatos() {
        try (BufferedReader reader = new BufferedReader(new FileReader(RUTA + "Materiales(Datos).txt"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
               desc.add(partes[0]);
                ctdad.add(Integer.parseInt(partes[1]));
                fecha.add(new Date(Long.parseLong(partes[2]))); 
            }
        } catch (Exception e) {
            System.out.println("Error al cargar los datos: " + e.getMessage());
        }
    }

    public static ArrayList<String> getMaterial() {
        return new ArrayList<>(desc);
    }

    public static void setMaterial(ArrayList<String> newMaterial) {
         desc = new ArrayList<>(newMaterial);
    }

    public static ArrayList<Integer> getUnidad() {
        return ctdad;
    }

    public static void setUnidad(ArrayList<Integer> unidad) {
        Materiales.ctdad = unidad;
    }

    public static ArrayList<Date> getFecha() {
        return fecha;
    }

    public static void setFecha(ArrayList<Date> newFecha) {
         fecha = new ArrayList<>(newFecha);
    }
}