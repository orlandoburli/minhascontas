//
//  TokenBe.m
//  Minhas Contas.me
//
//  Created by Orlando Burli on 23/11/14.
//  Copyright (c) 2014 Orlando Burli Projetos de TI Personalizados. All rights reserved.
//

#import "TokenBe.h"
#import "UsuarioBe.h"

@implementation TokenBe

-(void) save: (TokenVo *) token {
    TokenDao *dao = [[TokenDao alloc] init];
    
    if ([dao get:token.idToken]) {
        [dao update:token];
    } else {
        [dao insert:token];
    }
}

- (TokenVo *) getLastToken {
    TokenDao *dao = [[TokenDao alloc] init];
    
    NSArray *lista = [dao getList];
    
    if (lista.count > 0) {
        
        TokenVo *token = lista[0];
        
        token.usuario = [[[UsuarioBe  alloc] init] get:token.idUsuario];
        
        return token;
    }
    
    return nil;
}


@end
