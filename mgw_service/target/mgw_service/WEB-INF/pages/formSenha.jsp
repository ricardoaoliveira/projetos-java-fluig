<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Atento</title>
    
    <link rel="stylesheet" type="text/css" href="/portal/resources/style-guide/css/fluig-style-guide.min.css">
    <link type="text/css" rel="stylesheet" href="/portal/resources/css/login-fluig.css">
</head>
<body>
    <div id="loginContent">
        <div class="wrapper">
            <div class="logo left">
                <img src="/files_atento/resources/images/logo_login.png" alt="Atento">
            </div>
            
            <div class="login-form right" style="height:initial;margin:50px 0;">
                <div class="title">
                    <h1><fmt:message key="atualizar_senha" /></h1>
                </div>
                <div class="fields">
                    <form action="/GestaoAcessoUsuarios/AlterarSenha" method="post">
                    
                        <!-- Recupera a mensagem de erro -->
                        <c:if test="${param.cdMsg ne null}">
                        <div id="msg"><fmt:message key="motivo_alteracao_senha_${param.cdMsg}" /></div>
                        </c:if>
                        <c:if test="${requestScope.MSG_ERRO ne null}">
                        <div id="msg">${requestScope.MSG_ERRO}</div>
                        </c:if>
                        
                        <!-- Imprime a mensagem de erro -->
                        
                    
                        <span id="login">
                            <label for="senhaAtual"><fmt:message key="senha_atual" />:</label>
                            <input type="password" name="senhaAtual" id="senhaAtual">
                            
                            <label for="novaSenha"><fmt:message key="nova_senha" />:</label>
                            <input type="password" name="novaSenha" id="novaSenha">
                            
                            <label for="confirmacaoNovaSenha"><fmt:message key="confirmar_nova_senha" />:</label>
                            <input type="password" name="confirmacaoNovaSenha" id="confirmacaoNovaSenha">
                            
                            <input type="submit" class="button" value="<fmt:message key="confirmar" />">
                        </span>
                        <div>
                            <input type="hidden" name="token" value="${param.token}">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <script src="/portal/resources/js/jquery/jquery.js"></script>
    <script src="/portal/resources/js/jquery/jquery-ui.min.js"></script>
    <script src="/portal/resources/style-guide/js/fluig-style-guide.min.js"></script>
</body>
</html>