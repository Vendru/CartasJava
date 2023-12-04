import java.util.List;

public class Jogador {
    private String nome;
    private List<Carta> mao; // mao é uma lista
    private int pontuacao;

    public Jogador(String nome, List<Carta> mao) {
        this.nome = nome;
        this.mao = mao;
        this.pontuacao = 0;
    }

    public String getNome() {
        return nome;
    }

    public List<Carta> getMao() {
        return mao;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void atualizarPontuacao(int pontos) {
        pontuacao += pontos;
    }

    public void removerCartasMao(List<Carta> cartasRemovidas) { 
        mao.removeAll(cartasRemovidas); 
    }

    public void adicionarCartaMao(Carta carta) {
        mao.add(carta);
    }
}
