package controlador;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

public class Main extends JFrame {

    // Componentes Datos Personales
    private JTextField txtNombre, txtApellido1, txtApellido2, txtNumDoc, txtEmail, txtTelCasa, txtMovilPersonal, txtMovilTrabajo;
    private JComboBox<String> cboSexo, cboTipoDoc;
    
    // Componentes Dirección
    private JComboBox<String> cboTipoVia;
    private JTextField txtNombreVia, txtNumero, txtEscalera, txtPiso, txtPuerta, txtCodigoPostal, txtLocalidad, txtCodMunicipio;
    
    // REQUISITO: Estos ahora son JLabels, no JComboBox
    private JLabel lblProvinciaValor, lblComunidadValor, lblPaisValor;

    // Modelo de datos para Códigos Postales
    private Map<String, DatosDireccion> baseDeDatosCP;

    public Main() {
        setTitle("Ventana de Altas");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Inicializar "Base de datos" simulada
        inicializarDatosCP();

        // Panel Principal con Scroll
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        add(scrollPane, BorderLayout.CENTER);

        // --- SECCIÓN 1: DATOS PERSONALES ---
        JPanel panelPersonales = new JPanel(new GridBagLayout());
        panelPersonales.setBorder(new TitledBorder("Datos personales"));
        mainPanel.add(panelPersonales);
        
        agregarComponentesPersonales(panelPersonales);

        // --- SECCIÓN 2: DIRECCIÓN ---
        JPanel panelDireccion = new JPanel(new GridBagLayout());
        panelDireccion.setBorder(new TitledBorder("Dirección"));
        mainPanel.add(panelDireccion);

        agregarComponentesDireccion(panelDireccion);

        // --- BOTÓN DE VALIDACIÓN ---
        JButton btnGuardar = new JButton("Validar y Guardar");
        btnGuardar.setFont(new Font("Arial", Font.BOLD, 14));
        btnGuardar.setBackground(new Color(220, 230, 240));
        
        JPanel panelBoton = new JPanel();
        panelBoton.add(btnGuardar);
        add(panelBoton, BorderLayout.SOUTH);

        // --- LISTENERS (LÓGICA) ---
        
        // Listener para automatización por Código Postal 
        txtCodigoPostal.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                buscarCodigoPostal();
            }
        });

        // Listener del Botón Guardar para Validaciones Globales
        btnGuardar.addActionListener(e -> validarFormulario());
    }

    private void inicializarDatosCP() {
        baseDeDatosCP = new HashMap<>();

        // --- ANDALUCÍA ---
        baseDeDatosCP.put("29001", new DatosDireccion("Málaga - Centro", "Málaga", "Andalucía", "España"));
        baseDeDatosCP.put("29601", new DatosDireccion("Marbella", "Málaga", "Andalucía", "España"));
        baseDeDatosCP.put("41001", new DatosDireccion("Sevilla - Museo", "Sevilla", "Andalucía", "España"));
        baseDeDatosCP.put("11001", new DatosDireccion("Cádiz - Centro", "Cádiz", "Andalucía", "España"));
        baseDeDatosCP.put("18001", new DatosDireccion("Granada - Centro", "Granada", "Andalucía", "España"));

        // --- MADRID ---
        baseDeDatosCP.put("28001", new DatosDireccion("Madrid - Salamanca", "Madrid", "Madrid", "España"));
        baseDeDatosCP.put("28013", new DatosDireccion("Madrid - Gran Vía", "Madrid", "Madrid", "España"));
        baseDeDatosCP.put("28901", new DatosDireccion("Getafe", "Madrid", "Madrid", "España"));

        // --- CATALUÑA ---
        baseDeDatosCP.put("08001", new DatosDireccion("Barcelona - El Raval", "Barcelona", "Cataluña", "España"));
        baseDeDatosCP.put("08007", new DatosDireccion("Barcelona - Eixample", "Barcelona", "Cataluña", "España"));
        baseDeDatosCP.put("43001", new DatosDireccion("Tarragona - Centro", "Tarragona", "Cataluña", "España"));

        // --- COMUNIDAD VALENCIANA ---
        baseDeDatosCP.put("46001", new DatosDireccion("Valencia - Ciutat Vella", "Valencia", "C. Valenciana", "España"));
        baseDeDatosCP.put("03001", new DatosDireccion("Alicante - Centro", "Alicante", "C. Valenciana", "España"));
        baseDeDatosCP.put("12001", new DatosDireccion("Castellón de la Plana", "Castellón", "C. Valenciana", "España"));

        // --- PAÍS VASCO ---
        baseDeDatosCP.put("48001", new DatosDireccion("Bilbao - Casco Viejo", "Vizcaya", "País Vasco", "España"));
        baseDeDatosCP.put("20001", new DatosDireccion("San Sebastián", "Guipúzcoa", "País Vasco", "España"));
        baseDeDatosCP.put("01001", new DatosDireccion("Vitoria-Gasteiz", "Álava", "País Vasco", "España"));

        // --- GALICIA ---
        baseDeDatosCP.put("15001", new DatosDireccion("A Coruña - Ciudad Vieja", "A Coruña", "Galicia", "España"));
        baseDeDatosCP.put("36201", new DatosDireccion("Vigo", "Pontevedra", "Galicia", "España"));
        baseDeDatosCP.put("15701", new DatosDireccion("Santiago de Compostela", "A Coruña", "Galicia", "España"));

        // --- OTRAS REGIONES ---
        baseDeDatosCP.put("50001", new DatosDireccion("Zaragoza - Casco Antiguo", "Zaragoza", "Aragón", "España")); // Aragón
        baseDeDatosCP.put("33001", new DatosDireccion("Oviedo", "Asturias", "Asturias", "España")); // Asturias
        baseDeDatosCP.put("39001", new DatosDireccion("Santander", "Cantabria", "Cantabria", "España")); // Cantabria
        baseDeDatosCP.put("45001", new DatosDireccion("Toledo", "Toledo", "Castilla-La Mancha", "España")); // Castilla-La Mancha
        baseDeDatosCP.put("47001", new DatosDireccion("Valladolid", "Valladolid", "Castilla y León", "España")); // Castilla y León
        baseDeDatosCP.put("30001", new DatosDireccion("Murcia", "Murcia", "Murcia", "España")); // Murcia
        baseDeDatosCP.put("07001", new DatosDireccion("Palma de Mallorca", "Islas Baleares", "Baleares", "España")); // Baleares
        baseDeDatosCP.put("35001", new DatosDireccion("Las Palmas de G.C.", "Las Palmas", "Canarias", "España")); // Canarias
        baseDeDatosCP.put("38001", new DatosDireccion("Santa Cruz de Tenerife", "S.C. Tenerife", "Canarias", "España")); // Canarias
    }

    private void buscarCodigoPostal() {
        String cp = txtCodigoPostal.getText().trim();
        if (baseDeDatosCP.containsKey(cp)) {
            DatosDireccion datos = baseDeDatosCP.get(cp);
            
            // REQUISITO[cite: 12]: Automáticamente sale localidad, provincia, comunidad y país
            txtLocalidad.setText(datos.localidad);
            lblProvinciaValor.setText(datos.provincia);
            lblComunidadValor.setText(datos.comunidad);
            lblPaisValor.setText(datos.pais);
            
            // Simular código municipio
            txtCodMunicipio.setText(cp.substring(0, 3)); 
        } else {
            // Limpiar si no existe (opcional) o avisar
            // txtLocalidad.setText("");
            // lblProvinciaValor.setText("-");
        }
    }

    private void validarFormulario() {
        StringBuilder errores = new StringBuilder();

        // 1. REQUISITO[cite: 2]: Campos obligatorios no vacíos
        if (txtNombre.getText().trim().isEmpty() || 
            txtApellido1.getText().trim().isEmpty() || 
            txtNumDoc.getText().trim().isEmpty()) {
            errores.append("- Nombre, Primer Apellido y N° Documento son obligatorios.\n");
        }

        // 1.1 REQUISITO[cite: 2]: Al menos un teléfono
        if (txtTelCasa.getText().trim().isEmpty() && 
            txtMovilPersonal.getText().trim().isEmpty() && 
            txtMovilTrabajo.getText().trim().isEmpty()) {
            errores.append("- Debe introducir al menos un teléfono de contacto.\n");
        }

        // 2. Validación de Documento
        String tipoDoc = (String) cboTipoDoc.getSelectedItem();
        String numDoc = txtNumDoc.getText().trim();

        if ("DNI".equals(tipoDoc)) {
            // REQUISITO[cite: 4]: DNI Solo números
            if (!numDoc.matches("\\d+")) {
                errores.append("- El DNI debe contener SOLO números.\n");
            }
        } else if ("NIE".equals(tipoDoc)) {
            // REQUISITO[cite: 5]: NIE empieza letra, termina letra, total 8 caracteres
            // Regex: Empieza letra ([A-Za-z]), sigue cualquier cosa (.*), termina letra ([A-Za-z])
            if (numDoc.length() != 8 || !numDoc.matches("^[A-Za-z].*[A-Za-z]$")) {
                errores.append("- El NIE debe tener 8 caracteres, empezar y terminar por letra.\n");
            }
        }

        // 3. REQUISITO[cite: 6]: Correo con arroba
        if (!txtEmail.getText().trim().isEmpty() && !txtEmail.getText().contains("@")) {
            errores.append("- El correo electrónico debe contener '@'.\n");
        }

        // 4. REQUISITO[cite: 7]: Teléfonos solo números
        if (!esNumero(txtTelCasa.getText()) || !esNumero(txtMovilPersonal.getText()) || !esNumero(txtMovilTrabajo.getText())) {
            errores.append("- Los teléfonos deben contener solo números.\n");
        }

        // 5. REQUISITO[cite: 9]: Número de calle solo números
        if (!esNumero(txtNumero.getText())) {
            errores.append("- El número de calle debe ser numérico.\n");
        }

        // Resultado final
        if (errores.length() > 0) {
            JOptionPane.showMessageDialog(this, errores.toString(), "Errores de Validación", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "¡Formulario validado correctamente!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // Método auxiliar para validar si un texto es numérico (permitiendo vacíos como válidos para campos opcionales)
    private boolean esNumero(String texto) {
        if (texto == null || texto.trim().isEmpty()) return true;
        return texto.matches("\\d+");
    }

    private void agregarComponentesPersonales(JPanel p) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        // Fila 1: Nombre, Apellido1, Apellido2, Sexo
        gbc.gridy = 0;
        
        addLabelAndField(p, "Nombre:", txtNombre = new JTextField(), gbc, 0);
        addLabelAndField(p, "Primer Apellido:", txtApellido1 = new JTextField(), gbc, 2);
        addLabelAndField(p, "Segundo Apellido:", txtApellido2 = new JTextField(), gbc, 4);
        
        // REQUISITO[cite: 3]: Sexo Hombre/Mujer
        p.add(new JLabel("Sexo"), getGbc(6, 0));
        cboSexo = new JComboBox<>(new String[]{"HOMBRE", "MUJER"});
        p.add(cboSexo, getGbc(7, 0));

        // Fila 2: Tipo Doc, Num Doc, Fecha Nac
        gbc.gridy = 1;
        p.add(new JLabel("Tipo Documento"), getGbc(0, 1));
        cboTipoDoc = new JComboBox<>(new String[]{"DNI", "NIE"}); // 
        p.add(cboTipoDoc, getGbc(1, 1));
        
        addLabelAndField(p, "Número documento:", txtNumDoc = new JTextField(), gbc, 2);
        addLabelAndField(p, "Fecha nacimiento:", new JTextField("dd/mm/aaaa"), gbc, 4); // Simplificado sin JCalendar

        // Fila 3: Email, Tel Casa, Movil Personal
        gbc.gridy = 2;
        addLabelAndField(p, "Correo electrónico:", txtEmail = new JTextField(), gbc, 0); // [cite: 6]
        gbc.gridwidth = 3; // El correo ocupa más espacio visualmente
        p.add(txtEmail, getGbc(1, 2, 3)); 
        gbc.gridwidth = 1;
        
        addLabelAndField(p, "Teléfono casa:", txtTelCasa = new JTextField(), gbc, 4); // [cite: 7]
        addLabelAndField(p, "Móvil personal:", txtMovilPersonal = new JTextField(), gbc, 6);

        // Fila 4: Movil Trabajo
        gbc.gridy = 3;
        addLabelAndField(p, "Móvil trabajo:", txtMovilTrabajo = new JTextField(), gbc, 0);
    }

    private void agregarComponentesDireccion(JPanel p) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Fila 1: Tipo Via, Nombre Via, Numero, Esc, Piso, Puerta
        gbc.gridy = 0;
        p.add(new JLabel("Tipo vía"), getGbc(0, 0));
        // REQUISITO[cite: 8]: Varias opciones
        cboTipoVia = new JComboBox<>(new String[]{"Calle", "Avenida", "Plaza", "Paseo"});
        p.add(cboTipoVia, getGbc(1, 0));

        p.add(new JLabel("Nombre Vía"), getGbc(2, 0));
        txtNombreVia = new JTextField();
        gbc = getGbc(3, 0);
        gbc.weightx = 2.0; // Dar más espacio al nombre de la calle
        p.add(txtNombreVia, gbc);

        // Numeros pequeños
        gbc = getGbc(4, 0); gbc.weightx = 0.5;
        p.add(new JLabel("Núm"), gbc);
        txtNumero = new JTextField(); // [cite: 9]
        p.add(txtNumero, getGbc(5, 0));
        
        p.add(new JLabel("Piso"), getGbc(6, 0));
        txtPiso = new JTextField();
        p.add(txtPiso, getGbc(7, 0));
        
        p.add(new JLabel("Puerta"), getGbc(8, 0));
        txtPuerta = new JTextField();
        p.add(txtPuerta, getGbc(9, 0));

        // Fila 2: CP, Localidad, Cod Mun, Provincia, Comunidad
        gbc.gridy = 1;
        
        p.add(new JLabel("Código Postal"), getGbc(0, 1));
        txtCodigoPostal = new JTextField();
        txtCodigoPostal.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 2)); // Resaltar como en la imagen
        p.add(txtCodigoPostal, getGbc(1, 1));

        p.add(new JLabel("Localidad"), getGbc(2, 1));
        txtLocalidad = new JTextField();
        txtLocalidad.setEditable(false); // Automático
        p.add(txtLocalidad, getGbc(3, 1));

        p.add(new JLabel("Cód. Mun."), getGbc(4, 1));
        txtCodMunicipio = new JTextField();
        txtCodMunicipio.setEditable(false);
        p.add(txtCodMunicipio, getGbc(5, 1));

        // REQUISITO: JLabels para Provincia y Comunidad
        p.add(new JLabel("Provincia"), getGbc(6, 1));
        lblProvinciaValor = new JLabel("-");
        lblProvinciaValor.setFont(new Font("SansSerif", Font.BOLD, 12));
        lblProvinciaValor.setForeground(Color.BLUE);
        p.add(lblProvinciaValor, getGbc(7, 1));

        p.add(new JLabel("Comunidad"), getGbc(8, 1));
        lblComunidadValor = new JLabel("-");
        lblComunidadValor.setFont(new Font("SansSerif", Font.BOLD, 12));
        lblComunidadValor.setForeground(Color.BLUE);
        p.add(lblComunidadValor, getGbc(9, 1));

        // Fila 3: País
        gbc.gridy = 2;
        p.add(new JLabel("País"), getGbc(0, 2));
        // REQUISITO: JLabel para País
        lblPaisValor = new JLabel("España"); 
        lblPaisValor.setFont(new Font("SansSerif", Font.BOLD, 12));
        p.add(lblPaisValor, getGbc(1, 2));
    }

    // Utilidades para GridBagLayout
    private GridBagConstraints getGbc(int x, int y) {
        return getGbc(x, y, 1);
    }
    private GridBagConstraints getGbc(int x, int y, int width) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = width;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.weightx = 1.0;
        return gbc;
    }

    private void addLabelAndField(JPanel p, String text, Component field, GridBagConstraints gbc, int x) {
        gbc.gridx = x;
        p.add(new JLabel(text), gbc);
        gbc.gridx = x + 1;
        p.add(field, gbc);
    }

    // Clase auxiliar simple para guardar datos de dirección
    class DatosDireccion {
        String localidad, provincia, comunidad, pais;

        public DatosDireccion(String loc, String prov, String com, String pais) {
            this.localidad = loc;
            this.provincia = prov;
            this.comunidad = com;
            this.pais = pais;
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {}
        
        SwingUtilities.invokeLater(() -> {
            new Main().setVisible(true);
        });
    }
}
