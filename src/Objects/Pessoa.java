
package Objects;
import java.util.Random;

/**
 *
 * @author Pedro
 */
public class Pessoa {
    protected int idade;
    protected String nome;
    protected static final String alfabeto = "ABCDEFGHIJKLMNOPRSTUVXWYZ";
    protected Random geradorRandom = new Random();
    
    public Pessoa(){
        
    };
    
    public Pessoa(int idade, String nome){
        this.idade = idade;
        this.nome = nome;
    }
    
    public Pessoa generateRandomPessoa(){
        return new Pessoa(geradorRandom.nextInt(100), geraString(6));
    }
    
    public static String geraString(int n){
        StringBuilder sb = new StringBuilder(n);
  
        for (int i = 0; i < n; i++) {
  
            int index
                = (int)(alfabeto.length()
                        * Math.random());
  
            sb.append(alfabeto.charAt(index));
        }
  
        return sb.toString();
    }
    
    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "("+this.nome+","+this.idade+")";
    }
    
    
    
}
