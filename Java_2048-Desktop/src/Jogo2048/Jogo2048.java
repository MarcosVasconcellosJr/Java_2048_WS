/*
* Criado Por: 
* Marcos Junior - 18720920
* Hiago Silva - 18726455
*
* Este programa e uma copia barata do jogo 2048, cuja finalidade
* e juntar pares de valores iguais a fim de somar um quadrado de 
* valor = 2048
 */
package Jogo2048;
import model.Caixinha;
import controller.Movimentos;
import arquivo.Arquivos;
import controller.Http;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;
import java.util.logging.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import model.GameCommands;



// Classe principal que declara as variaveis e faz a orquestragem de funcionalidades
public class Jogo2048 extends JPanel {
    
    String arq = "src\\model\\maximumhighScore.txt";
    
    final Color[] tabelaDeCores = {
        new Color(0x701710), new Color(0xFFE4C3), new Color(0xfff4d3), new Color(0xffdac3),
        new Color(0xE7B08E), new Color(0xE7BF8E), new Color(0xffc4c3), new Color(0xE7948e), 
        new Color(0xbe7e56), new Color(0xbe5e56), new Color(0x9c3931), new Color(0x701710)
    }; // Cores usadas

    public static int objetivo = 2048; // Objetivo padrÃ£o do jogo, alcanÃ§ar recorde = 2048
    public static int recorde;
    public static int pontos; // PontuaÃ§Ã£o atual do jogador

    public Color caixaCor = new Color(0x4EB7CC); // Cor do container de fundo do jogo 2048
    public Color corCaixinhaVazia = new Color(0xEBEBEB);// Coir da caixinha do nÃºmero
    public Color textos = new Color(0x506266);
    public Color corTelaDeInicio = new Color(0xEBEBEB); // Cor de fundo da tela inicial
    public Color cabecalho = new Color(0xe8649b);
    public Color blocosCabecalho = new Color(0xEBEBEB);

    public static Caixinha[][] caixinha; // DeclaraÃ§Ã£o da matriz que serÃ¡ usada para toda a lÃ³gica do joguinho
    public static Caixinha[][] caixinhaEstadoAnterior;
    public int tam = 4; // usada para declarar as dimensÃµes da matriz

    public static String statusDoJogo = "inicio"; // usada para tomar aÃ§Ãµes dependendo do status atual que o usuÃ¡rio estÃ¡
    public boolean temMovimentosPossiveis; // Usada para fazer a verificaÃ§Ã£o dos movimentos para os 4 lados.
    public Movimentos mover;
    
    public boolean jaDesfez = false, jaRefez = false; //Usamos para saber se a aÃ§Ã£o de desfazer e refazer movimentos jÃ¡ foi executada
    
    public static String conteudo = ""; //Conteudo do arquivo de highscore
    
    public int clicouEmX, clicouEmY; //PosiÃ§Ã£o que o usuÃ¡rio clicou com o mouse
    public int soltouEmX, soltouEmY; //PosiÃ§Ã£o que o usuÃ¡rio soltou o mouse
    public int dX = 0, dY = 0; //VariaÃ§Ã£o do click e release do mouse

    public Jogo2048() {
        setPreferredSize(new Dimension(900, 700));// tamanho da tela a ser exibida
        setBackground(new Color(0xEBEBEB));// seta uma cor para o fundo do JPanel
        setFont(new Font("SansSerif", Font.BOLD, 48));// escolha da fonte para o jogo e tambem o tamanho dos numeros a serem exibidos.
        
        setFocusable(true);// Fazer com que os Jpanel seja ouvido pelos eventos

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                clicouEmX = e.getX(); // Pega posiÃ§Ã£o em X
                clicouEmY = e.getY(); // Pega posiÃ§Ã£o em Y
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                mouseEvents(e);
                try {
                    Thread.sleep(20);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Jogo2048.class.getName()).log(Level.SEVERE, null, ex);
                }
                repaint(); // Ele habilita um update no redesenhamento do jpanel quando os metodos graficos sao chamados
            }
        });

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP: mover.moveUp(); break;
                    
                    case KeyEvent.VK_W: mover.moveUp(); break;
                    
                    case KeyEvent.VK_DOWN: mover.moveDown(); break;

                    case KeyEvent.VK_S: mover.moveDown(); break;

                    case KeyEvent.VK_LEFT: mover.moveLeft(); break;

                    case KeyEvent.VK_A: mover.moveLeft(); break;

                    case KeyEvent.VK_RIGHT: mover.moveRight(); break;

                    case KeyEvent.VK_D: mover.moveRight(); break;
                    
                    case KeyEvent.VK_ENTER: iniciarJogo(); break;
                    
                    case KeyEvent.VK_ESCAPE: statusDoJogo = "inicio"; break;
                    
                    case KeyEvent.VK_P: 
                        addCaixinhaRandomica(512);
                        addCaixinhaRandomica(512);
                        repaint();
                        break;
                }
                repaint();
            }
        });
    }
    
    public void readResponseFromWS(StringBuffer sendGet) {
        if (sendGet.toString().contains("Down")) {
            mover.moveDown();
        }
        if (sendGet.toString().contains("Up")) {
            mover.moveUp();
        }
        if (sendGet.toString().contains("Left")) {
            mover.moveLeft();
        }
        if (sendGet.toString().contains("Right")) {
            mover.moveRight();
        }
        repaint();
    }
                
    public String Jogo2048(int Status) {
        if(Status == 1){
            return statusDoJogo;
        }
        return null;
    }
    
    @Override
    public void paintComponent(Graphics gg) {
        super.paintComponent(gg);
        Graphics2D g = (Graphics2D) gg;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        try {
            printaGradeDeCaixinhas(g);
        } catch (IOException ex) {
            Logger.getLogger(Jogo2048.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void iniciarJogo() {
        if (!statusDoJogo.equals("usuarioJogando")) {
            pontos = 0;
            recorde = 0;
            statusDoJogo = "usuarioJogando";
            caixinha = new Caixinha[tam][tam];
            caixinhaEstadoAnterior = new Caixinha[tam][tam];
            mover = new Movimentos();
            addCaixinhaRandomica(1);
            addCaixinhaRandomica(1);
            Http http = new Http();
            GameCommands gc = new GameCommands();
            
            try {
                StringBuffer lixo = http.sendGet();
            } catch (Exception ex) {
                Logger.getLogger(Jogo2048.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            new Thread() {
                @Override
                public void run() {
                   while(true){
                       try {
                           Thread.sleep(1000);
                           readResponseFromWS(http.sendGet());
                       } catch (InterruptedException ex) {
                       } catch (Exception ex) {
                           Logger.getLogger(Jogo2048.class.getName()).log(Level.SEVERE, null, ex);
                       }
                   }
                }
            }.start();
        }
    }

    public void mouseEvents(MouseEvent e) {
        try {
            dX = 0;
            dY = 0;
            soltouEmX = e.getX();
            soltouEmY = e.getY();
            dX = (soltouEmX - clicouEmX);
            dY = (soltouEmY - clicouEmY)*(-1);
            //Botoes Desfazer e refazer
            if (clicouEmX > 215 && clicouEmX < 275 && clicouEmY > 30 && clicouEmY < 80){
                if(jaDesfez == false){
                    troca(caixinhaEstadoAnterior, caixinha, 1);
                    jaDesfez = true;
                    jaRefez = false;
                }
            }
            if (clicouEmX > 290 && clicouEmX < 350 && clicouEmY > 30 && clicouEmY < 80){
                if(jaRefez == false){
                    troca(caixinhaEstadoAnterior, caixinha, 1);
                    jaRefez = true;
                    jaDesfez = false;
                }
            }

            //1 quadrante
            if (dX > 0 && dY > 0) {
                if (Math.abs(dX) > Math.abs(dY)) mover.moveRight();
                else mover.moveUp();
            }
            //2 quadrante
            if (dX < 0 && dY > 0) {
                if (Math.abs(dX) > Math.abs(dY)) mover.moveLeft();
                else mover.moveUp();
            }
            //3 quadrante
            if (dX < 0 && dY < 0) {
                if (Math.abs(dX) < Math.abs(dY)) mover.moveDown();
                else mover.moveLeft();
            }   
            //4 quadrante
            if (dX > 0 && dY < 0) {
                if (Math.abs(dX) > Math.abs(dY)) mover.moveRight();
                else mover.moveDown();
            }
        } catch (java.lang.NullPointerException el) {
            System.out.println("Impossível lidar com teclado, falha de sistema");
        }
    }
    
    public void troca(Caixinha[][] a, Caixinha[][] b, int func){
        Caixinha swap;
        for (int linha = 0; linha < tam; linha++) {
            for (int coluna = 0; coluna < tam; coluna++) {
                if(func == 1){
                    swap = a[linha][coluna];
                    a[linha][coluna] = b[linha][coluna];
                    b[linha][coluna] = swap;
                }//Func 1 faz a trocar com b
                if(func == 2) a[linha][coluna] = b[linha][coluna]; //Func 2 faz a = b
            }
        }
    }

    public void printaGradeDeCaixinhas(Graphics2D g) throws IOException {
        // Desenha o container e o cabecalho
        g.setColor(caixaCor);
        g.fillRoundRect(200, 100, 500, 500, 25, 25);
        g.setColor(cabecalho);
        g.fillRoundRect(200, 20, 500, 70, 25, 25);

        // Blocos de desfazer
        g.setColor(blocosCabecalho);
        g.fillRoundRect(215, 30, 60, 50, 25, 25);
        BufferedImage desfazer = ImageIO.read(getClass().getResourceAsStream("/view/assets/desfazer.png"));
        g.drawImage(desfazer, 230, 40, 30, 30, null);
            
        g.setColor(blocosCabecalho);
        g.fillRoundRect(290, 30, 60, 50, 25, 25);
        BufferedImage refazer = ImageIO.read(getClass().getResourceAsStream("/view/assets/refazer.png"));
        g.drawImage(refazer, 305, 40, 30, 30, null);
        
        // Blocos de Score atual e HighScore
        // Desenha o score do usuÃ¡rio
        g.setColor(blocosCabecalho);
        g.fillRoundRect(365, 30, 150, 50, 25, 25);
        g.setColor(textos);g.setFont(new Font("SansSerif", Font.BOLD, 17));
        String s = String.valueOf(pontos);
        g.drawString("Score: " + s, 375, 63);
        // Desenha o highScore
        g.setColor(blocosCabecalho);
        g.fillRoundRect(530, 30, 150, 50, 25, 25);
        g.setColor(textos);
        g.setFont(new Font("SansSerif", Font.BOLD, 20));
        
        //LÃª o arquivo e mostra o maior valor jÃ¡ feito no histÃ³rico do programa
        s = Arquivos.Read(arq);
        conteudo = Arquivos.Read(arq);
        double c = Double.parseDouble(conteudo);
        g.setFont(new Font("SansSerif", Font.BOLD, 17));
        
        if(pontos >= c){
            Arquivos.Write(arq, Integer.toString(pontos));
            String l = Integer.toString(pontos);
            g.drawString("HighScore: " + l, 540, 63);
        }else{
            g.drawString("HighScore: " + s, 540, 63);
        }
       
        if (statusDoJogo.equals("usuarioJogando")) {
            for (int linha = 0; linha < tam; linha++) {
                for (int coluna = 0; coluna < tam; coluna++) {
                    if (caixinha[linha][coluna] == null) {
                        g.setColor(corCaixinhaVazia);
                        g.fillRoundRect(215 + coluna * 121, 115 + linha * 121, 106, 106, 25, 25);
                    } else {
                        g.setFont(new Font("SansSerif", Font.BOLD, 40));
                        printaCaixinhas(g, linha, coluna);
                    }
                }
            }
        } else {
            g.setColor(corTelaDeInicio);
            g.fillRoundRect(215, 115, 469, 469, 25, 25);

            g.setColor(textos);
            g.setFont(new Font("SansSerif", Font.BOLD, 20));

            switch (statusDoJogo) {
                case "usuarioGanhou": g.drawString("Você Ganhou", 390, 350); break;
                case "usuarioPerdeu": g.drawString("Você Perdeu", 390, 350); break;
                case "inicio":
                    g.setFont(new Font("SansSerif", Font.BOLD, 150));
                    g.drawString("2048", 289, 395);
                    g.setColor(caixaCor);
                    g.setFont(new Font("SansSerif", Font.BOLD, 20));
                    g.drawString("Pressione enter para continuar", 300, 250);
                    g.drawString("As setas para jogar", 355, 450);
                    g.drawString("O mouse para jogar", 355, 480);
                    g.drawString("W,A,S,D para jogar", 357, 510);
                    break;
            }
        }
    }

    public void printaCaixinhas(Graphics2D g, int linha, int coluna) throws IOException {
        
        int value = caixinha[linha][coluna].pegaValor();
        g.setColor(tabelaDeCores[(int) (Math.log(value) / Math.log(2)) + 1]);
        g.fillRoundRect(215 + coluna * 121, 115 + linha * 121, 106, 106, 25, 25);
        String s = String.valueOf(value);

        g.setColor(value < 128 ? tabelaDeCores[0] : tabelaDeCores[1]);

        FontMetrics fm = g.getFontMetrics();
        int asc = fm.getAscent();
        int dec = fm.getDescent();

        int x = 215 + coluna * 121 + (106 - fm.stringWidth(s)) / 2;
        int y = 115 + linha * 121 + (asc + (106 - (asc + dec)) / 2);
        g.drawString(s, x, y);
    }

    // Funcao faz o trabalho exclusivo de adicionar uma caixinha de forma randomica
    public void addCaixinhaRandomica(int a) {

        Random rand = new Random();// Cria a vÃ¡riavel RandÃ´mica
        int flag = 0;// Setamos como 1 a flag e assim o do while para seu processo
        int maxInteracoes = 0;// Controlamos o (do while) para que ele nÃ£o entre em um loop infinito
        
        do {
            int n = rand.nextInt(4);// seta um nÃºmero aleatorio entre 0 e 3 para o N(nsera a posiÃ§Ã£o na matriz)
            int n1 = rand.nextInt(4);// seta outro nÃºmero para a posiÃ§Ã£o na matriz
            int valorRand = rand.nextInt(2);// Gera 1 ou 0 para escolher se o nÃºmero que aparecera sera 2 ou 4

            if (caixinha[n][n1] == null) {
                if(a == 1){
                    if (valorRand == 0) {
                        caixinha[n][n1] = new Caixinha(2);
                        flag = 1;
                    }
                    else if (valorRand == 1) {
                        caixinha[n][n1] = new Caixinha(4);
                        flag = 1;
                    }
                }
                if(a == 2){
                    caixinha[n][n1] = new Caixinha(512);
                }
            } 
            else maxInteracoes += 1;
        } while (flag == 0 && maxInteracoes <= 16);
    }

    public static void main(String[] args){
        
        SwingUtilities.invokeLater(() -> {
            JFrame f = new JFrame();
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setTitle("2048 by Hiago and Marcos");
            f.setResizable(true);
            f.add(new Jogo2048(), BorderLayout.CENTER);
            f.pack();
            f.setLocationRelativeTo(null);
            f.setVisible(true);
        });
    }
}
