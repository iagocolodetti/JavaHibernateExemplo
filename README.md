# JavaHibernateExemplo

Projeto simples desenvolvido no NetBeans utilizando a linguagem Java fazendo integração com o banco de dados MySQL para demonstrar o uso do Framework Hibernate.

As operações exemplificadas nesse projeto são as de: inserção, leitura, alteração e remoção (CRUD) de contatos no banco de dados MySQL.

* Downloads: https://github.com/iagocolodetti/JavaHibernateExemplo/releases
   * [Arquivo de Script MySQL](https://github.com/iagocolodetti/JavaHibernateExemplo/releases/download/v1.1/contatodb.sql "contatodb.sql")
   * [Bibliotecas Necessárias](https://github.com/iagocolodetti/JavaHibernateExemplo/releases/download/v1.1/bibliotecas.zip "bibliotecas.zip")
   * [Código-Fonte](https://github.com/iagocolodetti/JavaHibernateExemplo/archive/v1.1.zip "v1.1.zip")



# Adicionando arquivos necessários para usar o Hibernate

* Novo -> Outros -> Hibernate
   * Adicionar: "Assistente de Configuração do Hibernate"
   * Adicionar: "HibernateUtil.java"


# Configurando o "hibernate.cfg.xml" usando a aba "Design"
* Propriedades de JDBC
   * "hibernate.connection.driver_class" usar valor correspondente ao driver de conexão, aqui: com.mysql.jdbc.Driver
   * "hibernate.connection.url" usar como valor a url de acesso ao banco, aqui: jdbc:mysql://localhost:3306/contatodb
   * "hibernate.connection.username" usar como valor seu nome de usuário para acesso ao banco.
   * "hibernate.connection.password" usar como valor sua senha para acesso ao banco.
   
* Mapeamento: Adicionar a classe Entidade, aqui: Contato

* Propriedades Diversas: Adicionar a propriedade "hibernate.hbm2ddl.auto" valor "update"


**Código-fonte do arquivo:**
```<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE hibernate-configuration PUBLIC "-//Hibernate/Hibernate Configuration DTD 3.0//EN" "http://hibernate.sourceforge.net/hibernate-configuration-3.0.dtd">
<hibernate-configuration>
  <session-factory>
    <property name="hibernate.hbm2ddl.auto">update</property>
    <property name="hibernate.connection.driver_class">com.mysql.jdbc.Driver</property>
    <property name="hibernate.connection.url">jdbc:mysql://localhost:3306/contatodb</property>
    <property name="hibernate.connection.username">root</property>
    <property name="hibernate.connection.password">root</property>
    <mapping class="Contato"/>
  </session-factory>
</hibernate-configuration>
```