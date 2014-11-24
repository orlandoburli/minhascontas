//
//  AppDictionary.h
//  Minhas Contas.me
//
//  Created by Orlando Burli on 19/11/14.
//  Copyright (c) 2014 Orlando Burli Projetos de TI Personalizados. All rights reserved.
//

#ifndef Minhas_Contas_me_AppDictionary_h
#define Minhas_Contas_me_AppDictionary_h

#ifdef __OBJC__

static int const versaoSistema = 1;
static int const versaoBancoDeDados = 1;

// Definicoes de dicionario de USUARIO

static NSString *const tabelaUsuario = @"USUARIO";

static NSString *const campoUsuarioIdUsuario = @"ID_USUARIO";
static NSString *const campoUsuarioNomeUsuario = @"NOME_USUARIO";
static NSString *const campoUsuarioSobreNomeUsuario = @"SOBRENOME_USUARIO";
static NSString *const campoUsuarioTipoAutenticacao = @"TIPO_AUTENTICACAO";
static NSString *const campoUsuarioEmail = @"EMAIL";
static NSString *const campoUsuarioIdFacebook = @"ID_FACEBOOK";
static NSString *const campoUsuarioSenha = @"SENHA";
static NSString *const campoUsuarioFlagConfirmado = @"FLAG_CONFIRMADO";
static NSString *const campoUsuarioPathFoto = @"PATH_FOTO";
static NSString *const campoUsuarioHashEmail = @"HASH_EMAIL";

// Definicoes de dicionario de TOKEN

static NSString *const tabelaToken = @"TOKEN";

static NSString *const campoTokenIdToken = @"ID_TOKEN";
static NSString *const campoTokenChaveToken = @"CHAVE_TOKEN";
static NSString *const campoTokenIdUsuario = @"ID_USUARIO";
static NSString *const campoTokenDataHoraCriacao = @"DATA_HORA_CRIACAO";
static NSString *const campoTokenDataUltimoAcesso = @"DATA_ULTIMO_ACESSO";
static NSString *const campoTokenExpirado = @"EXPIRADO";

// Definicoes de dicionario de CATEGORIA

static NSString *const tabelaCategoria = @"CATEGORIA";

static NSString *const campoCategoriaIdCategoria = @"ID_CATEGORIA";
static NSString *const campoCategoriaNomeCategoria = @"NOME_CATEGORIA";
static NSString *const campoCategoriaTipoCategoria = @"TIPO_CATEGORIA";
static NSString *const campoCategoriaIdCategoriaPai = @"ID_CATEGORIA_PAI";
static NSString *const campoCategoriaIdCategoriaReferencia = @"ID_CATEGORIA_REFERENCIA";
static NSString *const campoCategoriaSincronizado = @"SINCRONIZADO";


#endif
#endif
