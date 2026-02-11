package controlador;

import vista.Vista;

public class Oyente {

  botonCrear.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        botonCrear.setEnabled(false);
        botonVisible.setEnabled(true);
      }
    }
  );
}
