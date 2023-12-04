import java.util.ArrayList;
import java.util.List;

public class Tabuleiro {
    private List<List<Carta>> linhas;

    public Tabuleiro() {
        linhas = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            linhas.add(new ArrayList<>());
        }
    }

    private int calcularPontuacao(List<Carta> cartas) {
        int pontos = 0;
        for (Carta carta : cartas) {
            pontos += carta.calcularPontuacao();
        }
        return pontos;
    }

    public void posicionarCarta(Carta carta) {
        int linhaParaPosicionar = encontrarLinha(carta);
        linhas.get(linhaParaPosicionar).add(carta);
    }

    private int encontrarLinha(Carta carta) {
        int linhaEscolhida = 0;
        int diferencaMinima = Integer.MAX_VALUE;

        for (int i = 0; i < linhas.size(); i++) {
            List<Carta> linha = linhas.get(i);
            if (linha.isEmpty()) {
                return i;
            }

            int diferenca = carta.getNumero() - linha.get(linha.size() - 1).getNumero();
            if (diferenca >= 0 && diferenca < diferencaMinima) {
                diferencaMinima = diferenca;
                linhaEscolhida = i;
            }
        }

        return linhaEscolhida;
    }

    public int coletarCartas(int linha) {
        List<Carta> cartasColetadas = linhas.get(linha);
        linhas.set(linha, new ArrayList<>());

        return calcularPontuacao(cartasColetadas);
    }

    public void imprimirTabuleiro() {
        for (List<Carta> linha : linhas) {
            for (Carta carta : linha) {
                System.out.print(carta.getNumero() + " ");
            }
            System.out.println();
        }
    }

    public void distribuirCartasIniciais(Baralho baralho) {
        for (List<Carta> linha : linhas) {
            List<Carta> cartas = baralho.distribuirCartas(1); 
            linha.addAll(0, cartas);
        }
    }
    
    
    

}

