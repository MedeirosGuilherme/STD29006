FROM ubuntu:latest

RUN apt-get update && apt-get install -y figlet
COPY mensagem.txt /

CMD cat /mensagem.txt | figlet