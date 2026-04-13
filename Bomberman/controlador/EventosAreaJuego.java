
public class EventosAreaJuego {
	private AreaJuego areaJuego;
	private int []estadoTeclas;

	public EventosAreaJuego(AreaJuego areaJuego) {
		this.areaJuego = areaJuego;
		
		estadoTeclas = new int[5];
		for(int i = 0; i < estadoTeclas.length; i++) {
			estadoTeclas[i]=0;
		}
	}
}
