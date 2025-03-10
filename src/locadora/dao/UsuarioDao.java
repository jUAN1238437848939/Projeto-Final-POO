package locadora.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import locadora.model.Usuario;

public class UsuarioDao {

    private static final String CAMINHO_ARQUIVO = "usuarios.json"; // Caminho para o arquivo JSON
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create(); // Configuração do Gson

    // Método para salvar um usuário em arquivo JSON
    public void salvarEmArquivo(Usuario usuario) {
        try (FileWriter writer = new FileWriter(CAMINHO_ARQUIVO, true)) { // O 'true' indica que iremos adicionar no final do arquivo
            gson.toJson(usuario, writer); 
            writer.write("\n"); 
            System.out.println("Usuário salvo no arquivo JSON com sucesso.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao salvar o usuário no arquivo.");
        }
    }
}

