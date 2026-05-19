import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        String fullCsv = "campeonato-brasileiro-full.csv";

        Repositorio repositorio = new Repositorio();

        try {

            List<Partida> partidas = repositorio.carregarPartidas(fullCsv);

            var partida0 = partidas.getFirst();

            System.out.println("A partida foi entre " + partida0.getTimeMandante() + " e " + partida0.getTimeVisitante());
            System.out.println("A placar foi " + partida0.getGolsMandante() + " x " + partida0.getGolsVisitante());


        } catch (IOException e) {
            System.err.println("Erro ao ler arquivos: " + e.getMessage());
            e.printStackTrace();
        }

    }

}
