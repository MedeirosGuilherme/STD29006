# usando uma imagem do python3.9 sobre o linux Alpine

FROM python:3.10-alpine

# Diretório de trabalho para os comandos RUN, CMD, COPY, ADD, ENTRYPOINT

WORKDIR /www

# Porta que será exposta ao host

EXPOSE 80

# comando que será executado durante o 'docker run'

CMD ["python3", "-m", "http.server", "80"]