//
//  AcessoServices.h
//  Minhas Contas.me
//
//  Created by Orlando Burli on 14/11/14.
//  Copyright (c) 2014 Orlando Burli Projetos de TI Personalizados. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "BaseServices.h"
#import "RetornoVo.h"

@interface AcessoServices : BaseServices

-(RetornoVo *) criarUsuario:( NSString *)nome withSobrenome: (NSString *) sobrenome withEmail: (NSString *) email withSenha: (NSString *) senha;


- (RetornoVo *) login : (NSString *)email withSenha: (NSString *) senha withSenhaMD5: (bool) senhaMD5;

@end
