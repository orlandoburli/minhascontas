//
//  TokenVo.h
//  Minhas Contas.me
//
//  Created by Orlando Burli on 14/11/14.
//  Copyright (c) 2014 Orlando Burli Projetos de TI Personalizados. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "UsuarioVo.h"
@interface TokenVo : NSObject

@property NSInteger idToken;
@property NSString *chaveToken;
@property NSInteger idUsuario;
@property NSDate *dataHoraCriacao;
@property NSDate *dataUltimoAcesso;
@property NSString *expirado;
@property UsuarioVo *usuario;

-(id) initWithJsonString: (NSString *) stringJson;

@end
