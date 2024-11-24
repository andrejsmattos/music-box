# Music Box Application 🎵

[![Java](https://img.shields.io/badge/Java-21-red.svg)](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.5-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-42.7.4-blue.svg)](https://www.postgresql.org/)
[![Maven](https://img.shields.io/badge/Maven-3.6%2B-orange.svg)](https://maven.apache.org/)


## 📋 Descrição

Music Box é uma aplicação Back-End console-based, desenvolvida em Java e Spring Boot. O sistema gerencia artistas e músicas com o uso da API Last.fm, com funcionalidades adicionais de tradução automática de biografias via API MyMemory.

## ✨ Funcionalidades

- Cadastro de artistas e músicas
- Listagem de artistas e músicas cadastradas
- Pesquisa de biografias de artistas com tradução automática
- Integração com a API Last.fm para obter informações sobre artistas e músicas
- Integração com a API MyMemory para tradução de textos

## 🚀 Tecnologias Utilizadas

![Java](https://img.shields.io/badge/Java-ED8B00?style=flat&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=flat&logo=spring&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=flat&logo=apache-maven&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-336791?style=flat&logo=postgresql&logoColor=white)
![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=flat&logo=hibernate&logoColor=white)
![Jackson](https://img.shields.io/badge/Jackson-000000?style=flat&logo=json&logoColor=white)

## 📁 Estrutura do Projeto

```
src/main/java/br/com/andrejsmattos/music_box
├── entities
│   ├── Artista.java
│   ├── Musica.java
│   └── Album.java
├── exceptions
│   └── ConversaoJsonException.java
├── principal 
│   └── Principal.java
├── repositories
│   ├── ArtistaRepository.java
│   ├── MusicaRepository.java
│   └── AlbumRepository.java
├── services
│   ├── ConsumoApi.java
│   ├── ConverteDados.java
│   └── IConverteDados.java
└── utils
    ├── FormatadorDuracao
    ├── FormatadorNumero
    └── traducao
        ├── DeletaLinksHtml.java
        ├── ConsultaMyMemory.java
        ├── DadosResposta.java
        └── DadosTraducao.java
```

## ⚙️ Configuração

### Pré-requisitos

- ☕ Java 17 ou superior
- 🔧 Maven 3.6 ou superior
- 🗃️ MySQL

### Configuração do Banco de Dados

Crie um banco de dados MySQL e configure as credenciais no arquivo `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost/music_box
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### Variáveis de Ambiente

Configure as seguintes variáveis de ambiente:

```shell
MY_MEMORY_API_KEY=sua_chave_mymemory
LAST_FM_API_KEY=sua_chave_lastfm
```

### 🔨 Compilação e Execução

```bash
# Compilar o projeto
mvn clean install

# Executar a aplicação
mvn spring-boot:run
```

## 📖 Uso

### Cadastro de Artistas

Para cadastrar um novo artista, siga as instruções no menu da aplicação. Você precisará fornecer:
- Nome do artista
- Tipo (solo, dupla ou banda)
- URL
- Número de ouvintes
- Total de reproduções
- Resumo

### Listagem de Artistas

A aplicação permite listar todos os artistas cadastrados, ordenados pelo total de reproduções.

### Pesquisa de Biografias

Você pode pesquisar a biografia de um artista específico. A biografia será traduzida automaticamente utilizando a API MyMemory.

### 🎵 Integração com a API Last.fm

A aplicação utiliza a API Last.fm para obter informações detalhadas sobre artistas e músicas. Para isso:

1. Configure a variável de ambiente `LAST_FM_API_KEY`
2. As informações obtidas incluem:
   - Biografia
   - Número de ouvintes
   - Total de reproduções
   - Gênero musical
   - Duração da música
