package vista;
import javax.swing.*;
import java.awt.*;

public class Vista extends JFrame {
    private JTextField txtNom, txtAp1, txtAp2, txtDoc, txtEmail, txtT1, txtT2, txtT3;
    private JTextField txtVia, txtNum, txtPiso, txtPta, txtCP, txtLoc, txtMun;
    private JComboBox<String> cbSex, cbDoc, cbVia;
    private JLabel lblProv, lblCom, lblPais;
    private JButton btn;

    public Vista() {
        setSize(900, 600);
        setDefaultCloseOperation(3);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel pCen = new JPanel();
        pCen.setLayout(new BoxLayout(pCen, BoxLayout.Y_AXIS));
        add(new JScrollPane(pCen));

        JPanel pPer = new JPanel(new GridBagLayout());
        pPer.setBorder(BorderFactory.createTitledBorder("Personales"));
        pCen.add(pPer);
        
        GridBagConstraints g = new GridBagConstraints();
        g.fill=1; g.insets=new Insets(2,2,2,2); g.weightx=1;

        add(pPer, "Nombre:", txtNom=new JTextField(), g, 0, 0);
        add(pPer, "Apellido 1:", txtAp1=new JTextField(), g, 2, 0);
        add(pPer, "Apellido 2:", txtAp2=new JTextField(), g, 4, 0);
        
        pPer.add(new JLabel("Sexo"), gc(6,0)); pPer.add(cbSex=new JComboBox<>(new String[]{"HOMBRE","MUJER"}), gc(7,0));
        pPer.add(new JLabel("Tipo"), gc(0,1)); pPer.add(cbDoc=new JComboBox<>(new String[]{"DNI","NIE"}), gc(1,1));
        
        add(pPer, "Num Doc:", txtDoc=new JTextField(), g, 2, 1);
        add(pPer, "Email:", txtEmail=new JTextField(), g, 0, 2);
        add(pPer, "Tel Casa:", txtT1=new JTextField(), g, 4, 2);
        add(pPer, "Móvil 1:", txtT2=new JTextField(), g, 6, 2);
        add(pPer, "Móvil 2:", txtT3=new JTextField(), g, 0, 3);

        JPanel pDir = new JPanel(new GridBagLayout());
        pDir.setBorder(BorderFactory.createTitledBorder("Dirección"));
        pCen.add(pDir);

        pDir.add(new JLabel("Vía"), gc(0,0)); pDir.add(cbVia=new JComboBox<>(new String[]{"Calle","Avda","Pza"}), gc(1,0));
        add(pDir, "Nombre:", txtVia=new JTextField(), g, 2, 0);
        add(pDir, "Num:", txtNum=new JTextField(), g, 4, 0);
        add(pDir, "Piso:", txtPiso=new JTextField(), g, 6, 0);
        add(pDir, "Pta:", txtPta=new JTextField(), g, 8, 0);

        add(pDir, "CP:", txtCP=new JTextField(), g, 0, 1);
        add(pDir, "Loc:", txtLoc=new JTextField(), g, 2, 1); txtLoc.setEditable(false);
        add(pDir, "Mun:", txtMun=new JTextField(), g, 4, 1); txtMun.setEditable(false);
        
        pDir.add(new JLabel("Provincia"), gc(6,1)); pDir.add(lblProv=new JLabel("-"), gc(7,1));
        pDir.add(new JLabel("Comunidad"), gc(8,1)); pDir.add(lblCom=new JLabel("-"), gc(9,1));
        pDir.add(new JLabel("País"), gc(0,2)); pDir.add(lblPais=new JLabel("España"), gc(1,2));

        add(btn=new JButton("Validar"), BorderLayout.SOUTH);
    }

    private void add(JPanel p, String t, Component c, GridBagConstraints g, int x, int y) {
        g.gridx=x; g.gridy=y; p.add(new JLabel(t), g);
        g.gridx=x+1; p.add(c, g);
    }
    private GridBagConstraints gc(int x, int y) {
        GridBagConstraints g = new GridBagConstraints();
        g.gridx=x; g.gridy=y; g.fill=1; g.insets=new Insets(2,2,2,2); g.weightx=1; return g;
    }

    public JButton getBtn() { return btn; }
    public JTextField getNom() { return txtNom; }
    public JTextField getAp1() { return txtAp1; }
    public JTextField getDoc() { return txtDoc; }
    public JTextField getEmail() { return txtEmail; }
    public JTextField getT1() { return txtT1; }
    public JTextField getT2() { return txtT2; }
    public JTextField getT3() { return txtT3; }
    public JComboBox<String> getCbDoc() { return cbDoc; }
    public JTextField getNum() { return txtNum; }
    public JTextField getCP() { return txtCP; }
    public void setLoc(String s) { txtLoc.setText(s); }
    public void setMun(String s) { txtMun.setText(s); }
    public void setProv(String s) { lblProv.setText(s); }
    public void setCom(String s) { lblCom.setText(s); }
    public void setPais(String s) { lblPais.setText(s); }
}