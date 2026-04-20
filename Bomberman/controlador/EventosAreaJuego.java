import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Timer;

public class EventosAreaJuego {
	private AreaJuego areaJuego;
	private int []estadoTeclas;
	private Timer reloj;

	public EventosAreaJuego(AreaJuego areaJuego) {
		this.areaJuego = areaJuego;

		estadoTeclas = new int[5];
		for(int i = 0; i < estadoTeclas.length; i++) {
			estadoTeclas[i]=0;
		}

		reloj = new Timer(40, new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {

		        if (areaJuego.getJugador().getEstado() == Jugador.VIVO) {
		            areaJuego.getJugador().mover();

		            for (int i = areaJuego.getArrayEnemigos().size() - 1; i >= 0; i--) {
		                if (areaJuego.getJugador().getRect().intersects(areaJuego.getArrayEnemigos().get(i).getRect())) {
		                    areaJuego.getArrayEnemigos().remove(i);
		                }
		            }
		        }

		        // ← SOLO este bloque, el duplicado borrado
		        if (areaJuego.getBomba() != null) {
		            areaJuego.getBomba().actualizar();

		            if (areaJuego.getBomba().getEstado() == Bomba.EXPLOTANDO) {
		                Explosion exp = new Explosion(
		                    areaJuego,
		                    areaJuego.getBomba().getCeldaFila(),
		                    areaJuego.getBomba().getCeldaCol()
		                );
		                areaJuego.setExplosion(exp);
		                areaJuego.setBomba(null);
		            }
		        }

		        if (areaJuego.getExplosion() != null) {
		            areaJuego.getExplosion().actualizar();

		            if (areaJuego.getExplosion().colisionaCon(areaJuego.getJugador().getRect())) {
		                areaJuego.getJugador().perderVida();
		            }

		            for (int i = areaJuego.getArrayEnemigos().size() - 1; i >= 0; i--) {
		                if (areaJuego.getExplosion().colisionaCon(areaJuego.getArrayEnemigos().get(i).getRect())) {
		                    areaJuego.getArrayEnemigos().remove(i);
		                    areaJuego.getJugador().sumarPuntos(100);
		                }
		            }

		            if (areaJuego.getExplosion().haTerminado()) {
		                areaJuego.setExplosion(null);
		            }
		        }

		        areaJuego.repaint();
		    }
		});

		reloj.start();

		areaJuego.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
					estadoTeclas[0]= 0;
					areaJuego.getJugador().setDirH(0);
				} else if(e.getKeyCode()==KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
					estadoTeclas[1] = 0;
					areaJuego.getJugador().setDirH(0);
				} else if(e.getKeyCode()==KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
					estadoTeclas[2] = 0;
					areaJuego.getJugador().setDirV(0);
				} else if(e.getKeyCode()==KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
					estadoTeclas[3] = 0;
					areaJuego.getJugador().setDirV(0);
				}

				if(estadoTeclas[0] == 0 && estadoTeclas[1] ==0 && estadoTeclas[2] == 0 && estadoTeclas[3] == 0) {
					areaJuego.getJugador().setDirV(0);
					areaJuego.getJugador().setDirH(0);
				}

				areaJuego.repaint();
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
					estadoTeclas[0]= 1;
					estadoTeclas[1] = 0;
					estadoTeclas[2] = 0;
					estadoTeclas[3] = 0;
					areaJuego.getJugador().setDirH(-1);

				}else if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
					estadoTeclas[0]= 0;
					estadoTeclas[1] = 1;
					estadoTeclas[2] = 0;
					estadoTeclas[3] = 0;
					areaJuego.getJugador().setDirH(1);

				}else if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
					estadoTeclas[1]= 0;
					estadoTeclas[0] = 0;
					estadoTeclas[2] = 1;
					estadoTeclas[3] = 0;
					areaJuego.getJugador().setDirV(1);

				}else if(e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
					estadoTeclas[1]= 0;
					estadoTeclas[0] = 0;
					estadoTeclas[2] = 0;
					estadoTeclas[3] = 1;
					areaJuego.getJugador().setDirV(-1);

				}

				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					if (areaJuego.getBomba() == null) {
						int jugadorX = areaJuego.getJugador().getPosX();
						int jugadorY = areaJuego.getJugador().getPosY();
						areaJuego.setBomba(new Bomba(areaJuego, jugadorX+areaJuego.getJugador().getAncho()/2, jugadorY+areaJuego.getJugador().getAncho()));
					}
				}

				areaJuego.repaint();
			}

		});
	}
}
