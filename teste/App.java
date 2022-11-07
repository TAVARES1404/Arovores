package teste;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import arvores.ArvoreAvlAleatoria;
import arvores.ArvoreAvlOrdenada;
import arvores.ArvoreBAleatoria1;
import arvores.ArvoreBOrdenada1;
import arvores.ArvoreBAleatoria5;
import arvores.ArvoreBOrdenada5;
import arvores.ArvoreBAleatoria10;
import arvores.ArvoreBOrdenada10;
import arvores.ArvoreRubroNegraAleatoria;
import arvores.ArvoreRubroNegraOrdenada;

public class App {
    public static void main(String[] args) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(new File("output.csv")));

        int n = 1000;
        int repeticoes = 10;

        writer.write("N;AvoreAVL-Ordenada;AvoreAVL-Aleatoria;AvoreRubroNegra-Ordenada;AvoreRubroNegra-Aleatoria;ArvoreB-Ordenada-1;ArvoreB-Aleatoria-1;ArvoreB-Ordenada-5;ArvoreB-Aleatoria-5;ArvoreB-Ordenada-10;ArvoreB-Aleatoria-10;\n");

        for (int i = 1; i <= n; i++) {

            int somaAVLOrdenada = 0;
            int somaAvlAleatoria = 0;

            int somaRubroNegraOrdenada = 0;
            int somaRubroNegraAleatoria = 0;

            int somaArvoreBOrdenada1 = 0;
            int somaArvoreBAleatoria1 = 0;
            
            int somaArvoreBOrdenada5 = 0;
            int somaArvoreBAleatoria5 = 0;
            
            int somaArvoreBOrdenada10 = 0;
            int somaArvoreBAleatoria10 = 0;

            for (int j = 0; j < repeticoes; j++) {

                ArvoreAvlOrdenada.vetorOrdenado(i);
                ArvoreAvlAleatoria.vetorAleatorio(i);

                ArvoreRubroNegraOrdenada.vetorOrdenado(i);
                ArvoreRubroNegraAleatoria.vetorAleatorio(i);

                ArvoreBOrdenada1.vetorOrdenado(i, 1);
                ArvoreBAleatoria1.vetorAleatorio(i, 1);

                ArvoreBOrdenada5.vetorOrdenado(i, 5);
                ArvoreBAleatoria5.vetorAleatorio(i, 5);

                ArvoreBOrdenada10.vetorOrdenado(i, 10);
                ArvoreBAleatoria10.vetorAleatorio(i, 10);

                somaAVLOrdenada += ArvoreAvlOrdenada.count;
                somaAvlAleatoria += ArvoreAvlAleatoria.count;

                somaRubroNegraOrdenada += ArvoreRubroNegraOrdenada.count;
                somaRubroNegraAleatoria += ArvoreRubroNegraAleatoria.count;

                somaArvoreBOrdenada1 += ArvoreBOrdenada1.count;
                somaArvoreBAleatoria1 += ArvoreBAleatoria1.count;

                somaArvoreBOrdenada5 += ArvoreBOrdenada5.count;
                somaArvoreBAleatoria5 += ArvoreBAleatoria5.count;

                somaArvoreBOrdenada10 += ArvoreBOrdenada10.count;
                somaArvoreBAleatoria10 += ArvoreBAleatoria10.count;

            }

            writer.write(i + ";" +
                    (somaAVLOrdenada) + ";" + (somaAvlAleatoria / repeticoes ) + ";" + 
                    (somaRubroNegraOrdenada) + ";" +  (somaRubroNegraAleatoria / repeticoes) + ";" + 
                    (somaArvoreBOrdenada1) + ";" +(somaArvoreBAleatoria1 / repeticoes) + ";" +
                    (somaArvoreBOrdenada5) + ";" +(somaArvoreBAleatoria5 / repeticoes) + ";" +
                    (somaArvoreBOrdenada10) + ";" +(somaArvoreBAleatoria10 / repeticoes) + ";" +
                    "\n");

            ArvoreAvlOrdenada.count = 0;
            ArvoreAvlAleatoria.count = 0;

            ArvoreRubroNegraOrdenada.count = 0;
            ArvoreRubroNegraAleatoria.count = 0;

            ArvoreBOrdenada1.count = 0;
            ArvoreBAleatoria1.count = 0;

            ArvoreBOrdenada5.count = 0;
            ArvoreBAleatoria5.count = 0;

            ArvoreBOrdenada10.count = 0;
            ArvoreBAleatoria10.count = 0;
        }

        writer.close();
    }
}
