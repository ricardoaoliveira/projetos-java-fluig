# Exemplo de Webservice
---

Este projeto é um exemplo rápido de como fazer execuções webservice SOAP em artefatos publicados na plataforma fluig.

### Passo - 1
Acesse nossa página que explica detalhes de como [**Criar Stubs**](http://tdn.totvs.com/pages/viewpage.action?pageId=73084007#UtilizaçãodeWebservices-CriarStubs)

### Passo - 2
Substitua dentro do projeto em /lib o arquivo **fluig-ws-client.jar** pelo novo arquivo gerado.

### Passo - 4
No arquivo **pom.xml** adicione a vesão do fluig na tag <fluig.version>1.4.12</fluig.version>.

### Passo - 5
Acesse o diretório e execute **mvn validate**

### Passo - 6
Acesse o diretório e execute **mvn clean install**

### Passo - 7
Copie o arquivo em seu projeto **target/sample-soap.war** para a instalação do fluig **{FLUIG_DIR}/jboss/apps/**

### Passo - 8
Acesse o browser e faça uma chamada para o seguinte serviço:

http://{FLUIG}:{PORT}/sample-soap/new-folder?parent={SEU_ID}&user={SEU_USUARIO}&tenantid={SEU_TENANT}&password={SEU_PASSWORD}&foldername={NOME_FOLDER}

Exemplo:

http://seufluig.com/sample-soap/new-folder?parent=0&user=admin&password=adm&tenantid=1&foldername=nova-pasta
