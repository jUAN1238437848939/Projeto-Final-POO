package locadora.model;

public class Usuario {
    private String nome;
    private String login;
    private String senha;
    private String cargo; 

    // Construtor com os quatro parâmetros
    public Usuario(String nome, String login, String senha, String cargo) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.cargo = cargo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    // Método para pegar o perfil, que é o mesmo que cargo
    public String getPerfil() {
        return this.cargo;
    }

    @Override
    public String toString() {
        return "Usuário: " + nome + " | Cargo: " + cargo;
    }
}
