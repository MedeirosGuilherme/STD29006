package ServerPackage;

public class Aluno
{
    private String login;
    private String senha;
    private int questCertas;


    public Aluno(String login, String senha, int questCertas)
    {
        this.login = login;
        this.senha = senha;
        this.questCertas = questCertas;
    }

    public void setQuestCertas(int newQuestCertas)
    {
        this.questCertas = newQuestCertas;
    }

    public int getCorretas()
    {
        return this.questCertas;
    }

    public String getSenha()
    {
        return this.senha;
    }


}
