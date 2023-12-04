import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("informe o número de jogadores (de 3 a 6): ");
        int numJogadores = scanner.nextInt();
        scanner.nextLine();
        if (numJogadores<3 || numJogadores>6){
            System.out.println("o numero de jogadores deve ser maior que 3 e menor que 6");
            scanner.close();
            return;
        }

        List<Jogador> jogadores = new ArrayList<>();
        for (int i = 1; i <= numJogadores; i++) {
            System.out.print("informe o nome do jogador " + i + ": ");
            String nomeJogador = scanner.nextLine();
            jogadores.add(new Jogador(nomeJogador, new ArrayList<>()));
        }

        Baralho baralho = new Baralho();
        baralho.embaralhar();

        Tabuleiro tabuleiro = new Tabuleiro();

        for (Jogador jogador : jogadores) {
            List<Carta> mao = baralho.distribuirCartas(12);
            jogador.getMao().addAll(mao);
        }

        tabuleiro.distribuirCartasIniciais(baralho);


        for (int i = 0; i < 12; i++) {
            for (Jogador jogador : jogadores) {
                System.out.println("Jogador: " + jogador.getNome());
                System.out.println("Mão atual: " + jogador.getMao().toString().replace("[", "").replace("]", ""));
                System.out.print("Escolha uma carta para jogar: ");
                int cartaEscolhida = scanner.nextInt();

                Carta carta = null;
                for (Carta c : jogador.getMao()) {
                    if (c.getNumero() == cartaEscolhida) {
                        carta = c;
                        break;
                    }
                }

                if (carta != null) {
                    jogador.removerCartasMao(List.of(carta));
                    tabuleiro.posicionarCarta(carta);
                } else {
                    System.out.println("Carta inválida. Escolha novamente.");
                    i--;
                }
            }

            System.out.println("Estado atual do tabuleiro:");
            tabuleiro.imprimirTabuleiro();

            for (Jogador jogador : jogadores) {
                int linha = 0; 
                int pontos = tabuleiro.coletarCartas(linha);
                jogador.atualizarPontuacao(pontos);
            }
        }

        System.out.println("Pontuações finais:");
        int menorPontuacao = Integer.MAX_VALUE;
        List<Jogador> vencedores = new ArrayList<>();

        for (Jogador jogador : jogadores) {
            int pontuacaoJogador = jogador.getPontuacao();
            System.out.println(jogador.getNome() + ": " + pontuacaoJogador);

            if (pontuacaoJogador < menorPontuacao) {
                menorPontuacao = pontuacaoJogador;
                vencedores.clear();
                vencedores.add(jogador);
            } else if (pontuacaoJogador == menorPontuacao) {
                vencedores.add(jogador);
            }
        }

        if (vencedores.size() == 1) {
            System.out.println("O vencedor é: " + vencedores.get(0).getNome() + " com " + menorPontuacao + " pontos!");
        } else {
            System.out.println("Empate entre os seguintes jogadores:");
            for (Jogador jogador : vencedores) {
                System.out.println(jogador.getNome() + " com " + menorPontuacao + " pontos!");
            }
        }

        scanner.close();
    }
}
