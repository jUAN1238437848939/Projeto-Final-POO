package locadora.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import locadora.model.Usuario;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDao {

    private static final String CAMINHO_ARQUIVO = "usuarios.json"; // Caminho para o arquivo JSON
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create(); // Configuração do Gson

    // Método para salvar um usuário em arquivo JSON
    public void salvarEmArquivo(Usuario usuario) {
        try {
            // Carregar os usuários existentes do arquivo
            List<Usuario> usuarios = carregarUsuarios();

            // Adicionar o novo usuário
            usuarios.add(usuario);

            // Salvar a lista atualizada de usuários no arquivo
            try (FileWriter writer = new FileWriter(CAMINHO_ARQUIVO)) { // Sobrescreve o arquivo com a lista atualizada
                gson.toJson(usuarios, writer);
                System.out.println("Usuário salvo no arquivo JSON com sucesso.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao salvar o usuário no arquivo.");
        }
    }

    // Método para carregar os usuários do arquivo JSON
    private List<Usuario> carregarUsuarios() {
        try {
            File file = new File(CAMINHO_ARQUIVO);
            if (!file.exists()) {
                return new ArrayList<>(); // Retorna uma lista vazia caso o arquivo não exista
            }

            // Lê o conteúdo do arquivo e converte para lista de usuários
            BufferedReader reader = new BufferedReader(new FileReader(file));
            Type type = new TypeToken<List<Usuario>>(){}.getType();
            List<Usuario> usuarios = gson.fromJson(reader, type);
            reader.close();
            return usuarios;
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>(); // Retorna uma lista vazia caso ocorra algum erro
        }
    }
}
