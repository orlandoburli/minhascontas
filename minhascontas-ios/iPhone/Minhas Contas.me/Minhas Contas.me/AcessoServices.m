//
//  AcessoServices.m
//  Minhas Contas.me
//
//  Created by Orlando Burli on 14/11/14.
//  Copyright (c) 2014 Orlando Burli Projetos de TI Personalizados. All rights reserved.
//

#import "AcessoServices.h"
#import "RetornoVo.h"
#import "TokenVo.h"

@implementation AcessoServices

// Funcão para criar usuário. Consome o webservice criarUsuario.
-(RetornoVo *) criarUsuario:( NSString *)nome withSobrenome: (NSString *) sobrenome withEmail: (NSString *) email withSenha: (NSString *) senha {

    NSString *post = [NSString stringWithFormat:@"&nomeUsuario=%@&email=%@&sobreNomeUsuario=%@&senha=%@",nome, email, sobrenome, senha];
    
    NSString *retorno = [self sendPostData:post withService:@"/acesso/criar"];
    
    return [[RetornoVo alloc] initWithJsonString: retorno];
}

// Função de login.
- (RetornoVo *) login : (NSString *)email withSenha: (NSString *) senha withSenhaMD5: (bool) senhaMD5 {
    NSString *post = [NSString stringWithFormat:@"&email=%@&senha=%@&senhaMD5=%@", email, senha, senhaMD5 ? @"true" : @"false"];
    
    NSString *retorno = [self sendPostData:post withService:@"/acesso/login"];
    
    RetornoVo *retornoVo = [[RetornoVo alloc] initWithJsonString: retorno];
    
    if (retornoVo.sucesso) {
        // Tenta dar um parser no Token
        NSLog(@"Objeto Token: %@",  [retornoVo objetoRetornoJson]);
        TokenVo *token = [[TokenVo alloc] initWithJsonString:[retornoVo objetoRetornoJson]];
        retornoVo.token = token;
    }
    
    return retornoVo;
}

@end
