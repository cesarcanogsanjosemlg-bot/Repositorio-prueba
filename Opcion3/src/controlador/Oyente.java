package controlador;

import java.awt.event.*;
import vista.Vista;

public class Oyente implements ActionListener {

  botonCrear.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        botonCrear.setEnabled(false);
        botonVisible.setEnabled(true);
      }
    }
  );
}
