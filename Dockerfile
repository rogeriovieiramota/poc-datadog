# ============================
# 1) Build da aplicação
# ============================
FROM maven:3.9.6-eclipse-temurin-21 AS builder

WORKDIR /app

# Copia o pom.xml e baixa dependências (cache)
COPY pom.xml .
RUN mvn -q dependency:go-offline

# Copia o código-fonte
COPY src ./src

# Compila e empacota
RUN mvn -q clean package -DskipTests


# ============================
# 2) Runtime (imagem leve)
# ============================
FROM eclipse-temurin:21-jre-alpine AS runtime

WORKDIR /app

# Copia o JAR gerado
COPY --from=builder /app/target/*.jar app.jar

# Copia o Datadog Java Agent (você deve colocar o arquivo na raiz do projeto)

# COPY libs/dd-java-agent.jar /app/dd-java-agent.jar
COPY dd-java-agent.jar /app/dd-java-agent.jar

# Variáveis de ambiente Datadog
ENV DD_SERVICE=pessoaDataDogServiceApp
ENV DD_ENV=local
ENV DD_VERSION=1.0.0
ENV DD_LOGS_INJECTION=true
ENV DD_TRACE_ENABLED=true
ENV DD_PROFILING_ENABLED=true
ENV DD_RUNTIME_METRICS_ENABLED=true


# Porta da aplicação
EXPOSE 8080

# Comando de inicialização com Datadog Agent
ENTRYPOINT ["java", "-javaagent:/app/dd-java-agent.jar", "-jar", "app.jar"]
