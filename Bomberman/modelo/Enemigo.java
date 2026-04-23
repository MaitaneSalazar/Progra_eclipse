import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;
import javax.swing.ImageIcon;

public class Enemigo {

    public static final int VIVO   = 0;
    public static final int MUERTO = 1;

    private static final int IZQUIERDA = 0;
    private static final int DERECHA   = 1;
    private static final int ARRIBA    = 2;
    private static final int ABAJO     = 3;

    private int posX, posY;
    private int ancho, alto;
    private int velocidad;
    private int direccion;
    private int pasosCambioDireccion;
    private int contadorPasos;
    private int estado;
    private int temporizadorMuerte; // cuenta atrás antes de desaparecer

    private Image imgVivo0, imgVivo1; // animación andando
    private Image imgMuerte;          // Enemy2.png
    private int imgActual;
    private int delayAnim;

    private AreaJuego areaJuego;
    private Random random;

    public Enemigo(AreaJuego areaJuego, int posX, int posY) {
        this.areaJuego = areaJuego;
        this.posX = posX;
        this.posY = posY;

        ancho                = 80;
        alto                 = 80;
        velocidad            = 3;
        imgActual            = 0;
        delayAnim            = 0;
        estado               = VIVO;
        temporizadorMuerte   = 12;
        random               = new Random();

        direccion            = random.nextInt(4);
        pasosCambioDireccion = random.nextInt(30) + 20;
        contadorPasos        = 0;

        cargarImagenes();
    }

    private void cargarImagenes() {
        imgVivo0  = new ImageIcon(getClass().getResource("Enemy0.png")).getImage();
        imgVivo1  = new ImageIcon(getClass().getResource("Enemy1.png")).getImage();
        imgMuerte = new ImageIcon(getClass().getResource("Enemy2.png")).getImage();
    }

    public void mover() {
        // Si está muerto no se mueve
        if (estado == MUERTO) return;

        int posAntX = posX;
        int posAntY = posY;

        if (direccion == IZQUIERDA) posX -= velocidad;
        if (direccion == DERECHA)   posX += velocidad;
        if (direccion == ARRIBA)    posY -= velocidad;
        if (direccion == ABAJO)     posY += velocidad;

        if (hayColision()) {
            posX = posAntX;
            posY = posAntY;
            cambiarDireccion();
        }

        contadorPasos++;
        if (contadorPasos >= pasosCambioDireccion) {
            cambiarDireccion();
        }

        // Animación (solo Enemy0 y Enemy1 cuando está vivo)
        delayAnim++;
        if (delayAnim == 5) {
            imgActual = (imgActual == 0) ? 1 : 0;
            delayAnim = 0;
        }
    }

    // Llamado desde EventosAreaJuego cuando la explosión le toca
    public void morir() {
        estado = MUERTO;
    }

    // Cuenta atrás de la animación de muerte
    // Devuelve true cuando ya se puede eliminar de la lista
    public boolean actualizarMuerte() {
        if (estado == MUERTO) {
            temporizadorMuerte--;
            if (temporizadorMuerte <= 0) {
                return true; // listo para eliminar
            }
        }
        return false;
    }

    public void dibujar(Graphics g) {
        if (estado == VIVO) {
            Image imgActual = (this.imgActual == 0) ? imgVivo0 : imgVivo1;
            g.drawImage(imgActual, posX, posY, ancho, alto, areaJuego);
        } else if (estado == MUERTO) {
            g.drawImage(imgMuerte, posX, posY, ancho, alto, areaJuego);
        }
    }

    private void cambiarDireccion() {
        direccion            = random.nextInt(4);
        pasosCambioDireccion = random.nextInt(30) + 20;
        contadorPasos        = 0;
    }

    private boolean hayColision() {
        int[][] mapa = areaJuego.getMapa();
        Rectangle rectEnemigo = getRect();

        for (int fila = 0; fila < AreaJuego.FILAS; fila++) {
            for (int col = 0; col < AreaJuego.COLS; col++) {
                if (mapa[fila][col] == 1 || mapa[fila][col] == 2) {
                    Rectangle rectMuro = new Rectangle(
                        col  * AreaJuego.ANCHO_CELDA,
                        fila * AreaJuego.ALTO_CELDA,
                        AreaJuego.ANCHO_CELDA,
                        AreaJuego.ALTO_CELDA
                    );
                    if (rectEnemigo.intersects(rectMuro)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public Rectangle getRect() {
        return new Rectangle(posX, posY, ancho, alto);
    }

    public int getEstado()  { return estado; }
    public int getPosX()    { return posX; }
    public int getPosY()    { return posY; }
}