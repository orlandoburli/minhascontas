//
//  UsuarioVo.h
//  Minhas Contas.me
//
//  Created by Orlando Burli on 15/11/14.
//  Copyright (c) 2014 Orlando Burli Projetos de TI Personalizados. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <CoreData/CoreData.h>

@interface UsuarioVo : NSObject

@property (nonatomic) NSInteger idUsuario;
@property (nonatomic, retain) NSString *nomeUsuario;
@property (nonatomic, retain) NSString *sobreNomeUsuario;
@property (nonatomic, retain) NSString *tipoAutenticacao;
@property (nonatomic, retain) NSString *email;
@property (nonatomic, retain) NSString *idFacebook;
@property (nonatomic, retain) NSString *senha;
@property (nonatomic, retain) NSString *flagConfirmado;
@property (nonatomic, retain) NSString *pathFoto;
@property (nonatomic, retain) NSString *hashEmail;

-(id) initWithJsonString: (NSString *) stringJson;

@end
