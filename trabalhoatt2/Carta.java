public class Carta {
    private int numero;

    public Carta(int numero) {
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

    public int calcularPontuacao() {
        int pontos = 1;

        if (numero % 10 == 5) { // Cartas terminadas com o dígito 5 valem 1 ponto extra
            pontos++;
        }
        if (numero % 10 == 0) { // Cartas múltiplas de 10 valem 2 pontos extras
            pontos += 2;
        }
        if (temDigitosRepetidos(numero)) { // Cartas com dois dígitos repetidos valem 4 pontos extras;
            pontos += 4;
        }

        return pontos;
    }

    private boolean temDigitosRepetidos(int num) { //chatgpt
        String numStr = String.valueOf(num);
        return numStr.length() == 2 && numStr.charAt(0) == numStr.charAt(1);
    }

    @Override
    public String toString() {
        return "Carta " + numero;
    }
}
