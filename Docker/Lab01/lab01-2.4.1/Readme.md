# Experimento 2.4.1

Criaremos uma imagem baseada no Ubuntu 20.04 LTS. Nessa instalaremos o aplicativo figlet e
depois copiaremos o arquivo mensagem.txt, que está na máquina host, para dentro da imagem.

1. Criando diretório e arquivo com a mensagem
mkdir std && cd std
echo "Sistemas Distribuídos" > mensagem.txt
IFSC – C AMPUS S ÃO J OSÉ
Página 42. Criando arquivo Dockerfile

## Nome da imagem que servirá de base
FROM ubuntu:latest

## Comandos que serão executados durante o 'docker build'
RUN apt-get update && apt-get install -y figlet && rm -rf /var/lib/apt/lists/*
COPY mensagem.txt /

## Comando que será executado durante o 'docker run'
CMD cat /mensagem.txt | figlet

3. Gerando a imagem a partir do Dockerfile
docker build -t stdfiglet .

4. Executando um contêiner a partir da imagem criada
docker run --rm --name segundo stdfiglet