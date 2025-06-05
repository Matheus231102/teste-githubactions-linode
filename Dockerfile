FROM maven:3.9.4-eclipse-temurin-17 AS build

WORKDIR /appteste

# Copia o pom.xml e baixa as dependências (cache) e passa o código fonte para dentro do containeró
COPY pom.xml .
COPY src ./src

RUN mvn dependency:go-offline

# Build do projeto (gera o WAR)
RUN mvn clean package -DskipTests

# Stage 2: Imagem Payara Full para rodar a aplicação
FROM payara/server-full:latest

# Copia o WAR gerado no stage anterior para o diretório de deploy do Payara
COPY --from=build /appteste/target/aplicacaoteste.war $DEPLOY_DIR

# Expõe a porta 8080
EXPOSE 8080 4848

CMD ["asadmin", "start-domain", "--verbose"]
