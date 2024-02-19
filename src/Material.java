import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;
import java.io.*;
import java.util.ArrayList;

public class Material extends JFrame implements Serializable {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField tMaterial;
    private JTextField tUnidades;
    private JTextArea textArea;
    private static ArrayList<String> desc = new ArrayList<>();
    private static ArrayList<Integer> ctdad = new ArrayList<>();
    private static final String RUTA = new File("").getAbsolutePath() + "\\src\\";

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    cargarDatos();
                    Material frame = new Material();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Material() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 636, 319);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton btnAdd = new JButton("Add");
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String material = tMaterial.getText();
                String unidades = tUnidades.getText();
                if (desc.contains(material)) {
                    int index = desc.indexOf(material);
                    ctdad.set(index, ctdad.get(index) + Integer.parseInt(unidades));
                } else {
                    desc.add(material);
                    ctdad.add(Integer.parseInt(unidades));
                }
                textArea.append("Material: " + material + ", Unidades: " + unidades + "\n");
                tMaterial.setText("");
                tUnidades.setText("");
            }
        });
        btnAdd.setBounds(414, 53, 89, 23);
        contentPane.add(btnAdd);

        tMaterial = new JTextField();
        tMaterial.setBounds(66, 49, 170, 30);
        contentPane.add(tMaterial);
        tMaterial.setColumns(10);

        tUnidades = new JTextField();
        tUnidades.setBounds(265, 49, 108, 30);
        contentPane.add(tUnidades);
        tUnidades.setColumns(10);

        JLabel lblNewLabel = new JLabel("Material");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel.setBounds(66, 11, 170, 27);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Unidades");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_1.setBounds(265, 14, 77, 21);
        contentPane.add(lblNewLabel_1);

        JButton btnSave = new JButton("Guardar");
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guardarDatos();
                escribirEnArchivoBinario();
                tMaterial.setText("");
                tUnidades.setText("");
                textArea.setText("");
            }
        });
        btnSave.setBounds(458, 212, 119, 44);
        contentPane.add(btnSave);

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setBounds(58, 124, 342, 132);
        contentPane.add(textArea);
    }

    static void cargarDatos() {
        try (BufferedReader reader = new BufferedReader(new FileReader(RUTA + "Materiales(Datos).txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                desc.add(parts[0]);
                ctdad.add(Integer.parseInt(parts[1]));
            }
        } catch (Exception e) {
            
        }
    }

    static void guardarDatos() {
        try (FileWriter writer = new FileWriter(RUTA + "Materiales(Datos).txt", false)) {
            for (int i = 0; i < desc.size(); i++) {
                writer.write(desc.get(i) + "," + ctdad.get(i) + "\n");
            }
        } catch (Exception e) {
           
        }
    }

    static void escribirEnArchivoBinario() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(RUTA + "materiales(frame).dat", false))) {
            outputStream.writeObject(desc);
            outputStream.writeObject(ctdad);
        } catch (Exception e) {
            System.out.println("Error al serializar los datos en el archivo binario: " + e.getMessage());
        }
    }
}

