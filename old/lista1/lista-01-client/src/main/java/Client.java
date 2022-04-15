import java.io.BufferedReader;
import java.io.IOException;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args)
    {
        int servidorPorta = 1234;
        String servidorIP = "127.0.0.1";

        try(Socket conexao = new Socket(servidorIP, servidorPorta))
        {
            System.out.println("Conectado" + conexao + "\n");
            System.out.println("Bem vindo ao sistema de questões! Digite seu login para continuar: ");

            DataOutputStream saida = new DataOutputStream(conexao.getOutputStream());
            BufferedReader entrada = new BufferedReader(new InputStreamReader(conexao.getInputStream()));

            Scanner keyboard = new Scanner(System.in);
            String comunicacaoIn = "";
            String comunicacaoOut = "";


            //Autenticação
            comunicacaoOut = keyboard.nextLine();
            saida.writeUTF(comunicacaoOut);
            saida.flush();

            comunicacaoIn = entrada.readLine();
            System.out.println(comunicacaoIn);

            comunicacaoOut = keyboard.nextLine();
            saida.writeUTF(comunicacaoOut);
            saida.flush();

            comunicacaoIn = entrada.readLine();
            System.out.println(comunicacaoIn);

            //Questões:

            boolean loop = true;
            while(loop)
            {
                comunicacaoIn = entrada.readLine();
                System.out.println(comunicacaoIn);

                comunicacaoOut = keyboard.nextLine();
                saida.writeUTF(comunicacaoOut);
                saida.flush();

                comunicacaoIn = entrada.readLine();
                System.out.println(comunicacaoIn);

                if(comunicacaoOut.equals("SAIR"))
                {
                    System.out.println("Saindo do questionário... Gravando as questões respondidas...");
                    conexao.close();
                    break;
                }
            }

            keyboard.close();
        }
        catch(Exception e)
        {
            System.err.println(e.toString());
        }

    }
}
