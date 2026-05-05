import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class AreaPeliculas extends JPanel{
	
	private VistaPeliculas vistaPeliculas;
	
	public AreaPeliculas(VistaPeliculas vistaPeliculas) {
		this.vistaPeliculas = vistaPeliculas;
		this.setBackground(Color.WHITE);
	}
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		for(int i=0; i<vistaPeliculas.getModelo2().size(); i++) {
			g.drawString(vistaPeliculas.getModelo2().getElementAt(i), 10, 10 + i*20);
		}
	}

}
