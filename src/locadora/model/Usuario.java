package locadora.model;

public class Usuario {
    private String nome;
    private String perfil;

    public Usuario(String nome, String perfil) {
        this.nome = nome;
        this.perfil = perfil;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    @Override
    public String toString() {
        return "Usuário: " + nome + " | Perfil: " + perfil;
    }
}
