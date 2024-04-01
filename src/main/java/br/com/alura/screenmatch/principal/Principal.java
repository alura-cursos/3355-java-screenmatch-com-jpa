package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.model.Serie;
import br.com.alura.screenmatch.repository.SerieRepository;
import br.com.alura.screenmatch.service.ConsumoApi;
import br.com.alura.screenmatch.service.ConverteDados;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
@RestController
public class Principal {

    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=6585022c";

    private SerieRepository serieRepository;

    public Principal(SerieRepository serieRepository) {
        this.serieRepository = serieRepository;
    }


    private List<DadosSerie> dadosSeries = new ArrayList<>();


    public void exibeMenu() {
        var opcao = -1;
//        enquanto não for 0, exibe o menu
        while (opcao != 0) {
            exibeMenuOpcoes();
            if (leitura.nextInt() == 0) {
                break;
            }
        }
    }

    private void exibeMenuOpcoes() {
        var menu = """
                1 - Buscar séries
                2 - Buscar episódios
                3 - Listar séries buscadas
                0 - Sair                                 
                """;

        System.out.println(menu);
        int opcao = leitura.nextInt();
        leitura.nextLine();

        switch (opcao) {
            case 1:
                buscarSerieWeb();
                break;
            case 2:
                buscarEpisodioPorSerie();
                break;
            case 3:
               listarSeriesBuscadas();
            case 0:
                System.out.println("Saindo...");
                break;
            default:
                System.out.println("Opção inválida");
        }

    }

    private void listarSeriesBuscadas() {
        List<Serie> series = serieRepository.findAll();
        series.forEach(System.out::println);
    }

    private void buscarSerieWeb() {
        DadosSerie dados = getDadosSerie();
        Serie serie = new Serie(dados);
//        busca serie por titulo e salve apenas se não existir
        if (serieRepository.findByTitulo(serie.getTitulo()).isEmpty()) {
            serieRepository.save(serie);
        }else {
            System.out.println("Série já cadastrada");
        }
        System.out.println(dados);
    }

    private DadosSerie getDadosSerie() {
        System.out.println("Digite o nome da série para busca");
        var nomeSerie = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
        return dados;
    }

    private void buscarEpisodioPorSerie(){
        DadosSerie dadosSerie = getDadosSerie();
        List<DadosTemporada> temporadas = new ArrayList<>();

        for (int i = 1; i <= dadosSerie.totalTemporadas(); i++) {
            var json = consumo.obterDados(ENDERECO + dadosSerie.titulo().replace(" ", "+") + "&season=" + i + API_KEY);
            DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
            temporadas.add(dadosTemporada);
        }
        temporadas.forEach(System.out::println);
    }

    public List<DadosSerie> getDadosSeries() {
        return dadosSeries;
    }

    public void setDadosSeries(List<DadosSerie> dadosSeries) {
        this.dadosSeries = dadosSeries;
    }
}