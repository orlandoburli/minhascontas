//
//  UsuarioBe.m
//  Minhas Contas.me
//
//  Created by Orlando Burli on 19/11/14.
//  Copyright (c) 2014 Orlando Burli Projetos de TI Personalizados. All rights reserved.
//

#import "UsuarioBe.h"


@implementation UsuarioBe

-(void) save : (UsuarioVo *) usuario {
    UsuarioDao *dao = [[UsuarioDao alloc] init];
    
    NSLog(@"Iniciando save...");
    
    if ([dao get:usuario.idUsuario]) {
        NSLog(@"Update usuário...");
        [dao update:usuario];
    } else {
        NSLog(@"Insert usuário...");
        [dao insert:usuario];
    }
}

// Retorna o ultimo usuário que fez login.
-(UsuarioVo *) getLastLogin {
    // TODO - Este metod nao faz exatamente ainda o que se propoe.
    // Preciso terminar o Dao e Be do Token para isto funcionar corretamente.
    
    UsuarioDao *dao = [[UsuarioDao alloc] init];

    NSArray *lista = [dao getList];
    
    if (lista.count > 0) {
        return lista[0];
    }
    
    NSLog(@"Não encontrado nenhum login!!");
    
    return nil;
}

-(UsuarioVo *) get: (NSInteger) idUsuario {
    UsuarioDao *dao = [[UsuarioDao alloc] init];
    
    return [dao get:idUsuario];
}

@end
