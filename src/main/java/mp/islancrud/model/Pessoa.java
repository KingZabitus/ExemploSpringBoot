package mp.islancrud.model;


import jakarta.persistence.*;

@Entity
public class Pessoa {

    @Id
    @Column(nullable = false, unique = true, length = 11)
    private long cpf;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = true)
    private String email;

    public long getCpf() {
        return cpf;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
