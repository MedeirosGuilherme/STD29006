package ServerPackage;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Server
{
    public static void main(String[] args) throws IOException
    {

        Server s = new Server();

        HashMap<Integer,Questao> questoes = s.addQuestoes();
        System.out.println(questoes.toString());

        HashMap<String,Aluno> alunos = s.addAlunos();
        System.out.println(alunos.toString());

        try (ServerSocket socket = new ServerSocket(1234))
        {
            System.out.println("Aguardadndo por conexões em:" + socket.getInetAddress() + ":" + socket.getLocalPort());

            try(Socket clientSocket = socket.accept())
            {
                //BufferedReader entrada = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                DataInputStream entrada = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream()));
                DataOutputStream saida = new DataOutputStream(clientSocket.getOutputStream());
                System.out.println("Conectado ao usuário!");

                String usuario = entrada.readUTF();
                System.out.println("Cliente> " + usuario + "\n");

                if(alunos.containsKey(usuario))
                {
                    saida.writeUTF("Bem vindo " + usuario + "! Digite agora sua senha!\n");;
                    saida.flush();
                    String senhaEntrada = entrada.readUTF();
                    if(alunos.get(usuario).getSenha().equals(senhaEntrada))
                    {
                        saida.writeUTF("Senha correta, bem vindo! Segue à seguir o seu questionário! Digite SAIR quando quiser deixar o programa!\n");
                        saida.flush();

                        int questionCounter = alunos.get(usuario).getCorretas();

                        while(true)
                        {
                            System.out.println("Corretas: " + questionCounter);
                            Questao atual = questoes.get(questionCounter);

                            saida.writeUTF(atual.toString());
                            saida.flush();

                            String resposta = entrada.readUTF();
                            System.out.println("Reposta: " + resposta);

                            if(atual.validar(resposta))
                            {
                                System.out.println("Questão certa!");
                                saida.writeUTF("Questão correta!\n");
                                saida.flush();
                                questionCounter++;
                            }

                            else
                            {
                                if(resposta.equals("SAIR"))
                                {
                                    saida.writeUTF("SAIR");
                                    saida.flush();
                                    clientSocket.close();
                                    break;
                                }

                                System.out.println("Questão errada!");
                                saida.writeUTF("Questão incorreta!\n");
                                saida.flush();
                            }

                        }

                    }
                    else
                    {
                        saida.writeUTF("Senha incorreta! Reinicie a aplicação.");
                        saida.flush();
                        clientSocket.close();
                    }

                }
                else{
                    saida.writeBytes("Usuário não encontrado! Inicie novamente a aplicação!");
                    saida.flush();
                    clientSocket.close();
                }
            }
            catch(Exception e)
            {
                System.out.println(e.toString());
            }

        }
        catch(Exception e)
        {
            System.out.println(e.toString());
        }
    }

    public HashMap<String,Aluno> addAlunos() throws IOException
    {

        HashMap<String,Aluno> alunos = new HashMap<String,Aluno>();

        ArrayList<String> listAlunos = new ArrayList<String>();
        listAlunos.add("guilherme,12345678,0");
        listAlunos.add("emerson,12345678,0");
        listAlunos.add("pedro,12345678,0");
        listAlunos.add("jefferson,87654321,0");

        try {

            String line;
            int i = 0;
            while (i<3) {
                line = listAlunos.get(i);
                String[] divide = line.split(",");
                Aluno aluno = new Aluno(divide[0], divide[1], Integer.parseInt(divide[2]));
                alunos.put(divide[0], aluno);
                i++;
            }
        } catch (Exception e){
            System.out.println("Erro aqui, problema ler arquivo" + e.toString());
        }

        return alunos;
    }

    public HashMap<Integer,Questao> addQuestoes() throws IOException
    {
        HashMap<Integer,Questao> questoes = new HashMap<Integer,Questao>();

        ArrayList<String> listQuestao = new ArrayList<String>();
        listQuestao.add("Um Stateless Server é um:,a) Servidor que não mantém a conexão com o cliente,b) Servidor que mantém informações sobre o cliente após fechar a conexão,c) Servidor incapaz de manter multiplas conexões,d) Servidor em manutenção,b");
        listQuestao.add("Mensagens são transmitidas como fluxos de bytes. A transmissão feita pela rede requer um acordo prévio entre cliente e servidor para que ambos possam representar os dados corretamente em seus ambientes. Máquinas distintas podem ter diferenças nos seguintes itens menos:,a)Na ordenação de bytes,b)Na quantidade de bytes para representar inteiros,c)Na codificação de caracteres,d)Nos protocolos que regem as comunicações,d");
        listQuestao.add("São semelhanças entre RPC e RMI exceto:, a)Possibilitam usar POO,b) Fazem uso de IDL,c) São construído sobre protocolos de pedido e resposta,d) Oferecem o mesmo nível de trasnparência ao desenvolvedor,a");
        listQuestao.add("Qual dos seguintes itens representa uma interface básica para sistemas de filas de mensagem?,a) ACK,b) POLL,c) TIMEOUT,d) ENQUEUE,b");
        listQuestao.add("Quando tratamos de um sistema ACID pode-se definir uma transação Atômica?,a) Toda ação deve levar ao próximo estado consistente,b) Toda transação deve ser executada como um todo ou não executada completamente,c) Transações podem ter acessos recorrentes que não interfiram entre si,d) Informações modificadas devem ser persistidas permanentemente,b");

        String line;
        int questionCounter = 0;
        while (questionCounter<5) {
            line = listQuestao.get(questionCounter);
            String[] divide = line.split(",");
            Questao q = new Questao(divide[0], divide[1], divide[2], divide[3], divide[4], divide[5]);
            questoes.put(questionCounter, q);
            questionCounter++;
        }
        return questoes;
    }
}