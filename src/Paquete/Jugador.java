package Paquete;

import java.awt.*;

public class Jugador {
    private String id;
    private String ficha;
    private Color color;
    private Ficha[] fichas = new Ficha[3];
    private int fichasColocadas = 0;


    public Jugador(String id, String ficha, Color color) {
        this.id = id;
        this.ficha = ficha;
        this.color = color;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFicha() {
        return ficha;
    }

    public Ficha[] getFichas() {
        return this.fichas;
    }

    public void setFicha(String ficha) {
        this.ficha = ficha;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getFichasColocadas() {
        return fichasColocadas;
    }

    public void colocarFicha(int fila, int columna) {
        fichas[fichasColocadas] = new Ficha(fila, columna);
        fichasColocadas++;
    }

    public void descolocarFicha(int numeroFicha) {
        fichasColocadas--;
        fichas[numeroFicha].descolocarFicha();
    }

    public int tieneFichaEnPosicion(int fila, int columna) {
        int indiceFicha = -1;

        for (int i = 0; i < fichas.length; i++){
            Ficha ficha = fichas[i];
            if (ficha.getFila() == fila && ficha.getColumna() == columna) {
                indiceFicha = i;
                break;
            }
        }


        return indiceFicha;
    }

    // x1[fila, columna] x2[fila2, columna2] x3[fila3, columna3]
    // horizontal = columna == columna2 == columna3
    // vertical = fila == fila2 == fila3
    // diagonal = fila == columna fila2 == columna2 fila3 == columna3
    // diagonal inversa = fila + columna == tamaño - 1 = 2

    // [] [] [x1] = 0 + 2 = 2  [x1] [] [] = 0 - 0 = 0
    // [] [x2] [] = 1 + 1 = 2  [] [x2] [] = 1 - 1 = 0
    // [x2] [] [] = 0 + 2 = 2  [] [] [x3] = 2 - 2 = 0

    public boolean esGanador() {
        boolean esGanador = true;

        // primera comprobacion fichas colocaldas < total de fichas posibles
        if (fichasColocadas < fichas.length) {
            return false;
        }

        // segunda comprobacion horizontal
        int columnaAnterior = fichas[0].getColumna();
        for (Ficha ficha: fichas) {
            if (ficha.getColumna() != columnaAnterior) {
                esGanador = false;
                break;
            }
        }

        if (esGanador) {
            return true;
        }

        // combinacion vertical
        esGanador = true;
        int filaAnterior = fichas[0].getFila();
        for (Ficha ficha: fichas) {
            if (ficha.getFila() != filaAnterior) {
                esGanador = false;
                break;
            }
        }

        if (esGanador) {
            return true;
        }

        // diagonal
        esGanador = true;
        int restaAnterior = fichas[0].getColumna() - fichas[0].getFila();
        for (Ficha ficha: fichas) {
            int resta = ficha.getColumna() - ficha.getFila();
            if (resta != restaAnterior) {
                esGanador = false;
                break;
            }
        }

        if (esGanador) {
            return true;
        }


        //diagonal inversa
        esGanador = true;
        int sumaAnteior = fichas[0].getColumna() + fichas[0].getFila();
        for (Ficha ficha: fichas) {
            int suma = ficha.getColumna() + ficha.getFila();
            if (suma != sumaAnteior) {
                esGanador = false;
                break;
            }
        }

        return esGanador;
    }
}