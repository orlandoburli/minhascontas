//
//  TokenDao.m
//  Minhas Contas.me
//
//  Created by Orlando Burli on 21/11/14.
//  Copyright (c) 2014 Orlando Burli Projetos de TI Personalizados. All rights reserved.
//

#import "TokenDao.h"
#import "DBManager.h"
#import "AppDictionary.h"

@implementation TokenDao

-(void) insert: (TokenVo *) token {
    
    [[DBManager getSharedInstance] openDB];
    
    NSString *sqlInsertToken = [[NSString alloc] initWithFormat:@"INSERT INTO %@ (%@, %@, %@, %@, %@, %@) VALUES (\"%ld\", \"%@\", \"%ld\", \"%@\", \"%@\", \"%@\")", tabelaToken, campoTokenIdToken, campoTokenChaveToken, campoTokenIdUsuario, campoTokenDataHoraCriacao, campoTokenDataUltimoAcesso, campoTokenExpirado, (long)token.idToken, token.chaveToken, (long)token.idUsuario, token.dataHoraCriacao, token.dataUltimoAcesso, token.expirado];
    
    [[DBManager getSharedInstance] executeSql:sqlInsertToken];
    
    [[DBManager getSharedInstance] closeDB];
    
}

-(void) update: (TokenVo *) token {
    [[DBManager getSharedInstance] openDB];
    
    NSString *sqlUpdateToken = [[NSString alloc] initWithFormat:@"UPDATE %@ SET %@ = \"%@\", %@ = \"%ld\", %@ = \"%@\", %@ = \"%@\", %@ = \"%@\" WHERE %@ = \"%ld\"", tabelaToken, campoTokenChaveToken, token.chaveToken, campoTokenIdUsuario, (long) token.idUsuario, campoTokenDataUltimoAcesso, token.dataUltimoAcesso, campoTokenDataHoraCriacao, token.dataHoraCriacao, campoTokenExpirado, token.expirado, campoTokenIdToken, (long) token.idToken];
    
    [[DBManager getSharedInstance] executeSql:sqlUpdateToken];
    
    [[DBManager getSharedInstance] closeDB];

}

-(TokenVo *) get: (NSInteger) idToken {
    [[DBManager getSharedInstance] openDB];
    
    NSString *sqlSelectToken = [[NSString alloc] initWithFormat:@"%@ WHERE %@ = \"%ld\"", [self getSqlBase], campoTokenIdToken, (long) idToken];

    NSMutableArray *dados = [[DBManager getSharedInstance] executeQuery:sqlSelectToken];
    
    if (dados.count > 0) {
        NSDictionary *row = dados[0];

        TokenVo *token = [[TokenVo alloc] init];
        
        [self fillToken:token withNSDictionary:row];
        
        return token;
    }

    [[DBManager getSharedInstance] closeDB];

    return nil;
}

-(NSArray *) getList {
    [[DBManager getSharedInstance] openDB];
    
    NSString *sqlSelectUsuario = [[NSString alloc] initWithFormat:@"%@ ORDER BY %@ DESC", [self getSqlBase], campoTokenDataHoraCriacao];
    
    NSMutableArray *dados = [[DBManager getSharedInstance] executeQuery:sqlSelectUsuario];
    
    NSMutableArray *retorno = [[NSMutableArray alloc] initWithCapacity:dados.count];
    
    for (int i = 0; i < dados.count; i++) {
        NSDictionary *row = dados[0];
        
        TokenVo *token = [[TokenVo alloc] init];
        
        [self fillToken:token withNSDictionary:row];
        
        retorno[i] = token;
    }
    
    [[DBManager getSharedInstance] closeDB];
    
    return retorno;
}

-(NSString *) getSqlBase {
    NSString *sqlSelectTokenBase = [[NSString alloc] initWithFormat:@"SELECT %@, %@, %@, %@, %@, %@ FROM %@ ", campoTokenIdToken, campoTokenChaveToken, campoTokenIdUsuario, campoTokenDataHoraCriacao, campoTokenDataUltimoAcesso, campoTokenExpirado, tabelaToken];
    
    return sqlSelectTokenBase;
}

-(void) fillToken: (TokenVo *) token withNSDictionary: (NSDictionary *) dictionary {
    token.idToken = [[dictionary valueForKey:campoTokenIdToken] integerValue];
    token.chaveToken = [dictionary valueForKey:campoTokenChaveToken];
    token.idUsuario = [[dictionary valueForKey:campoTokenIdUsuario] integerValue];
    
    NSDateFormatter *formatter = [[NSDateFormatter alloc] init];
    [formatter setDateFormat:@"dd/MM/yyyy hh24:mm:ss"];
    
    token.dataHoraCriacao = [formatter dateFromString:[dictionary valueForKey:@"dataHoraCriacao"]];
    token.dataUltimoAcesso = [formatter dateFromString:[dictionary valueForKey:@"dataUltimoAcesso"]];
    
    token.expirado = [dictionary valueForKey:campoTokenExpirado];
}

@end
