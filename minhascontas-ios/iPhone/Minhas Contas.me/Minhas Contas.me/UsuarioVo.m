//
//  UsuarioVo.m
//  Minhas Contas.me
//
//  Created by Orlando Burli on 15/11/14.
//  Copyright (c) 2014 Orlando Burli Projetos de TI Personalizados. All rights reserved.
//

#import "UsuarioVo.h"

@implementation UsuarioVo

@synthesize idUsuario;
@synthesize nomeUsuario;
@synthesize sobreNomeUsuario;
@synthesize tipoAutenticacao;
@synthesize email;
@synthesize idFacebook;
@synthesize senha;
@synthesize flagConfirmado;
@synthesize pathFoto;
@synthesize hashEmail;

-(id) initWithJsonString: (NSString *) stringJson {
    
    self = [super init];
    
    if (!self) {
        return nil;
    }
    
    // Parse do json recebido
    NSData *dataJson = [stringJson dataUsingEncoding:NSUTF8StringEncoding];
    
    id json = [NSJSONSerialization JSONObjectWithData:dataJson options:0 error:nil];
    
    self.idUsuario = [[json objectForKey:@"idUsuario"] integerValue];
    self.nomeUsuario = [json objectForKey:@"nomeUsuario"];
    self.sobreNomeUsuario = [json objectForKey:@"sobreNomeUsuario"];
    self.tipoAutenticacao = [json objectForKey:@"tipoAutenticacao"];
    self.email = [json objectForKey:@"email"];
    self.idFacebook = [json objectForKey:@"idFacebook"];
    self.senha = [json objectForKey:@"senha"];
    self.flagConfirmado = [json objectForKey:@"flagConfirmado"];
    self.pathFoto = [json objectForKey:@"pathFoto"];
    self.hashEmail = [json objectForKey:@"hashEmail"];
    
    return self;

}

@end
