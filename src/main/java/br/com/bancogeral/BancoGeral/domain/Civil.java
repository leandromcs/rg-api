package br.com.bancogeral.BancoGeral.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
@Entity
@Table(name = "tb_registro_geral")
public class Civil implements Comparable<Civil>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "registro_geral")
    private String rg;

    @Column(name = "nme_nome")
    private String nome;

    @Column(name = "dta_nascimento")
    private Date dataNascimento;

    @Column(name = "nme_pai")
    private String nomePai;

    @Column(name = "nme_mae")
    private String nomeMae;

    @Column(name = "dta_registro")
    private Date dataRegistro;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getNomePai() {
        return nomePai;
    }

    public void setNomePai(String nomePai) {
        this.nomePai = nomePai;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public Date getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(Date dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    @Override
    public int compareTo(Civil c) {
        if(this.dataRegistro.before(c.dataRegistro)){
            return -1;
        }
        if(this.dataRegistro.after(c.dataRegistro)){
            return 1;
        }
        return 0;
    }
}
