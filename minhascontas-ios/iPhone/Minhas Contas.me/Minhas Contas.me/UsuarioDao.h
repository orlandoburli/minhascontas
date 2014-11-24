//
//  UsuarioDao.h
//  Minhas Contas.me
//
//  Created by Orlando Burli on 17/11/14.
//  Copyright (c) 2014 Orlando Burli Projetos de TI Personalizados. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "BaseDao.h"
#import "UsuarioVo.h"
#import "AppDictionary.h"

@interface UsuarioDao : BaseDao

-(void) insert: (UsuarioVo *) usuario;

-(void) update: (UsuarioVo *) usuario;

-(UsuarioVo *) get: (NSInteger ) idUsuario;

-(UsuarioVo *) getByEmail: (NSString *) email;

-(NSArray *) getList;

@end
