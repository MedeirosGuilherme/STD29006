package ServerPackage;
import java.util.ArrayList;

public class Questao
{

    private String alternativaA;
    private String alternativaB;
    private String alternativaC;
    private String alternativaD;

    private String enunciado;
    private String alternativaCerta;

    public Questao(String enunciado, String alternativaA, String alternativaB, String alternativaC, String alternativaD, String alternativaCerta)
    {
        this.alternativaA = alternativaA;
        this.alternativaB = alternativaB;
        this.alternativaC = alternativaC;
        this.alternativaD = alternativaD;
        this.alternativaCerta = alternativaCerta;

        ArrayList<String> alternativas = new ArrayList<String>();
        alternativas.add(this.alternativaA);
        alternativas.add(this.alternativaB);
        alternativas.add(this.alternativaC);
        alternativas.add(this.alternativaD);

        this.enunciado = enunciado;
    }

    public boolean validar(String resposta)
    {
        System.out.println("Reposta certa: " + this.alternativaCerta);
        if(resposta.equals(this.alternativaCerta))
        {
            return true;
        }

        return false;
    }

    public String toString()
    {
        String str = new String();
        str += this.enunciado + "    " + this.alternativaA + "    " + this.alternativaB + "    " + this.alternativaC + "    " + this.alternativaD + "\n";

        return str;
    }

}
