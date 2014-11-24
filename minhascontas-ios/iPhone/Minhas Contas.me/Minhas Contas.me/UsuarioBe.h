//
//  UsuarioBe.h
//  Minhas Contas.me
//
//  Created by Orlando Burli on 19/11/14.
//  Copyright (c) 2014 Orlando Burli Projetos de TI Personalizados. All rights reserved.
//

#import "BaseBe.h"
#import "UsuarioVo.h"
#import "UsuarioDao.h"

@interface UsuarioBe : BaseBe

-(void) save : (UsuarioVo *) usuario;

-(UsuarioVo *) getLastLogin;

-(UsuarioVo *) get: (NSInteger) idUsuario;

@end
