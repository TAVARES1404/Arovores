package arvores;

import java.util.ArrayList;
import java.util.List;

public class ArvoreRubroNegraAleatoria<T extends Comparable<T>> {
    enum Cor {
        Vermelho,
        Preto
    }
    
    class Elemento {
        Elemento pai;
        Elemento esquerda;
        Elemento direita;
        Cor cor;
        T valor;
    
        public Elemento(T valor) {
            this.valor = valor;
        }
    }

    public ArvoreRubroNegraAleatoria() {
        nulo = new Elemento(null);
        nulo.cor = Cor.Preto;

        raiz = nulo;
    }
    
    private Elemento raiz;
    private Elemento nulo;

    public static int count;

    public boolean isVazia() {
        return raiz == nulo;
    }

    public Elemento adicionar(T valor) {

        Elemento e = new Elemento(valor);
        e.cor = Cor.Vermelho;
        e.esquerda = nulo;
        e.direita = nulo;
        e.pai = nulo;
        
        Elemento pai = this.raiz;

        //System.out.println("Adicionando " + valor);

        while (pai != nulo) {

            count ++; 

            if (valor.compareTo(pai.valor) < 0) {
                if (pai.esquerda == nulo) {
                    e.pai = pai;
                    pai.esquerda = e;
                    balanceamento(e);
                    
                    return e;
                } else {
                    pai = pai.esquerda;
                }
            } else {
                if (pai.direita == nulo) {
                    e.pai = pai;
                    pai.direita = e;
                    balanceamento(e);

                    return e;
                } else {
                    pai = pai.direita;
                }
            }
        }

        this.raiz = e;
        balanceamento(e);
        
        return e;
    }

    public void balanceamento(Elemento e) 
    {
        while (e.pai.cor == Cor.Vermelho) {  // Garante que todos os níveis foram balanceados 
            
            count ++; 

            Elemento pai = e.pai;
            Elemento avo = pai.pai;

            if (pai == avo.esquerda) {  // Identifica o lado (esquerda ou direita)
                Elemento tio = avo.direita;
                        
                if (tio.cor == Cor.Vermelho) {
                    tio.cor = Cor.Preto;  // Resolve o caso 2
                    pai.cor = Cor.Preto; 
                    avo.cor = Cor.Vermelho;
                    e = avo;  // Vai para o nível anterior (avô)
                } else {
                    if (e == pai.direita) {
                        e = pai;  // Vai para o nível anterior
                        rse(e);  // Resolve o caso 3
                    } else {
                        pai.cor = Cor.Preto;  // Resolve o caso 4
                        avo.cor = Cor.Vermelho;
                        rsd(avo);
                    }
                }
            } else {
                Elemento tio = avo.esquerda;
                        
                if (tio.cor == Cor.Vermelho) {
                    tio.cor = Cor.Preto;  // Resolve o caso 2
                    pai.cor = Cor.Preto; 
                    avo.cor = Cor.Vermelho;
                    e = avo;  // Vai para o nível anterior (avô)
                } else {
                    if (e == pai.esquerda) {
                        e = pai;  // Vai para o nível anterior
                        rsd(e);  // Resolve o caso 3
                    } else {
                        pai.cor = Cor.Preto;  // Resolve o caso 4
                        avo.cor = Cor.Vermelho;
                        rse(avo);
                    }
                }
            }
        }
        
        raiz.cor = Cor.Preto;  // Resolve caso 1
    }

    private void rse(Elemento e) {

        count ++;

        Elemento direita = e.direita;
        e.direita = direita.esquerda; 
          
        if (direita.esquerda != nulo) {
          direita.esquerda.pai = e;
        }
          
        direita.pai = e.pai;  // Se houver filho à esquerda em direita, ele será pai do nó
              
        if (e.pai == nulo) {
            raiz = direita;  // Se nó for raiz, o nó direita será a nova raiz da árvore
        } else if (e == e.pai.esquerda) {
            e.pai.esquerda = direita;  // Corrige relação pai-filho do novo pai (esquerda)
        } else {
            e.pai.direita = direita;  // Corrige relação pai-filho do novo pai (direita)
        }
          
        direita.esquerda = e;  // Corrige relação pai-filho entre o nó pivô e o nó à direita
        e.pai = direita;
    }

    private void rsd(Elemento e) {

        count ++; 

        Elemento esquerda = e.esquerda;
        e.esquerda = esquerda.direita;
              
        if (esquerda.direita != nulo) {
            esquerda.direita.pai = e;  // Se houver filho à direita em esquerda, ele será pai do nó
        }
              
        esquerda.pai = e.pai;  // Ajusta no pai do nó à esquerda
              
        if (e.pai == nulo) {
            raiz = esquerda;  // Se nó for raiz, o nó esquerda será a nova raiz da árvore
        } else if (e == e.pai.esquerda) {
            e.pai.esquerda = esquerda;  // Corrige relação pai-filho do novo pai (esquerda)
        } else {
            e.pai.direita = esquerda;  // Corrige relação pai-filho do novo pai (direita)
        }
              
        esquerda.direita = e;  // Corrige relação pai-filho entre o nó pivô e o nó à esquerda
        e.pai = esquerda;
    }
    
    public Elemento pesquisar(Elemento e, T valor) {
        while (e != nulo) {
            if (e.valor.equals(valor)) {
                return e;
            } else if (valor.compareTo(e.valor) > 0) {
                e = e.direita;
            } else {
                e = e.esquerda;
            }
        }
        
        return null;
    }

    public int caminho(Elemento e) {
        int contador = 1;

        while (e.pai != nulo) { //Enquanto não alcançamos a raiz
            contador++;
            e = e.pai;
        }

        return contador;
    }

    public static void vetorAleatorio(int n) {

        ArvoreRubroNegraAleatoria<Integer> a = new ArvoreRubroNegraAleatoria<>();

        int numero;

        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {

            do {
                numero = ArvoreAvlAleatoria.gerarNumeroAleatorio(n);
            } while (list.contains(numero));
            list.add(numero);
            a.adicionar(numero);
        }
    }

    public static int gerarNumeroAleatorio(int n) {
        int v = (int) (Math.random() * n * 1000);
        return v;
    }

}