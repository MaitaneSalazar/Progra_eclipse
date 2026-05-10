import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class EventosAreaJuego {
	private AreaJuego areaJuego;
	private int[] estadoTeclas;
	private Timer reloj;
	private boolean pantallaFinalMostrada = false;

	public EventosAreaJuego(AreaJuego areaJuego) {
		this.areaJuego = areaJuego;

		estadoTeclas = new int[5];
		for (int i = 0; i < estadoTeclas.length; i++) {
			estadoTeclas[i] = 0;
		}

		reloj = new Timer(40, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				// Mover jugador
				if (areaJuego.getJugador().getEstado() == Jugador.VIVO) {
					areaJuego.getJugador().mover();

					for (int i = areaJuego.getArrayEnemigos().size() - 1; i >= 0; i--) {
						Enemigo enemigo = areaJuego.getArrayEnemigos().get(i);
						if (enemigo.getEstado() == Enemigo.VIVO) {
							if (areaJuego.getJugador().getRect().intersects(enemigo.getRect())) {
								areaJuego.getJugador().perderVida();
							}
						}
					}
				}

				// Animacion de muerte
				if (areaJuego.getJugador().getEstado() == Jugador.MUERTO) {
					areaJuego.getJugador().morir();
				}

				areaJuego.getJugador().actualizar();
				// Mover todos los enemigos
				for (int i = 0; i < areaJuego.getArrayEnemigos().size(); i++) {
					areaJuego.getArrayEnemigos().get(i).mover();
				}

				// Gestionar la bomba
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

				// Gestionar la explosion
				if (areaJuego.getExplosion() != null) {
					areaJuego.getExplosion().actualizar();

					// Colision explosion jugador
					if (areaJuego.getExplosion().colisionaCon(areaJuego.getJugador().getRect())) {
						areaJuego.getJugador().perderVida();
					}

					// Colision explosion enemigos
					for (int i = 0; i < areaJuego.getArrayEnemigos().size(); i++) {
						Enemigo enemigo = areaJuego.getArrayEnemigos().get(i);
						if (enemigo.getEstado() == Enemigo.VIVO) {
							if (areaJuego.getExplosion().colisionaCon(enemigo.getRect())) {
								enemigo.morir();
								areaJuego.getJugador().sumarPuntos(100);
							}
						}
					}

					if (areaJuego.getExplosion().haTerminado()) {
						areaJuego.setExplosion(null);
					}
				}

				// Actualizar temporizador de muerte de enemigos
				for (int i = areaJuego.getArrayEnemigos().size() - 1; i >= 0; i--) {
					if (areaJuego.getArrayEnemigos().get(i).actualizarMuerte()) {
						areaJuego.getArrayEnemigos().remove(i);
					}
				}

				areaJuego.actualizarMuroEspecial();
				comprobarVictoria();
				comprobarDerrota();

				areaJuego.repaint();
			}
		});

		reloj.start();

		areaJuego.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			    if (e.getKeyCode() == KeyEvent.VK_A) {
			        estadoTeclas[0] = 0;
			        if (estadoTeclas[1] == 0) {
			            areaJuego.getJugador().setDirH(0);
			        } else {
			            areaJuego.getJugador().setDirH(1);
			        }
			    } else if (e.getKeyCode() == KeyEvent.VK_D) {
			        estadoTeclas[1] = 0;
			        if (estadoTeclas[0] == 0) {
			            areaJuego.getJugador().setDirH(0);
			        } else {
			            areaJuego.getJugador().setDirH(-1);
			        }
			    } else if (e.getKeyCode() == KeyEvent.VK_W) {
			        estadoTeclas[2] = 0;
			        if (estadoTeclas[3] == 0) {
			            areaJuego.getJugador().setDirV(0);
			        } else {
			            areaJuego.getJugador().setDirV(-1);
			        }
			    } else if (e.getKeyCode() == KeyEvent.VK_S) {
			        estadoTeclas[3] = 0;
			        if (estadoTeclas[2] == 0) {
			            areaJuego.getJugador().setDirV(0);
			        } else {
			            areaJuego.getJugador().setDirV(1);
			        }
			    }

			    areaJuego.repaint();
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_A) {
					estadoTeclas[0] = 1;
					estadoTeclas[1] = 0;
					estadoTeclas[2] = 0;
					estadoTeclas[3] = 0;
					areaJuego.getJugador().setDirH(-1);

				} else if (e.getKeyCode() == KeyEvent.VK_D) {
					estadoTeclas[0] = 0;
					estadoTeclas[1] = 1;
					estadoTeclas[2] = 0;
					estadoTeclas[3] = 0;
					areaJuego.getJugador().setDirH(1);

				} else if (e.getKeyCode() == KeyEvent.VK_W) {
					estadoTeclas[0] = 0;
					estadoTeclas[1] = 0;
					estadoTeclas[2] = 1;
					estadoTeclas[3] = 0;
					areaJuego.getJugador().setDirV(1);

				} else if (e.getKeyCode() == KeyEvent.VK_S) {
					estadoTeclas[0] = 0;
					estadoTeclas[1] = 0;
					estadoTeclas[2] = 0;
					estadoTeclas[3] = 1;
					areaJuego.getJugador().setDirV(-1);
				}

				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					if (areaJuego.getBomba() == null) {

						Rectangle rect = areaJuego.getJugador().getRect();
						int centroX = rect.x + rect.width / 2;
						int centroY = rect.y + rect.height / 2;

						int celdaCol = centroX / AreaJuego.ANCHO_CELDA;
						int celdaFila = centroY / AreaJuego.ALTO_CELDA;

						// Solo colocar bomba si la celda es libre (valor 0)
						if (areaJuego.getMapa()[celdaFila][celdaCol] == 0) {
							areaJuego.setBomba(new Bomba(areaJuego,
									centroX, centroY));
						}
					}
				}

				areaJuego.repaint();
			}
		});
	}

	private void comprobarVictoria() {
		if (pantallaFinalMostrada) {
			return;
		}
		if (areaJuego.getJugador().getEstado() != Jugador.VIVO) {
			return;
		}

		if (areaJuego.getMapa()[AreaJuego.MURO_FILA][AreaJuego.MURO_COL] == 0) {

			Rectangle rect = areaJuego.getJugador().getRect();
			int centroX = rect.x + rect.width  / 2;
			int centroY = rect.y + rect.height / 2;
			int celdaCol = centroX / AreaJuego.ANCHO_CELDA;
			int celdaFila = centroY / AreaJuego.ALTO_CELDA;

			if (celdaFila == AreaJuego.VICTORIA_FILA && celdaCol == AreaJuego.VICTORIA_COL) {
				pantallaFinalMostrada = true;
				int score = areaJuego.getHud().getScore();
				PantallaFinal pantalla = new PantallaFinal(PantallaFinal.VICTORIA, score);
				pantalla.setVisible(true);
				SwingUtilities.getWindowAncestor(areaJuego).dispose();
				reloj.stop();
			}
		}
	}

	private void comprobarDerrota() {
		if (pantallaFinalMostrada) return;
		Jugador jugador = areaJuego.getJugador();
		if (jugador.getEstado() == Jugador.MUERTO && jugador.animacionMuerteTerminada()) {
			pantallaFinalMostrada = true;
			int score = areaJuego.getHud().getScore();
			PantallaFinal pantalla = new PantallaFinal(PantallaFinal.DERROTA, score);
			pantalla.setVisible(true);
			SwingUtilities.getWindowAncestor(areaJuego).dispose();
			reloj.stop();
		}
	}
}