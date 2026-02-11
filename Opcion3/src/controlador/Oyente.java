package controlador;

public class Oyente {

  JButton botonCrear = new JButton("Crear");
  botonCrear.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        botonCrear.setEnabled(false);
        botonVisible.setEnabled(true);
      }
    }
  );
}
