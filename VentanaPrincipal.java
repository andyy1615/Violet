package vista;

import logica.GestionEstudiantes;
import modelo.Estudiante;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class VentanaPrincipal extends JFrame {

    private GestionEstudiantes gestion;

    // MODELOS
    private DefaultListModel<String> modeloLista;
    private DefaultListModel<String> modeloResultados;

    // COMPONENTES
    private JSpinner spCodigo;
    private JTextField txtNombre;
    private JComboBox<String> cbCarrera;
    private JSpinner spPromedio;

    private JList<String> listaEstudiantes;
    private JList<String> listaResultados;

    private JLabel lblTotal;
    private JLabel lblPromedio;
    private JLabel lblMayor;

    public VentanaPrincipal() {

        gestion = new GestionEstudiantes();

        configurarVentana();
        inicializarComponentes();
    }

    private void configurarVentana() {

        setTitle("Sistema de Gestión Académica");

        setSize(900, 600);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        getContentPane().setBackground(
                new Color(245,245,245));
    }

    private void inicializarComponentes() {

        JTabbedPane tabs = new JTabbedPane();

        tabs.addTab("Registro",
                crearPanelRegistro());

        tabs.addTab("Búsqueda y Ordenamiento",
                crearPanelBusqueda());

        tabs.addTab("Recursividad",
                crearPanelRecursividad());

        add(tabs);

        actualizarLista();
        actualizarRecursividad();
    }

    // PANEL REGISTRO
    private JPanel crearPanelRegistro() {

        JPanel panel = new JPanel(
                new BorderLayout(10,10));

        panel.setBorder(
                BorderFactory.createEmptyBorder(
                        10,10,10,10));

        JPanel formulario = new JPanel(
                new GridLayout(5,2,10,10));

        formulario.setBorder(
                new TitledBorder("Registro de Estudiantes"));

        spCodigo = new JSpinner(
                new SpinnerNumberModel(
                        1,1,9999,1));

        txtNombre = new JTextField();

        cbCarrera = new JComboBox<>();

        cbCarrera.addItem("Software");
        cbCarrera.addItem("TI");
        cbCarrera.addItem("Redes");

        spPromedio = new JSpinner(
                new SpinnerNumberModel(
                        7.0,0.0,10.0,0.1));

        JButton btnAgregar =
                new JButton("Agregar Estudiante");

        formulario.add(new JLabel("Código"));
        formulario.add(spCodigo);

        formulario.add(new JLabel("Nombre"));
        formulario.add(txtNombre);

        formulario.add(new JLabel("Carrera"));
        formulario.add(cbCarrera);

        formulario.add(new JLabel("Promedio"));
        formulario.add(spPromedio);

        formulario.add(new JLabel());
        formulario.add(btnAgregar);

        modeloLista = new DefaultListModel<>();

        listaEstudiantes =
                new JList<>(modeloLista);

        JScrollPane scroll =
                new JScrollPane(listaEstudiantes);

        scroll.setBorder(
                new TitledBorder("Lista de Estudiantes"));

        panel.add(formulario, BorderLayout.NORTH);
        panel.add(scroll, BorderLayout.CENTER);

        btnAgregar.addActionListener(
                e -> agregarEstudiante());

        return panel;
    }

    // PANEL BÚSQUEDA
    private JPanel crearPanelBusqueda() {

        JPanel panel = new JPanel(
                new BorderLayout(10,10));

        panel.setBorder(
                BorderFactory.createEmptyBorder(
                        10,10,10,10));

        JPanel botones = new JPanel(
                new GridLayout(2,2,10,10));

        botones.setBorder(
                new TitledBorder(
                        "Búsqueda y Ordenamiento"));

        JButton btnOrdenPromedio =
                new JButton(
                        "Ordenar por Promedio");

        JButton btnOrdenCodigo =
                new JButton(
                        "Ordenar por Código");

        JButton btnBuscarCodigo =
                new JButton(
                        "Buscar por Código");

        JButton btnBuscarNombre =
                new JButton(
                        "Buscar por Nombre");

        botones.add(btnOrdenPromedio);
        botones.add(btnOrdenCodigo);
        botones.add(btnBuscarCodigo);
        botones.add(btnBuscarNombre);

        modeloResultados =
                new DefaultListModel<>();

        listaResultados =
                new JList<>(modeloResultados);

        JScrollPane scroll =
                new JScrollPane(listaResultados);

        scroll.setBorder(
                new TitledBorder("Resultados"));

        panel.add(botones, BorderLayout.NORTH);
        panel.add(scroll, BorderLayout.CENTER);

        // EVENTOS

        btnOrdenPromedio.addActionListener(e -> {

            gestion.ordenarPorPromedioDescendente();

            mostrarResultados();
        });

        btnOrdenCodigo.addActionListener(e -> {

            gestion.ordenarPorCodigoAscendente();

            mostrarResultados();
        });

        btnBuscarCodigo.addActionListener(
                e -> buscarCodigo());

        btnBuscarNombre.addActionListener(
                e -> buscarNombre());

        return panel;
    }

    // PANEL RECURSIVIDAD
    private JPanel crearPanelRecursividad() {

        JPanel panel = new JPanel(
                new GridLayout(3,1,10,10));

        panel.setBorder(
                BorderFactory.createEmptyBorder(
                        20,20,20,20));

        lblTotal = new JLabel();

        lblPromedio = new JLabel();

        lblMayor = new JLabel();

        lblTotal.setFont(
                new Font("Arial", Font.BOLD, 18));

        lblPromedio.setFont(
                new Font("Arial", Font.BOLD, 18));

        lblMayor.setFont(
                new Font("Arial", Font.BOLD, 18));

        panel.add(lblTotal);
        panel.add(lblPromedio);
        panel.add(lblMayor);

        return panel;
    }

    // MÉTODOS

    private void agregarEstudiante() {

        int codigo =
                (int) spCodigo.getValue();

        String nombre =
                txtNombre.getText().trim();

        String carrera =
                cbCarrera.getSelectedItem().toString();

        double promedio =
                (double) spPromedio.getValue();

        // VALIDACIONES

        if (nombre.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Ingrese un nombre válido");

            return;
        }

        Estudiante estudiante =
                new Estudiante(
                        codigo,
                        nombre,
                        carrera,
                        promedio);

        boolean agregado =
                gestion.agregarEstudiante(estudiante);

        if (agregado) {

            JOptionPane.showMessageDialog(
                    this,
                    "Estudiante agregado correctamente");

            actualizarLista();

            actualizarRecursividad();

            limpiarCampos();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "El código ya existe");
        }
    }

    private void buscarCodigo() {

        String dato =
                JOptionPane.showInputDialog(
                        this,
                        "Ingrese código");

        if (dato == null) {
            return;
        }

        int codigo = Integer.parseInt(dato);

        Estudiante encontrado =
                gestion.buscarPorCodigoBinario(codigo);

        modeloResultados.clear();

        if (encontrado != null) {

            modeloResultados.addElement(
                    encontrado.toString());

        } else {

            modeloResultados.addElement(
                    "No se encontró el estudiante");
        }
    }

    private void buscarNombre() {

        String nombre =
                JOptionPane.showInputDialog(
                        this,
                        "Ingrese nombre");

        if (nombre == null) {
            return;
        }

        Estudiante encontrado =
                gestion.buscarPorNombreSecuencial(nombre);

        modeloResultados.clear();

        if (encontrado != null) {

            modeloResultados.addElement(
                    encontrado.toString());

        } else {

            modeloResultados.addElement(
                    "No se encontró el estudiante");
        }
    }

    private void actualizarLista() {

        modeloLista.clear();

        for (Estudiante e : gestion.getLista()) {

            modeloLista.addElement(e.toString());
        }
    }

    private void mostrarResultados() {

        modeloResultados.clear();

        for (Estudiante e : gestion.getLista()) {

            modeloResultados.addElement(
                    e.toString());
        }
    }

    private void actualizarRecursividad() {

        lblTotal.setText(
                "Total de estudiantes: "
                        + gestion.contarRecursivo(0));

        lblPromedio.setText(
                "Promedio general: "
                        + String.format("%.2f",
                        gestion.promedioGeneralRecursivo()));

        lblMayor.setText(
                "Mayor promedio: "
                        + String.format("%.2f",
                        gestion.mayorPromedioRecursivo(0)));
    }

    private void limpiarCampos() {

        txtNombre.setText("");

        spCodigo.setValue(1);

        spPromedio.setValue(7.0);

        cbCarrera.setSelectedIndex(0);
    }
}