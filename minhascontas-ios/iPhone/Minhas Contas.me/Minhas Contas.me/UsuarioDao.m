//
//  UsuarioDao.m
//  Minhas Contas.me
//
//  Created by Orlando Burli on 17/11/14.
//  Copyright (c) 2014 Orlando Burli Projetos de TI Personalizados. All rights reserved.
//

#import "UsuarioDao.h"
#import "DBManager.h"

@implementation UsuarioDao

-(void) insert: (UsuarioVo *) usuario {
    
    [[DBManager getSharedInstance] openDB];
    
    NSString *sqlInsertUsuario = [[NSString alloc] initWithFormat:@"INSERT INTO %@ (%@, %@, %@, %@, %@, %@, %@, %@, %@, %@) VALUES (\"%ld\", \"%@\", \"%@\", \"%@\", \"%@\", \"%@\", \"%@\", \"%@\", \"%@\", \"%@\")", tabelaUsuario, campoUsuarioIdUsuario, campoUsuarioNomeUsuario, campoUsuarioSobreNomeUsuario, campoUsuarioTipoAutenticacao, campoUsuarioEmail, campoUsuarioIdFacebook, campoUsuarioSenha, campoUsuarioFlagConfirmado, campoUsuarioPathFoto, campoUsuarioHashEmail, (long) usuario.idUsuario, usuario.nomeUsuario, usuario.sobreNomeUsuario, usuario.tipoAutenticacao, usuario.email, usuario.idFacebook, usuario.senha, usuario.flagConfirmado, usuario.pathFoto, usuario.hashEmail];
    
    [[DBManager getSharedInstance] executeSql:sqlInsertUsuario];
    
    [[DBManager getSharedInstance] closeDB];
}

-(void) update: (UsuarioVo *) usuario {
    
    [[DBManager getSharedInstance] openDB];

    NSString *sqlUpdateUsuario = [[NSString alloc] initWithFormat:@"UPDATE %@ SET %@ = \"%@\",  %@ = \"%@\",  %@ = \"%@\", %@ = \"%@\", %@ = \"%@\",  %@ = \"%@\",  %@ = \"%@\",  %@ = \"%@\",  %@ = \"%@\" WHERE %@ = \"%ld\"", tabelaUsuario, campoUsuarioNomeUsuario , usuario.nomeUsuario, campoUsuarioSobreNomeUsuario, usuario.sobreNomeUsuario, campoUsuarioTipoAutenticacao, usuario.tipoAutenticacao, campoUsuarioEmail, usuario.email, campoUsuarioIdFacebook, usuario.idFacebook, campoUsuarioSenha, usuario.senha, campoUsuarioFlagConfirmado, usuario.flagConfirmado, campoUsuarioPathFoto , usuario.pathFoto, campoUsuarioHashEmail, usuario.hashEmail, campoUsuarioIdUsuario, (long) usuario.idUsuario];
    
    [[DBManager getSharedInstance] executeSql:sqlUpdateUsuario];
    
    [[DBManager getSharedInstance] closeDB];
}

-(UsuarioVo *) get:(NSInteger)idUsuario {
    
    [[DBManager getSharedInstance] openDB];
    
    NSString *sqlSelectUsuario = [[NSString alloc] initWithFormat:@"%@ WHERE %@ = \"%ld\"", [self getSqlBase], campoUsuarioIdUsuario, (long) idUsuario];
    
    NSMutableArray *dados = [[DBManager getSharedInstance] executeQuery:sqlSelectUsuario];
    
    if (dados.count > 0) {
        NSDictionary *row = dados[0];
        
        UsuarioVo *usuario = [[UsuarioVo alloc] init];
        
        [self fillUsuario:usuario withNSDictionary:row];
        
        return usuario;
    }
    
    [[DBManager getSharedInstance] closeDB];
    
    return nil;
}

-(UsuarioVo *) getByEmail: (NSString *) email {
    
    [[DBManager getSharedInstance] openDB];
    
    NSString *sqlSelectUsuario = [[NSString alloc] initWithFormat:@"%@ WHERE %@ = \"%@\"", [self getSqlBase], campoUsuarioEmail, email];
    
    NSMutableArray *dados = [[DBManager getSharedInstance] executeQuery:sqlSelectUsuario];
    
    if (dados.count > 0) {
        NSDictionary *row = dados[0];
        
        UsuarioVo *usuario = [[UsuarioVo alloc] init];
        
        [self fillUsuario:usuario withNSDictionary:row];
        
        return usuario;
    }
    
    [[DBManager getSharedInstance] closeDB];
    
    return nil;
}

-(NSArray *) getList {
    [[DBManager getSharedInstance] openDB];
    
    NSString *sqlSelectUsuario = [[NSString alloc] initWithFormat:@"%@ ORDER BY %@", [self getSqlBase], campoUsuarioNomeUsuario];
    
    NSMutableArray *dados = [[DBManager getSharedInstance] executeQuery:sqlSelectUsuario];
    
    NSMutableArray *retorno = [[NSMutableArray alloc] initWithCapacity:dados.count];
    
    for (int i = 0; i < dados.count; i++) {
        NSDictionary *row = dados[0];
        
        UsuarioVo *usuario = [[UsuarioVo alloc] init];
        
        [self fillUsuario:usuario withNSDictionary:row];
        
        retorno[i] = usuario;
    }
    
    [[DBManager getSharedInstance] closeDB];
    
    return retorno;
}

-(NSString *) getSqlBase {
    NSString *sqlSelectUsuarioBase = [[NSString alloc] initWithFormat:@"SELECT %@, %@, %@, %@, %@, %@, %@, %@, %@, %@ FROM %@ ", campoUsuarioIdUsuario, campoUsuarioNomeUsuario, campoUsuarioSobreNomeUsuario, campoUsuarioTipoAutenticacao, campoUsuarioEmail, campoUsuarioIdFacebook, campoUsuarioSenha, campoUsuarioFlagConfirmado, campoUsuarioPathFoto, campoUsuarioHashEmail, tabelaUsuario];
    
    return sqlSelectUsuarioBase;
}

// Preenche um vo de usuario com base em um objeto NSDictionary vindo do banco de dados.
-(void) fillUsuario: (UsuarioVo *) usuario withNSDictionary: (NSDictionary *) dictionary {
    usuario.idUsuario = [[dictionary valueForKey:campoUsuarioIdUsuario] integerValue];
    usuario.nomeUsuario = [dictionary valueForKey:campoUsuarioNomeUsuario];
    usuario.sobreNomeUsuario = [dictionary valueForKey:campoUsuarioSobreNomeUsuario];
    usuario.tipoAutenticacao = [dictionary valueForKey:campoUsuarioTipoAutenticacao];
    usuario.email = [dictionary valueForKey:campoUsuarioEmail];
    usuario.idFacebook = [dictionary valueForKey:campoUsuarioIdFacebook];
    usuario.senha = [dictionary valueForKey:campoUsuarioSenha];
    usuario.flagConfirmado = [dictionary valueForKey:campoUsuarioFlagConfirmado];
    usuario.pathFoto = [dictionary valueForKey:campoUsuarioPathFoto];
    usuario.hashEmail = [dictionary valueForKey:campoUsuarioHashEmail];
}

@end
