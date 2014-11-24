//
//  TokenVo.m
//  Minhas Contas.me
//
//  Created by Orlando Burli on 14/11/14.
//  Copyright (c) 2014 Orlando Burli Projetos de TI Personalizados. All rights reserved.
//

#import "TokenVo.h"

@implementation TokenVo

@synthesize idToken;
@synthesize chaveToken;
@synthesize idUsuario;
@synthesize dataHoraCriacao;
@synthesize dataUltimoAcesso;
@synthesize expirado;
@synthesize usuario;

-(id) initWithJsonString: (NSString *) stringJson {
    
    self = [super init];
    
    if (!self) {
        return nil;
    }
    
    // Parse do json recebido
    NSData *dataJson = [stringJson dataUsingEncoding:NSUTF8StringEncoding];
    
    id json = [NSJSONSerialization JSONObjectWithData:dataJson options:0 error:nil];
    
    self.idToken = [[json objectForKey:@"idToken"] integerValue];
    self.chaveToken = [json objectForKey:@"chaveToken"];
    self.idUsuario = [[json objectForKey:@"idUsuario"] integerValue];
    
    // Formato padrao do Json utilizado
    NSDateFormatter *formatter = [[NSDateFormatter alloc] init];
    [formatter setDateFormat:@"dd/MM/yyyy hh24:mm:ss"];

    self.dataHoraCriacao = [formatter dateFromString:[json objectForKey:@"dataHoraCriacao"]];
    self.dataUltimoAcesso = [formatter dateFromString:[json objectForKey:@"dataUltimoAcesso"]];
    self.expirado = [json objectForKey:@"expirado"];
    
    NSDictionary *jsonUsuario = [json objectForKey:@"usuario"];
    
    NSData *jsonData = [NSJSONSerialization dataWithJSONObject:jsonUsuario options:NSJSONWritingPrettyPrinted                                                    error:nil];
    
    NSString *stringUsuario = [[NSString alloc] initWithData:jsonData encoding:NSUTF8StringEncoding];
    
    self.usuario = [[UsuarioVo alloc] initWithJsonString: stringUsuario];
    
    return self;
}


@end
