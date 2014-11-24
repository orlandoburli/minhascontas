//
//  RetornoVo.h
//  Minhas Contas.me
//
//  Created by Orlando Burli on 14/11/14.
//  Copyright (c) 2014 Orlando Burli Projetos de TI Personalizados. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "TokenVo.h"

@interface RetornoVo : NSObject

@property Boolean sucesso;
@property NSString *mensagem;
@property NSString *fieldFocus;
@property NSString *codigoRetorno;
@property NSString *original;
@property NSDictionary *objeto;
@property TokenVo *token;

-(id) initWithJsonString: (NSString *) json;

-(NSString *) objetoRetornoJson;

@end
