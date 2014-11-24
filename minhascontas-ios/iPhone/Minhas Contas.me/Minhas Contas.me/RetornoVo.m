//
//  RetornoVo.m
//  Minhas Contas.me
//
//  Created by Orlando Burli on 14/11/14.
//  Copyright (c) 2014 Orlando Burli Projetos de TI Personalizados. All rights reserved.
//

#import "RetornoVo.h"

@implementation RetornoVo

@synthesize sucesso;
@synthesize mensagem;
@synthesize fieldFocus;
@synthesize codigoRetorno;
@synthesize original;
@synthesize objeto;
@synthesize token;

-(id) initWithJsonString: (NSString *) stringJson {
    
    self = [super init];
    
    if (!self) {
        return nil;
    }
    self.original = stringJson;
    
    // Parse do json recebido
    NSData *dataJson = [stringJson dataUsingEncoding:NSUTF8StringEncoding];
    
    id json = [NSJSONSerialization JSONObjectWithData:dataJson options:0 error:nil];
    
    self.sucesso = [[json objectForKey:@"sucesso"] boolValue];
    self.mensagem = [json objectForKey:@"mensagem"];
    self.fieldFocus = [json objectForKey:@"fieldFocus"];
    self.codigoRetorno = [json objectForKey:@"codigoRetorno"];
    self.objeto = [json objectForKey:@"objeto"];
    
    return self;
}

-(NSString *) objetoRetornoJson {
    NSError *error;
    NSData *jsonData = [NSJSONSerialization dataWithJSONObject:self.objeto options:NSJSONWritingPrettyPrinted                                                    error:&error];
    NSString *retorno = [[NSString alloc] initWithData:jsonData encoding:NSUTF8StringEncoding];

    return retorno;
}


@end
